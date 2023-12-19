
public class App {
    public static void main(String[] args) {
        BinarySearchTreeOfInteger b = new BinarySearchTreeOfInteger();
        b.add(15);
        b.add(23);
        b.add(9);
        b.add(11);
        b.add(2);
        b.add(20);
        b.add(38);
        System.out.println(b.positionsCentral());
        b.GeraDOT();
        
        System.out.println("b contem 38? " + b.contains(38));
        System.out.println("b contem 23? " + b.contains(23));
        System.out.println("b contem 55? " + b.contains(55));
        System.out.println("Elemento a esq de 23: " + b.getLeft(23));
        System.out.println("Elemento a esq de 2: " + b.getLeft(2));
        System.out.println("Total de folhas da arvore: " + b.countLeaves());

        b.remove(9);
        b.GeraDOT();
        b.remove(15);
        b.GeraDOT();
     }
   
}
