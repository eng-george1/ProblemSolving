package Array;

public class Proplem1 {
     public static void main(String[] args) {
        System.out.println(getFirstIndex(new int[] { 1, 2, 4, 4, 4, 5, 6 }, 6));
    }

    public static int getFirstIndex(int[] array, int value) {
        if (array == null || array.length == 0)
            return -1;
        int index = array.length / 2;
        while (index < array.length) {
            if (array[index] < value)
                index += ((array.length-index) / 2);
            else if (array[index] > value)
            index += -(index / 2);
            else
                return findFirstIndex(array, index);
        }

        return -1;

    }
    public static int getFirstIndex(int[] array, int value,int startIndex,int endIndex ){
        if (array == null || array.length == 0)
            return -1;
        int index =startIndex + ((endIndex-startIndex) / 2);
        while (index < array.length) {
            if (array[index] < value)
                index += ((array.length-index) / 2);
            else if (array[index] > value)
            index += -(index / 2);
            else
                return findFirstIndex(array, index);
        }

        return -1;

    }
    private static int findFirstIndex(int[] array, int currentindex) {
        int newIndex = currentindex;
        while (array[currentindex] == array[newIndex - 1]) {
            newIndex--;
            if (newIndex - 1 == 0)
                break;
        }
        return newIndex;
    }
}

