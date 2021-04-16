package hashTable;

import hashTable.HashEntry;
import hashTable.HashFunction;
import hashTable.openHashing.HashTableOpenHashing;
import hashTable.openHashing.LinkedList;

public class TestMain {
    public static void main(String[] args) {
        HashFunction hf = new HashFunction(13);
        System.out.println(hf.hashFunction("hello"));
        System.out.println(hf.loadFactor(7));

        LinkedList ll = new LinkedList();
        HashEntry entry = new HashEntry("door", "pintu");
        HashEntry entry2 = new HashEntry("window", "jendela");
        ll.insertAtFront(entry);
        ll.insertAtFront(entry2);

        Map map = new HashTableOpenHashing(11);
        map.put("one", "uno");
        map.put("two", "do");
        map.put("three", "tres");
        map.put("four", "cuantro");
        map.put("five", "cinco");
        map.put("six", "seis");
        map.put("seven", "siete");
        map.put("rehash", "timeToRehash");
        System.out.println(map.toString());
        System.out.println(map.containsKey("one"));
        System.out.println(map.containsKey("bitcoin"));
    }
}
