package Pilhas_Alest;

import java.util.EmptyStackException;

public class linkedStack {
    private class Node {
        public Integer element;
        public Node next;
        public Node(Integer e){
            element = e;
        }
    }
    //atributos
    private Node topo;
    private int count;

    public linkedStack(){
        topo = null;
        count = 0;
    }

    //insere elemento recebido por parametro no topo da pilha
    public void push (Integer element){ // O(1)
        //primeiro cria o nodo
        Node n = new Node(element);
        if(count>0) { // se a pilha não está vazia
            n.next = topo; //faz o encadeamento
        }
        topo=n;
        count++;
    }
    // remove o elemento da pilha
    //retorna o elemento removido
    public Integer pop(){ //O(1)
        if(count== 0){
            throw new EmptyStackException();
        }
        Integer aux = topo.element;
        //faz a remoção
        topo = topo.next;
        count--;
        //retorna o elemento removido
        return aux;
    }

    //retorna o elemento do topo da pilha
    public Integer top(){
        if(count== 0){
            throw new EmptyStackException();
        }
        return topo.element;
    }

    //esvazia a pilha
    public void clear(){
        topo=null;
        count=0;
    }
    //retorna o número de elementos armazenados da pilha
    public int size(){
        return count;
    }

    
    //retorna true se a pilha esta vazua e false caso contrario
    public boolean isEmpty(){
        return count==0;
    }

    public linkedStack getClone(linkedStack p){
        linkedStack p2 = new linkedStack();
        p2 = p;
        return p2;
    }
}
