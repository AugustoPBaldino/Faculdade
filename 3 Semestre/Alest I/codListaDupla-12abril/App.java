
public class App {
    public static void main(String[] args) {
        DoubleLinkedListOfInteger l = new DoubleLinkedListOfInteger();
        l.add(10);
        l.add(20);
        l.add(30);
		l.add(40);
		l.add(50);
        l.reverse();
        System.out.print(l);
        
        System.out.println("size="+l.size());
        System.out.println("Elemento da posicao 2: " + l.get(2));
        System.out.println("Elemento removido da posicao 0: " + l.removeByIndex(0));
        System.out.println("Removeu 30? " + l.remove(30));
        System.out.println("Removeu 33? " + l.remove(33));
        System.out.println(l);

    }
}
