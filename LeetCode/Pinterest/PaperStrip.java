package LeetCode.Pinterest;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

// int i=0,j=0,ans = 0;        
// int arrayLen = original.length;
// boolean[] visited = new boolean[arrayLen];
// while (i < desired.length){
//     j = 0;
//     while (j < original.length){
//         if (visited[j]){
//             j++;
//             continue;
//         }
//         if (original[j] == desired[i]){
//             while (j < arrayLen && i < arrayLen && original[j] == desired[i]){
//                 visited[j] = true;
//                 i++;
//                 j++;
//             }
//             ans++;                   
//             break;
//         }
//         j++;
//     }
// }
// return ans;
public class PaperStrip {
    public static int minPieces(int[] original, int[] desired) {
        int[] sub = new int[original.length];
        int count = 0;
        for (var i = 0; i < desired.length; i++) {
            original[i]--;
            desired[i]--;
            sub[desired[i]] = i;
        }
        for (var i = 0; i < original.length; i++)
            original[i] = sub[original[i]];
        for (var i = 1; i < original.length; i++)
            if (original[i - 1] + 1 != original[i])
                count++;
        return count + 1;
    }

    public static void main(String[] args) {
        int[] original = new int[] { 1, 4, 3, 2 };
        int[] desired = new int[] { 1, 2, 4, 3 };
        System.out.println(PaperStrip.minPieces(original, desired));
    }

}
