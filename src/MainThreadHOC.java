import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class MainThreadHOC {
    private static List<NameValuePair> header = new ArrayList<>();
    private String getUser;

    private LinkedList<Update> TraverseThread(String getID, String threadID) throws IOException, JSONException {
        String topCommentID = getID;
        String parent = "";
        LinkedList<Update> linkedList = new LinkedList<>();
        String url = "https://oauth.reddit.com" + createURLFromID(threadID);
        linkedList.add(getGetUpdate(getID));
        while (!parent.equals(threadID)) {
            String response = HTTPRequests.getRequest(url + topCommentID + "/?context=100", header);
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
                linkedList.add(stack.pop());
            }
            topCommentID = storedID;
            parent = ((String) updateJSON.get("parent_id")).split("_")[1];
        }
        linkedList.pollLast();
        return linkedList;
    }

    static String createURLFromID(String id) throws IOException, JSONException {
        String t = HTTPRequests.getRequest("https://oauth.reddit.com/api/info?id=t3_" + id, header);
        JSONObject jsonObject = new JSONObject(t);
        JSONObject threadJSON = (JSONObject) ((JSONObject) ((JSONArray) ((JSONObject) jsonObject.get("data"))
                .get("children")).get(0)).get("data");
        return (String) threadJSON.get("permalink");
    }

    private Update getGetUpdate(String id) throws IOException, JSONException {
        String t = HTTPRequests.getRequest("https://oauth.reddit.com/api/info?id=t1_" + id, header);
        JSONObject jsonObject = new JSONObject(t);
        JSONObject getJSON = (JSONObject) ((JSONObject) ((JSONArray) ((JSONObject) jsonObject.get("data"))
                .get("children")).get(0)).get("data");
        getUser = (String) getJSON.get("author");
        return new Update((String) getJSON.get("id"), (String) getJSON.get("author"),
                getJSON.get("created").toString(), ((String) getJSON.get("link_id")).split("_")[1]);
    }

    private HOCUtil generateHOC(LinkedList<Update> linkedList, String thread) throws IOException {
        HashMap<String, Integer> hashMap = new HashMap<>();
        Double endingTimestamp = Double.parseDouble(linkedList.peek().getTimestamp());
        Double startingTimestamp = 0.0;
        String filename = "output/" + thread + ".csv";
        StringBuilder threadLog = new StringBuilder();
        while (!linkedList.isEmpty()) {
            Update update = linkedList.poll();
            if (!thread.equals("")) {
                threadLog.append(update.toString()).append("\n");
            }
            if (linkedList.isEmpty()) {
                startingTimestamp = Double.parseDouble(update.getTimestamp());
            }
            if (hashMap.containsKey(update.getAuthor())) {
                hashMap.put(update.getAuthor(), hashMap.get(update.getAuthor()) + 1);
            } else {
                hashMap.put(update.getAuthor(), 1);
            }
        }
        HandleCSV.appendCSVToTop(filename, threadLog.toString());
        int timeTaken = (int) (endingTimestamp - startingTimestamp);
        TimeFormat timeFormatConverted = Convert(timeTaken);
        return new HOCUtil(Utility.sortHashMap(hashMap), timeFormatConverted);
    }

    private TimeFormat Convert(int timeTaken) {
        int seconds = timeTaken % 60;
        int minutes = (timeTaken / 60) % 60;
        int hours = ((timeTaken / 60) / 60) % 24;
        int days = ((timeTaken / 60) / 60) / 24;
        return new TimeFormat(seconds, minutes, hours, days);
    }

    private void postDataToThread(HOCUtil hocUtil, String threadID) throws IOException, JSONException {
        String t = HTTPRequests.getRequest("https://oauth.reddit.com/api/info?id=t3_" + threadID, header);
        JSONObject jsonObject = new JSONObject(t);
        JSONObject ThreadJSON = (JSONObject) ((JSONObject) ((JSONArray) ((JSONObject) jsonObject.get("data"))
                .get("children")).get(0)).get("data");
        StringBuilder postString = new StringBuilder("Thread Participation for " + ThreadJSON.get("title") + "\n\n");
        List<NameValuePair> postData = new ArrayList<>();
        postData.add(new BasicNameValuePair("api_type", "json"));
        postData.add(new BasicNameValuePair("thing_id", "t3_" + threadID));
        int rank = 1;
        postString.append("Rank|Username|Counts\n---|---|---\n");
        for (Map.Entry<String, Integer> entry : hocUtil.hashMap.entrySet()) {
            if (!entry.getKey().equals(getUser) && !entry.getKey().equals("[deleted]")) {
                postString.append(rank).append("|/u/").append(entry.getKey()).append("|").append(entry.getValue())
                        .append("\n");
                rank++;
            } else if (!entry.getKey().equals("[deleted]")) {
                postString.append(rank).append("|**/u/").append(entry.getKey()).append("**|").append(entry.getValue())
                        .append("\n");
                rank++;
            }

        }
        postString.append("\nIt took ").append(hocUtil.hashMap.size()).append(" counters ").append(hocUtil.
                timeFormat.toString()).append(" to complete this thread. Bold is the user with the get");
        postData.add(new BasicNameValuePair("text", postString.toString()));
        System.out.println(HTTPRequests.postRequest("https://oauth.reddit.com/api/comment", header, postData));
    }

    private void updateAllSideThreads() throws IOException, JSONException {
        ThreadLogStats.generateStats("alphanumeric", header);
        ThreadLogStats.generateStats("binary", header);
        ThreadLogStats.generateStats("collatz conjecture", header);
        ThreadLogStats.generateStats("dates", header);
        ThreadLogStats.generateStats("hexadecimal", header);
        ThreadLogStats.generateStats("increasing base", header);
        ThreadLogStats.generateStats("increasing sequences", header);
        ThreadLogStats.generateStats("letters", header);
        ThreadLogStats.generateStats("musical notes", header);
        ThreadLogStats.generateStats("negative numbers", header);
        ThreadLogStats.generateStats("number palindromes", header);
        ThreadLogStats.generateStats("octal", header);
        ThreadLogStats.generateStats("permutations", header);
        ThreadLogStats.generateStats("powerball", header);
        ThreadLogStats.generateStats("prime factorization", header);
        ThreadLogStats.generateStats("quaternary", header);
        ThreadLogStats.generateStats("rational numbers", header);
        ThreadLogStats.generateStats("roman numerals", header);
        ThreadLogStats.generateStats("senary", header);
        ThreadLogStats.generateStats("ternary", header);
        ThreadLogStats.generateStats("top subreddits", header);
        ThreadLogStats.generateStats("wave", header);
        ThreadLogStats.generateStats("writing numbers", header);
    }

    public static void main(String[] args) throws IOException, JSONException {
        String accessKey = "";
        String secretKey = "";
        String username = "";
        String password = "";
        String threadID = "6s148g";
        String getID = "dn6lzdt";
        String thread = "";
        String s = HTTPRequests.getToken(accessKey, secretKey, username, password);
        JSONObject jsonObject = new JSONObject(s);
        MainThreadHOC mainThreadHOC = new MainThreadHOC();
        header.add(new BasicNameValuePair("Authorization", "bearer " + jsonObject.
                get("access_token")));
        header.add(new BasicNameValuePair("User-Agent", "Something"));
        LinkedList<Update> updates = mainThreadHOC.TraverseThread(getID, threadID);
        HOCUtil userCounts = mainThreadHOC.generateHOC(updates, thread);
        mainThreadHOC.postDataToThread(userCounts, threadID);
//        ThreadLogStats.generateStats(thread, header);
//        mainThreadHOC.updateAllSideThreads();
    }

    class HOCUtil {
        HashMap<String, Integer> hashMap;
        TimeFormat timeFormat;

        HOCUtil(HashMap<String, Integer> hashMap, TimeFormat timeFormat) {
            this.hashMap = hashMap;
            this.timeFormat = timeFormat;
        }
    }

    public class TimeFormat {
        private int seconds;
        private int minutes;
        private int hours;
        private int days;

        TimeFormat(int seconds, int minutes, int hours, int days) {
            this.seconds = seconds;
            this.minutes = minutes;
            this.hours = hours;
            this.days = days;
        }

        @Override
        public String toString() {
            return days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds ";
        }
    }

}
