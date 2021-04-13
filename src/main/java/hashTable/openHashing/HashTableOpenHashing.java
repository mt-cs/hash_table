package hashTable.openHashing;
import hashTable.HashEntry;
import hashTable.Map;

public class HashTableOpenHashing implements Map {
    private Node[] table ;
    private int maxSize;
    private int size;

    /** Constructor for class HashTableOpenHashing
     *
     * @param maxSize the size of the table
     */
    public HashTableOpenHashing(int maxSize) {
        // FILL IN CODE

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
     * @param key
     * @param value associated value
     */
    public void put(String key, Object value) {
            // FILL IN CODE
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


}
