package doublylinkedlist;

/**
 * Класс для представления позиции в двусвязном списке.
 */
public class Position {
    public Node index; // Узел, связанный с этой позицией

    /**
     * Конструктор, инициализирующий позицию с указанным узлом.
     * @param element Узел, связанный с позицией.
     */
    public Position(Node element) {
        this.index = element;
    }

    /**
     * Метод для получения узла, связанного с этой позицией.
     * @return Узел, связанный с этой позицией.
     */
    public Node getIndex() {
        return index;
    }

    /**
     * Метод для сравнения двух объектов Position.
     * @param object Объект, с которым сравнивается текущий объект.
     * @return true, если объекты равны; false в противном случае.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true; // Проверка на ссылочную равность
        if (object == null || getClass() != object.getClass()) return false; // Проверка типа
        Position position = (Position) object;
        return this.index == position.index; // Сравнение узлов
    }
}



