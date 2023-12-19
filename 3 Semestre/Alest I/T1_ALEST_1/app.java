public class app {
    public static void main(String[] args) {
        
        algoritmos e = new algoritmos();
        
        for(int i=10; i<=300; i+=10){
            
            int nroOp = e.f5(i);
            System.out.println(i+";"+nroOp);
        }
}
}
