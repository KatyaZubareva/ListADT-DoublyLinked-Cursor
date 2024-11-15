package cursorlist;

/**
 * Класс, реализующий АТД "Список на курсорах".
 * Список представляет собой коллекцию элементов, хранящихся в узлах с использованием курсоров для управления позициями.
 * Все операции, такие как вставка, удаление, поиск и навигация, основаны на манипуляциях с курсорами.
 * Внутренний массив узлов используется для реализации списка, где каждый узел ссылается на следующий элемент.
 * Класс поддерживает основные операции: вставка элемента в конец или перед заданной позицией, поиск элемента,
 * удаление элемента по позиции, и навигация по элементам списка.
 */
public class List {
    private static class Node {
        ListElement element; // Элемент списка
        int next;            // Курсор на следующий элемент

        Node(ListElement element, int next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node[] nodes;    // Массив узлов
    private int head;        // Курсор на начало списка
    private int free;        // Курсор на начало списка свободных ячеек

    /**
     * Конструктор, инициализирующий список с заданной емкостью.
     * @param capacity Максимальный размер списка.
     */
    public List(int capacity) {
        nodes = new Node[capacity];
        head = -1;          // Начало списка пустое
        free = 0;           // Указатель на первую свободную ячейку

        // Инициализация списка свободных ячеек
        for (int i = 0; i < capacity - 1; i++) {
            nodes[i] = new Node(null, i + 1);
        }
        nodes[capacity - 1] = new Node(null, -1); // Последний свободный указывает на -1
    }

    /**
     * Метод для получения позиции конца списка (по сути, возвращает -1).
     * @return Позиция конца списка.
     */
    public Position END() {
        return new Position(-1);
    }

    /**
     * Метод для вставки элемента в список на указанной позиции.
     * @param element Элемент, который нужно вставить.
     * @param position Позиция, перед которой нужно вставить элемент.
     */
    public void INSERT(ListElement element, Position position) {
        if (free == -1) throw new IllegalStateException("List is full.");

        int newNodeIndex = free;        // Индекс новой ячейки
        free = nodes[free].next;        // Обновляем список свободных

        nodes[newNodeIndex] = new Node(element, -1); // Создаем новый узел
        if (position.getIndex() == -1) { // Вставка в конец
            if (head == -1) {
                head = newNodeIndex;
            } else {
                int last = head;
                while (nodes[last].next != -1) {
                    last = nodes[last].next;
                }
                nodes[last].next = newNodeIndex;
            }
        } else { // Вставка перед заданной позицией
            if (position.getIndex() == head) {
                nodes[newNodeIndex].next = head;
                head = newNodeIndex;
            } else {
                int prev = head;
                while (nodes[prev].next != position.getIndex()) {
                    prev = nodes[prev].next;
                }
                nodes[newNodeIndex].next = nodes[prev].next;
                nodes[prev].next = newNodeIndex;
            }
        }
    }

    /**
     * Метод для поиска элемента по имени.
     * @param name Имя элемента, который нужно найти.
     * @return Позиция найденного элемента, или END(), если элемент не найден.
     */
    public Position LOCATE(String name) {
        int current = head;
        while (current != -1) {
            if (nodes[current].element.getName().equals(name)) {
                return new Position(current);
            }
            current = nodes[current].next;
        }
        return END();
    }

    /**
     * Метод для получения элемента на указанной позиции.
     * @param position Позиция элемента, который нужно получить.
     * @return Элемент на указанной позиции.
     */
    public ListElement RETRIEVE(Position position) {
        if (position.getIndex() == -1) throw new IllegalArgumentException("Invalid position.");
        return nodes[position.getIndex()].element;
    }

    /**
     * Метод для удаления элемента на указанной позиции.
     * @param position Позиция элемента, который нужно удалить.
     */
    public void DELETE(Position position) {
        if (position.getIndex() == -1) throw new IllegalArgumentException("Invalid position.");
        int indexToDelete = position.getIndex();

        if (indexToDelete == head) {
            head = nodes[head].next;
        } else {
            int prev = head;
            while (nodes[prev].next != indexToDelete) {
                prev = nodes[prev].next;
            }
            nodes[prev].next = nodes[indexToDelete].next;
        }

        // Возвращаем ячейку в список свободных
        nodes[indexToDelete].element = null;
        nodes[indexToDelete].next = free;
        free = indexToDelete;
    }

    /**
     * Метод для получения первой позиции списка.
     * @return Первая позиция списка (голова списка), или END(), если список пуст.
     */
    public Position FIRST() {
        return head == -1 ? END() : new Position(head);
    }

    /**
     * Метод для получения следующей позиции после указанной.
     * @param position Позиция текущего элемента.
     * @return Следующая позиция, или END(), если следующего элемента нет.
     */
    public Position NEXT(Position position) {
        int index = position.getIndex();
        if (index == -1 || nodes[index].next == -1) return END();
        return new Position(nodes[index].next);
    }

    /**
     * Метод для очистки списка, возвращая все элементы в список свободных ячеек.
     */
    public void MAKENULL() {
        head = -1;
        free = 0;
        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i] = new Node(null, i + 1);
        }
        nodes[nodes.length - 1] = new Node(null, -1);
    }

    /**
     * Метод для печати всех элементов списка.
     */
    public void PRINTLIST() {
        int current = head;
        while (current != -1) {
            System.out.println(nodes[current].element);
            current = nodes[current].next;
        }
    }
}

