package hashTable;

import hashTable.closedHashing.HashTableClosedHashingDH;
import hashTable.closedHashing.HashTableClosedHashingLP;
import hashTable.openHashing.HashTableOpenHashing;

public class TestMain {
    public static void main(String[] args) {
        HashFunction hf = new HashFunction(13);
        System.out.println(hf.hash("hello"));
        System.out.println(hf.getSecondHash("hello"));
        System.out.println(hf.getLoadFactor(7));

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

//        Map mapLP = new HashTableClosedHashingLP(11);
//        mapLP.put("one", "uno");
//        mapLP.put("two", "do");
//        mapLP.put("three", "tres");
//        mapLP.put("four", "cuantro");
//        mapLP.put("five", "cinco");
//        mapLP.put("six", "seis");
//        mapLP.put("seven", "siete");
//        System.out.println(mapLP.toString());
//        mapLP.remove("five");
//        System.out.println(mapLP.toString());
//        mapLP.put("fifteen", "lima belas");

//        System.out.println(mapLP.toString());
//        System.out.println(mapLP.containsKey("one"));
//        System.out.println(mapLP.containsKey("five"));
//        System.out.println(mapLP.containsKey("Lodie"));
//        System.out.println(mapLP.get("one"));

        Map mapDH = new HashTableClosedHashingDH(11);


    }
}
