

public class App {
    public static void main(String[] args) {
        ListArrayOfInteger lista = new ListArrayOfInteger();
        lista.add(60);
        lista.add(70);
        lista.add(80);
        lista.add(90);
        
        System.out.println(lista);
       
        System.out.println("Contém 70?" +lista.contains(70));
        System.out.println("Contém 15?" +lista.contains(15));
        System.out.println(lista);

        System.out.println("Contém 70?" +lista.containsRecursivo(70));
        System.out.println("Contém 15?" +lista.containsRecursivo(15));
        System.out.println(lista);

        Integer sublista[] = lista.subList(1, 4);
        System.out.println("COnteudo da sublista");
        for(Integer i : sublista) {
            System.out.println(i + " ");
        }
        lista.set(0, 25);
        System.out.println(lista);
    }
}
