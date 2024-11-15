package cursorlist;

/**
 * Класс для представления элемента списка.
 * Содержит информацию об элементе, включая его имя и адрес.
 */
public class ListElement {
    private String name;    // Имя элемента
    private String address; // Адрес элемента

    /**
     * Конструктор для создания элемента списка.
     * @param name Имя элемента.
     * @param address Адрес элемента.
     */
    public ListElement(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * Метод для получения имени элемента.
     * @return Имя элемента.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод для получения адреса элемента.
     * @return Адрес элемента.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Метод для представления элемента в виде строки.
     * @return Строка с именем и адресом элемента.
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Address: " + address;
    }
}

