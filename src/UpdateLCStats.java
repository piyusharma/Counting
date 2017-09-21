import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Created by Piyush Sharma on 21-Sep-17.
 */
class UpdateLCStats {
    private static HashMap<String, Integer> userCounts = new HashMap<>();
    private static HashMap<String, Integer> trip3Counts = new HashMap<>();
    private static HashMap<String, Integer> trip6Counts = new HashMap<>();
    private static HashMap<String, Integer> dromeCounts = new HashMap<>();
    private static HashMap<String, Integer> assistCounts = new HashMap<>();
    private static HashMap<String, Integer> getCounts = new HashMap<>();
    private static HashMap<String, Integer> repdigCounts = new HashMap<>();
    private static Stack<String> trip3 = new Stack<>();
    private static Stack<String> trip6 = new Stack<>();
    private static Stack<String> drome = new Stack<>();
    private static Stack<String> assist = new Stack<>();
    private static Stack<String> get = new Stack<>();
    private static Stack<String> repdig = new Stack<>();
    private static Stack<String> trip3Stack = new Stack<>();
    private static Stack<String> trip6Stack = new Stack<>();
    private static Stack<String> dromeStack = new Stack<>();
    private static Stack<String> assistStack = new Stack<>();
    private static Stack<String> getStack = new Stack<>();
    private static Stack<String> repdigStack = new Stack<>();
//    static HashMap<String, String> firstCount = new HashMap<>();
//    static HashMap<String, Integer> kPart = new HashMap<>();
//    static HashMap<String, Integer> dayPart = new HashMap<>();

    static void updateStats() throws FileNotFoundException, JSONException {
        for (int i = 0; i <= 1/*LiveCounting.getFileNumber()*/; i++) {
            Stack<String> chatRead = HandleCSV.readCSV("LiveCounting/chat" + i + ".json");
            JSONArray chats = new JSONArray(chatRead.pop());
            for (int j = 0; j < chats.length(); j++) {
                JSONObject chat = (JSONObject) chats.get(j);
                String number = getCountFromChat((String) chat.get("body"));
                if (!number.equals("") && !((Boolean) chat.get("stricken"))) {
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
            }
            transferValues();
        }
        printStack(trip3);
    }

    private static void transferValues() {
        transferValues(trip3Stack, trip3);
        transferValues(trip6Stack, trip6);
        transferValues(dromeStack, drome);
        transferValues(assistStack, assist);
        transferValues(getStack, get);
        transferValues(repdigStack, repdig);
    }

    private static void transferValues(Stack<String> a, Stack<String> b) {
        while (!a.empty()) {
            b.push(a.pop());
        }
    }

    private static String createEntry(String author, String id, String number) {
        String redditBaseUpdateUrl = "https://www.reddit.com/live/ta535s1hq2je/updates/";
        String url = redditBaseUpdateUrl + id;
        return "[" + number + "](" + url + ") - " + author;
    }

    private static void updateMap(HashMap<String, Integer> hashMap, String author) {
        if (hashMap.containsKey(author)) {
            hashMap.put(author, hashMap.get(author) + 1);
        } else {
            hashMap.put(author, 1);
        }
    }

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
            j = (length / 2) + 1;
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
        List<Character> allowedCharacters = Arrays.asList(',', ' ', '.');
        List<Character> allowedCharactersBeginning = Arrays.asList('~', '^', '#', '*', '>', '`', '\n');
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

    private static void printStack(Stack k) {
        while (!k.empty()) {
            System.out.println(k.pop());
        }
    }
}
