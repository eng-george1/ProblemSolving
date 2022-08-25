package Interview;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainGM {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line = "";
        String ss;
        while ((ss = in.readLine()) != null) {
            if (ss.equals("@"))
                break;
            if (ss.isEmpty()||ss.equals(System.lineSeparator()))
            line+= " ";
            else
            line += ss + "@";
            //System.out.println(line);
        }
        //System.out.println(line);
        String[] fragments = line.split(" ");
        String result = "";
        for (int i = 0; i < fragments.length; i++) {
            String[] words = fragments[i].split("@");
            result += words[1].toLowerCase();
            for (int j = 2; j < words.length; j++) {
                result += words[j].substring(0, 1).toUpperCase();
                result += words[j].substring(1, words[j].length()).toLowerCase();
            }
          if(i<fragments.length-1)
          result+=" ";
        }
        System.out.println(result);
    }
}