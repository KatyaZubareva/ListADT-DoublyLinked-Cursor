package doublylinkedlist;

/**
 * Класс для представления узла в двусвязном списке.
 */
class Node {
    ListElement data; // Данные, хранящиеся в узле
    Node prev;        // Ссылка на предыдущий узел
    Node next;        // Ссылка на следующий узел

    /**
     * Конструктор, инициализирующий узел с данными.
     * @param data Данные, которые будут храниться в узле.
     */
    public Node(ListElement data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

