package hashTable.openHashing;

import hashTable.HashEntry;

/* A class representing a node in a singly linked list. Do not modify.  */
public class Node {
    private HashEntry elem;
    private Node next;

    /**
     * Constructs a node with the given value. Sets the next reference to null.
     * @param elem
     */
    public Node(HashEntry elem) {
        this.elem = elem;
        next = null;
    }

    /** Constructs a node with the given element and the next pointer
     *
     * @param elem
     * @param next
     */
    public Node(HashEntry elem, Node next) {
        this.elem = elem;
        this.next = next;
    }

    public HashEntry entry() {
        return elem;
    }

    public void setElem(HashEntry elem) {
        this.elem = elem;
    }

    public Node next() {
        return next;
    }

    public void setNext(Node other) {
        next = other;
    }

    public String toString() {
        return "element:" + elem.getKey() + " " + elem.getValue();
    }
}

