package HashTable;

import java.util.Arrays;

public class MyHash<K, V> {
    private K key;
    private V value;
    private MyHash next;
    MyHash[] data;

    public MyHash(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void put(K key, V value) {
        int index = findIndex(key);
        if (index > 0) {
            findListValue(data[index]).next=new MyHash<K,V>(key, value);
        }else{
            insertEntry(new MyHash<K,V>(key, value));
        }
         
    }
    public MyHash getHash(K key){
        int index = findIndex(key);
        if (index > 0)
        return data[index];
        return null;
    }
    public int findIndex(K key) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].key == key)
                return i;
        }
        return -1;
    }

    public boolean hasNext() {
        if (next != null)
            return true;
        return false;
    }
    public MyHash findListValue(MyHash myhash){
       
        while (myhash.hasNext()) {
            myhash = myhash.next;
        }
        return myhash;
    }
    public void insertEntry(MyHash myhash){
        MyHash[] newMyHashs=new MyHash[data.length+1];
        System.arraycopy(data,0,newMyHashs,0,data.length);
        newMyHashs[newMyHashs.length-1]=myhash;
        data=newMyHashs;
    }

}
