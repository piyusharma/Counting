import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
        String response = HttpRequests.getRequest(redditBaseUrl + liveThreadID + "?" + limitStatement, header);
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
            response = HttpRequests.getRequest(redditBaseUrl + liveThreadID + "?after=" +
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

    private int getFileNumber() throws FileNotFoundException {
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

    private void getCountFromChat(String s) {
        Character[] allowedCharacters = {',','-',' '};
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                number.append(s.charAt(i));
            }
        }
    }

    private void updateStats() throws FileNotFoundException, JSONException {
        Map<String, Integer> userCounts = new HashMap<>();
        for (int i = 0; i <= getFileNumber(); i++) {
            Stack<String> chatRead = HandleCSV.readCSV("LiveCounting/chat" + i + ".json");
            JSONArray chats = new JSONArray(chatRead.pop());
            for (int j = 0; j < chats.length(); j++) {
                JSONObject chat = new JSONObject(chats.get(j));
            }
        }
    }

    private void updateStatsFromBeginning() {

    }

    private void checkValidityOfJson() throws FileNotFoundException, JSONException {
        Stack<String> k = HandleCSV.readCSV(getLatestChatFile());
        JSONArray jsonArray = new JSONArray(k.pop());
        for (int i = 0; i < jsonArray.length(); i++) {
            System.out.println(((JSONObject) jsonArray.get(i)).get("body").toString().trim());
        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        String accessKey = "xtYsNzO4a8Curw";
        String secretKey = "00w7cP3wUcuyIjQdtgh1g7JKL9c";
        String username = "piyushsharma301";
        String password = "loseyourself1";
        String s = HttpRequests.getToken(accessKey, secretKey, username, password);
        JSONObject jsonObject = new JSONObject(s);
        LiveCounting liveCounting = new LiveCounting();
        header.add(new BasicNameValuePair("Authorization", "bearer " + jsonObject.
                get("access_token")));
        header.add(new BasicNameValuePair("User-Agent", "Something"));
        PreviousChatData previousChatData = liveCounting.getLastChatFileData();
        liveCounting.traverseThreadWriteToFile("ta535s1hq2je", previousChatData);
        liveCounting.checkValidityOfJson();
    }

    private class PreviousChatData {
        String lastUpdateID;
        JSONArray lastChatData;

        PreviousChatData(JSONArray lastChatFileData) {
            this.lastChatData = lastChatFileData;
        }
    }
}
