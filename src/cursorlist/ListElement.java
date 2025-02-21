package cursorlist;

/**
 * Класс, представляющий элемент списка.
 * Каждый элемент содержит имя и адрес, которые хранятся в массивах символов с ограничением по длине.
 * Имя обрезается до 20 символов, а адрес — до 50 символов.
 */
public class ListElement {
    private char[] name;    // Имя (максимум 20 символов)
    private char[] address; // Адрес (максимум 50 символов)

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
     * Метод для получения имени.
     * @return Строка, представляющая имя, обрезанное до 20 символов.
     */
    public String getName() {
        return new String(name).trim();
    }

    /**
     * Метод для получения адреса.
     * @return Строка, представляющая адрес, обрезанный до 50 символов.
     */
    public String getAddress() {
        return new String(address).trim();
    }

    /**
     * Переопределение метода toString для вывода информации об объекте.
     * @return Строка, представляющая объект ListElement в виде "Name: <имя>, Address: <адрес>".
     */
    @Override
    public String toString() {
        return "Name: " + getName() + ", Address: " + getAddress();
    }

    /**
     * Переопределение метода equals для сравнения элементов по имени и адресу.
     * @param obj Объект для сравнения.
     * @return true, если объекты равны по имени и адресу; иначе false.
     */
    @Override
    public boolean equals(Object obj) {
        // Проверяем, что объект не является null и что это тот же класс
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // Приводим объект к типу ListElement
        ListElement other = (ListElement) obj;

        // Сравниваем имена и адреса
        return getName().equals(other.getName()) && getAddress().equals(other.getAddress());
    }
}

