import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Filosofo_Condições_Locks implements Runnable {
    private int id;
    private Lock mutex;
    private Condition[] condicoes;
    private boolean[] garfos; 

    public Filosofo_Condições_Locks(int id, Lock mutex, Condition[] condicoes, boolean[] garfos) {
        this.id = id;
        this.mutex = mutex;
        this.condicoes = condicoes;
        this.garfos = garfos;
    }

    public void run() {
        try {
            while (true) {
                pensar();
                pegarGarfos();
                comer();
                largarGarfos();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando...");
        Thread.sleep(1000);
    }

    private void pegarGarfos() throws InterruptedException {
        mutex.lock();
        try {
            System.out.println("Filósofo " + id + " tentando pegar os garfos.");

            int garfoEsquerda = id;
            int garfoDireita = (id + 1) % garfos.length;

            while (true) {
                if (garfos[garfoEsquerda] && garfos[garfoDireita]) {
                    garfos[garfoEsquerda] = false;
                    garfos[garfoDireita] = false;
                    break; 
                } else {
                    
                    if (!garfos[garfoEsquerda]) {
                        garfos[garfoEsquerda] = true;
                    }
                    if (!garfos[garfoDireita]) {
                        garfos[garfoDireita] = true;
                    }
                    condicoes[id].awaitNanos(100000); // 100 milissegundos
                }
            }
        } finally {
            mutex.unlock();
        }
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " está comendo...");
        Thread.sleep(2000);
    }

    private void largarGarfos() {
        mutex.lock();
        try {
            System.out.println("Filósofo " + id + " largou os garfos.");
            garfos[id] = true;
            garfos[(id + 1) % garfos.length] = true;
            condicoes[(id + 1) % garfos.length].signal(); 
            condicoes[id].signal(); 
        } finally {
            mutex.unlock();
        }
    }

    public static void main(String[] args) {
        int numFilosofos = 5;
        Lock mutex = new ReentrantLock();
        Condition[] condicoes = new Condition[numFilosofos];
        boolean[] garfos = new boolean[numFilosofos]; // Inicialmente, todos os garfos estão livres

        for (int i = 0; i < numFilosofos; i++) {
            condicoes[i] = mutex.newCondition();
        }

        Thread[] filosofos = new Thread[numFilosofos];

        for (int i = 0; i < numFilosofos; i++) {
            filosofos[i] = new Thread(new Filosofo_Condições_Locks(i, mutex, condicoes, garfos));
            filosofos[i].start();
        }
    }
}
