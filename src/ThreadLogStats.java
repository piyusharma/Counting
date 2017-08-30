import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


class ThreadLogStats {
    static void generateStats(String thread, List<NameValuePair> header) throws IOException, JSONException {
        HashMap<String, String> permalinks = new HashMap<>();
        HashMap<String, Integer> userCounts = new HashMap<>();
        HashMap<String, String> firstCount = new HashMap<>();
        String accurateUpTo = "";
        Stack<String> updates = HandleCSV.readCSV("output/" + thread + ".csv");
        while (!updates.isEmpty()) {
            String update = updates.pop();

            String[] valueUpdate = update.split(",");

            if (updates.isEmpty()) {
                accurateUpTo = permalinks.get(valueUpdate[3]) + valueUpdate[2];
            }
            if (userCounts.containsKey(valueUpdate[0])) {
                userCounts.put(valueUpdate[0], userCounts.get(valueUpdate[0]) + 1);
            } else {
                Date date = new Date(((long) Double.parseDouble(valueUpdate[1])) * 1000L);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd-MMMM-yyyy' 'k:m:s a");
                if (permalinks.containsKey(valueUpdate[3])) {
                    firstCount.put(valueUpdate[0], "[" + simpleDateFormat.format(date) + "](" + permalinks.get
                            (valueUpdate[3]) + valueUpdate[2] + ")");
                } else {
                    permalinks.put(valueUpdate[3], MainThreadHOC.createURLFromID(valueUpdate[3]));
                    firstCount.put(valueUpdate[0], "[" + simpleDateFormat.format(date) + "](" + permalinks.get
                            (valueUpdate[3]) + valueUpdate[2] + ")");
                }
                userCounts.put(valueUpdate[0], 1);
            }
        }
        postLogStats(Utility.sortHashMap(userCounts), firstCount, accurateUpTo, header, thread);
    }

    private static void postLogStats(HashMap<String, Integer> userCounts, HashMap<String, String> firstCount,
                                     String accurateUpTo, List<NameValuePair> header, String thread) throws IOException {
        int noOfCounters = userCounts.size();
        if (userCounts.containsKey("[deleted]")) {
            noOfCounters--;
        }
        StringBuilder postString = new StringBuilder(Utility.threadNameUtility(thread, 2,
                noOfCounters, accurateUpTo));
        int rank = 1;
        for (Map.Entry<String, Integer> entry : userCounts.entrySet()) {
            if (!entry.getKey().equals("[deleted]")) {
                postString.append(rank).append("|/u/").append(entry.getKey()).append("|").append(entry.getValue())
                        .append("\n");
                rank++;
            }
        }
        postString.append("\n##Hall of First Counts\nUsername|First Count\n---|---\n");
        for (Map.Entry<String, String> entry : firstCount.entrySet()) {
            if (!entry.getKey().equals("[deleted]")) {
                postString.append("/u/").append(entry.getKey()).append("|").append(entry.getValue())
                        .append("\n");
                rank++;
            }
        }
        if (thread.equals("powerball")){
            postString.append(Utility.powerballSchedule);
        }
        List<NameValuePair> postData = new ArrayList<>();
        postData.add(new BasicNameValuePair("page", Utility.threadNameUtility(thread, 1,
                0, "")));
        postData.add(new BasicNameValuePair("content", postString.toString()));
        postData.add(new BasicNameValuePair("reason", "Updated to " + accurateUpTo));
        HttpRequests.postRequest("https://oauth.reddit.com/r/test_piyush/api/wiki/edit", header, postData);
    }

}
