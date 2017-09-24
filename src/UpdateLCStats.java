import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

class UpdateLCStats {
    private static HashMap<String, Integer> userCounts = new HashMap<>();
    private static HashMap<String, Integer> trip3Counts = new HashMap<>();
    private static HashMap<String, Integer> trip6Counts = new HashMap<>();
    private static HashMap<String, Integer> dromeCounts = new HashMap<>();
    private static HashMap<String, Integer> assistCounts = new HashMap<>();
    private static HashMap<String, Integer> getCounts = new HashMap<>();
    private static HashMap<String, Integer> repdigCounts = new HashMap<>();
    private static Stack<String> trip3Stack = new Stack<>();
    private static Stack<String> trip6Stack = new Stack<>();
    private static Stack<String> dromeStack = new Stack<>();
    private static Stack<String> assistStack = new Stack<>();
    private static Stack<String> getStack = new Stack<>();
    private static Stack<String> repdigStack = new Stack<>();
    //    static HashMap<String, String> firstCount = new HashMap<>();
    private static HashMap<String, KPart> kPart = new HashMap<>();
//    static HashMap<String, Integer> dayPart = new HashMap<>();

    static void updateStats(int updatedToFileNumber, String updatedToID) throws IOException, JSONException {
        int k = 0;
        int lastCount = 0;
        boolean statStarted = false;
        int numberChat = 0;
        for (int i = updatedToFileNumber; i <= LiveCounting.getFileNumber(); i++) {
            Stack<String> chatRead = HandleCSV.readCSV("LiveCounting/chat" + i + ".json");
            JSONArray chats = new JSONArray(chatRead.pop());
            for (int j = chats.length() - 1; j > -1; j--) {
                JSONObject chat = (JSONObject) chats.get(j);
                String number = getCountFromChat((String) chat.get("body"));
                try {
                    numberChat = Integer.parseInt(number);
                } catch (NumberFormatException ignored) {

                }
                if (!number.equals("") && !((Boolean) chat.get("stricken")) && statStarted && numberChat > lastCount) {
                    lastCount = numberChat;
                    String author;
                    try {
                        author = (String) chat.get("author");
                    } catch (ClassCastException ex) {
                        author = "[deleted]";
                    }
                    String id = (String) chat.get("id");
                    String lastThree;
                    if (number.length() > 3) {
                        lastThree = number.substring(number.length() - 3);
                    } else {
                        lastThree = number;
                    }
                    int checkKValue = lastCount / 1000;
                    if (checkKValue == k + 1) {
                        k++;
                    }
                    updateMapsAndStacks(author, k, lastThree, id, number);
                }
                if (updatedToID.equals(chat.get("id"))) {
                    statStarted = true;
                }
            }
        }
        writeOutputToFiles();
    }

    private static void updateMapsAndStacks(String author, int k, String lastThree, String id, String number) {
        updateKpartMap(kPart, author, k);
        updateMap(userCounts, author);
        switch (lastThree) {
            case "333":
                updateMap(trip3Counts, author);
                trip3Stack.push(createEntry(author, id, number));
                break;
            case "666":
                updateMap(trip6Counts, author);
                trip6Stack.push(createEntry(author, id, number));
                break;
            case "999":
                updateMap(assistCounts, author);
                assistStack.push(createEntry(author, id, number));
                break;
            case "000":
                updateMap(getCounts, author);
                getStack.push(createEntry(author, id, number));
                break;
        }
        if (isPalindrome(number)) {
            updateMap(dromeCounts, author);
            dromeStack.push(createEntry(author, id, number));
        }
        if (isRepdig(number)) {
            updateMap(repdigCounts, author);
            repdigStack.push(createEntry(author, id, number));
        }
    }

