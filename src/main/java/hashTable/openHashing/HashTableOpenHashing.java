package hashTable.openHashing;
import hashTable.HashEntry;
import hashTable.Map;
import hashTable.HashFunction;

public class HashTableOpenHashing implements Map {
    private Node[] table ; // this node is the head of the LinkedList
    private int maxSize; // prime number
    private int size;
    private HashFunction hf;

    /** Constructor for class HashTableOpenHashing
     *
     * @param maxSize the size of the table
     */
    public HashTableOpenHashing(int maxSize) {
        this.maxSize = maxSize;
        table = new Node[maxSize];
        size = 0;
        hf = new HashFunction(maxSize);
    }

    /** Return true if the map contains a (key, value) pair associated with this key,
     *  otherwise return false.
     *
     * @param key key
     * @return true if the key (and the corresponding value) is the in map
     */
    public boolean containsKey(String key) {
        int idx = hf.hash(key);
        Node current = this.table[idx];
        while (current != null) {
            if (current.entry().getKey().equals(key)) {
                return true;
            }
            current = current.next();
        }
        return false;
    }

    /** Add (key, value) to the map.
     * Will replace previous value that this key was mapped to.
     * If key is null, throw IllegalArgumentException.
     *
     * @param key associated key
     * @param value associated value
     */
    public void put(String key, Object value) {
        updateKey(key, value); // check if key is already in the table, not required to pass the test
        HashEntry entry = new HashEntry(key, value);
        int idx;
        if (hf.getLoadFactor(size) <= 0.6) {
            idx = hf.hash(key);
            if (table[idx] == null) {
                Node head = new Node(entry);
                table[idx] = head;
                size++;
            } else {
                insertInFront(idx, entry);
            }
        } else {
            rehash();
            idx = hf.hash(key);
            insertInFront(idx, entry);
        }
    }

    /** Return the value associated with the given key or null, if the map does not contain the key.
     * If the key is null, throw IllegalArgumentException.
     *
     * @param key key
     * @return value associated value
     */
    public Object get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        int idx = hf.hash(key);
        Node current = this.table[idx];
        while (current != null) {
            if (current.entry().getKey().equals(key)) {
                return current.entry().getValue();
            }
            current = current.next();
        }
        return null;
    }

    /** Remove a (key, value) entry if it exists.
     * Return the previous value associated with the given key, otherwise return null
     * @param key key
     * @return previous value
     */
    public Object remove(String key) {
        if (!containsKey(key)) {
            return null;
        }
        int idx = hf.hash(key);
        String val = null;

        Node current = this.table[idx];
        if (current.entry().getKey().equals(key)) { // Remove head
            val = (String) current.entry().getValue();
            this.table[idx] = current.next();
            size--;
        }
        else {
            Node prev = null;
            while (current != null) {
                if (current.entry().getKey().equals(key)) {
                    val = (String) current.entry().getValue();
                    if (prev != null) {
                        prev.setNext(current.next());
                        size--;
                    }
                }
                prev = current;
                current = current.next();
            }
        }
        return val;
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

        for (int i = 0; i < this.table.length; i++) {
            sb.append(i).append(": ");
            if (table[i] == null) {
                sb.append("null\n");
            } else {
                Node current = this.table[i];
                while (current != null) {
                    sb.append("(").append(current.entry().getKey()).append(", ").
                            append(current.entry().getValue()).append(", ").
                            append(current.entry().isDeleted()).append(")");
                    current = current.next();
                    if (current != null) {
                        sb.append(", ");
                    }
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * A helper me to rehash table to a new maxSize
     */
    private void rehash () {
        maxSize = hf.getNewSize();
        Node [] temp = table;
        HashTableOpenHashing rehash_table = new HashTableOpenHashing(maxSize);
        for (Node node : temp) {
            Node current = node;
            while (current != null && !current.entry().isDeleted()) {
                rehash_table.put(current.entry().getKey(), current.entry().getValue());
                current = current.next();
            }
        }
        this.table = rehash_table.table;
        this.size = rehash_table.size;
        this.hf = rehash_table.hf;
    }

    /**
     * A helper method to insert node in front of the linkedlist
     * @param idx current index
     * @param entry the entry to be inserted
     */
    private void insertInFront (int idx, HashEntry entry) {
        Node newNode = new Node(entry, table[idx]);
        table[idx] = newNode;
        size++;
    }

    /**
     * Update key's value if the key is already in the table
     * @param key key
     * @param val value
     */
    private void updateKey(String key, Object val) {
        int idx = hf.hash(key);
        Node current = this.table[idx];
        while (current != null) {
            if (current.entry().getKey().equals(key)) {
                current.entry().setValue(val);
            }
            current = current.next();
        }
    }
}
