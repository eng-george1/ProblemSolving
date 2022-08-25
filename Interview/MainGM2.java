package Interview;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Map;

public class MainGM2 {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
            String[] numbers = line.substring(0, line.indexOf(";")).split(",");
            int[] nums = new int[numbers.length];
            for (int i = 0; i < numbers.length; i++) {
                nums[i] = Integer.parseInt(numbers[i]);
            }
            System.out
                    .println(numberPairs(nums, Integer.parseInt(line.substring(line.indexOf(";") + 1, line.length()))));

        }
    }

    private static String numberPairs(int[] nums, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        String result = "";
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            if (map.containsKey(nums[i])) {
                result +=Math.min( nums[i],(sum-nums[i])) + "," +Math.max(nums[i],sum -  nums[i]) + ";";
            } else {
                if (nums[i] < sum)
                    map.put(sum - nums[i], 0);
            }
            if(i==j)
            break;
            if (map.containsKey(nums[j])) {
                result +=Math.min( nums[j],(sum-nums[j])) + "," +Math.max(nums[j],sum -  nums[j]) + ";";
            } else {
                if (nums[j] < sum)
                    map.put(sum - nums[j], 0);
            }

            i++;
            j--;
        }

        return result.substring(0, result.length()-1);
    }

    private static String numberPairs2(int[] nums, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        String result = "";
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                result += sum - nums[i] + "," + nums[i] + ";";
            } else {
                map.put(sum - nums[i], 0);
            }
        }
        if(result.isEmpty())
        return "NULL";
        return result.substring(result.length() - 1, result.length());
    }
}