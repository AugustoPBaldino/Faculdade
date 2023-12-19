import java.util.*;
import java.math.BigInteger;

public class Graph {
    private Map<String, BigInteger> elementToHydrogen = new HashMap<>();
    private Map<String, List<String>> elementToNeighbors = new LinkedHashMap<>();

    public void addEdge(String elementL, String elementR) {
        if (elementL.contains("hidrogenio")) {
            BigInteger hydrogenCount = new BigInteger(elementL.substring(0, elementL.indexOf(" ")));
            elementToHydrogen.put(elementR, hydrogenCount);
        } else {
            // Se não, adiciona à mapa elementToNeighbors
            elementToNeighbors.computeIfAbsent(elementR, k -> new ArrayList<>()).add(elementL);
        }
    }

    public void calculateHydrogen() {
        for (Map.Entry<String, List<String>> entry : elementToNeighbors.entrySet()) {
            String element = entry.getKey();
            List<String> neighbors = entry.getValue();

            BigInteger hydrogenSum = BigInteger.ZERO;
            String neighborElement = "";

            if (elementToHydrogen.get(element) == null) {
                for (String neighbor : neighbors) {
                    int spaceIndex = neighbor.indexOf(" ");

                    if (spaceIndex != -1) {
                        // Se houver um espaço, extrai o número e o nome do elemento
                        int hydrogenNumber = Integer.parseInt(neighbor.substring(0, spaceIndex));
                        neighborElement = neighbor.substring(spaceIndex + 1);

                        if (elementToHydrogen.get(neighborElement) == null) {
                            calculateHydrogenForElement(neighborElement);
                        }

                        hydrogenSum = hydrogenSum.add(BigInteger.valueOf(hydrogenNumber).multiply(elementToHydrogen.get(neighborElement)));

                        

                    }
                }

                if (!element.equals("ouro")) {
                    //System.out.println(element + " -> " + hydrogenSum);
                    elementToHydrogen.put(element, hydrogenSum);
                } else {
                    System.out.println("final->" + element + " = " + hydrogenSum);
                }
            }
        }
    }

    public void calculateHydrogenForElement(String element) {
        List<String> neighbors = elementToNeighbors.get(element);

        if (neighbors == null) {
            System.out.println("Elemento não encontrado: " + element);
            return;
        }

        BigInteger hydrogenSum = BigInteger.ZERO;

        if (elementToHydrogen.get(element) == null) {
            for (String neighbor : neighbors) {
                int spaceIndex = neighbor.indexOf(" ");

                if (spaceIndex != -1) {
                    // Se houver um espaço, extrai o número e o nome do elemento
                    int hydrogenNumber = Integer.parseInt(neighbor.substring(0, spaceIndex));
                    String neighborElement = neighbor.substring(spaceIndex + 1);

                    if (elementToHydrogen.get(neighborElement) == null) {
                        calculateHydrogenForElement(neighborElement);
                    }
                    

                    hydrogenSum = hydrogenSum.add(BigInteger.valueOf(hydrogenNumber).multiply(elementToHydrogen.get(neighborElement)));
                }
            }

            if (!element.equals("ouro")) {
                //System.out.println(element + " -> " + hydrogenSum);
                elementToHydrogen.put(element, hydrogenSum);
            } else {
                System.out.println(element + " " + hydrogenSum);
            }
        }
    }

    
}
