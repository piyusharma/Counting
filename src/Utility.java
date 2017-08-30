import java.util.*;

class Utility {
    private static final String credit = "\n" +
            "Thanks to /u/rschaosid for starting the concept of HOC and /u/sbb618, /u/anothershittyalt and /u/" +
            "Idunnowhy9000 for maintaining the stats.\n" +
            "\n" +
            "Thanks to /u/Urbul and /u/qwertylool for compiling all the side threads for me.\n" +
            "\n";

    static final String powerballSchedule = "\n##Get Schedule\n" +
            "Get|Thread Length|Cumulative Total Counts  \n" +
            "-|-|-  \n" +
            "1 2 3 4 5 +1|-|0\n" +
            "1 2 3 4 43 + 1|988|988\n" +
            "1 2 3 5 6 + 1|702|1,690\n" +
            "1 2 3 5 38 + 1|832|2,522\n" +
            "1 2 3 6 7 + 1|832|3,354\n" +
            "1 2 3 6 39 + 1|832|4,186\n" +
            "1 2 3 7 8 + 1|806|4,992\n" +
            "1 2 3 7 39 + 1|806|5,798\n" +
            "1 2 3 8 9 + 1|806|6,604\n" +
            "1 2 3 8 40 + 1|806|7,410\n" +
            "1 2 3 9 10 + 1|780|8,190\n" +
            "1 2 3 9 40 + 1|780|8,970\n" +
            "1 2 3 10 11 + 1|780|9,750\n" +
            "1 2 3 10 41 + 1|780|10,530\n" +
            "1 2 3 11 12 + 1|754|11,284\n" +
            "1 2 3 11 41 + 1|754|12,038\n" +
            "1 2 3 12 13 + 1|754|12,792\n" +
            "1 2 3 12 42 + 1|754|13,546\n" +
            "1 2 3 13 14 + 1|728|14,274\n" +
            "1 2 3 13 42 + 1|728|15,002\n" +
            "1 2 3 14 15 + 1|728|15,730\n" +
            "1 2 3 14 43 + 1|728|16,458\n" +
            "1 2 3 15 16 + 1|702|17,160\n" +
            "1 2 3 15 43 + 1|702|17,862\n" +
            "1 2 3 16 17 + 1|702|18,564\n" +
            "1 2 3 16 44 + 1|702|19,266\n" +
            "1 2 3 17 18 + 1|676|19,942\n" +
            "1 2 3 17 44 + 1|676|20,618\n" +
            "1 2 3 18 19 + 1|676|21,294\n" +
            "1 2 3 18 45 + 1|676|21,970\n" +
            "1 2 3 19 20 + 1|650|22,620\n" +
            "1 2 3 19 45 + 1|650|23,270\n" +
            "1 2 3 20 21 + 1|650|23,920\n" +
            "1 2 3 21 22 + 1|1,274|25,194\n" +
            "1 2 3 22 23 + 1|1,248|26,442\n" +
            "1 2 3 23 24 + 1|1,222|27,664\n" +
            "1 2 3 24 25 + 1|1,196|28,860\n";

