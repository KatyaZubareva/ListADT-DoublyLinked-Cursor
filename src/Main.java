import doublylinkedlist.*;
// Замените на doublylinkedlist.* для работы с двусвязным списком.
// Замените на cursorlist.* для работы со списком на курсорах.

public class Main {
    public static void main(String[] args) {
        // Создание списка
        List list = new List();

        // Заполнение списка элементами
        list.insert(new ListElement("Tom", "address6"), list.end());
        list.insert(new ListElement("Tom", "address6"), list.end());
        list.insert(new ListElement("Tom", "address6"), list.end());
        list.insert(new ListElement("Tom", "address6"), list.end());

        System.out.println("====================================");
        System.out.println("Исходный список:");
        System.out.println("====================================");
        printList(list);

        // Удаление дубликатов
        removeDuplicates(list);

        System.out.println("\n====================================");
        System.out.println("Список после удаления дубликатов:");
        System.out.println("====================================");
        printList(list);
    }

    /**
     * Удаляет дубликаты из списка.
     * @param list Список, из которого нужно удалить дубликаты.
     */
    public static void removeDuplicates(List list) {
        Position currentPosition = list.first();

        while (!currentPosition.equals(list.end())) {
            Position nextPosition = list.next(currentPosition);

            while (!nextPosition.equals(list.end())) {
                if (list.retrieve(currentPosition).equals(list.retrieve(nextPosition))) {
                    list.delete(nextPosition);
                    nextPosition = list.next(currentPosition);
                } else {
                    nextPosition = list.next(nextPosition);
                }
            }
            currentPosition = list.next(currentPosition);
        }
    }

    /**
     * Выводит список в красивом формате.
     * @param list Список для вывода.
     */
    public static void printList(List list) {
        Position current = list.first();
        int index = 1;

        while (!current.equals(list.end())) {
            ListElement element = list.retrieve(current);
            System.out.printf("%d. Имя: %-10s | Адрес: %s%n", index, element.getName(), element.getAddress());
            current = list.next(current);
            index++;
        }

        if (index == 1) {
            System.out.println("Список пуст.");
        }
    }
}
