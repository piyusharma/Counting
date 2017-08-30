import java.util.*;

class Utility {
    static String threadNameUtility(String thread, int operation) {
        switch (thread.toLowerCase()) {
            case "letters": {

            }
            case "hexadecimal": {

            }
            case "binary": {

            }
            case "time": {

            }
            case "roman numerals": {

            }
            case "wave": {

            }
            case "number palindromes": {

            }
            case "quaternary": {

            }
            case "alphanumeric": {

            }
            case "writing numbers": {

            }
            case "ternary": {

            }
            case "increasing base": {

            }
            case "dates": {

            }
            case "octal": {

            }
            case "prime factorization": {

            }
            case "rational numbers": {

            }
            case "collatz conjecture": {

            }
            case "negative numbers": {

            }
            case "increasing sequences": {

            }
            case "powerball": {

            }
            case "musical notes": {

            }
            case "senary": {

            }
            case "top subreddits": {

            }
            case "permutations": {

            }
            case "quinary": {

            }
        }
        return "";
    }

    static HashMap<String, Integer> sortHashMap(HashMap<String, Integer> hashMap) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(hashMap.entrySet());

        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        HashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
}
