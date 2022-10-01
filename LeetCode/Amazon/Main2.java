package LeetCode.Amazon;

public class Main2 {
    public static void main(String[] args) {
        System.out.println(addSpaces("LeetcodeHelpsMeLearn", new int[] { 8, 13, 15 }));
    }

    public static String addSpaces(String s, int[] spaces) {
        StringBuilder sb = new StringBuilder();
         sb.append(s);
        for (int i =spaces.length-1; i >=0 ; i--) {
           
            sb.insert(spaces[i] , ' ');
        }
        return sb.toString();
    }
}
