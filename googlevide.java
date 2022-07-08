import java.util.HashMap;

public class googlevide {


    // Given a collection of numbers,
// You need to take this collection of numbers and find a matching pair that is equal to a given sum
// Example
// [1,2,3,9] Sum = 8 return false
// [1,2,4,4] Sum = 8 return true

boolean bruteForceMethod(int[] array,int sum) {
    for (int i = 0; i < array.length; i++) {
      int first = array[i];
      for (int j = 1; j < array.length; j++) {
        int second = array[j];
        if (first + second == sum) {
          return true;
        }
      }
    }
    return false;
  } // O(n^2)
  
  // works with a sorted array
  boolean cursorSolution(int[] array ,int sum) {
    int low = 0;
    int high = array.length - 1;
    while (low < high) {
      int result = array[low] + array[high];
      if (result == sum) {
        return true;
      }
      if (result > sum) {
        high--;
      }
      if (result < sum) {
        low++;
      }
    }
    return false;
  } // O(n)
  
  // works with unsorted array
  boolean setSolution(int[] array,int sum) {
    HashMap<Integer,Integer> set = new HashMap<>();
    for (int index = 0; index < array.length; index++) {
      int element = array[index];
      if (set.containsValue(sum - element)) {
        return true;
      }
      set.put(element, element);
    }
    return false;
  } // O(n)
    
}
