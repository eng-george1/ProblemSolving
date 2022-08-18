package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.w3c.dom.DOMImplementation;

public class Main3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcdeafghibb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("au"));

        System.out.println(longestPalindrome("aacabdkacaa"));
        System.out.println(Arrays.toString(subdomainVisits(
                new String[] { "900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org" }).toArray()));
        System.out.println(validPalindrome2("abca"));
        System.out.println(validPalindrome2("abc"));
        System.out.println(validPalindrome2("eeccccbebaeeabebccceea"));
        System.out.println(validPalindrome2(
                "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s.isEmpty())
            return 0;
        int maxLen = 1;
        for (int i = 0; i < s.length(); i++) {
            HashMap<Character, Integer> hash = new HashMap<>();
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) != s.charAt(j)) {
                    if (!hash.containsKey(s.charAt(j))) {
                        hash.put(s.charAt(j), j);
                        if (j == s.length() - 1) {
                            maxLen = Math.max(maxLen, j + 1 - i);
                        }
                    } else {
                        maxLen = Math.max(maxLen, j - i);
                        break;
                    }
                } else {
                    maxLen = Math.max(maxLen, j - i);
                    break;
                }

            }

        }

        return maxLen;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int i = 0;
        int maxLen = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0; j < s.length(); j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(i, map.get(s.charAt(j)));
            }
            maxLen = Math.max(maxLen, i - j + 1);
            map.put(s.charAt(j), j + 1);
        }
        return maxLen;
    }

    public static String longestPalindrome(String s) {
        if (s.length() == 0)
            return "";
        String longS = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            if (s.length() - i <= longS.length())
                break;
            for (int j = s.length() - 1; j > i; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    StringBuilder sB = new StringBuilder(s.substring(i, j + 1));
                    String reverse = sB.reverse().toString();
                    if (s.substring(i, j + 1).equals(reverse)) {
                        longS = longS.length() < sB.length() ? sB.toString() : longS;
                        break;
                    }
                }
            }
        }
        return longS;
    }

    public static List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < cpdomains.length; i++) {
            String[] domains = cpdomains[i].split(" ")[1].split("[.]");
            int visitNo = Integer.parseInt(cpdomains[i].split(" ")[0]);
            int index = domains.length - 1;
            String domain = domains[index];
            while (index >= 0) {

                if (result.containsKey(domain)) {
                    result.put(domain, result.get(domain) + visitNo);
                } else {
                    result.put(domain, visitNo);
                }
                index--;
                if (index < 0)
                    break;
                domain = domains[index] + '.' + domain;
            }
        }
        List<String> result1 = new ArrayList<>();
        result.forEach((key, value) -> result1.add(value + " " + key));
        return result1;

    }

    private static boolean checkPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }

    public static boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            // Found a mismatched pair - try both deletions
            if (s.charAt(i) != s.charAt(j)) {
                return (checkPalindrome(s, i, j - 1) || checkPalindrome(s, i + 1, j));
            }

            i++;
            j--;
        }

        return true;
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix == "")
                    return "";
            }

        }
        return prefix;
    }

}
