package Array;

public class Reverse {
    public static String reverse(String s) {
        if(s==null||s.isEmpty())
        return "";
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length / 2; i++) {
            char tempchar = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = tempchar;
        }
        return new String( array);
    }
}
