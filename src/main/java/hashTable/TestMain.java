package hashTable;

import hashTable.closedHashing.HashTableClosedHashingDH;
import hashTable.closedHashing.HashTableClosedHashingLP;
import hashTable.openHashing.HashTableOpenHashing;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("HASH FUNCTION");
        HashFunction hf = new HashFunction(13);
        System.out.println(hf.hash("hello"));
        System.out.println(hf.getSecondHash("hello"));
        System.out.println(hf.getLoadFactor(7));
        System.out.println("=====================================================================\n");
        System.out.println("OPEN HASHING\n");

        Map map = new HashTableOpenHashing(11);
        map.put("one", "satu");
        System.out.println("Get 'one' --> expected satu, result: " + map.get("one"));
        map.put("one", "1");
        System.out.println("Get 'one' --> expected 1, result: " + map.get("one"));
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");
        map.put("five", "5");
        map.put("six", "6");
        map.put("seven", "7");
        map.put("eight", "8");
        System.out.println(map.toString());
        System.out.println("Contains 'one' --> expected true, result: " + map.containsKey("one"));
        System.out.println("Contains 'bitcoin' --> expected false, result: " + map.containsKey("bitcoin"));
        System.out.println("Get 'five' --> expected 5, result: " + map.get("five"));
        map.remove("five");
        System.out.println("Get 'five' --> expected null, result: " + map.get("five"));
        System.out.println("Contains 'five' --> expected false, result: " + map.containsKey("five"));
        map.put("five", "lima");
        System.out.println("Get 'five' --> expected lima, result: " + map.get("five"));
        System.out.println("=====================================================================\n");
        System.out.println("CLOSED HASHING LINEAR PROBING");
        Map mapLP = new HashTableClosedHashingLP(11);
        mapLP.put("one", "uno");
        mapLP.put("two", "do");
        mapLP.put("three", "tres");
        mapLP.put("four", "cuantro");
        mapLP.put("five", "cinco");
        mapLP.put("six", "seis");
        mapLP.put("seven", "siete");
        mapLP.put("one", "satu");
        System.out.println(mapLP.toString());
        mapLP.remove("five");
        System.out.println(mapLP.toString());
        mapLP.put("fifteen", "lima belas");

        System.out.println(mapLP.toString());
        System.out.println("Contains 'one' --> expected true, result: " + mapLP.containsKey("one"));
        System.out.println("Contains 'five' --> expected true, result: " + mapLP.containsKey("five"));
        System.out.println("Contains 'Lodie' --> expected false, result: " + mapLP.containsKey("Lodie"));
        System.out.println("Get 'one' --> expected satu, result: " + mapLP.get("one"));

        System.out.println("=====================================================================\n");
        System.out.println("CLOSED HASHING DOUBLE HASHING\n");
        Map mapDH = new HashTableClosedHashingDH(11);
        mapDH.put("one", "1");
        System.out.println("Get 'one' --> expected 1, result: " + mapDH.get("one"));
        mapDH.put("one", "satu");
        System.out.println("Get 'one' --> expected satu, result: " + mapDH.get("one"));
        mapDH.put("two", "dua");
        mapDH.put("three", "tiga");
        mapDH.put("four", "empat");
        mapDH.put("five", "lima");
        mapDH.put("six", "enam");
        System.out.println(mapDH.toString());
        System.out.println("Contains 'six' --> expected true, result: " + mapDH.containsKey("six"));
        System.out.println("Contains 'ten' --> expected false, result: " + mapDH.containsKey("ten"));
        System.out.println("Get 'four' --> expected empat, result: " + mapDH.get("four"));


        mapDH.put("eight", "delapan");
        mapDH.put("nine", "sembilan");
        System.out.println("\n---After Rehash---\n");
        System.out.println(mapDH.toString());
        System.out.println("Get 'eight' --> expected delapan, result: " + mapDH.get("eight"));
        System.out.println("Get 'six' --> expected enam, result: " + mapDH.get("six"));

        System.out.println(mapDH.remove("one"));
        System.out.println(mapDH.remove("two"));
        System.out.println("\n---After removing one and two, their isDeleted flag should be true.---\n");
        System.out.println(mapDH.toString());
        System.out.println("Get 'one' --> expected null, result: " + mapDH.get("one"));
        System.out.println("Contains 'two' --> expected false, result: " + mapDH.containsKey("two"));
    }
}
