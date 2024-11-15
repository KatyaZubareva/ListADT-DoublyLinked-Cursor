package doublylinkedlist;


/**
 * Класс для представления двусвязного списка с операциями вставки, удаления,
 * поиска, получения первой и последней позиции, а также очистки списка.
 */
public class List {
    private Node head; // Указатель на первый узел списка
    private Node tail; // Указатель на последний узел списка

    /**
     * Конструктор, инициализирующий пустой список.
     */
    public List() {
        head = null;
        tail = null;
    }

    /**
     * Метод для получения позиции конца списка.
     * @return Позиция конца списка, указывающая на null.
     */
    public Position END() {
        return new Position(null);
    }

    /**
     * Метод для вставки элемента в список на указанной позиции.
     * @param element Элемент для вставки.
     * @param position Позиция, на которую будет вставлен элемент.
     */
    public void INSERT(ListElement element, Position position) {
        Node newNode = new Node(element); // Создаем новый узел с данными

        if (position.getElement() == null) {
            // Вставка в конец списка
            if (tail == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        } else {
            Node current = position.getElement();

            if (current == head) {
                // Вставка в начало списка
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else {
                // Вставка в середину списка
                newNode.next = current;
                newNode.prev = current.prev;
                if (current.prev != null) {
                    current.prev.next = newNode;
                }
                current.prev = newNode;
            }
        }
    }

    /**
     * Метод для поиска элемента по имени.
     * @param name Имя элемента для поиска.
     * @return Позиция элемента с заданным именем или END, если элемент не найден.
     */
    public Position LOCATE(String name) {
        Node current = head;
        while (current != null) {
            if (current.data.getName().equals(name)) {
                return new Position(current);
            }
            current = current.next;
        }
        return END();
    }

    /**
     * Метод для получения элемента на указанной позиции.
     * @param position Позиция, содержащая элемент.
     * @return Элемент на указанной позиции.
     * @throws IllegalArgumentException если позиция недопустима.
     */
    public ListElement RETRIEVE(Position position) {
        Node node = position.getElement();
        if (node == null) throw new IllegalArgumentException("Invalid position.");
        return node.data;
    }

    /**
     * Метод для удаления элемента на указанной позиции.
     * @param position Позиция, с которой будет удален элемент.
     */
    public void DELETE(Position position) {
        Node nodeToDelete = position.getElement();
        if (nodeToDelete == null) return;

        if (nodeToDelete == head) {
            head = head.next;
            if (head != null) head.prev = null;
            else tail = null;
        } else if (nodeToDelete == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            nodeToDelete.prev.next = nodeToDelete.next;
            nodeToDelete.next.prev = nodeToDelete.prev;
        }
    }

    /**
     * Метод для получения следующей позиции.
     * @param position Текущая позиция.
     * @return Следующая позиция в списке.
     * @throws IllegalArgumentException если позиция недопустима или является последней.
     */
    public Position NEXT(Position position) {
        Node node = position.getElement();
        if (node == null || node.next == null) throw new IllegalArgumentException("Invalid position.");
        return new Position(node.next);
    }

    /**
     * Метод для получения предыдущей позиции.
     * @param position Текущая позиция.
     * @return Предыдущая позиция в списке.
     * @throws IllegalArgumentException если позиция недопустима или является первой.
     */
    public Position PREVIOUS(Position position) {
        Node node = position.getElement();
        if (node == null || node.prev == null) throw new IllegalArgumentException("Invalid position.");
        return new Position(node.prev);
    }

    /**
     * Метод для получения первой позиции в списке.
     * @return Позиция первого элемента или END, если список пуст.
     */
    public Position FIRST() {
        return head == null ? END() : new Position(head);
    }

    /**
     * Метод для очистки списка, возвращая его в пустое состояние.
     */
    public void MAKENULL() {
        head = null;
        tail = null;
    }

    /**
     * Метод для вывода элементов списка в консоль.
     */
    public void PRINTLIST() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}
