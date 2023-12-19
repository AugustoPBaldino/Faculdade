
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

        System.out.println("b contém 38"+ b.contains(38));
        System.out.println("b contém 23"+ b.contains(23));
        System.out.println("b contém 55"+ b.contains(55));
        
     }
   
}
