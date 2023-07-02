import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CLL list = new CLL();
        while (true) {
            String data = input.nextLine();
            if (data.equals("EXIT")) {
                break;
            }
            String[] array = data.split(" ");
            switch (array[0]) {
                case "INSERT":
                    list.insertTail(array[1]);
                    break;
                case "MOVE":
                    int fromIndex = Integer.parseInt(array[1]);
                    int toIndex = Integer.parseInt(array[2]);
                    list.move(fromIndex, toIndex);
                    break;
                case "REMOVE":
                    int index = Integer.parseInt(array[1]);
                    list.removeAt(index);
                    break;
                case "PLAYAT":
                    int playindex = Integer.parseInt(array[1]);
                    list.playAt(playindex);
                    break;
                case "NEXT":
                    list.nextButton();
                    break;
                case "PREV":
                    list.PrevButton();
                    break;
            }

        }
    }

}

class CLL {
    Node head;
    Node tail;
    Node pointer;
    int size;
    
  void insertTail(String data) {
        Node newNode = new Node(data);
        if (size == 0) {
            head = newNode;
            tail = head;
            tail.next = head;
            tail.prev = head;
            head.next = tail;
            head.prev = tail;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
            tail.next = head;
            head.prev = tail;
        }
        size++;
    }

    
    void insertAt(String data, int index) {
        Node newNode = new Node(data);
        Node current = head;
        if (index == 0) {
            if (size == 0) {
                head = newNode;
                tail = head;
                tail.next = head;
                tail.prev = head;
                head.next = tail;
                head.prev = tail;

            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
                tail.next = head;
                head.prev = tail;

            }

        } else {
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            if (current == tail) {
                insertTail(data);
            } else {
                newNode.prev = current;
                newNode.next = current.next;
                current.next.prev = newNode;
                current.next = newNode;
            }
        }
        size++;
    }

    String removeAt(int index) {
        Node current = head;
        if (index == 0) {
            removeHead();
        } else {
            current = index(index);
            if (current == pointer) {
                pointer = pointer.next;
            }
            if (current == head) {
                head = head.next;
                head.prev = tail;
                tail.next = head;
                size--;
            }
            if (current == tail) {
                tail = tail.prev;
                tail.next = head;
                head.prev = tail;
                size--;
            } else {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                size--;
            }
        }
        return current.data;
    }

   
    void move(int fromIndex, int toIndex) {
        if (fromIndex == toIndex) {
            return;
        }
        if (size == 0) {
            return;
        }
            Node current = index(fromIndex);
            Node temp = index(toIndex);
            
        
        if (pointer == current) {
            pointer = pointer.next;
        }
        current.prev.next = current.next;
        current.next.prev = current.prev;

        if (temp == head) {
            head = current;
        }
        current.prev = temp.prev;
        current.next = temp;
        temp.prev.next = current;
        temp.prev = current;
    }  

    
            void remove(int index) {
                Node current = index(index);
                if (pointer == current) {
                    pointer = current.next;
                }
                if (current == head) {
                    head = current.next;
                    head.prev = tail;
                    tail.next = head;
                } else if (current == tail) {
                    tail = current.prev;
                    tail.next = head;
                    head.prev = tail;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
            }

    void removeHead() {
        if (head == tail) {
            head = null;
            tail = null;
            size--;
        } else {
            head = head.next;
            head.prev = tail;
            tail.next = head;
            size--;
        }

    }

    Node index(int index) {
        while (index < 0) {
            index += size;
        }
        while (index >= size) {
            index -= size;
        }
        if (size == 0) {
            return null;
        }
        int nilai = index;
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    void playAt(int index) {

   
        Node current = index(index);
        pointer = current;
        System.out.println("Sedang diputar: " + pointer.data);
    }

    public void nextButton() {
        if (pointer == null) {
            pointer = tail;
            pointer = pointer.next;
        } else {
            pointer = pointer.next;
        }
        System.out.println("Sedang diputar: " + pointer.data);
    }

    public void PrevButton() {
        if (pointer == null) {
            pointer = tail;
            pointer = pointer.next;
        } else {
            pointer = pointer.prev;
        }
        System.out.println("Sedang diputar: " + pointer.data);
    }
  

    class Node {
        String data;
        Node next;
        Node prev;

        Node(String data) {

            this.data = data;
        }
    }
}