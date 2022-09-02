package LeetCode.Pinterest;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import javax.print.attribute.standard.RequestingUserName;

public class Main {
    public static void main(String[] args) {
        System.out.println(reverse(1236676));
        System.out.println(isPalindrome2(121));
        char[] s = new char[] { 'a', 'b', 'c' };
        reverseString(s);
        System.out.println(Arrays.toString(s));
        System.out.println(longestCommonPrefix(new String[] { "car", "ca", "carr" }));
        System.out.println(decodeString("abc3[cd]xyz"));
        System.out.println(decodeString("3[a2[c]]"));
        System.out.println(decodeString("3[a]2[bc]"));
        System.out.println(groupAnagrams(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" }));
        System.out.println(groupAnagrams(new String[] { "a" }));

    }

    public static int reverse(int x) {
        int result = 0;
        while (x != 0) {
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 100 && x > 7))
                return 0;
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 100 && x < -8))
                return 0;
            result = result * 10 + (x % 10);
            x = x / 10;
        }
        return result;
    }

    public int reverse1(int x) {
        String reversed = new StringBuilder().append(Math.abs(x)).reverse().toString();
        try {
            return (x < 0) ? Integer.parseInt(reversed) * -1 : Integer.parseInt(reversed);
        } catch (NumberFormatException e) {
            return 0;
        }

    }

    public static boolean isPalindrome(int x) {
        try {
            if (x == Integer.parseInt(new StringBuilder().append(x).reverse().toString()))
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static boolean isPalindrome2(int x) {
        if (x < 0)
            return false;
        String s = String.valueOf(x);
        int index = 0;
        while (index < s.length() / 2) {
            if (s.charAt(index) != s.charAt(s.length() - 1 - index))
                return false;
            index++;
        }
        return true;
    }

    public static double fast(double x, int n) {
        if (n == 0)
            return 1;
        double result = fast(x, n / 2);
        if (n % 2 != 0)
            result *= result * x;
        return result * result;
    }

    public static double myPow(double x, int n) {
        int newN = n;
        if (newN < 0) {
            newN *= -1;
        }
        if (n < 0)
            return 1 / fast(x, n);
        return fast(x, n);
    }

    public static void reverseString(char[] s) {
        int index = 0;
        while (index <= s.length / 2) {
            char c = s[index];
            s[index] = s[s.length - 1 - index];
            s[s.length - 1 - index] = c;
            index++;
        }
    }

    public static String longestCommonPrefix(String[] strs) {
        String prefex = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (prefex.length() > 0) {
                if (strs[i].indexOf(prefex) == 0)
                    break;
                prefex = prefex.substring(0, prefex.length() - 1);
                if (prefex.isEmpty())
                    return "";
            }
        }
        return prefex;
    }

    public String longestPalindrome(String s) {
        String maxStr = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                StringBuilder sb = new StringBuilder(s.substring(i, j + 1));
                if (s.substring(i, j + 1).equals(sb.reverse())) {
                    if (s.substring(i, j + 1).length() > maxStr.length())
                        maxStr = s.substring(i, j + 1);
                }
            }
        }
        return maxStr;
    }

    static int startI = 0;
    static int endI = 0;

    public String longestPalindrome2(String s) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            expandarroundCenter(s, i, i);
            expandarroundCenter(s, i, i + 1);

        }
        return s.substring(startI, endI + 1);
    }

    private static void expandarroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        if (endI - startI + 1 < right - left - 1) {
            startI = left + 1;
            endI = right - 1;
        }
    }

    public static String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        StringBuilder tempR = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                StringBuilder sb = new StringBuilder();
                sb.insert(0, tempR.toString());
                tempR = new StringBuilder();
                while (stack.peek() != '[') {
                    sb.insert(0, stack.pop());
                }
                stack.pop();
                StringBuilder sbint = new StringBuilder();
                char c = stack.peek();
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    sbint.insert(0, stack.pop());
                }
                String temp = sb.toString();
                if (sbint.isEmpty())
                    sbint.append("0");
                for (int j = 0; j < Integer.parseInt(sbint.toString()) - 1; j++) {
                    sb.insert(0, temp);
                }
                if (stack.isEmpty()) {
                    result.append(sb.toString());
                } else
                    tempR.insert(0, sb.toString());

            } else {
                stack.push(s.charAt(i));
            }
        }
        tempR = new StringBuilder();
        while (!stack.isEmpty()) {
            tempR.insert(0, stack.pop());
        }
        result.append(tempR.toString());
        return result.toString();
    }

    public static List<List<String>> groupAnagrams(String[] strs) {      
        Map<String, List<String>> map = new HashMap();
        int[] count = new int[26];
        Arrays.fill(count, 0);     
        for (int i = 0; i < strs.length; i++) {
            String l=convertStrtoKey(strs[i],count);
            if(map.containsKey(l)){
                map.get(l).add(strs[i]);
            }
            else{
                List<String> list=new ArrayList<>();
                list.add(strs[i]);
                map.put(l,list);      
            }
        }       
        return new ArrayList<>(map.values());
    }

    private static String convertStrtoKey(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
    private static String convertStrtoKey(String s,int[] count) {
        Arrays.fill(count, 0);     
        for (char c : s.toCharArray()) count[c - 'a']++;
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < 26; i++) {
            sb.append('#');
            sb.append(count[i]);
        }
        return sb.toString();
    }

    public static List<List<String>> groupAnagrams2(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}
