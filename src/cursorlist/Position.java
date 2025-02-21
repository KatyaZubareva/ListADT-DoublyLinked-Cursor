package cursorlist;

/**
 * Класс, представляющий позицию в списке.
 * Позиция хранит индекс, который указывает на местоположение элемента в массиве.
 */
public class Position {
    public int index;

    /**
     * Конструктор, инициализирующий позицию индексом.
     * @param index Индекс, который будет присвоен позиции.
     */
    public Position(int index) {
        this.index = index;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Position position = (Position) object;
        return (this.index == position.index);
    }
}
