package LinkedList;

public class MyNode {
   private Object value;
   private MyNode next;
   private MyNode previous;
    public MyNode(Object value){
        this.value=value;
    }
    public MyNode(MyNode previous,Object value){
        this.value=value;
        this.previous=previous;
    }
    public boolean hasNext(){
        return this.getNext()!=null?true:false;
    }

    /**
     * @return Object return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * @return MyNode return the next
     */
    public MyNode getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(MyNode next) {
        this.next = next;
        next.previous=this;
    }


    /**
     * @return MyNode return the previous
     */
    public MyNode getPrevious() {
        return previous;
    }

    /**
     * @param previous the previous to set
     */
    public void setPrevious(MyNode previous) {
        this.previous = previous;
        this.previous.next=this;
    }

}
