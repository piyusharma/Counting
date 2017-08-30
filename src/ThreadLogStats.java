import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Piyush Sharma on 30-Aug-17.
 */
public class ThreadLogStats {
    static HashMap<String, Integer> generateStats(String thread) throws IOException, JSONException {
        HashMap<String, String> permalinks = new HashMap<>();
        HashMap<String, Integer> userCounts = new HashMap<>();
        HashMap<String, String> firstCount = new HashMap<>();
        Stack<String> updates = HandleCSV.readCSV("test.csv");
//        Stack<String> updates = HandleCSV.readCSV(Utility.threadNameUtility(thread, 1));
        while (!updates.isEmpty()) {
            String update = updates.pop();
            String[] valueUpdate = update.split(",");
            if (userCounts.containsKey(valueUpdate[0])) {
                userCounts.put(valueUpdate[0], userCounts.get(valueUpdate[0]) + 1);
            } else {
                if (permalinks.containsKey(valueUpdate[3])) {
                    firstCount.put(valueUpdate[0], permalinks.get(valueUpdate[3]) + valueUpdate[2]);
                } else {
                    permalinks.put(valueUpdate[3], MainThreadHOC.createURLFromID(valueUpdate[3]));
                    firstCount.put(valueUpdate[0], permalinks.get(valueUpdate[3]) + valueUpdate[2]);
                }
                userCounts.put(valueUpdate[0], 1);
            }
        }
        postLogStats(Utility.sortHashMap(userCounts), firstCount);
        return Utility.sortHashMap(userCounts);
    }

    static void postLogStats(HashMap<String, Integer> userCounts, HashMap<String, String> firstCount) {
        StringBuilder postString = new StringBuilder(Utility.threadNameUtility("", 2));
        int rank = 1;
        for (Map.Entry<String, Integer> entry : userCounts.entrySet()) {
            if (!entry.getKey().equals("[deleted]")) {
                postString.append(rank).append("|/u/").append(entry.getKey()).append("|").append(entry.getValue())
                        .append("\n");
                rank++;
            }
        }
        postString.append("##Hall of First Counts\nUsername|First Count\n---|---\n");
        for (Map.Entry<String, String> entry : firstCount.entrySet()) {
            if (!entry.getKey().equals("[deleted]")) {
                postString.append("\n/u/").append(entry.getKey()).append("|").append(entry.getValue())
                        .append("\n");
                rank++;
            }
        }
        System.out.println(postString.toString());
    }

}
