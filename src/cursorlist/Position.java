package cursorlist;

/**
 * Класс для представления позиции в списке.
 * Позиция указывает на индекс элемента в массиве узлов.
 */
public class Position {
    private int index; // Индекс элемента в массиве узлов

    /**
     * Конструктор для создания позиции.
     * @param index Индекс в массиве элементов.
     */
    public Position(int index) {
        this.index = index;
    }

    /**
     * Метод для получения индекса элемента на позиции.
     * @return Индекс элемента в массиве.
     */
    public int getIndex() {
        return index;
    }
}

