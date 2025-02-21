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
     * Проверяет, существует ли позиция в списке.
     *
     * Метод проходит по всему списку, начиная с головы (head), и проверяет,
     * совпадает ли индекс позиции с индексом текущего узла.
     *
     * @param position Позиция, которую нужно проверить на наличие в списке.
     * @return true, если позиция существует в списке, иначе false.
     */
    private Boolean positionExists(Position position) {
        Node current = head;
        while (current != null) {
            if (position.index == current) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Метод для получения позиции  конца списка.
     * @return Позиция конца списка, указывающая на null.
     */
    public Position end() {
        return new Position(null); // Конец списка всегда указывает на null
    }

    /**
     * Метод для вставки элемента в список на указанной позиции.
     * @param index Элемент для вставки.
     * @param position Позиция, перед которой будет вставлен элемент.
     */
    /**
     * Метод для вставки нового элемента перед указанной позицией.
     * @param element Элемент для вставки.
     * @param position Позиция, перед которой будет вставлен элемент. Если позиция равна END(), элемент добавляется в конец.
     */
    public void insert(ListElement element, Position position) {
        // Вставка в пустой список
        if (head == tail) {
            if (position.index == null) {
                if (head == null) { // Если список пустой
                    head = new Node(element);
                    tail = head;
                    head.next = head;
                    head.prev = head;
                    return;
                }
                tail = new Node(element); // Добавим в конец
                tail.prev = head;
                head.next = tail;
                return;
            }
            // Если список содержит только 1 элемент
            head = new Node(element);
            tail = head;
            head.next = head;
            head.prev = head;
            return;
        }

        if (position.index == null) { // Добавим в конец
            Node newNode = new Node(element);
            newNode.prev = tail;
            newNode.next = head;
            tail.next = newNode;
            head.prev = newNode;
            tail = newNode;
            return;
        }

        // Поставим перед tail
        if (position.index == tail) {
            Node newNode = new Node(element);
            newNode.prev = tail.prev;
            newNode.next = tail;
            tail.prev.next = newNode;
            tail.prev = newNode;
            return;
        }

        // Вставка в середину списка
        Node current = position.index;
        Node newNode = new Node(element);
        newNode.next = current;
        newNode.prev = current.prev;

        if (current.prev != null) {
            current.prev.next = newNode;
        }
        current.prev = newNode;

        // Если вставка в начало
        if (current == head) {
            head = newNode;
        }
    }


    /**
     * Метод для поиска элемента по имени.
     * @param element элемент для поиска.
     * @return Позиция элемента с заданным именем или END, если элемент не найден.
     */
    public Position locate(ListElement element) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(element)) {
                return new Position(current); // Возвращаем позицию найденного узла
            }
            current = current.next;
        }
        return new Position(null); // Если элемент не найден
    }

    /**
     * Метод для получения элемента на указанной позиции.
     * @param position Позиция, содержащая элемент.
     * @return Элемент на указанной позиции.
     * @throws IllegalArgumentException если позиция недопустима.
     */
    public ListElement retrieve(Position position) {
        if (head == null || position.index == null) {
            throw new IllegalArgumentException("Invalid position.");
        }
        return position.index.data; // Возвращаем данные узла
    }

    /**
     * Метод для удаления элемента на указанной позиции.
     * @param position Позиция, с которой будет удален элемент.
     */
    public void delete(Position position) {
        if (head == null || tail == null || position.index == null) {
            return; // Если список пуст или позиция не существует, ничего не делаем
        }

        // Если в списке только один элемент и его нужно удалить
        if (head == tail && position.index == head) {
            head = null;
            tail = null;
            return;
        }

        // Удаление из головы (отдельно обрабатываем случай одного и нескольких элементов) если единмтвенный элемент
        if (position.index == head) {// Если head = tail и position == head
            head = head.next;
            if (head != null) {
                head.prev = null; // Удаляем ссылку на предыдущий элемент
            } else {
                tail = null; // Если список стал пустым, корректируем tail
            }
            return;
        }

        // Удаление из хвоста
        if (position.index == tail) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null; // Удаляем ссылку на следующий элемент
            } else {
                head = null; // Если список стал пустым, корректируем head
            }
            return;
        }

        // Удаление из середины
        if (positionExists(position)) {
            Node previous = position.index.prev;
            Node next = position.index.next;
            previous.next = next; // Перенаправляем ссылку предыдущего элемента
            if (next != null) {
                next.prev = previous; // Перенаправляем ссылку следующего элемента
            }
        }
    }

    /**
     * Метод для получения следующей позиции.
     * @param position Текущая позиция.
     * @return Следующая позиция в списке.
     * @throws IllegalArgumentException если позиция недопустима.
     */
    public Position next(Position position) {
        if (position.index == null || !positionExists(position)) {
            throw new IllegalArgumentException("Invalid position.");
        }
        if (position.index == head) {
            return new Position(head.next);
        }
        if (position.index == tail) {
            return new Position(null);
        }
        return new Position(position.index.next);
    }

    /**
     * Метод для получения предыдущей позиции.
     * @param position Текущая позиция.
     * @return Предыдущая позиция в списке.
     * @throws IllegalArgumentException если позиция недопустима.
     */
    public Position previous(Position position) {
        if (head == null || position.index == head || !positionExists(position)) {
            throw new IllegalArgumentException("Invalid position.");
        }

        if (position.index == tail) {
            return new Position(tail.prev);
        }

        return new Position(position.index.prev);
    }

    /**
     * Метод для получения первой позиции в списке.
     * @return Позиция первого элемента или END, если список пуст.
     */
    public Position first() {
        return new Position(head);
    }

    /**
     * Метод для очистки списка, возвращая его в пустое состояние.
     */
    public Position makeNull() {
        head = null;
        tail = null;
        return new Position(null);
    }

    /**
     * Метод для вывода элементов списка в консоль.
     */
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}
