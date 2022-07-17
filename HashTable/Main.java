package HashTable;

import java.io.Console;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
      //  System.out.println(firstRecurring2(new int[] { 2, 5, 1, 2, 3, 5, 1, 2, 4 }));
        System.out.println(firstRecurring2(new int[] { 2,5, 1,2,4,5,2, 1, 2, 3, 5, 1, 2, 4 }));
        //System.out.println(firstRecurring2(new int[] { 2, 3, 4, 5 }));

    }

    // O(n)
    public static int firstRecurring(int[] array) {
        HashSet<Integer> map = new HashSet<Integer>();
        for (int element : array) {
            if (!map.add(element))
                return element;
            ;
        }
        return -1;
    }
//without using hashtable
    public static int firstRecurring2(int[] array) {
       // System.out.println("start:"+Arrays.toString(array));
        if(array==null||array.length==0)
        return -1;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j])
              {
              //  System.out.println("from:"+i+" To:"+j);
               // System.out.println("f:"+array[i]);
                int[] newArray=Arrays.copyOfRange(array, i+1, j);
               // System.out.println("new array:"+Arrays.toString(newArray));
               int result= firstRecurring2(newArray);
                if(result==-1)
                 return array[i];
                 else
                 return result;
              }
                   
            }
        }
        return -1;
    }

    public static Object firstRecurring(Object[] array) {
        HashMap<Object, Integer> map = new HashMap<>();
        for (Object element : array) {
            if (map.containsKey(element))
                return element;
            map.put(element, 0);
        }
        return -1;
    }
}