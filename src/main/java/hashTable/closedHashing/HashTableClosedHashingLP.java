package hashTable.closedHashing;

import hashTable.HashEntry;
import hashTable.HashTableDoubleHashing;
import hashTable.Map;

/** The class that implements the Map interface using closed hashing;
 *  extends DoubleHashing class;
 *  uses linear probing to resolve collisions */
public class HashTableClosedHashingLP extends HashTableDoubleHashing implements Map {

    /** Constructor for class HashTableClosedHashingLP.
     *  Creates a hash table of the given size.
     * @param maxSize maximum number of elements the hash table can store
     */
    public HashTableClosedHashingLP(int maxSize) {
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
        return searchLPContains (idx, key);
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
        updateKeyLP(key, value); // check if key is already in the table, not required to pass the test
        HashEntry entry = new HashEntry(key, value);
        int idx = getHf().hash(key);
        if (!(getHf().getLoadFactor(size()) <= 0.6)) {
            rehashLP();
            idx = getHf().hash(key);
        }
        idx = checkIndex(idx, key);
        getTable()[idx] = entry;
        setSize(size() + 1);
        if (getTable()[idx].isDeleted()) {
            getTable()[idx].setDeleted(false);
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

        for (int i = idx + 1; i != idx; i = (i + 1) % getMaxSize()) {
            if (checkIfNull(i)) {
                return null;
            }
            if (this.getTable()[i].getKey().equals(key)) {
                return this.getTable()[i].getValue();
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

        for (int i = idx + 1; i != idx; i = (i + 1) % getMaxSize()) {
            if (checkIfNull(i)) {
                return null;
            }
            if (this.getTable()[i].getKey().equals(key)) {
                getTable()[i].setDeleted(true);
                return this.getTable()[i].getValue();
            }
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
     * A helper method for insertion to do linear probing and find the next empty index
     * @param idx current index
     * @return idx new integer index
     */
    private int searchEmptyIndex (int idx) {
        if (checkIfNull(idx)) {
            return idx;
        }
        for (int i = idx + 1; i != idx; i = (i + 1) % getMaxSize()) {
            if (checkIfNull(i)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * A helper me to rehash table to a new maxSize
     */
    private void rehashLP () {
        setMaxSize(getHf().getNewSize());
        HashEntry[] temp = getTable();

        HashTableClosedHashingLP rehash_table = new HashTableClosedHashingLP(getMaxSize());
        for (HashEntry entry : temp) {
            if (entry != null && !entry.isDeleted()) {
                rehash_table.put(entry.getKey(), entry.getValue());
            }
        }
        setTable(rehash_table.getTable());
        setSize(rehash_table.size());
        setHf(rehash_table.getHf());
    }

    /**
     * If the key is in the table, replace the value for this key.
     * @param key key
     * @param value new value
     */
    private void updateKeyLP(String key, Object value) {
        int idx = getHf().hash(key);
        if (this.getTable()[idx] != null && this.getTable()[idx].getKey().equals(key)) {
            this.getTable()[idx].setValue(value);
        }
        if (idx + 1 != getMaxSize()) {
            for (int i = idx + 1; i != idx; i = (i + 1) % getMaxSize()) {
                if (checkIfNull(i)) {
                    break;
                }
                if (this.getTable()[i] != null && this.getTable()[i].getKey().equals(key)) {
                    if (this.getTable()[i].isDeleted()) {
                        break;
                    }
                    this.getTable()[i].setValue(value);
                }
            }
        }
    }

    /**
     * A private helper method to check if index is not null
     * @param idx current index
     * @param key object String key
     * @return idx integer new index
     */
    private int checkIndex(int idx, String key) {
        if (checkIfNotNull(idx)) {
            idx = searchEmptyIndex(idx);
            if (idx == 0) {
                rehashLP();
                idx = getHf().hash(key);
            }
        }
        return idx;
    }

    /**
     * search linear probing if the table contain the element circularly
     * @param idx current index
     * @param key String key
     * @return true if table contains the key, false otherwise
     */
    private boolean searchLPContains (int idx, String key) {
        for (int i = idx + 1; i != idx; i = (i + 1) % getMaxSize()) {
            if (!checkIfNull(idx)) {
                if (this.getTable()[i].getKey().equals(key)) {
                    return !this.getTable()[i].isDeleted();
                }
            } else {
                return false;
            }
        }
        return false;
    }
}
