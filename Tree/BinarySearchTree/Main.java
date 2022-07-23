package Tree.BinarySearchTree;


public class Main {
    public static void main(String[] args) {
        System.out.println("Start");
        Node root = new Node(3);
        // root.setLeftChild(new Node(2));
        // root.setRightChild(new Node(4));
        // root.getRightChild().setRightChild(new Node(5));
        // root.getLeftChild().setLeftChild(new Node(1));
        root.insert(2);
        root.insert(4);
        root.insert(1);
        root.insert(5);
        //System.out.println(root.toStringOrder());
        root.insert(6);
        //System.out.println(root.toStringOrder());
        root.insert(-1);
        //System.out.println(root.toStringOrder());
        root.insert(0);
        //System.out.println(root.toStringOrder());
        root.insert(9);
        //System.out.println(root.toStringOrder());
        root.insert(8);
        //System.out.println(root.toStringOrder());
        root.insert(7);

        System.out.println(root.toStringOrder());


        // Node root2=new Node(9);
        // root2.insert2(4);
        // root2.insert2(6);
        // root2.insert2(20);
        // root2.insert2(170);
        // root2.insert2(15);
        // root2.insert2(1);
        // System.out.println(root2.toStringOrder());

       root.remove(3);
       System.out.println(root.toStringOrder());
    }
}
