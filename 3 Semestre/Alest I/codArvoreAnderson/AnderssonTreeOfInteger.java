
public class AnderssonTreeOfInteger {

    private static final class Node {

        public Node left;
        public Node right;
        public Integer element;
        public Integer level;

        public Node(Integer element, Integer level) {
            this.element = element;
            this.level = level;
            left = nil;
            right = nil;
        }
    }

    // Atributos
    private Node root;
    private static Node nil;
    private int count;

    public AnderssonTreeOfInteger() {
        count = 0;
        nil = new Node(0,0);
        root = nil;
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    public int size() {
        return count;
    }

    public void clear() {
        root = null;
        count = 0;
    }

    public int getRoot() {
        if (count == 0) {
            throw new NullPointerException();
        }
        return root.element;
    }

    private Node skew(Node root) {
        if (root.level != 0) {
            if (root.left.level.equals(root.level)) {
                Node save = root;
                root = root.left;
                save.left = root.right;
                root.right = save;
            }
            root.right = skew(root.right);
        }
        return root;
    }

    private Node split(Node root) {
        if (root.right != nil) {
            if (root.right.right.level.equals(root.level)) {//&& root.level != 0) {
                Node save = root;
                root = root.right;
                save.right = root.left;
                root.left = save;
                ++root.level;
                root.right = split(root.right);
            }
        }
        return root;
    }

    public void add(Integer element) {
        root = add(root, element);
    }
    private Node add(Node root, Integer element) {
        if (root == nil) {
            root = new Node(element, 1);
        } else {
            if (root.element < element) {
                root.right = add(root.right, element);
            } else {
                root.left = add(root.left, element);
            }
            root = skew(root);
            root = split(root);
        }
        return root;
    }

    public void remove(Integer element) {
        root = remove(root, element);
    }
    private Node remove(Node root, Integer element) {
        if (root != nil) {
            if (element.equals(root.element)) {
                if (root.left != nil && root.right != nil) {
                    Node heir = root.left;

                    while (heir.right != nil) {
                        heir = heir.right;
                    }

                    root.element = heir.element;
                    root.left = remove(root.left, root.element);
                } else {
                    if (root.left == nil) {
                        root = root.right;
                    } else {
                        root = root.left;
                    }
                }
            } else {
                if (root.element < element) {
                    root.right = remove(root.right, element);
                } else {
                    root.left = remove(root.left, element);
                }
            }
        }

        
        if (root != nil) {// (root.left != nil && root.right != nil) {
            if (root.left.level < root.level - 1 || root.right.level < root.level - 1) {
                if (root.right.level > --root.level) {
                    root.right.level = root.level;
                }
                root = skew(root);
                root = split(root);
            }
        }

        return root;
    }
    
    public int height() {        
        if (root == nil )
            return 0;
        else if (root.left==nil && root.right==nil)
            return 0;
        else
            return height(root);
    }    
    private int height(Node n) {       
        if ( n.left == nil && n.right == nil ) {
            return 0;
        }
        else {
            int h=0;
            if ( n.left != nil )
                h = Math.max(h, height(n.left));
            if ( n.right != nil )
                h = Math.max(h, height(n.right));
            return 1+h;
        }
    }  
    
    public LinkedListOfInteger positionsPre() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsPreAux(root, res);
        return res;
    }

    private void positionsPreAux(Node n, LinkedListOfInteger res) {
        if (n != nil) {
            res.add(n.element); //Visita o nodo
            positionsPreAux(n.left, res); //Visita a sub�rvore da esquerda
            positionsPreAux(n.right, res); //Visita a sub�rvore da direita
        }

    }

    public LinkedListOfInteger positionsPos() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsPosAux(root, res);
        return res;
    }

    private void positionsPosAux(Node n, LinkedListOfInteger res) {
        if (n != nil) {
            positionsPosAux(n.left, res); //Visita a sub�rvore da esquerda
            positionsPosAux(n.right, res); //Visita a sub�rvore da direita
            res.add(n.element); //Visita o nodo
        }
    }

    public LinkedListOfInteger positionsCentral() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsCentralAux(root, res);
        return res;
    }

    private void positionsCentralAux(Node n, LinkedListOfInteger res) {
        if (n != nil) {
            positionsCentralAux(n.left, res); //Visita a sub�rvore da esquerda
            res.add(n.element); //Visita o nodo
            positionsCentralAux(n.right, res); //Visita a sub�rvore da direita
        }
    }

    public LinkedListOfInteger positionsWidth() {
        Queue<Node> fila = new Queue<>();
        Node atual = null;
        LinkedListOfInteger res = new LinkedListOfInteger();
        if (root != nil) {
            fila.enqueue(root);
            while (!fila.isEmpty()) {
                atual = fila.dequeue();
                if (atual.left != nil) {
                    fila.enqueue(atual.left);
                }
                if (atual.right != nil) {
                    fila.enqueue(atual.right);
                }
                res.add(atual.element);
            }
        }
        return res;
    }    
    
    private void GeraConexoesDOT(Node nodo) {
        if (nodo == null) {
            return;
        }

        GeraConexoesDOT(nodo.left);
        //   "nodeA":esq -> "nodeB" [color="0.650 0.700 0.700"]
        if (nodo.left != null) {
            System.out.println("\"node" + nodo.element + "\":esq -> \"node" + nodo.left.element + "\" " + "\n");
        }

        GeraConexoesDOT(nodo.right);
        //   "nodeA":dir -> "nodeB";
        if (nodo.right != null) {
            System.out.println("\"node" + nodo.element + "\":dir -> \"node" + nodo.right.element + "\" " + "\n");
        }
        //"[label = " << nodo->hDir << "]" <<endl;
    }

    private void GeraNodosDOT(Node nodo) {
        if (nodo == null) {
            return;
        }
        GeraNodosDOT(nodo.left);
        //node10[label = "<esq> | 10 | <dir> "];
        System.out.println("node" + nodo.element + "[label = \"<esq> | " + nodo.element + " | <dir> \"]" + "\n");
        GeraNodosDOT(nodo.right);
    }

    public void GeraConexoesDOT() {
        GeraConexoesDOT(root);
    }

    public void GeraNodosDOT() {
        GeraNodosDOT(root);
    }

    // Gera uma saida no formato DOT
    // Esta saida pode ser visualizada no GraphViz
    // Versoes online do GraphViz pode ser encontradas em
    // http://www.webgraphviz.com/
    // http://viz-js.com/
    // https://dreampuf.github.io/GraphvizOnline 
    public void GeraDOT() {
        System.out.println("digraph g { \nnode [shape = record,height=.1];\n" + "\n");

        GeraNodosDOT();
        System.out.println("");
        GeraConexoesDOT(root);
        System.out.println("}" + "\n");
    }    
}
