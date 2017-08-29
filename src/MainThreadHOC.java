import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

/**
 * Created by Blizzard on 05-Aug-17.
 */
public class MainThreadHOC {
    private static List<NameValuePair> header = new ArrayList<>();

    private static Queue<Update> TraverseThread(String getID, String threadID) throws IOException, JSONException {
        String topCommentID = getID;
        String parent = "";
        Queue<Update> queue = new LinkedList();
        while (!parent.equals(threadID)) {
            String response = HttpRequests.getRequest("https://oauth.reddit.com/r/counting" +
                    "/comments/6wfft7/senary_12_0000/" + topCommentID + "/?context=100", header);
            JSONArray jsonArray = new JSONArray(response);
            JSONObject updateJSON = (JSONObject) ((JSONObject) (((JSONArray) ((JSONObject)
                    ((JSONObject) jsonArray.get(1)).get("data")).get("children")).get(0))).get("data");
            String storedID = (String) updateJSON.get("id");
            Stack<Update> stack = new Stack<>();
            while (!updateJSON.get("id").equals("_") && !updateJSON.get("id").equals(topCommentID)) {
                Update update = new Update((String) updateJSON.get("id"), (String) updateJSON.get("author"),
                        updateJSON.get("created").toString(), ((String) updateJSON.get("link_id")).split("_")[1]);
                stack.add(update);
                updateJSON = (JSONObject) ((JSONObject) (((JSONArray) ((JSONObject)
                        ((JSONObject) updateJSON.get("replies")).get("data")).get("children")).get(0))).get("data");
            }
            while (!stack.isEmpty()) {
                queue.add(stack.pop());
            }
            topCommentID = storedID;
            parent = ((String) updateJSON.get("parent_id")).split("_")[1];
        }
        return queue;
    }

    private static HashMap<String, Integer> generateHOC(Queue<Update> queue) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        Double endingTimestamp = Double.parseDouble(queue.peek().getTimestamp());
        Double startingTimestamp = 0.0;
        while (!queue.isEmpty()) {
            Update update = queue.poll();
            if (queue.isEmpty()) {
                startingTimestamp = Double.parseDouble(update.getTimestamp());
                continue;
            }
            if (hashMap.containsKey(update.getAuthor())) {
                hashMap.put(update.getAuthor(), hashMap.get(update.getAuthor()) + 1);
            } else {
                hashMap.put(update.getAuthor(), 1);
            }
        }
        Double timeTaken = endingTimestamp - startingTimestamp;
        System.out.print(timeTaken);
        return hashMap;
    }

    private static void printMap(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        String s = HttpRequests.getToken("xtYsNzO4a8Curw", "00w7cP3wUcuyIjQdtgh1g7JKL9c",
                "piyushsharma301", "loseyourself1");
        JSONObject jsonObject = new JSONObject(s);
        header.add(new BasicNameValuePair("Authorization", "bearer " + jsonObject.get("access_token")));
        header.add(new BasicNameValuePair("User-Agent", "Something"));
        Queue<Update> updates = TraverseThread("dm8q63n", "6wfft7");
        HashMap<String, Integer> userCounts = generateHOC(updates);
        printMap(userCounts);
    }
}
