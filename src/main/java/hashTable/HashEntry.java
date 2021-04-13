package hashTable;

/**
 * An entry in the hash table. Stores a key, a corresponding value and a boolean flag.
 * Null keys or values are not permitted.
 */
public class HashEntry {
    private String key;
    private Object value;
    private boolean deleted; // if there was previously a value here that was later deleted.


    /**
     * Constructor for class HashEntry
     *
     * @param key   key
     * @param value corresponding value
     */
    public HashEntry(String key, Object value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        this.key = key;
        this.value = value;
        this.deleted = false;
    }


    /**
     * Returns the key stored in the entry
     *
     * @return key
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the value stored in the entry
     *
     * @return value
     */
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        this.value = value;
    }

    /**
     * Returns true if this entry was deleted
     *
     * @return true if the entry was deleted and false otherwise
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Sets the value of the "deleted" attribute
     *
     * @param flag new boolean value of the "deleted" variable
     */
    public void setDeleted(boolean flag) {
        deleted = flag;
    }

    /**
     * String representation of the HashEntry in the form (key, value, deleted)
     *
     * @return String representation of the HashEntry
     */
    @Override
    public String toString() {
        return "(" + key + ", " + value.toString() + ", " + deleted + ")";
    }

    // Feel free to add other methods if needed

}
