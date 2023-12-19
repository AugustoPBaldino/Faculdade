package Fila_Alest;

public class QueueArray {
//artributos
private Integer[] fila;
private int count;
private int inicio;
private int fim;

public QueueArray() {
    fila = new Integer[30];
    count=0;
    inicio=0;
    fim=0;
}  
public void clear(){
    fila = new Integer[30];
    count=0;
    inicio=0;
    fim=0;
}

public boolean isEmpty(){
    return count==0;
}

public int size(){
    return count;
}

//insere elemento no final da fila
public void enqueue(Integer element){
    if(count == fila.length){
        throw new RuntimeException("FIla está cheia");
    }
    fila[fim] =element;
    count++;
    fim = (fim+1) % fila.length;
}

//remove e retorna o elemento e do inicio da fila 
public Integer dequeue(){
    if(count==0){
        throw new RuntimeException("FIla está vazia");
    }
    Integer aux = fila[inicio];
    count--;
    fila[inicio] = null;
    inicio = (inicio+1) % fila.length;
    return aux;
}

//retorna, mas não remove, o primeiro elemento da fila
public Integer head(){
    if(count==0){
        throw new RuntimeException("FIla está vazia");
    }
    return fila[inicio];
}
}
