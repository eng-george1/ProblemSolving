package LeetCode.Pinterest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.print.attribute.standard.RequestingUserName;

public class Main {
    public static void main(String[] args) {
        System.out.println(reverse(1236676));
        System.out.println(isPalindrome2(121));
        char[] s=new char[]{'a','b','c'};
        reverseString(s);
        System.out.println(Arrays.toString(s));
        System.out.println(longestCommonPrefix(new String[]{"car","ca","carr"}));
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
        int index=0;
        while(index<=s.length/2){
            char c= s[index];
            s[index]=s[s.length-1-index];
            s[s.length-1-index]=c;
            index++;
        }
    }

    public static String longestCommonPrefix(String[] strs) {
        String prefex=strs[0];
        for(int i=1;i<strs.length;i++){            
            while(prefex.length()>0){
                if(strs[i].indexOf(prefex)==0)
                break;
                prefex=prefex.substring(0, prefex.length()-1);
                if(prefex.isEmpty())
                return "";
            }
        }
        return prefex;
    }
    

}
