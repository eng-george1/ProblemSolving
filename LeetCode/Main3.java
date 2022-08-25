package LeetCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiConsumer;

import javax.swing.text.Position;

import org.w3c.dom.DOMImplementation;

public class Main3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcdeafghibb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("au"));

        System.out.println(longestPalindrome("aacabdkacaa"));
        System.out.println(Arrays.toString(subdomainVisits(
                new String[] { "900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org" }).toArray()));
        System.out.println(validPalindrome("abca"));
        System.out.println(validPalindrome("abc"));
        System.out.println(validPalindrome("eeccccbebaeeabebccceea"));
        System.out.println(validPalindrome(
                "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
        System.out.println(racecar(6));
        System.out.println(racecar(1));
        // try {
        //     main2(args);
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        int x = 1;
        x = 1111111111;

        System.out.println(numPairsDivisibleBy60(new int[]{418,204,77,278,239,457,284,263,372,279,476,416,360,18}));
        System.out.println(numPairsDivisibleBy60(new int[]{30,20,150,100,40})); 
         System.out.println(numPairsDivisibleBy60(new int[]{60,60,60})); 
         System.out.println(numPairsDivisibleBy60(new int[]{15,63,451,213,37,209,343,319})); 
         System.out.println(numPairsDivisibleBy60(new int[]{174,188,377,437,54,498,455,239,183,347,59,199,52,488,147,82}));
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = in.readLine()) != null) {
            Integer i = new Integer(s);
            System.out.println(i * i);
        }

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

    public static int racecar(int target) {
        // if(target==1)
        // return 1;
        int currentP = 0;
        int currentS = 1;
        int result = 0;
        while (currentP != target) {
            if (target - currentP < currentP + currentS - target) {
                currentS = -1;
                result += 2;
            }
            if (currentP > target && currentS > 0) {
                // R
                if (currentS < 0) {
                    currentS = 1;
                } else {
                    currentS = -1;
                }
                result++;
            } else {
                // A
                currentP += currentS;
                currentS *= 2;
                // currentP++;
                result++;
            }
        }
        return result;
    }

    public static boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    public static String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] comp = path.split("/");
        for (String directory : comp) {
            if (directory.equals(".") || directory.isEmpty())
                continue;
            if (directory.equals("..")) {
                if (!stack.isEmpty())
                    stack.pop();
            } else {
                stack.add(directory);
            }
        }
        StringBuilder reslt = new StringBuilder();
        while (!stack.isEmpty()) {
            reslt.insert(0, "/" + stack.pop());
        }
        return reslt.length() > 0 ? reslt.toString() : "/";
    }

    public static int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < time.length; i++) {          
           
            if (map.containsKey(time[i] % 60)) {
                count+=map.get(time[i] % 60);        
            }
            int tempcount=0;
            if(map.containsKey((60 - time[i] % 60)%60))
            tempcount=map.get((60 - time[i] % 60)%60);           
            map.put((60 - time[i] % 60)%60, tempcount+1);
        }
        return count;
    }

}
