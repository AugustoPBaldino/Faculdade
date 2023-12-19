
public class WordTree {
    private CharNode root;

    public WordTree() {
        this.root = new CharNode('.', "");
    }

    public void addChild(String word, String significado) {
        root.addChild(word, significado);
    }

    public void printNodes() {
        root.printNodes("");
    }

    public void printTree() {
        root.printTree("");
    }

    public boolean findAll(String word) {
        CharNode ptr = root.findPrefix(word);
        word = word.substring(0, word.length() - 1);

        if (ptr != null) {
            ptr.printTreeWithoutSignificado(word);
            return true;
        }

        return false;
    }

    public void findSingleWord(String word) {
        root.findSingleWord(word, "");
    }

}
