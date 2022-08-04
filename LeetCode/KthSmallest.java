package LeetCode;

import java.util.Comparator;
import java.util.PriorityQueue;

class KthSmallest {

    public static void main(String[] args) {
        System.out.println(kthSmallest2(new int[][] { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } }, 8));
    }

    /*
     * Complexity Analysis
     * 
     * Time Complexity: \text{let X=} \text{min}(K, N); X + K \log(X)let
     * X=min(K,N);X+Klog(X)
     * 
     * Well the heap construction takes O(X)O(X) time.
     * After that, we perform KK iterations and each iteration has two operations.
     * We extract the minimum element from a heap containing XX elements. Then we
     * add a new element to this heap. Both the operations will take
     * O(\log(X))O(log(X)) time.
     * Thus, the total time complexity for this algorithm comes down to be O(X +
     * K\log(X))O(X+Klog(X)) where XX is \text{min}(K, N)min(K,N).
     * Space Complexity: O(X)O(X) which is occupied by the heap.
     */
    public static int kthSmallest(int[][] matrix, int k) {

        int N = matrix.length;

        PriorityQueue<MyHeapNode> minHeap = new PriorityQueue<MyHeapNode>(Math.min(N, k), new MyHeapComparator());

        // Preparing our min-heap
        for (int r = 0; r < Math.min(N, k); r++) {

            // We add triplets of information for each cell
            minHeap.offer(new MyHeapNode(matrix[r][0], r, 0));
        }

        MyHeapNode element = minHeap.peek();
        while (k-- > 0) {

            // Extract-Min
            element = minHeap.poll();
            int r = element.getRow(), c = element.getColumn();

            // If we have any new elements in the current row, add them
            if (c < N - 1) {

                minHeap.offer(new MyHeapNode(matrix[r][c + 1], r, c + 1));
            }
        }

        return element.getValue();
    }

public static int kthSmallest2(int[][] matrix, int k) {
   // Store value of matrix size
   int n = matrix.length;
        
   int low = matrix[0][0]; // first element
   int high = matrix[n-1][n-1]; // Last element
   
   int mid, temp, count;
   
   while(low < high){
       mid = low + (high-low)/2;
       temp = n - 1;
       count = 0;
       
       // For each row count the elements that are smaller than mid
       for(int i = 0; i < n; i++){
           
           while(temp >= 0 && matrix[i][temp] > mid){
               temp--;
           }
           count+= (temp+1);
       }
       
       if(count < k){
           low = mid + 1;
       }else{
           high = mid;
       }
   }
   return low;
}}
class MyHeapNode {

    int row;
    int column;
    int value;

    public MyHeapNode(int v, int r, int c) {
        this.value = v;
        this.row = r;
        this.column = c;
    }

    public int getValue() {
        return this.value;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }
}

class MyHeapComparator implements Comparator<MyHeapNode> {
    public int compare(MyHeapNode x, MyHeapNode y) {
        return x.value - y.value;
    }
}
