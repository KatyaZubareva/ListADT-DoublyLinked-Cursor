package doublylinkedlist;

/**
 * Класс для представления элемента списка.
 * Содержит информацию об элементе, включая его имя и адрес.
 */
public class ListElement {
    private char[] name;
    private char[] address;

    /**
     * Конструктор для создания объекта ListElement.
     * @param name Имя, которое будет обрезано до 20 символов, если оно длиннее.
     * @param address Адрес, который будет обрезан до 50 символов, если он длиннее.
     */
    public ListElement(String name, String address) {
        this.name = new char[20];
        this.address = new char[50];

        copyToArray(name, this.name);
        copyToArray(address, this.address);
    }

    /**
     * Метод для копирования строки в массив символов с ограничением по длине.
     * @param source Строка для копирования.
     * @param result Массив символов, в который копируется строка.
     */
    private void copyToArray(String source, char[] result) {
        int length = Math.min(source.length(), result.length);
        for (int i = 0; i < length; i++) {
            result[i] = source.charAt(i);
        }
    }

    /**
     * Метод для получения имени элемента.
     * @return Имя элемента.
     */
    public String getName() {
        return new String(name).trim();
    }

    /**
     * Метод для получения адреса элемента.
     * @return Адрес элемента.
     */
    public String getAddress() {
        return new String(address).trim();
    }

    /**
     * Метод для представления элемента в виде строки.
     * @return Строка с именем и адресом элемента.
     */
    @Override
    public String toString() {
        return "Name: " + getName() + ", Address: " + getAddress();
    }
}

