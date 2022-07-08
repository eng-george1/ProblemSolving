import java.util.HashMap;

public class getCommain {
    public static void main(String[] args) {

    }

    public static boolean isCommanItems(char[] array1, char[] array2) {
        HashMap<Character, Character> set = new HashMap<>();
        for (int i = 0; i < array1.length; i++) {
            set.put(array1[i], array1[i]);
        }
        for (int i = 0; i < array2.length; i++) {
            if (set.containsKey(array2[i]))
                return true;
        }
        return false;
    }
}
