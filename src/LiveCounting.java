import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class LiveCounting {
    private static List<NameValuePair> header = new ArrayList<>();

    private void traverseThreadWriteToFile(String liveThreadID, PreviousChatData previousChatData) throws IOException, JSONException {
        String limitStatement = "limit=100";
        String redditBaseUrl = "https://oauth.reddit.com/live/";
        String response = HTTPRequests.getRequest(redditBaseUrl + liveThreadID + "?" + limitStatement, header);
        boolean reachedStartPoint = false;
        JSONArray tempArray = new JSONArray();
        while (((JSONArray) ((JSONObject) new JSONObject(response).get("data")).get("children")).length() != 0) {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = (JSONArray) ((JSONObject) jsonObject.get("data")).get("children");
            for (int i = 0; i < jsonArray.length(); i++) {
                if (((JSONObject) ((JSONObject) jsonArray.get(i)).get("data")).get("id").
                        equals(previousChatData.lastUpdateID)) {
                    reachedStartPoint = true;
                    break;
                }
                tempArray.put(((JSONObject) jsonArray.get(i)).get("data"));
            }
            if (reachedStartPoint) {
                break;
            }
            response = HTTPRequests.getRequest(redditBaseUrl + liveThreadID + "?after=" +
                    ((JSONObject) ((JSONObject) jsonArray.get(jsonArray.length() - 1)).get("data")).get("name")
                    + "&" + limitStatement, header);
        }
        writeToChat(tempArray);
    }

    private void writeToChat(JSONArray lastChatFileData) throws JSONException, IOException {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < lastChatFileData.length(); i++) {
            stringBuilder.append(lastChatFileData.get(i));
            stringBuilder.append(",");
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(getLatestChatFile(), "rw");
        randomAccessFile.write(32);
        HandleCSV.appendCSVToTop(getLatestChatFile(), stringBuilder.toString());
    }

    static int getFileNumber() throws FileNotFoundException {
        return Integer.parseInt(HandleCSV.readCSV("LiveCounting/lastChatFile.txt").pop());
    }


    private String getLatestChatFile() throws FileNotFoundException {
        return "LiveCounting/chat" + getFileNumber() + ".json";
    }

    private String getSecondLastChatFile() throws FileNotFoundException {
        return "LiveCounting/chat" + (getFileNumber() - 1) + ".json";
    }

    private PreviousChatData getLastChatFileData() throws FileNotFoundException, JSONException {
        String data = HandleCSV.readCSV(getLatestChatFile()).pop();
        JSONArray lastChatFileData = new JSONArray(data);
        PreviousChatData previousChatData = new PreviousChatData(lastChatFileData);
        if (lastChatFileData.length() == 0) {
            previousChatData.lastUpdateID = (String) ((JSONObject) (new JSONArray(HandleCSV.readCSV(getSecondLastChatFile())
                    .pop()).get(0))).get("id");
            return previousChatData;
        }
        previousChatData.lastUpdateID = (String) ((JSONObject) lastChatFileData.get(0)).get("id");
        return previousChatData;
    }


    private void checkValidityOfJson() throws FileNotFoundException, JSONException {
        Stack<String> k = HandleCSV.readCSV(getLatestChatFile());
        JSONArray jsonArray = new JSONArray(k.pop());
        for (int i = 0; i < jsonArray.length(); i++) {
            System.out.println(((JSONObject) jsonArray.get(i)).get("body").toString().trim());
        }
    }

    private String[] lastStatUpdatePoint() throws FileNotFoundException {
        Stack<String> k = HandleCSV.readCSV("LiveCounting/UpdatedUpto");
        return new String[]{k.pop(),k.pop()};
    }

    public static void main(String[] args) throws IOException, JSONException {
        String accessKey = "";
        String secretKey = "";
        String username = "";
        String password = "";
        String s = HTTPRequests.getToken(accessKey, secretKey, username, password);
        JSONObject jsonObject = new JSONObject(s);
        LiveCounting liveCounting = new LiveCounting();
        header.add(new BasicNameValuePair("Authorization", "bearer " + jsonObject.
                get("access_token")));
        header.add(new BasicNameValuePair("User-Agent", "Something"));
        PreviousChatData previousChatData = liveCounting.getLastChatFileData();
        liveCounting.traverseThreadWriteToFile("ta535s1hq2je", previousChatData);
        liveCounting.checkValidityOfJson();
//        String[] updatePoint = liveCounting.lastStatUpdatePoint();
//        UpdateLCStats.updateStats(Integer.parseInt(updatePoint[1]),updatePoint[0]);
    }

    private class PreviousChatData {
        String lastUpdateID;
        JSONArray lastChatData;

        PreviousChatData(JSONArray lastChatFileData) {
            this.lastChatData = lastChatFileData;
        }
    }
}
