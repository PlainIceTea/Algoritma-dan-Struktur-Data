import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DoublyLinkedList dll = new DoublyLinkedList();
        boolean loop = false;
        while(!loop){
            String catat = input.nextLine();
            String[] rute = catat.split(" ");
            
            if(rute[0].equalsIgnoreCase("MASUK")){
                dll.insertTail(rute[1]);
            }else if(rute[0].equalsIgnoreCase("SISIP")){
                dll.insertAt(rute[1],Integer.parseInt(rute[2]));
            }else if(rute[0].equalsIgnoreCase("HAPUS")){
                dll.removeAt(Integer.parseInt(rute[1]));
            }else if(rute[0].equalsIgnoreCase("SIMPAN")){
                loop = true;    
            }   
        }
        loop = false;
            Node cetak = dll.head;
            System.out.println("Mode Perjalanan");
             
            while(!loop){
                String catat = input.nextLine();
            String[] arah = catat.split(" ");
            if(arah[0].equalsIgnoreCase("PERGI")){
                if(arah[1].equalsIgnoreCase("NEXT")){
                    if(cetak.data==dll.tail.data){
                        System.out.println("Tujuan tidak valid");
                        cetak = dll.tail;

                    }else{
                        cetak = cetak.next;
                        System.out.println("Sedang berada di " + cetak.data);
                    }
                }
                if(arah[1].equalsIgnoreCase("BEFORE")){
                    if(cetak.data==dll.head.data){
                        System.out.println("Tujuan tidak valid");
                        cetak = dll.head;
                    }else {
                        cetak = cetak.prev;
                        System.out.println("Sedang berada di " + cetak.data);
                    }
                
                }

            }else if(arah[0].equalsIgnoreCase("SELESAI")){
                
                loop = true;
            }
        }

            
        }
    }

    class DoublyLinkedList<T> {
        Node<T> head;
        Node<T> tail;

        
        void insertTail(T data){
            if (tail == null) {
                tail = new Node<T>(data);
                head = tail;
                
            } else {
                Node<T> input = new Node<T>(data);
                input.prev = tail;
                tail.next = input;
                tail = input;
                
            }
        }
        void insertAt(T data, int idx){
            Node<T> baruNode = new Node<T>(data);
            Node<T> a = head;
            if (idx == 0) {
                if (head == null) {
                    head = baruNode;
                    tail = head;
                }else {
                    
                    baruNode.next = head;
                    head.prev = baruNode;
                    head = baruNode;  
                }
            }
            else{
                for(int i=0 ; i< idx-1;i++){
                a = a.next;}
                Node<T> b = new Node<T>(data); 
                if(a.next==null){
                    insertTail(data);
                }
                else{
                b.prev = a;
                b.next = a.next;
                a.next.prev = b;
                a.next = b;
            }
        }
    }
        
    
        void removeAt(int idx){
        
        if (idx == 0) {
            if (head == tail) {
                head = null;
                tail = null;
            }else {
                head = head.next;
                head.prev = null;

            }
        } else {
            Node currNode = head;
            
            for (int i=0; currNode != null && i< idx - 1; i++){
                currNode = currNode.next;
            }
            if (currNode == null || currNode.next == null) {
                return;
            }
            else if (currNode.next == tail) {
                currNode.next = null;
                tail = currNode;
            }
            else {
                currNode.next = currNode.next.next;
                currNode.next.prev = currNode;
               
            }
            
        }
    }
}
        

 
        class Node<T> {
            T data;
            Node<T> next;
            Node<T> prev;
        
            Node(T data){
                 
                this.data = data;
            }
        }