package hashTable;

import hashTable.closedHashing.HashTableClosedHashingLP;
import hashTable.openHashing.HashTableOpenHashing;

public class TestMain {
    public static void main(String[] args) {
        HashFunction hf = new HashFunction(13);
        System.out.println(hf.hashFunction("hello"));
        System.out.println(hf.loadFactor(7));

//        Map map = new HashTableOpenHashing(11);
//        map.put("one", "uno");
//        map.put("two", "do");
//        map.put("three", "tres");
//        map.put("four", "cuantro");
//        map.put("five", "cinco");
//        map.put("six", "seis");
//        map.put("seven", "siete");
//        map.put("rehash", "timeToRehash");
//        System.out.println(map.toString());
//        System.out.println(map.containsKey("one"));
//        System.out.println(map.containsKey("bitcoin"));
//        System.out.println((String) map.get("five"));

        Map mapLP = new HashTableClosedHashingLP(11);
        mapLP.put("one", "uno");
        mapLP.put("two", "do");
        mapLP.put("three", "tres");
        mapLP.put("four", "cuantro");
        mapLP.put("five", "cinco");
        mapLP.put("six", "seis");

        System.out.println(mapLP.toString());

    }
}
