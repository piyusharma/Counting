import java.util.*;

class Utility {
    private static final String credit = "\n" +
            "Thanks to /u/rschaosid for starting the concept of HOC and /u/sbb618, /u/anothershittyalt and /u/" +
            "Idunnowhy9000 for maintaining the stats.\n" +
            "\n" +
            "Thanks to /u/Urbul and /u/qwertylool for compiling all the side threads for me.\n" +
            "\n";

    static String threadNameUtility(String thread, int operation, int counters, String accurateUpTo) {
        switch (thread.toLowerCase()) {
            case "letters": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR LETTERS\n" +
                            "\n" +
                            "First Count : [A](https://www.reddit.com/r/counting/comments/1rigvd/counting_with_letters" +
                            "/cdnkd66/)- /u/boxofkangaroos\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##Hall of Counters\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "hexadecimal": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR HEXADECIMAL\n" +
                            "\n" +
                            "First Count : [1](https://www.reddit.com/r/counting/comments/1dbmp2/count_to_ace_in_" +
                            "hexadecimal_system/c9ork0v/)- /u/astroknitter\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##HALL OF COUNTERS\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "binary": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR BINARY\n" +
                            "\n" +
                            "First Count : [1](https://www.reddit.com/r/counting/comments/1g8kn3/been_" +
                            "a_while_since_we_gave_binary_a_shot_lets_try/cahv02j/)- [deleted]\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##Hall of Counters\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "time": {
                if (operation == 1) {
                    return "";
                } else if (operation == 2) {
                    return "";
                }
            }
            case "roman numerals": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR ROMAN NUMERALS\n" +
                            "\n" +
                            "First Count : [I](https://www.reddit.com/r/counting/comments/z2i3c/lets_" +
                            "count_in_roman_numbers/c60vjd5/)- /u/jrkv\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##HALL OF COUNTERS\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "wave": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR WAVE\n" +
                            "\n" +
                            "First Count : [0 (0)](https://www.reddit.com/r/counting/comments/1uky9d/count_" +
                            "in_a_wave_ie_0_1_0_1_0_1_2_1_0_1_2/cej4883/)- /u/quantiplex\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "###HALL OF COUNTERS\n" +
                            "Rank | Username | Counts\n" +
                            "--- | --- | ---\n";
                }
            }
            case "number palindromes": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR NUMBERS PALINDROMES\n" +
                            "\n" +
                            "First Count :  [1](https://www.reddit.com/r/counting/comments/1stqru/" +
                            "counting_in_palindromes/ce14xul/)- /u/astikoes\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "###HALL OF COUNTERS\n" +
                            "\n" +
                            "Rank | Username | Counts\n" +
                            "--- | --- | ---\n";
                }
            }
            case "quaternary": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR QUATERNARY\n" +
                            "\n" +
                            "First Count : [0](https://www.reddit.com/r/counting/comments/2cr8e2/counting_" +
                            "in_base_4/cji82ka/)- [deleted]\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##HALL OF COUNTERS\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "alphanumeric": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR ALPHANUMERIC\n" +
                            "\n" +
                            "First Count : [0](https://www.reddit.com/r/counting/comments/3je4es/alphanumerics" +
                            "/cuofgel/) - /u/BrownSugar0\n" +
                            credit +
                            "The gets are at [Hall of Side Threads](https://docs.google.com/spreadsheets/d" +
                            "/1q-rBqyvcCBn8b4CoLBs0WF4NX8hPif2Cf1Dg1zzuU8Q/pubhtml#) in this Google Sheet.\n" +
                            "\n" +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "###HALL OF COUNTERS\n" +
                            "\n" +
                            "Rank|Username | Counts\n" +
                            "--- | --- | ---\n";
                }
            }
            case "writing numbers": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR WRITING NUMBERS\n" +
                            "\n" +
                            "First Count : [One](https://www.reddit.com/r/counting/comments/1nj3mv/writing" +
                            "_numbers_thread/ccj10yh/)- /u/Laust17\t\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##Hall of Counters\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "ternary": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR TERNARY\n" +
                            "\n" +
                            "First Count : [1](https://www.reddit.com/r/counting/comments/2ihghh/ternary_1/" +
                            "cl26wdb/)- /u/origamimissile\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##HALL OF COUNTERS\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "increasing base": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "STATS FOR INCREASING BASE WITH EACH DIGIT\n" +
                            "\n" +
                            "First Count : [1 Ill get it going!](https://www.reddit.com/r/counting/comments/2j6tua/" +
                            "increasing_base_with_each_new_digit/cl8x3th/)- /u/ndo8v5\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "###HALL OF COUNTERS\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "dates": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR DATES\n" +
                            "\n" +
                            "First Count : [Jan 1st, 1\n" +
                            "](https://www.reddit.com/r/counting/comments/349in5/counting_by_" +
                            "dates_jan_1st_1/cqsi34q/)- /u/TieSoul\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##Hall of Counters\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "octal": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR OCTAL\n" +
                            "\n" +
                            "First Count : [0](https://www.reddit.com/r/counting/comments/1k2tmd/octal_" +
                            "counting_thread/cbksg2q/)- /u/Sharkyg\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##HALL OF COUNTERS\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "prime factorization": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR PRIME FACTORS\n" +
                            "\n" +
                            "First Count : [2x5](https://www.reddit.com/r/counting/comments/1x0ac4/" +
                            "counting_using_prime_factorization/cf7121z/) - /u/SN4T14\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "###HALL OF COUNTERS\n" +
                            "Rank |Username | Counts\n" +
                            "--- | --- | ---\n";
                }
            }
            case "rational numbers": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR RATIONAL NUMBERS\n" +
                            "\n" +
                            "First Count : [2/1](https://www.reddit.com/r/counting/comments/1jnt5y/" +
                            "count_the_positive_rational_numbers/cbghzy7/)- /u/PUBLIQclopAccountant\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##HALL OF COUNTERS\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "collatz conjecture": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR COLLATZ CONJECTURE\n" +
                            "\n" +
                            "First Count : [1 (1+0)](https://www.reddit.com/r/counting/comments/3sk9jq/collatz_" +
                            "conjecture_counting/cwxxyv8/)- /u/Removedpixel \n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##HALL OF COUNTERS\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "negative numbers": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR NEGATIVE NUMBERS\n" +
                            "\n" +
                            "First Count : [-1](https://www.reddit.com/r/counting/comments/3alw8q/counting" +
                            "_to_negative_infinity/csdt7ub/)- /u/removedpixel \n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##HALL OF COUNTERS\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "increasing sequences": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR INCREASING SEQUENCE\n" +
                            "\n" +
                            "First Count : [1 (1)](https://www.reddit.com/r/counting/comments/3bp25m/counting_" +
                            "by_increasing_goals/cso5xjt/)- /u/removedpixel \n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##Hall of Counters\n" +
                            "Rank |Username | Counts\n" +
                            "---|---|---\n";
                }
            }
            case "powerball": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR POWERBALL\n" +
                            "\n" +
                            "First Count : [1 2 3 4 5 + 1](https://www.reddit.com/r/counting/comments/4hm2eq/" +
                            "counting_powerball_numbers/d2qqqxi/)- /u/Pookah\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##Hall of Counters\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "musical notes": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR MUSICAL NOTES\n" +
                            "\n" +
                            "First Count : [C](https://www.reddit.com/r/counting/comments/44dcsn/counting_" +
                            "base_12_with_musical_notes/czpdyln/)- /u/elyisgreat \n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##HALL OF COUNTERS\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "senary": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR SENARY\n" +
                            "\n" +
                            "First Count : [1](https://www.reddit.com/r/counting/comments/5a8666/counting_in" +
                            "_senary/d9ee8si/)- /u/orangey10\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##Hall of Counters\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "top subreddits": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR TOP SUBREDDIT\n" +
                            "\n" +
                            "First Count : [1 - /r/announcements](https://www.reddit.com/r/counting/comments/27rsil/" +
                            "counting_by_top_subreddits/ci3qdko/)- /u/Greypo\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##Hall of Counters\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "permutations": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "#STATS FOR PERMUTATIONS\n" +
                            "\n" +
                            "First Count : [1](https://www.reddit.com/r/counting/comments/4sx7th/new_" +
                            "permutations/d5cvh1s/)- /u/Unknow3n\n" +
                            credit +
                            "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                            "[this](" + accurateUpTo + "))\n" +
                            "\n" +
                            "##Hall of Counters\n" +
                            "Rank|Username|Counts\n" +
                            "---|---|---\n";
                }
            }
            case "quinary": {
                if (operation == 1) {

                } else if (operation == 2) {
                    return "";
                }
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
