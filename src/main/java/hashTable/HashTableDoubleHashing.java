package hashTable;

/**
 * This class in the parent class for ClosedHashingLP and ClosedHashingDH
 */
public class HashTableDoubleHashing {
    private HashEntry[] table;
    private int maxSize;
    private int size;
    private HashFunction hf;

    /** Constructor for class HashTableClosedHashingDH.
     *  Creates a hash table of the given size.
     * @param maxSize maximum number of elements the hash table can store
     */
    public HashTableDoubleHashing(int maxSize) {
        this.maxSize = maxSize;
        table = new HashEntry[maxSize];
        size = 0;
        hf = new HashFunction(maxSize);
    }

    /**
     * Getter for table
     * @return table
     */
    public HashEntry[] getTable() {
        return table;
    }

    /**
     * Getter for maxSize
     * @return maxSize
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Getter for size
     * @return size
     */
    public int getSize() {
        return size;
    }

    /**
     * Getter for hf
     * @return hf
     */
    public HashFunction getHf() {
        return hf;
    }

    /**
     * setter for table
     * @param inTable HashEntry[] table
     */
    public void setTable(HashEntry[] inTable) {
        table = inTable;
    }

    /**
     * setter for MaxSize
     * @param inMaxSize integer for maximum size
     */
    public void setMaxSize(int inMaxSize) {
        maxSize = inMaxSize;
    }

    /**
     * Setter for size
     * @param inSize integer size
     */
    public void setSize(int inSize) {
        size = inSize;
    }

    /**
     * Setter for hf
     * @param inHf HashFunction hf
     */
    public void setHf(HashFunction inHf) {
        hf = inHf;
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
     * a helper method to check if the table is null
     * @param index current int index
     * @return true if table is null, false otherwise
     */
    protected boolean checkIfNull(int index) {
        return table[index] == null || table[index].isDeleted();
    }

    /**
     * a helper method to check if the table is not null
     * @param index current int index
     * @return true if table is not null, false otherwise
     */
    protected boolean checkIfNotNull(int index) {
        return table[index] != null && !table[index].isDeleted();
    }

}
