package hashTable.openHashing;

import hashTable.HashEntry;

/** A class representing a linked list. */
public class LinkedList {
    private Node head, tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    /**
     * Inserts a new node to the front of the list
     */
    public void insertAtFront(HashEntry elem) {
        Node newNode = new Node(elem);
        if (head != null) {
            newNode.setNext(head);
        } else {
            tail = newNode;
        }
        head = newNode;
    }

    /**
     * Creates a new node with the given element and adds it to the back of the
     * list
     */
    public void append(HashEntry elem) {
        Node newNode = new Node(elem);
        if (tail != null) {
            tail.setNext(newNode);
            tail = newNode;
        } else {
            head = tail = newNode;
        }

    }

    /**
     * Insert a given element at index i in the linked list
     *
     * @param index index where to insert
     * @param elem  element to insert
     */
    public void insert(int index, HashEntry elem) {
        Node newNode = new Node(elem);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else if (index == 0) {
            newNode.setNext(head);
            head = newNode;
        } else {
            Node prev = head;
            int count = 0;
            while (prev != null && count < index - 1) {
                prev = prev.next();
                count++;
            }

            if (prev != null) {
                newNode.setNext(prev.next());
                prev.setNext(newNode);
            }
        }

    }

    /**
     * Prints all the nodes in the link list
     */
    public void printNodes() {
        Node current = head;
        while (current != null) {
            System.out.print(current.entry() + " ");
            current = current.next();
        }
        System.out.println();

    }

    /**
     * Return true if the given element is in the list
     */
    public boolean find(HashEntry elem) {
        Node current = head;
        while (current != null) {
            if (current.entry() == elem)
                return true;
            current = current.next();
        }
        return false;
    }


    /**
     * Remove the node after "previousNode". Return the value of the element at
     * the deleted node
     */
    public HashEntry remove(Node previousNode) {
        if ((previousNode == null) || (previousNode.next() == null)) {
            System.out.println("Nothing to remove");
            return null;
        }
        HashEntry elem = previousNode.next().entry();

        // if removing the tail
        if (previousNode.next() == tail) {
            tail = previousNode;
            tail.setNext(null);

        } else {
            // delete node
            previousNode.setNext(previousNode.next().next());
        }
        return elem;
    }
}
