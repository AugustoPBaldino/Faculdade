

public class BinarySearchTreeOfInteger {

    private static final class Node {

        public Node father;
        public Node left;
        public Node right;
        public Integer element;

        public Node(Integer element) {
            father = null;
            left = null;
            right = null;
            this.element = element;
        }
    }

    // Atributos        
    private int count; //contagem do número de nodos
    private Node root; //referência para o nodo raiz

    public BinarySearchTreeOfInteger() {
        count = 0;
        root = null;
    }

    public void clear() {
        count = 0;
        root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return count;
    }

    public Integer getRoot() {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        return root.element;
    }

    public void add(Integer element) {
        root = add(root,element,null);
        count++;
    }
    private Node add(Node n, Integer element, Node father){
        if(n == null){ //insercao
            Node aux = new Node(element);
            aux.father = father;
            return aux;
        }
        //Sen]aon insere na sub arvore da direita ou da esquerda
        if(n.element.compareTo(element) < 0){
            //Insere na direita
            n.right = add(n.right,element,n);
        } else{
            //Insere na esquerda
        n.left = add(n.left,element,n);
        }
        return n;
    }

    public Integer getLeft(Integer element) {
        Node aux = searchNodeRef(element, root);
        if(aux==null){
            return null;
        }
        if(aux.left != null){
                return aux.left.element;
        } else
        return null;
    }

    public Integer getRight(Integer element) {
        return null;
    }

    public Integer getParent(Integer element) {
        return null;
    }

    public BinarySearchTreeOfInteger clone(){
        BinarySearchTreeOfInteger abpClone = new BinarySearchTreeOfInteger();
        clone(root,abpClone);
        return abpClone;
    }
    private void clone(Node n, BinarySearchTreeOfInteger b){
        if(n!=null){
            b.add(n.element);
            clone(n.left, b);
            clone(n.right,b);
        }
    }

    public LinkedListOfInteger positionsPre() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsPreAux(root, res);
        return res;
    }

    private void positionsPreAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            res.add(n.element); //Visita o nodo
            positionsPreAux(n.left, res); //Visita a subárvore da esquerda
            positionsPreAux(n.right, res); //Visita a subárvore da direita
        }
    }

    public LinkedListOfInteger positionsPos() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsPosAux(root, res);
        return res;
    }

    private void positionsPosAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            positionsPosAux(n.left, res); //Visita a subárvore da esquerda
            positionsPosAux(n.right, res); //Visita a subárvore da direita
            res.add(n.element); //Visita o nodo
        }
    }

    public LinkedListOfInteger positionsCentral() {
        LinkedListOfInteger res = new LinkedListOfInteger();
        positionsCentralAux(root, res);
        return res;
    }

    private void positionsCentralAux(Node n, LinkedListOfInteger res) {
        if (n != null) {
            positionsCentralAux(n.left, res); //Visita a subárvore da esquerda
            res.add(n.element); //Visita o nodo
            positionsCentralAux(n.right, res); //Visita a subárvore da direita
        }
    }

    public LinkedListOfInteger positionsWidth() {
        Queue<Node> fila = new Queue<>();
        Node atual = null;
        LinkedListOfInteger res = new LinkedListOfInteger();
        if (root != null) {
            fila.enqueue(root);
            while (!fila.isEmpty()) {
                atual = fila.dequeue();
                if (atual.left != null) {
                    fila.enqueue(atual.left);
                }
                if (atual.right != null) {
                    fila.enqueue(atual.right);
                }
                res.add(atual.element);
            }
        }
        return res;
    }

    public String strTraversalCentral() {
        return strTraversalCentral(root);
    }

    private String strTraversalCentral(Node n) {
        return null;
    }

    public boolean contains(Integer element) {
        Node n = searchNodeRef(element, root);
        return(n!=null);
    }

    private Node searchNodeRef(Integer element, Node target) {
        
        if(element == null || target ==null){
            return null;
        }
        int c = element.compareTo(target.element);
        if(c==0){
            return target;
        }
        if(c<0){
            return searchNodeRef(element, target.left);
        } else
            return searchNodeRef(element, target.right);

    }

    public boolean remove(Integer element) {

        return false;
    }

    public Integer getSmallest(){
        if(root != null){
            Node aux = root;
            while(aux.left != null){
                aux = aux.left;
            }
            return aux.element;
        } else{
            return null;
        }
    }

    private Node smallest(Node n){
        if(n ==null){
            return null;
        }
        if(n.left == null){
            return n;
        } else 
        return smallest(n.left);   
    }

    public int countLeaves(){
        return countLeaves(root);

    }

    private int countLeaves(Node n ){
        if(n ==null){
            return 0;
        }
        if(n.left == null && n.right==null){
            return 1;
        }else {
            return countLeaves(n.left) + countLeaves(n.right);
        }
    }
    

    public Integer set(Integer old, Integer element) { //Se não encontrar old, retorna null
        //Se rencontra retorna o old
        Integer elementRemovido = null;
        Node aux = searchNodeRef(old, root);

        if(aux != null){
            elementRemovido = old;
            remove(elementRemovido);
            add(old);
        }

        return elementRemovido;
    }

    public boolean isExternal(int element) {
        return false;
    }

    public boolean isInternal(int element) {
        return false;
    }
}
