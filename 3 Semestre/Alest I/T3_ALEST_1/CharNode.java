import java.util.ArrayList;
import java.util.List;

public class CharNode {
    private char character;
    private String significado;
    private CharNode father;
    private List<CharNode> subtrees;

    public CharNode(char element, String significado) {
        this.father = null;
        this.character = element;
        this.significado = significado;
        this.subtrees = new ArrayList<>();
    }

    public CharNode addChild(String word, String significado) {
        if (word.length() == 0) {
            return this.father;
        }

        char primeiraLetra = word.charAt(0);
        CharNode childNode = null;

        for (CharNode node : subtrees) {
            if (node.character == primeiraLetra) {
                childNode = node;
                break;
            }
        }

        if (childNode == null) {
            childNode = new CharNode(primeiraLetra, word.length() == 1 ? significado : "");
            childNode.father = this;
            subtrees.add(childNode);
        }

        return childNode.addChild(word.substring(1), significado);
    }

    public void printTree(String word) {
        if (!significado.isEmpty()) {
            System.out.println(word + this.character + " - significado: " + significado);
        }

        for (CharNode node : subtrees) {
            node.printTree(word + this.character); // Passa a palavra atualizada para a chamada recursiva
        }
    }

    public void printTreeWithoutSignificado(String word) {
        if (!significado.isEmpty()) {
            System.out.println(word + this.character);
        }

        for (CharNode node : subtrees) {
            node.printTreeWithoutSignificado(word + this.character);
        }
    }

    public void printNodes(String prefix) {

        String nodePrefix = prefix + this.character;

        if (!significado.isEmpty()) {
            System.out.println(nodePrefix + "- significado: " + significado);
        } else {
            System.out.println(nodePrefix);
        }

        for (CharNode node : subtrees) {
            node.printNodes(nodePrefix);
        }
    }

    public CharNode findPrefix(String word) {
        if (word.isEmpty()) {
            return this;
        }

        for (CharNode node : subtrees) {
            if (node.character == word.charAt(0)) {
                return node.findPrefix(word.substring(1));
            }
        }

        System.out.println("Prefixo não existe no dicionário");
        return null;
    }

    public void findSingleWord(String word, String conc) {
        if (word.isEmpty() && !significado.isEmpty()) {
            System.out.println(conc + ", significado: " + significado);
            return;
        }

        for (CharNode node : subtrees) {
            if (node.character == word.charAt(0)) {
                conc += word.charAt(0);
                node.findSingleWord(word.substring(1), conc);
                return;
            }
        }

        System.out.println("Palavra não encontrada");
    }

}