    private static String createEntry(String author, String id, String number) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        String redditBaseUpdateUrl = "https://www.reddit.com/live/ta535s1hq2je/updates/";
        String url = redditBaseUpdateUrl + id;
        return "[" + numberFormat.format(Integer.parseInt(number)) + "](" + url + ") - /u/" + author;
    }

    private static void updateMap(HashMap<String, Integer> hashMap, String author) {
        if (hashMap.containsKey(author)) {
            hashMap.put(author, hashMap.get(author) + 1);
        } else {
            hashMap.put(author, 1);
        }
    }

    private static void updateKpartMap(HashMap<String, KPart> hashMap, String author, int k) {
        if (hashMap.containsKey(author)) {
            if (k > hashMap.get(author).lastK) {
                hashMap.put(author, new KPart(k, kPart.get(author).value + 1));
            }
        } else {
            hashMap.put(author, new KPart(k, 1));
        }
    }

    private static void writeOutputToFiles() throws IOException {
        HandleCSV.writeFile("LC output/333.txt", convertMapToTable(Utility.sortHashMap(trip3Counts)));
        HandleCSV.writeFile("LC output/666.txt", convertMapToTable(Utility.sortHashMap(trip6Counts)));
        HandleCSV.writeFile("LC output/assists.txt", convertMapToTable(Utility.sortHashMap(assistCounts)));
        HandleCSV.writeFile("LC output/gets.txt", convertMapToTable(Utility.sortHashMap(getCounts)));
        HandleCSV.writeFile("LC output/repdig.txt", convertMapToTable(Utility.sortHashMap(repdigCounts)));
        HandleCSV.writeFile("LC output/drome.txt", convertMapToTable(Utility.sortHashMap(dromeCounts)));
        HandleCSV.writeFile("LC output/333logs.txt", convertStackToLog(trip3Stack));
        HandleCSV.writeFile("LC output/666logs.txt", convertStackToLog(trip6Stack));
        HandleCSV.writeFile("LC output/assistslogs.txt", convertStackToLog(assistStack));
        HandleCSV.writeFile("LC output/getslogs.txt", convertStackToLog(getStack));
        HandleCSV.writeFile("LC output/repdiglogs.txt", convertStackToLog(repdigStack));
        HandleCSV.writeFile("LC output/dromelogs.txt", convertStackToLog(dromeStack));
    }

    private static String convertStackToLog(Stack<String> stack) {
        StringBuilder logString = new StringBuilder();
        while (!stack.empty()) {
            logString.append(stack.pop()).append("\n");
        }
        return logString.toString();
    }

    private static String convertMapToTable(HashMap<String, Integer> hashMap) {
        int rank = 1;
        StringBuilder tableString = new StringBuilder();
        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            if (!entry.getKey().equals("[deleted]")) {
                tableString.append(rank).append("|/u/").append(entry.getKey()).append("|")
                        .append(entry.getValue())
                        .append("\n");
                rank++;
            }
        }
        return tableString.toString();
    }

//    private static void updateDayPartMap(HashMap<String, KPart> hashMap, String author, int timestamp) {
//        if (hashMap.containsKey(author)) {
//
//        }
//    }

    private static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        boolean result = true;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                result = false;
                break;
            }
            i++;
            j--;
        }
        return result;
    }

    private static boolean isRepdig(String s) {
        int length = s.length();
        int i = 0;
        int j;
        boolean result = true;
        if (length % 2 == 0) {
            j = (length / 2);
            while (j < length) {
                if (s.charAt(i) != s.charAt(j)) {
                    result = false;
                    break;
                }
                i++;
                j++;
            }
        } else {
            return isRepdig(s.substring(1));
        }
        return result;
    }

    private static String getCountFromChat(String s) {
        List<Character> allowedCharacters = Arrays.asList(',', ' ', '.', '*');
        List<Character> allowedCharactersBeginning = Arrays.asList('~', '^', '#', '>', '`', '\n');
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                number.append(s.charAt(i));
            } else if (!allowedCharacters.contains(s.charAt(i))) {
                if (allowedCharactersBeginning.contains(s.charAt(i))) {
                    if (!number.toString().equals("")) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return number.toString();
    }

    public static class KPart {
        int lastK;
        int value;

        KPart(int k, int i) {
            this.lastK = k;
            this.value = i;
        }
    }
}
