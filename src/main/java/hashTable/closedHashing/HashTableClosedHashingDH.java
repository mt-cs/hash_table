package hashTable.closedHashing;

import hashTable.HashEntry;
import hashTable.HashTableDoubleHashing;
import hashTable.Map;

/** The class that implements the Map interface using closed hashing;
 *  uses double hashing to resolve collisions */
public class HashTableClosedHashingDH extends HashTableDoubleHashing implements Map{

    /** Constructor for class HashTableClosedHashingDH.
     *  Creates a hash table of the given size.
     * @param maxSize maximum number of elements the hash table can store
     */
    public HashTableClosedHashingDH(int maxSize) {
        super(maxSize);
    }

    /** Return true if the map contains a (key, value) pair associated with this key,
     *  otherwise return false.
     *
     * @param key  key
     * @return true if the key (and the corresponding value) is the in map
     */
    @Override
    public boolean containsKey(String key) {
        int idx = hf.hash(key);
        if (checkIfNull(idx)) {
            return false;
        }
        if (this.table[idx].getKey().equals(key)) {
            return true;
        }

        int dk = hf.getSecondHash(key);
        int j = 1;
        int newIdx = (idx + (j * dk)) % maxSize;

        while (checkIfNotNull(newIdx)) {
            if (checkIfNull(newIdx)) {
                return false;
            }
            if (this.table[newIdx].getKey().equals(key)) {
                return !this.table[newIdx].isDeleted();
            }
            j++;
            newIdx = (idx + (j * dk)) % maxSize;
        }
        return false;
    }

    /** Add (key, value) to the map.
     * Will replace previous value that this key was mapped to.
     * If key is null, throw IllegalArgumentException.
     *
     * @param key key
     * @param value associated value
     */
    @Override
    public void put(String key, Object value) {
        updateKeyDH(key, value); // check if key is already in the table, not required to pass the test
        HashEntry entry = new HashEntry(key, value);
        int idx = hf.hash(key);
        if (!(hf.getLoadFactor(size) <= 0.6)) {
            rehashDH();
            idx = hf.hash(key);
        }
        idx = checkIndex(idx, key);
        table[idx] = entry;
        size++;
        if (table[idx].isDeleted()) {
            table[idx].setDeleted(false);
        }
    }

    /**
     * A helper method for insertion to do linear probing and find the next empty index
     * @param idx current index
     * @return idx new integer index
     */
    private int searchEmptyIndex (int idx, String key) {
        if (checkIfNull(idx)) {
            return idx;
        }
        int dk = hf.getSecondHash(key);
        int j = 1;
        int newIdx = (idx + (j * dk)) % maxSize;

        while (checkIfNotNull(newIdx)) {
            j++;
            newIdx = (idx + (j * dk)) % maxSize;
        }
        return newIdx;
    }

    /**
     * A helper me to rehash table to a new maxSize
     */
    private void rehashDH () {
        maxSize = hf.getNewSize();
        HashEntry[] temp = table;

        HashTableClosedHashingDH rehash_table = new HashTableClosedHashingDH(maxSize);
        for (HashEntry entry : temp) {
            if (entry != null && !entry.isDeleted()) {
                rehash_table.put(entry.getKey(), entry.getValue());
            }
        }
        this.table = rehash_table.table;
        this.size = rehash_table.size;
        this.hf = rehash_table.hf;
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
        int idx = hf.hash(key);
        if (checkIfNull(idx)) {
            return null;
        }
        if (this.table[idx].getKey().equals(key)) {
            if (this.table[idx].isDeleted()) {
                return null;
            }
            return this.table[idx].getValue();
        }

        int dk = hf.getSecondHash(key);
        int j = 1;
        int newIdx = (idx + (j * dk)) % maxSize;

        while (checkIfNotNull(newIdx)) {
            if (checkIfNull(newIdx)) {
                return null;
            }
            if (this.table[newIdx].getKey().equals(key)) {
                return this.table[newIdx].getValue();
            }
            j++;
            newIdx = (idx + (j * dk)) % maxSize;
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
        if (!containsKey(key)) {
            return null;
        }
        int idx = hf.hash(key);
        if (checkIfNull(idx)) {
            return null;
        }
        if (this.table[idx].getKey().equals(key)) {
            table[idx].setDeleted(true);
            return this.table[idx].getValue();
        }

        int dk = hf.getSecondHash(key);
        int j = 1;
        int newIdx = (idx + (j * dk)) % maxSize;

        while (checkIfNotNull(newIdx)) {
            if (checkIfNull(newIdx)) {
                return null;
            }
            if (this.table[newIdx].getKey().equals(key)) {
                table[newIdx].setDeleted(true);
                return this.table[newIdx].getValue();
            }
            j++;
            newIdx = (idx + (j * dk)) % maxSize;
        }
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

//    /**
//     * toString
//     * @return a string representing a hash table
//     */
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < table.length; i++) {
//            sb.append(i).append(": ");
//            if (table[i] == null) {
//                sb.append("null\n");
//            } else {
//                sb.append("(").append(table[i].getKey()).append(", ")
//                        .append(table[i].getValue()).append(", ")
//                        .append(table[i].isDeleted()).append(")\n");
//            }
//        }
//        return sb.toString();
//    }

    /**
     * A private helper method to check if index is not null
     * @param idx current index
     * @param key object String key
     * @return idx integer new index
     */
    private int checkIndex(int idx, String key) {
        if (checkIfNotNull(idx)) {
            idx = searchEmptyIndex(idx, key);
            if (idx == 0) {
                rehashDH();
                idx = hf.hash(key);
            }
        }
        return idx;
    }

    /**
     * If the key is in the table, replace the value for this key.
     * @param key key
     * @param value new value
     */
    private void updateKeyDH(String key, Object value) {
        int idx = hf.hash(key);
        if (this.table[idx] != null && this.table[idx].getKey().equals(key)) {
            this.table[idx].setValue(value);
        }
        if (idx + 1 != maxSize) {
            int dk = hf.getSecondHash(key);
            int j = 1;
            int newIdx = (idx + (j * dk)) % maxSize;

            while (checkIfNotNull(newIdx)) {
                if (checkIfNull(newIdx)) {
                    break;
                }
                if (this.table[newIdx] != null && this.table[newIdx].getKey().equals(key)) {
                    if (this.table[newIdx].isDeleted()) {
                        break;
                    }
                    this.table[newIdx].setValue(value);
                }
                j++;
                newIdx = (idx + (j * dk)) % maxSize;
            }
        }
    }
}
