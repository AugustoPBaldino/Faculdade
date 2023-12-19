package Fila_Alest;

public class app {
    public static void main(String [] args){
        QueueArray fila = new QueueArray();

        fila.enqueue(1);
        fila.enqueue(2);
        fila.enqueue(3);
        while(!fila.isEmpty()){
            System.out.println(fila.dequeue());
        }
    }

}
