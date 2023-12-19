package Pilhas_Alest;

public class app {
    public static void main(String args[]){
        linkedStack pilha = new linkedStack();
        pilha.push(10);
        pilha.push(20);
        pilha.push(30);
        pilha.push(40);
        int tam = pilha.size();

        pilha.getClone(pilha);
        System.out.println(pilha);

    }
}
