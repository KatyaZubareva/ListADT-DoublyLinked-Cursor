# ListADT-DoublyLinkedList-Cursors

This repository contains the implementation of a List Abstract Data Type (ADT) using two different representations: doubly linked lists and cursor-based lists. The main goal is to create a modular design where the main method can be used with either representation by simply changing the imported package.

## Features

- **Doubly Linked List Representation**
- **Cursor-Based List Representation**
- Operations supported:
  - Insert
  - Delete
  - Locate
  - Retrieve
  - Next
  - Previous
  - Make Null
  - First
  - End
  - Print List
- **Main Method**: Removes duplicates from the list using the List ADT, working with all representations from both Lab Assignment 1 and Lab Assignment 2.

## Project Structure

The project is divided into separate packages for each representation of the List ADT. Each package contains the necessary classes and methods to support the operations listed above.

- **doublylinkedlist:** Implements the List ADT using doubly linked lists.
- **cursors:** Implements the List ADT using cursor-based lists.
- **main:** Contains the `Main` class to test the operations.

## How to Use

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/ListADT-DoublyLinkedList-Cursors.git
    ```
2. Navigate to the project directory:
    ```bash
    cd ListADT-DoublyLinkedList-Cursors
    ```
3. To test the doubly linked list implementation, ensure the `doublylinkedlist` package is imported in the `Main` class.
4. To test the cursor-based list implementation, switch the import in the `Main` class to the `cursors` package.

## Example Usage

```java
import doublylinkedlist.List; // or cursors.List

public class Main {
    public static void main(String[] args) {
        List list = new List();

        list.insert(1, list.end());
        list.insert(2, list.end());
        list.insert(3, list.end());

        list.printList(); // Output: 1, 2, 3

        // Remove duplicates
        removeDuplicates(list);

        list.printList(); // Output after duplicates removed
    }
}
