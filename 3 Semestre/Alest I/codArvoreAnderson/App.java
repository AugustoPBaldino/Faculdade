
public class App {
    public static void main(String[] args) {
        AnderssonTreeOfInteger a = new AnderssonTreeOfInteger();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
        a.add(6);
        a.add(7);
        //a.remove(0);
        //a.remove(3);
        //a.remove(1);
        //a.remove(5);
        //a.remove(4);
        //a.remove(2);
        //a.remove(6);
        //System.out.println("Altura: " + a.height());
        System.out.println(a.positionsWidth().toString());
        System.out.println("------------");
        a.GeraDOT();
    }
}
