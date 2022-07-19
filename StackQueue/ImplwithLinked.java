package StackQueue;

import java.util.LinkedList;

public class ImplwithLinked {
    private LinkedList<Object> data = new LinkedList<>();
    public String toString(){
        return data.toString();
    } 


    // #region Stack
    public void push(Object element) {
        data.add(element);
    }

    public Object pop() {
        return data.removeLast();
    }

    public Object peek() {
        return data.getLast();
    }

    public boolean isExmpty() {
        return peek() == null ? true : false;
    }

    public boolean isExist(Object element) {
        ImplwithLinked newdata = new ImplwithLinked();
        boolean isExist = false;
        while (!isExmpty()) {
            if (element == data.peek()) {
                isExist = true;
                break;
            }
            newdata.push(data.pop());
        }
        while (!newdata.isExmpty()) {
            data.push(newdata.pop());
        }
        return isExist;
    }

    // #endregion

    //#region Queue
    public void enqueue(Object element){
        data.addFirst(element);
    }
    public Object dequeue(){
      return   data.removeLast();
    }
    //#endregion
}
