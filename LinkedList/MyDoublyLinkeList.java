package LinkedList;

public class MyDoublyLinkeList {
    private MyNode head;
    private MyNode tail;

    public MyDoublyLinkeList(Object head) {
        this.head = new MyNode(null, head);
        tail = this.head;
    }

    public void append(Object value) {
        tail.setNext(new MyNode(tail, value));
        tail = tail.getNext();
    }

    // #endregion
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        MyNode currentNode = this.head;
        strBuilder.append("-->" + currentNode.getValue().toString());
        while (currentNode.hasNext()) {
            strBuilder.append("-->" + currentNode.getNext().getValue().toString());
            currentNode = currentNode.getNext();
        }
        strBuilder.append("******* tail: " + tail.getValue());
        return strBuilder.toString();
    }

    public void prepend(Object value) {
        MyDoublyLinkeList dLink = new MyDoublyLinkeList(value);
        dLink.head.setNext(this.head);
        this.head = dLink.head;
    }

    public void insert(int index, Object value) {

        MyNode currentNode = this.head;
        int currentIndex = 0;
        while (currentNode.hasNext()) {

            if (currentIndex == index) {
                MyNode myNode = new MyNode(value);
                myNode.setNext(currentNode.getNext());
                currentNode.setNext(myNode);
                break;
            }
            currentIndex++;
            currentNode = currentNode.getNext();
        }
    }

    public void delete(int index) {

        MyNode currentNode = this.head;
        int currentIndex = 0;
        while (currentNode.hasNext()) {

            if (currentIndex == index) {
                currentNode.setNext(currentNode.getNext().getNext());
                break;
            }
            currentIndex++;
            currentNode = currentNode.getNext();
        }
    }

    public MyDoublyLinkeList reverse() {
        MyDoublyLinkeList reversedList = this;
        Object tempNode = reversedList.head.getValue();
        MyNode forwardPointer = reversedList.head;
        MyNode reversePointer = reversedList.tail;

        while (forwardPointer != reversePointer && forwardPointer.getPrevious() != reversePointer) {
            forwardPointer.setValue(reversePointer.getValue());
            reversePointer.setValue(tempNode);
            forwardPointer = forwardPointer.getNext();
            tempNode = forwardPointer.getValue();
            reversePointer = reversePointer.getPrevious();

        }

        return reversedList;
    }
}
