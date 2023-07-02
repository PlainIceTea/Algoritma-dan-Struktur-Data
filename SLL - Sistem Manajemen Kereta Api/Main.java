import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String kereta ;
        boolean loop = false;
        rapihdhoho rd = new rapihdhoho();
        rd.inisialisasi();
        String namakereta = input.nextLine();
        while(!loop){
            kereta = input.nextLine();
            if (kereta.equalsIgnoreCase("cetak")){
                System.out.println(namakereta);
                rd.print();
                loop = true;
                break;
            }
            String[] arrOfStr = kereta.split(" ");
            String kata1 = arrOfStr[0];
            String kata2 = arrOfStr[1];
            if (kata1.equalsIgnoreCase("tambah")){                          
                rd.tambahGerbong(kata2);
            }else if(kata1.equalsIgnoreCase("lepas")){
                int idx = Integer.parseInt(kata2);
                rd.lepasGerbong(idx);
            }
        
            
        }
        
    }


    static class Node {
        String data;
        Node next;

        Node(String data){
            this.data = data;
        }
    }

    static class rapihdhoho{
        Node head;
        Node tail; 
        int size = 0;

        void inisialisasi(){
            head = null;
            tail = null;
        }

        void tambahGerbong(String data){
            if (tail == null) {
                tail = new Node(data);
                head = tail;
            } else {
                tail.next = new Node(data);
                tail = tail.next;
            }
            size++;
        }

       void lepasGerbong(int idx){
            if (idx == 0) {
                if (head == tail) {
                    head = null;
                    tail = null;
                }else head = head.next;
            }else{
                Node currNode = head;
                for (int i=0; i < idx - 1; i++){
                    currNode = currNode.next;
                }if (currNode.next == tail) {
                    currNode.next = null;
                    tail = currNode;}else{
                currNode.next = currNode.next.next;}
            }
        }
        
        
           
       void print(){
            Node currentNode = head;
            if(currentNode==null){
                System.out.println("");
            }else{
            System.out.print(currentNode.data);
            currentNode = currentNode.next;
            while(currentNode != null){
                System.out.print("-" +currentNode.data );
                currentNode = currentNode.next;
            }
        }
        }
        
    }

}
