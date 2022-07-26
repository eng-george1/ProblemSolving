package Graph;

import java.util.HashMap;

public class Node {
    private HashMap<Node,Integer> nodes=new HashMap<>();
    Object value;
    public Node(Object value){
        this.value=value;
    }
public void add(Node node,int edge){
    nodes.put(node, edge);
}

}