    static String threadNameUtility(String thread, int operation, int counters, String accurateUpTo) {
        String commonText = credit +
                "A total of " + counters + " counters have counted in this Side Thread.(Accurate up to " +
                "[this](" + accurateUpTo + "))\n" +
                "\n" +
                "##Hall of Counters\n" +
                "Rank|Username|Counts\n" +
                "---|---|---\n";
        switch (thread.toLowerCase()) {
            case "letters": {
                if (operation == 1) {
                    return "letters";
                } else if (operation == 2) {
                    return "#STATS FOR LETTERS\n" +
                            "\n" +
                            "First Count : [A](https://www.reddit.com/r/counting/comments/1rigvd/counting_with_letters" +
                            "/cdnkd66/)- /u/boxofkangaroos\n" +
                            commonText;
                }
            }
            case "hexadecimal": {
                if (operation == 1) {
                    return "hexadecimal";
                } else if (operation == 2) {
                    return "#STATS FOR HEXADECIMAL\n" +
                            "\n" +
                            "First Count : [1](https://www.reddit.com/r/counting/comments/1dbmp2/count_to_ace_in_" +
                            "hexadecimal_system/c9ork0v/)- /u/astroknitter\n" +
                            commonText;
                }
            }
            case "binary": {
                if (operation == 1) {
                    return "binary";
                } else if (operation == 2) {
                    return "#STATS FOR BINARY\n" +
                            "\n" +
                            "First Count : [1](https://www.reddit.com/r/counting/comments/1g8kn3/been_" +
                            "a_while_since_we_gave_binary_a_shot_lets_try/cahv02j/)- [deleted]\n" +
                            commonText;
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
                    return "roman";
                } else if (operation == 2) {
                    return "#STATS FOR ROMAN NUMERALS\n" +
                            "\n" +
                            "First Count : [I](https://www.reddit.com/r/counting/comments/z2i3c/lets_" +
                            "count_in_roman_numbers/c60vjd5/)- /u/jrkv\n" +
                            commonText;
                }
            }
            case "wave": {
                if (operation == 1) {
                    return "wave";
                } else if (operation == 2) {
                    return "#STATS FOR WAVE\n" +
                            "\n" +
                            "First Count : [0 (0)](https://www.reddit.com/r/counting/comments/1uky9d/count_" +
                            "in_a_wave_ie_0_1_0_1_0_1_2_1_0_1_2/cej4883/)- /u/quantiplex\n" +
                            commonText;
                }
            }
            case "number palindromes": {
                if (operation == 1) {
                    return "number_palindromes";
                } else if (operation == 2) {
                    return "#STATS FOR NUMBERS PALINDROMES\n" +
                            "\n" +
                            "First Count :  [1](https://www.reddit.com/r/counting/comments/1stqru/" +
                            "counting_in_palindromes/ce14xul/)- /u/astikoes\n" +
                            commonText;
                }
            }
            case "quaternary": {
                if (operation == 1) {
                    return "quaternary";
                } else if (operation == 2) {
                    return "#STATS FOR QUATERNARY\n" +
                            "\n" +
                            "First Count : [0](https://www.reddit.com/r/counting/comments/2cr8e2/counting_" +
                            "in_base_4/cji82ka/)- [deleted]\n" +
                            commonText;
                }
            }
            case "alphanumeric": {
                if (operation == 1) {
                    return "alphanumeric";
                } else if (operation == 2) {
                    return "#STATS FOR ALPHANUMERIC\n" +
                            "\n" +
                            "First Count : [0](https://www.reddit.com/r/counting/comments/3je4es/alphanumerics" +
                            "/cuofgel/) - /u/BrownSugar0\n" +
                            commonText;
                }
            }
            case "writing numbers": {
                if (operation == 1) {
                    return "writing_numbers";
                } else if (operation == 2) {
                    return "#STATS FOR WRITING NUMBERS\n" +
                            "\n" +
                            "First Count : [One](https://www.reddit.com/r/counting/comments/1nj3mv/writing" +
                            "_numbers_thread/ccj10yh/)- /u/Laust17\t\n" +
                            commonText;
                }
            }
            case "ternary": {
                if (operation == 1) {
                    return "ternary";
                } else if (operation == 2) {
                    return "#STATS FOR TERNARY\n" +
                            "\n" +
                            "First Count : [1](https://www.reddit.com/r/counting/comments/2ihghh/ternary_1/" +
                            "cl26wdb/)- /u/origamimissile\n" +
                            commonText;
                }
            }
            case "increasing base": {
                if (operation == 1) {
                    return "increasing_base";
                } else if (operation == 2) {
                    return "STATS FOR INCREASING BASE WITH EACH DIGIT\n" +
                            "\n" +
                            "First Count : [1 Ill get it going!](https://www.reddit.com/r/counting/comments/2j6tua/" +
                            "increasing_base_with_each_new_digit/cl8x3th/)- /u/ndo8v5\n" +
                            commonText;
                }
            }
            case "dates": {
                if (operation == 1) {
                    return "dates";
                } else if (operation == 2) {
                    return "#STATS FOR DATES\n" +
                            "\n" +
                            "First Count : [Jan 1st, 1\n" +
                            "](https://www.reddit.com/r/counting/comments/349in5/counting_by_" +
                            "dates_jan_1st_1/cqsi34q/)- /u/TieSoul\n" +
                            commonText;
                }
            }
            case "octal": {
                if (operation == 1) {
                    return "octal";
                } else if (operation == 2) {
                    return "#STATS FOR OCTAL\n" +
                            "\n" +
                            "First Count : [0](https://www.reddit.com/r/counting/comments/1k2tmd/octal_" +
                            "counting_thread/cbksg2q/)- /u/Sharkyg\n" +
                            commonText;
                }
            }
            case "prime factorization": {
                if (operation == 1) {
                    return "prime_factors";
                } else if (operation == 2) {
                    return "#STATS FOR PRIME FACTORS\n" +
                            "\n" +
                            "First Count : [2x5](https://www.reddit.com/r/counting/comments/1x0ac4/" +
                            "counting_using_prime_factorization/cf7121z/) - /u/SN4T14\n" +
                            commonText;
                }
            }
            case "rational numbers": {
                if (operation == 1) {
                    return "rational";
                } else if (operation == 2) {
                    return "#STATS FOR RATIONAL NUMBERS\n" +
                            "\n" +
                            "First Count : [2/1](https://www.reddit.com/r/counting/comments/1jnt5y/" +
                            "count_the_positive_rational_numbers/cbghzy7/)- /u/PUBLIQclopAccountant\n" +
                            commonText;
                }
            }
            case "collatz conjecture": {
                if (operation == 1) {
                    return "collatz_conjecture";
                } else if (operation == 2) {
                    return "#STATS FOR COLLATZ CONJECTURE\n" +
                            "\n" +
                            "First Count : [1 (1+0)](https://www.reddit.com/r/counting/comments/3sk9jq/collatz_" +
                            "conjecture_counting/cwxxyv8/)- /u/Removedpixel \n" +
                            commonText;
                }
            }
            case "negative numbers": {
                if (operation == 1) {
                    return "negative_numbers";
                } else if (operation == 2) {
                    return "#STATS FOR NEGATIVE NUMBERS\n" +
                            "\n" +
                            "First Count : [-1](https://www.reddit.com/r/counting/comments/3alw8q/counting" +
                            "_to_negative_infinity/csdt7ub/)- /u/removedpixel \n" +
                            commonText;
                }
            }
            case "increasing sequences": {
                if (operation == 1) {
                    return "increasing_sequence";
                } else if (operation == 2) {
                    return "#STATS FOR INCREASING SEQUENCE\n" +
                            "\n" +
                            "First Count : [1 (1)](https://www.reddit.com/r/counting/comments/3bp25m/counting_" +
                            "by_increasing_goals/cso5xjt/)- /u/removedpixel \n" +
                            commonText;
                }
            }
            case "powerball": {
                if (operation == 1) {
                    return "powerball";
                } else if (operation == 2) {
                    return "#STATS FOR POWERBALL\n" +
                            "\n" +
                            "First Count : [1 2 3 4 5 + 1](https://www.reddit.com/r/counting/comments/4hm2eq/" +
                            "counting_powerball_numbers/d2qqqxi/)- /u/Pookah\n" +
                            commonText;
                }
            }
            case "musical notes": {
                if (operation == 1) {
                    return "musical_notes";
                } else if (operation == 2) {
                    return "#STATS FOR MUSICAL NOTES\n" +
                            "\n" +
                            "First Count : [C](https://www.reddit.com/r/counting/comments/44dcsn/counting_" +
                            "base_12_with_musical_notes/czpdyln/)- /u/elyisgreat \n" +
                            commonText;
                }
            }
            case "senary": {
                if (operation == 1) {
                    return "senary";
                } else if (operation == 2) {
                    return "#STATS FOR SENARY\n" +
                            "\n" +
                            "First Count : [1](https://www.reddit.com/r/counting/comments/5a8666/counting_in" +
                            "_senary/d9ee8si/)- /u/orangey10\n" +
                            commonText;
                }
            }
            case "top subreddits": {
                if (operation == 1) {
                    return "top_subreddits";
                } else if (operation == 2) {
                    return "#STATS FOR TOP SUBREDDIT\n" +
                            "\n" +
                            "First Count : [1 - /r/announcements](https://www.reddit.com/r/counting/comments/27rsil/" +
                            "counting_by_top_subreddits/ci3qdko/)- /u/Greypo\n" +
                            commonText;
                }
            }
            case "permutations": {
                if (operation == 1) {
                    return "permutations";
                } else if (operation == 2) {
                    return "#STATS FOR PERMUTATIONS\n" +
                            "\n" +
                            "First Count : [1](https://www.reddit.com/r/counting/comments/4sx7th/new_" +
                            "permutations/d5cvh1s/)- /u/Unknow3n\n" +
                            commonText;
                }
            }
            case "quinary": {
                if (operation == 1) {
                    return "";
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
