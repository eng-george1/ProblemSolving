package LeetCode;

import java.util.HashMap;

public class Main3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcdeafghibb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("au"));
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
}
