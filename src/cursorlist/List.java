package cursorlist;

/**
 * Класс для представления списка на курсорах с операциями вставки, удаления,
 * поиска, получения первой и последней позиции, а также очистки списка.
 */
public class List {

    private static class Item {
        ListElement element; // Элемент списка
        int next;            // Курсор на следующий элемент

        Item(int next) {
            this.element = null;
            this.next = next;
        }
    }

    private static final Item[] items; // Статический массив узлов
    private static int space;          // Статический индекс начала списка свободных ячеек
    private int start;                 // Курсор на начало списка

    // Инициализация статического массива и списка свободных ячеек
    static {
        int capacity = 50;
        items = new Item[capacity];
        space = 0; // Начало списка свободных ячеек

        for (int i = 0; i < capacity - 1; i++) {
            items[i] = new Item(i + 1);
        }
        items[capacity - 1] = new Item(-1); // Последний свободный указывает на -1
    }

    /**
     * Конструктор, инициализирующий пустой список.
     */
    public List() {
        start = -1; // Начало списка пустое
    }

    /**
     * Метод для получения последнего узла списка.
     * @return Последний узел списка или -1, если список пуст.
     */
    private int getLast() {
        int current = start;
        int previous = -1;
        while (current != -1) {
            previous = current;
            current = items[current].next;
        }
        return previous;
    }

    /**
     * Метод для поиска позиции, предшествующей указанной.
     * Если позиция является головой списка или не найдена, возвращается -1.
     * @param position Позиция, для которой нужно найти предыдущую.
     * @return Позиция, предшествующая указанной, или -1, если её нет.
     */
    private int prevPosition(Position position) {
        int current = start;
        int prev = -1;
        while (current != -1) {
            if (position.index == current) {
                return prev;
            }
            prev = current;
            current = items[current].next;
        }
        return -1;
    }

    /**
     * Метод для получения позиции конца списка (по сути, возвращает -1).
     * @return Позиция конца списка.
     */
    public Position end() {
        return new Position(-1);
    }

    /**
     * Метод для вставки элемента в список на указанной позиции.
     * @param element Элемент, который нужно вставить.
     * @param position Позиция, перед которой нужно вставить элемент.
     */
    public void insert(ListElement element, Position position) {

        // Если список пуст и вставка идёт в конец
        if (position.index == -1) {
            if (start == -1) {
                start = space;
                int newSpace = items[space].next;
                items[start].element = element;
                items[start].next = -1;
                space = newSpace;
                return;
            }
            // Вставка в конец списка
            int last = getLast();
            int newItemIndex = space;
            space = items[space].next;

            items[newItemIndex].element = element;
            items[newItemIndex].next = -1;
            items[last].next = newItemIndex;
            return;
        }

        // Вставка в начало списка
        if (position.index == start) { // отедльно рассматриваем голову, тк у головы нет предыдущего
            int newItemIndex = space;
            space = items[space].next;

            items[newItemIndex].element = element;
            items[newItemIndex].next = start;
            start = newItemIndex;
            return;
        }

        // Вставка в середину списка
        int prev = prevPosition(position);
        if (prev != -1) {
            int newItemIndex = space;
            space = items[space].next;

            items[newItemIndex].element = element;
            items[newItemIndex].next = items[prev].next;
            items[prev].next = newItemIndex;
        }
    }

    /**
     * Метод для поиска элемента по объекту ListElement.
     * @param element Элемент, который нужно найти.
     * @return Позиция найденного элемента, или END(), если элемент не найден.
     */
    public Position locate(ListElement element) {
        int current = start;
        while (current != -1) {
            if (items[current].element.equals(element)) {
                return new Position(current);
            }
            current = items[current].next;
        }
        return new Position(-1); // Если элемент не найден
    }

    /**
     * Метод для получения элемента на указанной позиции.
     * @param position Позиция элемента, который нужно получить.
     * @return Элемент на указанной позиции.
     */
    public ListElement retrieve(Position position) {
        // Проверяем, что позиция находится в допустимых пределах
        if (position.index < 0 || position.index >= items.length) {
            throw new IllegalArgumentException("Invalid position.");
        }

        // Проверяем, является ли позиция началом списка
        if (position.index == start) {
            return items[start].element;
        }

        // Возвращаем элемент на указанной позиции
        return items[position.index].element;
    }

    /**
     * Метод для удаления элемента на указанной позиции.
     * @param position Позиция элемента, который нужно удалить.
     */
    public void delete(Position position) {
        // Вставка после последнего, если список пуст, ничего не делаем
        if (position.index == -1) {
            return;
        }

        // Удаление с начала списка
        if (position.index == start) {
            int temp = start; // Сохраняем текущий start
            start = items[start].next; // Обновляем start на следующий элемент
            items[temp].next = space; // Связываем освобожденный узел с space
            space = temp; // Обновляем space
            return;
        }

        // Поиск предыдущего узла
        int previous = prevPosition(position); // Используем метод prevPosition для поиска предыдущего узла
        if (previous != -1 && items[previous].next != -1) {
            int nodeToDelete = items[previous].next; // Узел, который нужно удалить
            items[previous].next = items[nodeToDelete].next; // Обновляем ссылку предыдущего узла
            items[nodeToDelete].next = space; // Связываем освобожденный узел с space
            space = nodeToDelete; // Обновляем space
        }
    }

    /**
     * Метод для получения первой позиции списка.
     * @return Первая позиция списка (голова списка), или END(), если список пуст.
     */
    public Position first() {
        return new Position(start);
    }

    /**
     * Метод для получения следующей позиции после указанной.
     * @param position Позиция текущего элемента.
     * @return Следующая позиция, или END(), если следующего элемента нет.
     * @throws IllegalArgumentException Если позиция недопустима.
     */
    public Position next(Position position) {
        // Проверка на недопустимую позицию
        if (position.index >= items.length) {
            throw new IllegalArgumentException("Invalid position.");
        }

        // Получаем индекс следующего элемента
        int nextIndex = items[position.index].next;

        // Возвращаем следующую позицию
        return new Position(nextIndex);
    }

    /**
     * Метод для получения позиции, предшествующей указанной.
     * @param position Позиция, для которой нужно найти предыдущую.
     * @return Позиция, предшествующая указанной, или END(), если предыдущей позиции нет.
     * @throws IllegalArgumentException Если позиция недопустима.
     */
    public Position previous(Position position) {
        // Проверка на недопустимую позицию
        if (prevPosition(position) == -1 || position.index >= items.length || position.index == start) {
            throw new IllegalArgumentException("Invalid position.");
        }

        // Используем метод prevPosition для поиска предыдущей позиции
        int previousIndex = prevPosition(position);
        return new Position(previousIndex);
    }

    /**
     * Метод для очистки списка, возвращая все элементы в список свободных ячеек.
     */
    public Position makeNull() {
        if (start == -1) {
            return new Position(-1); // Список уже пуст
        }

        // Обновляем конец списка
        items[getLast()].next = space; // Последний элемент теперь указывает на начало списка свободных ячеек
        space = start; // Обновляем начало списка свободных ячеек на start
        start = -1; // Очистка списка, делаем его пустым

        return new Position(-1); // Возвращаем END()
    }

    /**
     * Метод для печати всех элементов списка.
     */
    public void printList() {
        int current = start;
        while (current != -1) {
            System.out.println(items[current].element);
            current = items[current].next;
        }
    }
}