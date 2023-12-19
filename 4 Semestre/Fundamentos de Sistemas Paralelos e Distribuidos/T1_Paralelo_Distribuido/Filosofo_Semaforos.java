import java.util.concurrent.Semaphore;

public class Filosofo_Semaforos implements Runnable {
    private int id;
    private Semaphore[] garfos;

    public Filosofo_Semaforos(int id, Semaphore[] garfos) {
        this.id = id;
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
        garfos[id].acquire(); 
        garfos[(id + 1) % garfos.length].acquire(); 
        System.out.println("Filósofo " + id + " pegou os garfos.");
    }

    private void comer() throws InterruptedException {
        System.out.println("Filósofo " + id + " está comendo...");
        Thread.sleep(2000); 
    }

    private void largarGarfos() {
        garfos[id].release(); 
        garfos[(id + 1) % garfos.length].release(); 
        System.out.println("Filósofo " + id + " largou os garfos.");
    }

    public static void main(String[] args) {
        int numFilosofos = 5;
        Semaphore[] garfos = new Semaphore[numFilosofos];

        for (int i = 0; i < numFilosofos; i++) {
            garfos[i] = new Semaphore(1);
        }

        Thread[] filosofos = new Thread[numFilosofos];

        for (int i = 0; i < numFilosofos; i++) {
            filosofos[i] = new Thread(new Filosofo_Semaforos(i, garfos));
            filosofos[i].start();
        }
    }
}
