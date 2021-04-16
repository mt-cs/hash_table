package hashTable.closedHashing;

import hashTable.HashEntry;
import hashTable.HashFunction;
import hashTable.Map;
import hashTable.openHashing.HashTableOpenHashing;
import hashTable.openHashing.Node;

/** The class that implements the Map interface using closed hashing;
 *  uses linear probing to resolve collisions */
public class HashTableClosedHashingLP implements Map {
    private HashEntry[] table; // hash table
    private int maxSize;
    private int size; // the number of elements currently in the hash table
    HashFunction hf;

    /** Constructor for class HashTableClosedHashingLP.
     *  Creates a hash table of the given size.
     * @param maxSize maximum number of elements the hash table can store
     */
    public HashTableClosedHashingLP(int maxSize) {
        this.maxSize = maxSize;
        table = new HashEntry[maxSize];
        size = 0;
        hf = new HashFunction(maxSize);
    }

    /** Return true if the map contains a (key, value) pair associated with this key,
     *  otherwise return false.
     *
     * @param key  key
     * @return true if the key (and the corresponding value) is the in map
     */
    @Override
    public boolean containsKey(String key) {
        int idx = hf.hashFunction(key);
        if (this.table[idx] == null || this.table[idx].isDeleted()) {
            return false;
        }
        return linearProbSearch(idx, key);
    }

    private boolean linearProbSearch (int idx, String key) {
        for (int i = idx; i < maxSize; i++) {
            if (table[i] == null) {
                return false;
            }
            if (this.table[i].getKey().equals(key)) {
                return true;
            }
        }
        for (int i = 0; i < idx; i++) {
            if (table[i] == null) {
                return false;
            }
            if (this.table[i].getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /** Add (key, value) to the map.
     * Will replace previous value that this key was mapped to.
     * If key is null, throw IllegalArgumentException.
     *
     * @param key ne key to be inserted
     * @param value associated value
     */
    @Override
    public void put(String key, Object value) {
        HashEntry entry = new HashEntry(key, value);
        int idx = hf.hashFunction(key);
        if (hf.loadFactor(size) <= 0.6) {
            if (table[idx] != null && !table[idx].isDeleted()) {
                idx = linearProbing(idx);
                if (idx == 0) {
                    rehash();
                    idx = hf.hashFunction(key);
                }
            }
        } else {
            rehash();
            idx = hf.hashFunction(key);
        }
        table[idx] = entry;
        size++;
        if (table[idx].isDeleted()) {
            table[idx].setDeleted(false);
        }

    }

    /** Return the value associated with the given key or null, if the map does not contain the key.
     * If the key is null, throw IllegalArgumentException.
     *
     * @param key key
     * @return value associated value
     */
    @Override
    public Object get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        if (!containsKey(key)) {
            return null;
        }
        int idx = hf.hashFunction(key);
        if (this.table[idx] == null || this.table[idx].isDeleted()) {
            return null;
        }
        for (int i = idx; i < maxSize; i++) {
            if (table[i] == null) {
                return null;
            }
            if (this.table[i].getKey().equals(key)) {
                return this.table[i].getValue();
            }
        }

        for (int i = 0; i < idx; i++) {
            if (table[i] == null) {
                return null;
            }
            if (this.table[i].getKey().equals(key)) {
                return this.table[i].getValue();
            }
        }
        return null;
    }

    /** Remove a (key, value) entry if it exists.
     * Return the previous value associated with the given key, otherwise return null
     * @param key key
     * @return previous value
     */
    @Override
    public Object remove(String key) {
        // FILL IN CODE

        return null;
    }

    /** Return the actual number of elements in the map.
     *
     * @return number of elements currently in the map.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * toString
     * @return a string representing a hash table
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            sb.append(i).append(": ");
            if (table[i] == null) {
                sb.append("null\n");
            } else {
                sb.append("(").append(table[i].getKey()).append(", ")
                        .append(table[i].getValue()).append(", ")
                        .append(table[i].isDeleted()).append(")\n");
            }
        }
        return sb.toString();
    }

    /**
     * A helper method for insertion to do linear probing and find the next empty index
     * @param idx current index
     * @return idx new integer index
     */
    private int linearProbing (int idx) {
        for (int i = idx; i < maxSize; i++) {
            if (table[i] == null || table[idx].isDeleted()) {
                return i;
            }
        }
        for (int i = 0; i < idx; i++) {
            if (table[i] == null || table[idx].isDeleted()) {
                return i;
            }
        }
        return 0;
    }

    /**
     * A helper me to rehash table to a new maxSize
     */
    private void rehash () {
        maxSize = hf.getNewSize();
        HashEntry[] temp = table;

        HashTableClosedHashingLP rehash_table = new HashTableClosedHashingLP(maxSize);
        for (HashEntry entry : temp) {
            if (entry != null) {
                rehash_table.put(entry.getKey(), entry.getValue());
            }
        }
        this.table = rehash_table.table;
        this.size = rehash_table.size;
        this.hf = rehash_table.hf;
    }

}
