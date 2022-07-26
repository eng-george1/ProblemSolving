package Graph;

import java.util.HashMap;

public class MyGraph {
    HashMap<Node,Boolean> data=new HashMap<>();
   
    public void add(Node node1,Node node2,int edge){
        node1.add(node2, edge);
        node2.add(node1, edge);
    }
}
