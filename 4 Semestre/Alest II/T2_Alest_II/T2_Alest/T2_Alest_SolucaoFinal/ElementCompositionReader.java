import java.io.*;

public class ElementCompositionReader {
    public static void main(String[] args) throws IOException {
        Graph graph = new Graph();
        String elementL = "";
        BufferedReader br = new BufferedReader(new FileReader(".\\casos_t2_2023_2\\casoj240.txt"));
        String line;
        long start = System.currentTimeMillis();
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" -> ");
            String[] lhs = parts[0].split(" ");
            String[] rhs = parts[1].split(" ");
            for(int i=0; i<lhs.length ; i=i+2){
            elementL = lhs[i]+" "+lhs[i+1];}
            String elementR = rhs[1];
            //System.out.println("ReaderR:"+elementR);
            for(int i=0; i<lhs.length ; i=i+2){
            elementL = lhs[i]+" "+lhs[i+1];
            graph.addEdge(elementL, elementR);
            //System.out.println("ReaderL:"+elementL);
            }
            elementL = "";
        }
        br.close();

        
        graph.calculateHydrogen();
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Tempo de execução: " + elapsed + " milissegundos");

  
       

    }
}