package LeetCode.Pinterest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.util.ElementScanner14;
import javax.naming.spi.DirStateFactory.Result;

import Array.arrayList;

public class Main5 {
    public static void main(String[] args) {
        List<List<Integer>> result = threeSum(new int[] { -1, 0, 1, 2, -1, -4 });
        System.out.println(result);
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }

    public static List<List<Integer>> threeSum1(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 1; i++) {
            if ((i > 0 && nums[i] == nums[i - 1]))
                continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int complement = (nums[left] + nums[right]);
                if (nums[i] + complement == 0) {
                    result.add(Arrays.asList(nums[left], nums[right], nums[i]));
                }
                if (nums[i] + complement > 0) {
                    right--;
                } else {
                    left++;
                }
            }

        }
        return result.stream().distinct().toList();
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        Set<Integer> dups = new HashSet<>();
        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < nums.length; ++i)
            if (dups.add(nums[i])) {
                for (int j = i + 1; j < nums.length; ++j) {
                    int complement = -nums[i] - nums[j];
                    if (seen.containsKey(complement) && seen.get(complement) == i) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(triplet);
                        res.add(triplet);
                    }
                    seen.put(nums[j], i);
                }
            }
        return new ArrayList(res);
    }

    public static int maxSubArray(int[] nums) {
        int maxSum = 0;
        int currentSum=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]>currentSum)
            currentSum= nums[i];
            else
            currentSum+=nums[i];
            maxSum=Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static void mergeSort(int[] array, int low, int high) {
        if (high <= low) return;
    
        int mid = (low+high)/2;
        mergeSort(array, low, mid);
        mergeSort(array, mid+1, high);
        merge(array, low, mid, high);
    }
    public static void merge(int[] array, int low, int mid, int high) {
        // Creating temporary subarrays
        int leftArray[] = new int[mid - low + 1];
        int rightArray[] = new int[high - mid];
    
        // Copying our subarrays into temporaries
        for (int i = 0; i < leftArray.length; i++)
            leftArray[i] = array[low + i];
        for (int i = 0; i < rightArray.length; i++)
            rightArray[i] = array[mid + i + 1];
    
        // Iterators containing current index of temp subarrays
        int leftIndex = 0;
        int rightIndex = 0;
    
        // Copying from leftArray and rightArray back into array
        for (int i = low; i < high + 1; i++) {
            // If there are still uncopied elements in R and L, copy minimum of the two
            if (leftIndex < leftArray.length && rightIndex < rightArray.length) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                   array[i] = leftArray[leftIndex];
                   leftIndex++;
                } else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            } else if (leftIndex < leftArray.length) {
                // If all elements have been copied from rightArray, copy rest of leftArray
                array[i] = leftArray[leftIndex];
                leftIndex++;
            } else if (rightIndex < rightArray.length) {
                // If all elements have been copied from leftArray, copy rest of rightArray
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }
}
