package hashTable;

/**
 * This class in the parent class for ClosedHashingLP and ClosedHashingDH
 */
public class HashTableDoubleHashing {
    protected HashEntry[] table;
    protected int maxSize;
    protected int size;
    protected HashFunction hf;

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
