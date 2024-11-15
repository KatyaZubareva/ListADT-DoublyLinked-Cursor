package doublylinkedlist;

/**
 * Класс для представления позиции в двусвязном списке.
 */
public class Position {
    private Node element; // Узел, связанный с этой позицией

    /**
     * Конструктор, инициализирующий позицию с указанным узлом.
     * @param element Узел, связанный с позицией.
     */
    public Position(Node element) {
        this.element = element;
    }

    /**
     * Метод для получения узла, связанного с позицией.
     * @return Узел, связанный с позицией.
     */
    public Node getElement() {
        return element;
    }

    /**
     * Метод для установки нового узла для данной позиции.
     * @param element Новый узел, который будет связан с позицией.
     */
    public void setElement(Node element) {
        this.element = element;
    }
}


