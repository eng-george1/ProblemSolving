// package LeetCode;

// import java.util.LinkedList;

// import Graph.Node;

// public class Qwayls {
//     //class MinStack {
//         LinkedList<Integer,Integer> linked=new LinkedList<>();
//         //  1,-1
//         //  1,1
//         //  2,1 
//         //  1,2,0,3,-1
             
//         //  1,1    head-->2-->1
//         //  2,1  head-->0-->2-->1
//         //  0,0   head-->3--.0-->2-->1
//         //  3,0  head-->-1-->3-->0-->2-->1
//         //  -1,-1   head-->5-->-1-->3-->0-->2-->1 
//         //  5,-1
//         //  get min -1   
//         //  pop() head-->-1-->3-->0-->2-->1 
//         //  5,-1     
//         //  getMin() -1
             
//         // int min=Integer.MaxValue;
//          public MinStack(int default) {
//              //head node , not used for any logic
//              linked.head=new Node(1,Integer.Maxvalue);
//          }
         
//          public void push(int val) {
//              //put after the head
//              Node newNode=new Node(val,Math.min(getMin(),val));
//              newNode.next=linked.head.next;        
//                  linked.head.next=newNode;
//          }
         
//          public void pop() {
//              //remove after the head
//              if(linked.head.next!=null)
//              Linked.head.next=linked.head.next.next;
//             // min=getMin();
//          }
         
//          public int top() {
//              //return the head.next elment  
//              if(linked.head.next!=null)
//              return linked.head.next[0];
//           return -1;
//          }
         
//          public int getMin() {
//              //return the head.next element with the min value 
//               if(linked.head.next!=null)
//              return linked.head.next[1];
//              return linked.head[1];
//          }
//      }
     
//      /**
//       * Your MinStack object will be instantiated and called as such:
//       * MinStack obj = new MinStack();
//       * obj.push(val);
//       * obj.pop();
//       * int param_3 = obj.top();
//       * int param_4 = obj.getMin();
//       */
// }
