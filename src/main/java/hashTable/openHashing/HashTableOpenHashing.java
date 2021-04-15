package hashTable.openHashing;
import hashTable.HashEntry;
import hashTable.Map;
import hashTable.HashFunction;
import java.util.HashMap;

// check java source code
public class HashTableOpenHashing implements Map {
    private Node[] table ; // this node is the head of the LinkedList
    private int maxSize; // prime number
    private int size;

    /** Constructor for class HashTableOpenHashing
     *
     * @param maxSize the size of the table
     */
    public HashTableOpenHashing(int maxSize) {
        this.maxSize = maxSize;
        table = new Node[maxSize];
        size = 0;
    }

    /** Return true if the map contains a (key, value) pair associated with this key,
     *  otherwise return false.
     *
     * @param key  key
     * @return true if the key (and the corresponding value) is the in map
     */
    public boolean containsKey(String key) {
        // FILL IN CODE
        return false; // change
    }

    /** Add (key, value) to the map.
     * Will replace previous value that this key was mapped to.
     * If key is null, throw IllegalArgumentException.
     *
     * @param key associated key
     * @param value associated value
     */
    public void put(String key, Object value) {
        HashEntry entry = new HashEntry(key, value);
        HashFunction hf = new HashFunction(maxSize);
        if (hf.loadFactor(size) <= 0.6) {
            int idx = hf.hashFunction(key);
            if (table[idx] == null) {
                Node head = new Node(entry);
                table[idx] = head;
                size ++;
            } else {
                Node newNode = new Node(entry, table[idx]);
                table[idx] = newNode;
                size++;
            }
        } else {
            rehash(hf);
        }
    }

    /** Return the value associated with the given key or null, if the map does not contain the key.
     * If the key is null, throw IllegalArgumentException.
     *
     * @param key key
     * @return value associated value
     */
    public Object get(String key) {
        // FILL IN CODE

        return null; // change
    }

    /** Remove a (key, value) entry if it exists.
     * Return the previous value associated with the given key, otherwise return null
     * @param key key
     * @return previous value
     */
    public Object remove(String key) {
        // FILL IN CODE

        return null; // change
    }

    /** Return the actual number of elements in the map.
     *
     * @return number of elements currently in the map.
     */
    public int size() {
        return this.size;
    }

    /**
     * toString
     * @return a string representing a hash table
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // FILL IN CODE

        return sb.toString();
    }

    // Add may implement other helper methods as needed

    /**
     * A helper me to rehash table to a new maxSize
     * @param hf HashFunction
     */
    private void rehash (HashFunction hf) {
        maxSize = hf.getNewSize();
        Node [] temp = table;
        HashTableOpenHashing rehash_table = new HashTableOpenHashing(maxSize);
        for (Node node : temp) {
            Node current = node;
            while (current != null) {
                rehash_table.put(current.entry().getKey(), current.entry().getValue());
                current = current.next();
            }
        }
        this.table = rehash_table.table;
    }
}
