import cursorlist.*;

/**
 * Главный класс для демонстрации работы со списком и удаления дубликатов.
 */
public class Main {
    public static void main(String[] args) {
        List list = new List(10);

        list.INSERT(new ListElement("Алиса", "ул. Главная, 123"), list.END());
        list.INSERT(new ListElement("Дима", "ул. Кленовая, 456"), list.END());
        list.INSERT(new ListElement("Алиса", "ул. Главная, 123"), list.END());
        list.INSERT(new ListElement("Оля", "ул. Дубовая, 789"), list.END());

        System.out.println("Список после вставки элементов:");
        list.PRINTLIST();

        removeDuplicates(list);

        System.out.println("\nСписок после удаления дубликатов:");
        list.PRINTLIST();
    }

    /**
     * Метод для удаления дубликатов элементов в списке.
     * Проходит по каждому элементу и удаляет все последующие элементы с такими же данными.
     *
     * @param list Список, из которого необходимо удалить дубликаты.
     */
    private static void removeDuplicates(List list) {
        Position current = list.FIRST();  // Начинаем с первого элемента
        while (current != null) {
            ListElement currentElement = list.RETRIEVE(current);
            Position next = list.NEXT(current);  // Следующий элемент

            while (next != null) {
                ListElement nextElement = list.RETRIEVE(next);
                // Если нашли дубликат, удаляем его
                if (currentElement.getName().equals(nextElement.getName()) && currentElement.getAddress().equals(nextElement.getAddress())) {
                    list.DELETE(next);  // Удаляем дубликат
                    next = list.NEXT(current);  // Переходим к следующему элементу, чтобы избежать использования удаленной позиции
                } else {
                    next = list.NEXT(next);  // Иначе продолжаем проверку
                }
            }
            current = list.NEXT(current);  // Переходим к следующему элементу
        }
    }
}
