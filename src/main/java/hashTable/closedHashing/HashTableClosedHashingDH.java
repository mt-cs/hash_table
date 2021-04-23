package hashTable.closedHashing;

import hashTable.HashEntry;
import hashTable.Map;

/** The class that implements the Map interface using closed hashing;
 *  extends DoubleHashing class;
 *  uses double hashing to resolve collisions */
public class HashTableClosedHashingDH extends HashTableClosedHashing implements Map{

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
        int idx = getHf().hash(key);
        if (checkIfNull(idx)) {
            return false;
        }
        if (this.getTable()[idx].getKey().equals(key)) {
            return true;
        }

        int dk = getHf().getSecondHash(key);
        int j = 1;
        int newIdx = (idx + (j * dk)) % getMaxSize();

        while (checkIfNotNull(newIdx)) {
            if (checkIfNull(newIdx)) {
                return false;
            }
            if (this.getTable()[newIdx].getKey().equals(key)) {
                return !this.getTable()[newIdx].isDeleted();
            }
            if (j == getMaxSize() - 1) {
                System.out.println("--> The table is full! Key is not found.");
                return false;
            }
            j++;
            newIdx = (idx + (j * dk)) % this.getMaxSize();
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
        boolean updated = updateKeyDH(key, value); // check if key is already in the table, not required to pass the test
        if (!updated) {
            HashEntry entry = new HashEntry(key, value);
            int idx = getHf().hash(key);
        if (!(getHf().getLoadFactor(size()) <= 0.6)) {
            rehashDH();
            idx = getHf().hash(key);
        }
            idx = checkIndex(idx, key);
            getTable()[idx] = entry;
            setSize(size() + 1);
            if (getTable()[idx].isDeleted()) {
                getTable()[idx].setDeleted(false);
            }
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
        int dk = getHf().getSecondHash(key);
        int j = 1;
        int newIdx = (idx + (j * dk)) % getMaxSize();

        while (checkIfNotNull(newIdx)) {
            if (j == getMaxSize() - 1) {
                System.out.println("--> The table is full! Key is not found.");
                return 0;
            }
            j++;
            newIdx = (idx + (j * dk)) % getMaxSize();
            // If we turn off load factor and table is full
            if (size() == getMaxSize()) {
                System.out.println("--> Please resize the table...");
                throw new IllegalStateException("The table is full!");
            }
        }
        return newIdx;
    }

    /**
     * A helper me to rehash table to a new maxSize
     */
    private void rehashDH () {
        setMaxSize(getHf().getNewSize());
        HashEntry[] temp = getTable();

        HashTableClosedHashingDH rehash_table = new HashTableClosedHashingDH(getMaxSize());
        for (HashEntry entry : temp) {
            if (entry != null && !entry.isDeleted()) {
                rehash_table.put(entry.getKey(), entry.getValue());
            }
        }
        setTable(rehash_table.getTable());
        setSize(rehash_table.getSize());
        setHf(rehash_table.getHf());
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
        int idx = getHf().hash(key);
        if (checkIfNull(idx)) {
            return null;
        }
        if (this.getTable()[idx].getKey().equals(key)) {
            if (this.getTable()[idx].isDeleted()) {
                return null;
            }
            return this.getTable()[idx].getValue();
        }

        int dk = getHf().getSecondHash(key);
        int j = 1;
        int newIdx = (idx + (j * dk)) % getMaxSize();

        while (checkIfNotNull(newIdx)) {
            if (checkIfNull(newIdx)) {
                return null;
            }
            if (this.getTable()[newIdx].getKey().equals(key)) {
                return this.getTable()[newIdx].getValue();
            }
            if (j == getMaxSize() - 1) {
                System.out.println("--> The table is full! Key is not found.");
                return null;
            }
            j++;
            newIdx = (idx + (j * dk)) % getMaxSize();
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
        int idx = getHf().hash(key);
        if (checkIfNull(idx)) {
            return null;
        }
        if (this.getTable()[idx].getKey().equals(key)) {
            getTable()[idx].setDeleted(true);
            return this.getTable()[idx].getValue();
        }

        int dk = getHf().getSecondHash(key);
        int j = 1;
        int newIdx = (idx + (j * dk)) % getMaxSize();

        while (checkIfNotNull(newIdx)) {
            if (checkIfNull(newIdx)) {
                return null;
            }
            if (this.getTable()[newIdx].getKey().equals(key)) {
                getTable()[newIdx].setDeleted(true);
                return this.getTable()[newIdx].getValue();
            }
            if (j == getMaxSize() - 1) {
                System.out.println("--> The table is full! Key is not found.");
                return false;
            }
            j++;
            newIdx = (idx + (j * dk)) % getMaxSize();
        }
        return null;
    }

    /** Return the actual number of elements in the map.
     *
     * @return number of elements currently in the map.
     */
    @Override
    public int size() {
        return this.getSize();
    }

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
                idx = getHf().hash(key);
            }
        }
        return idx;
    }

    /**
     * If the key is in the table, replace the value for this key.
     * @param key key
     * @param value new value
     */
    private boolean updateKeyDH(String key, Object value) {
        int idx = getHf().hash(key);
        if (this.getTable()[idx] != null && this.getTable()[idx].getKey().equals(key)) {
            this.getTable()[idx].setValue(value);
            return true;
        }
        if (idx + 1 != getMaxSize()) {
            int dk = getHf().getSecondHash(key);
            int j = 1;
            int newIdx = (idx + (j * dk)) % getMaxSize();

            while (checkIfNotNull(newIdx)) {
                if (!checkIfNull(newIdx)) {
                    if (this.getTable()[newIdx] != null && this.getTable()[newIdx].getKey().equals(key)) {
                        if (this.getTable()[newIdx].isDeleted()) {
                            return false;
                        }
                        this.getTable()[newIdx].setValue(value);
                        return true;
                    }
                    if (j == getMaxSize() - 1) {
                        System.out.println("--> The table is full! Key is not found");
                        return false;
                    }
                    j++;
                    newIdx = (idx + (j * dk)) % getMaxSize();
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
