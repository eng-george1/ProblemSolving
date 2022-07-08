import java.io.Console;

public class Main {
  public static void main(String[] args) {
    // final String[] nemo = { "nemo" };
    // findNemo(nemo);

    // // System.out.println("Elapsed Time in minutes: "+ stopWatch.getTime());
    // int[] list = { 1, 2, 3, 4 };
    // allPairs(list);

    int[] list = { 1, 2, 3, 9 };
    int[] list1 = { 1, 2, 4, 4 };
    pairsSum(list, 8);
    System.out.println("------------");
    pairsSum(list1, 8);

  }

  static void findNemo(String[] array) {
    for (int i = 0; i < array.length; i++) {
      if (array[i] == "nemo")
        System.out.println("Find Nemo!");
    }
  }

  static void allPairs(int[] list) {
    for (int i = 0; i < list.length; i++) {
      for (int ii = 0; ii < list.length; ii++) {
        System.out.println(list[i] + "-->" + list[ii]);
      }
    }
  }

  static void pairsSum(int[] array, int sum) {
    for (int i = 0; i < array.length; i++) {
      for (int ii = 0 + 1; ii < array.length; ii++) {
        if (array[i] + array[ii] == sum)
          System.out.println(array[i] + "+" + array[ii] + "=" + sum);
      }
    }
  }

  static int[] pairsSum2(int[] array, int sum) {
    int middIndex = 0;
    int greaterIndex = 0;
    int zeroIndex = 0;
    for (int i = 0; i < array.length; i++) {
      if (array[i] == 0)
        zeroIndex = i;
      else if (array[i] <= sum / 2)
        middIndex = i - 1;
      else if (array[i] < sum)
        greaterIndex = i;
    }

    for (int i = zeroIndex; i <= middIndex; i++) {
      for (int ii = middIndex + 1; ii <= greaterIndex; ii++) {
        if (array[i] + array[ii] == sum)
          return new int[] { i, ii };
      }
    }

    for (int i = 0; i <= zeroIndex; i++) {
      for (int ii = greaterIndex + 1; ii < array.length; ii++) {
        if (array[i] + array[ii] == sum)
          return new int[] { i, ii };
      }
    }
    return new int[] {};
  }

  public boolean twoSum(int[] nums, int target) {

    int low = 0;
    int high = nums.length - 1;
    while (low < high) {
      int sum = nums[low] + nums[high];
      if (sum == target)
        return true;
      if (sum > target)
        high--;
      if (sum < target)
        low++;
    }
    return false;
  }
}
