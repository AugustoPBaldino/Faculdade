import java.util.*;
/*

Esta classe é usada para representar um nó na árvore de Huffman. Cada nó possui informações sobre 
sua frequência (representada por data), um símbolo associado (representado por symbol), e referências 
para seus filhos esquerdo (left) e direito (right).

 */
class HuffmanNode {
    int data;
    char symbol;
    HuffmanNode left;
    HuffmanNode right;

    HuffmanNode(int data, char symbol) {
        this.data = data;
        this.symbol = symbol;
        left = right = null;
    }
}

/*
 * Este método realiza uma travessia na árvore de Huffman e imprime os códigos associados a cada 
 * símbolo. Ele é chamado recursivamente para percorrer os nós esquerdo e direito, acrescentando "0" 
 * ao código para o nó esquerdo e "1" para o nó direito.


 * 
 */
public class HuffmanCoding {
    public static void printCodes(HuffmanNode root, String code) {
        if (root == null) {
            return;
        }
        if (root.symbol != '\0') {
            System.out.println(root.symbol + ": " + code);
        }
        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }
/*

Neste método main, estamos calculando os códigos de Huffman para uma sequência de entrada "abbcccddddeeeee".

1: Primeiro, criamos um frequencyMap para armazenar as frequências de cada símbolo na sequência.
2: Em seguida, usamos uma priorityQueue (fila de prioridade) para construir uma árvore de Huffman. 
Cada nó da fila de prioridade representa um símbolo com sua frequência.
3:Depois, utilizamos um loop para criar a árvore de Huffman. Em cada iteração, removemos os dois nós 
de menor frequência da fila de prioridade, combinamos-os em um novo nó e o adicionamos de volta à fila.
4:Finalmente, o nó raiz da árvore de Huffman é obtido a partir da fila de prioridade e o método 
printCodes é chamado para imprimir os códigos associados a cada símbolo.

*/
    public static void main(String[] args) {
        String input = "abbcccddddeeeee";
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.data));
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getValue(), entry.getKey()));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();
            HuffmanNode newNode = new HuffmanNode(left.data + right.data, '\0');
            newNode.left = left;
            newNode.right = right;
            priorityQueue.add(newNode);
        }

        HuffmanNode root = priorityQueue.poll();
        printCodes(root, "");
    }
}
