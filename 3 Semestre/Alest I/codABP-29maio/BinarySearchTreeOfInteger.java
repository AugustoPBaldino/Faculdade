

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
        root = add(root, element, null);
        count ++;
    }
    private Node add(Node n, Integer element, Node father) {
        if (n == null) { // insere
            Node aux = new Node(element);
            aux.father = father;
            return aux;
        }
        // Senão, insere na subarvore da esq ou da dir
        if (n.element.compareTo(element) < 0) {
            n.right = add(n.right, element, n); // dir 
        }
        else {
            n.left = add(n.left, element, n); // esq
        }
        return n;
    }

    public void addNaoRecursivo(Integer element){
        //primeiro cria o nodo
        Node n = new Node(element);
        count++;

        if(root == null){ //Se a arvore estiver vazia
            root = n;
            n.father = null;
            return;
        }

        Node aux = root;
        boolean inserted = false;
        while(!inserted){
            if(aux.element.compareTo(element) > 0){
                if(aux.left == null){
                    n.father = aux;
                    aux.left = n;
                    inserted = true;
                }
                aux = aux.left;
            }
            else{
                if(aux.right == null){
                    n.father = aux;
                    aux.right = n;
                    inserted = true;
                }
                aux = aux.right;
            }
        }
    }

    public Integer getLeft(Integer element) {
        Node aux = searchNodeRef(element, root);
        if (aux == null)
            return null;
        if (aux.left != null)
            return aux.left.element;
        else
            return null;
    }

    public Integer getRight(Integer element) {
        return null;
    }

    public Integer getParent(Integer element) {
        return null;
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
        return (n != null);
    }

    private Node searchNodeRef(Integer element, Node target) {
        if (element == null || target == null) {
            return null;
        }
        int c = element.compareTo(target.element);
        if (c==0)
            return target;
        if (c<0)
            return searchNodeRef(element, target.left);
        else
            return searchNodeRef(element, target.right);
    }

    public boolean remove(Integer element) {
        if(element == null || root == null){
            return false;
        }

        Node aux = searchNodeRef(element, root);

        if(aux == null){ // Se não encontrou
            return false;
        }
        
        remove(aux); // chama o metodo que remove
        count--; // atualiza p count 
        return true;
    }

    private void remove(Node n){
        //Guarda a referencia para o pai do elemento removido
        Node father = n.father;

        if(n.left == null && n.right==null){ //remoção do nodo folha
            if(root ==n){ //Se remocao da raiz
                root = null;
                return;
            }
            if(father.left == n){ // verifica se está a esquerda do pai 
                father.left =null;
            } else //Ou está a direita
                father.right = null;
            }
            else if(n.left == null && n.right!=null){ //Remoção de um nodo com um unico filho a direita
                if(root ==n){
                    root = root.right;
                    root.father = n.right;
                    return;
                }
                if(father.left == n){ //ferifica se esta a esquerda  ou a direita do pai
                    father.left =n.right;
                } else
                    father.right = n.right;
                    n.right.father = father;
                
            } 
            else if(n.left != null && n.right==null){ //remoção deum nodo com um unico filho a direita
                if(root ==n){ //Se remocao da raiz
                    root = null;
                    return;
                }
                if(father.left == n){ // verifica se está a esquerda do pai 
                    father.left =n.right;
                } else //Ou está a direita
                    father.right = n.right;
                    n.left.father = father;
                
            } 
            
            else{ //remoção de um nodo com dois filhos 
                Node menor = smallest(n.right);
                n.element = menor.element;
                remove(menor);
            }
        }
    

    /**
     * Retorna o menor elemento armazenado na arvore.
     * @return 
     */
    public Integer getSmallest() {
        if (root != null) {
            Node aux = root;
            while(aux.left != null) {
                aux = aux.left;
            }
            return aux.element;
        }
        else {
            return null;
        }
    }
    
    private Node smallest(Node n) {
        if (n == null)
            return null;
        if (n.left == null)
            return n;
        return smallest(n.left);
    }

    /**
     * Retorna o total de folha da arvore.
     * @return 
     */
    public int countLeaves() {
        return countLeaves(root);
    }
    private int countLeaves(Node n) {
        if (n == null)
            return 0;
        if(n.left==null && n.right==null)
            return 1;
        return countLeaves(n.left) + countLeaves(n.right);
    }

    public Integer set(Integer old, Integer element) {
        return null;
    }

    public boolean isExternal(int element) {
        return false;
    }

    public boolean isInternal(int element) {
        return false;
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
