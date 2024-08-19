package DoublyLinkedLIst;

public class DoublyLinkedList {
    private Node head;

    private class Node {
        Object value;
        Node next;
        Node prev;

        Node(Object value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }


}
