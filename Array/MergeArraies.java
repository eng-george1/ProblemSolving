package Array;

public class MergeArraies {
    public static int[] merge(int[] array1, int[] array2) {
        if(array1==null ||array1.length==0)
        return array2;
        if(array2==null ||array2.length==0)
        return array1;
        int index2 = 0;
        int index = 0;
        int[] newArray = new int[array1.length + array2.length];
        for (int element : array1) {
            while (array2[index2] < element) {
                newArray[index] = array2[index2];
                index++;
                index2++;
                if (index2 == array2.length)
                    break;
            }
            newArray[index] = element;
            index++;
        }
        for(int i=index2;i<array2.length;i++){
            newArray[index]=array2[i];
            index++;
        }
        return newArray;
    }
    public static int[] merge2(int[] array1, int[] array2) {
        if(array1==null ||array1.length==0)
        return array2;
        if(array2==null ||array2.length==0)
        return array1;
        int index2 = 0;
        int index = 0;
        int[] newArray = new int[array1.length + array2.length];
        for (int element : array1) {
            while (array2[index2] < element) {
                newArray[index] = array2[index2];
                index++;
                index2++;
                if (index2 == array2.length)
                    break;
            }
            newArray[index] = element;
            index++;
        }
        for(int i=index2;i<array2.length;i++){
            newArray[index]=array2[i];
            index++;
        }
        return newArray;
    }
}