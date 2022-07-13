package Array;

import java.rmi.server.ObjID;
import java.util.Arrays;

public class ImplementArray {
    Object[] data;
    int length = 0;
    int increaseOffset = 0;

    ImplementArray(int size) {
        data = new Object[size];
        length = size;
    }

    public int getLength() {
        return length;
    }
    public void assign(int index, Object element) {
        if (index < length)
            data[index] = element;
    }

    public Object getData(int index) {
        if (index < length)
            return data[index];
        return null;
    }
    public void push(Object element) {
        // on top
        insert(0, element);
    }


    public Object pop() {
        Object tempObj = data[length - 1];
        delete(length - 1);
        return tempObj;
    }
    public void insert(int index, Object element) {
        // on top

        moveDown(index, 1);
        data[index] = element;
    }

    public void delete(int index) {
        // on top
        moveUp(index, 1);
    }

    public String toString() {

        return Arrays.toString(data) + " -->Size: " + length;
    }

    private void moveDown(int startIndex, int size) {
        if (length + size > data.length) {
            Object[] newData = new Object[length + size + increaseOffset];
            if (startIndex > 0)
                System.arraycopy(data, 0, newData, 0, startIndex - 1);
            System.arraycopy(data, startIndex, newData, startIndex + size, data.length - startIndex);
            data = newData;
            length += size;
        }
    }

    public void Shrink() {
        Object[] newData = new Object[length];
        System.arraycopy(data, 0, newData, 0, length);
        data = newData;
    }

    private void moveUp(int startIndex, int size) {

        Object[] newData = new Object[data.length];
        if (startIndex > 0)
            System.arraycopy(data, 0, newData, 0, startIndex);
        System.arraycopy(data, startIndex + size, newData, startIndex, data.length - startIndex - size);
        data = newData;
        length -= size;

    }
}