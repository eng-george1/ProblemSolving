package Search;

import javax.lang.model.util.ElementScanner14;

public class Main {
    public static void main(String[] args) {
        int[] array = { 2, 4, 6, 2, 4, -1, 3, 9, -4, 20, 10, 55 };
        System.out.println("Start");
        System.out.println(binarySearch(array, 550));
    }

    public static int binarySearch(int[] array, int num) {
        int currentIndex = array.length / 2;
        int maxIndex = array.length - 1;
        int minIndex = 0;

        while (currentIndex >= 0 && currentIndex < array.length) {
            if (array[currentIndex] < num) {
                minIndex = currentIndex + 1;

            } else if (array[currentIndex] > num) {
                maxIndex = currentIndex - 1;
            } else
                return currentIndex;

            currentIndex =minIndex+ (maxIndex - minIndex) / 2;
            // 3-->5,1
            // 0-6
            // 3/2,3+(6-3)/2+1
        }
        return -1;
    }
}
