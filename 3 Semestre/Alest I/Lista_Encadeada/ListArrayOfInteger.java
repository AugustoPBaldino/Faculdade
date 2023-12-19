/**
 * Classe que implementa uma lista linear usando arranjo.
 * @author Isabel H. Manssour
 */

public class ListArrayOfInteger {

    // Atributos
    private static final int INITIAL_SIZE = 10;
    private Integer[] data;
    private int count;

    /**
     * Construtor da lista.
     */
    public ListArrayOfInteger() {
        this(INITIAL_SIZE);
    }

    /**
     * Construtor da lista.
     * @param tam tamanho inicial a ser alocado para data[]
     */
    public ListArrayOfInteger(int tam) {
        if (tam <= 0) {
            tam = INITIAL_SIZE;
        }
        data = new Integer[tam];
        count = 0;
    }

    /**
     * Esvazia a lista.
     */
    public void clear() { // O(1)
        data = new Integer[INITIAL_SIZE];
        count = 0;
    }

    /**
     * Retorna true se a lista nao contem elementos.
     * @return true se a lista nao contem elementos
     */
    public boolean isEmpty() { // O(1)
        return (count == 0);
    }

    /**
     * Retorna o numero de elementos armazenados na lista.
     * @return o numero de elementos da lista
     */
    public int size() { // O(1)
        return count;
    }

    /**
     * Adiciona um elemento ao final da lista.
     * @param element elemento a ser adicionado ao final da lista
     */
    public void add(Integer element) { // O(n)
        if (count == data.length) {
            setCapacity(data.length * 2);
        }
        data[count] = element;
        count++;
    }

    /**
     * Retorna o elemento de uma determinada posicao da lista.
     * @param index a posicao da lista
     * @return o elemento da posicao especificada
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public int get(int index) { // O(1)
        if ((index < 0) || (index >= count)) {
            throw new IndexOutOfBoundsException("Index = " + index);
        }
        return data[index];
    }
    
    @Override
    public String toString() { // O(n)
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < count; i++) {
            s.append(data[i]);
            if (i != (count - 1)) {
                s.append(",");
            }
        }
        s.append("\n");
        return s.toString();
    }

    private void setCapacity(int newCapacity) {
        if (newCapacity != data.length) {
            int min = 0;
            Integer[] newData = new Integer[newCapacity];
            if (data.length < newCapacity) {
                min = data.length;
            } else {
                min = newCapacity;
            }
            for (int i = 0; i < min; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
    }
	
    /**
     * Remove a primeira ocorrencia do elemento na lista, se estiver presente
     *
     * @param element o elemento a ser removido
     * @return true se a lista contem o elemento especificado
     */
    public boolean remove(Integer element) { // O(n)
        // Percorre a lista procurando por element
        for (int i=0; i<count; i++) {
            if (element.equals(data[i])) { // Se achou element
                // Faz a remocao
                for(int j=i; j<count-1; j++) {
                    data[j] = data[j+1];
                }
                data[count-1] = null; // opcional
                // Atualiza o count
                count--;
                // Returna verdadeiro
                return true;
            }
        }
        return false; // Se não encontrou, retorna falso
    }	
	
	
    ////////////////////////////////////////////
    // PARA FAZER JUNTO COM A PROFESSORA
    ////////////////////////////////////////////	
    
    /**
     * Substitui o elemento armazenado em uma determinada posicao da lista pelo
     * elemento passado por parametro, retornando o elemento que foi substituido.
     * @param index a posicao da lista
     * @param element o elemento a ser armazenado na lista
     * @return o elemento armazenado anteriormente na posicao da lista
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public int set(int index, Integer element) { // O(1)
        
        
         for(int i =0; i<=data.length; i++){
            if(data[i] == null){
                return 0;
            } else {
                data[index] = data.length;
            }
        }
         
        // primeiro verifica se o index é válido
        if(index < 0 || index >=size() ) {
            throw new IndexOutOfBoundsException();
        }
        Integer aux = data[index];
        data[index] = element;
        return aux;
    
    }    

    /**
     * Procura pelo elemento passado por parametro na lista e retorna true se a 
     * lista contem este elemento.
     * @param element o elemento a ser procurado
     * @return true se a lista contem o elemento passado por parametro
     */
    public boolean contains(Integer element) { // O(n)
        for(int i = 0; i<count; i++) {
            if(data[i].equals(element)) {
                return true;
            }
        }
        return false; 
    }
    
    
    
    ////////////////////////////////////////////
    // EXERCICIOS: Implemente os metodos 
    // conforme a documentacao javadoc e
    // indique a notacao O().
    ////////////////////////////////////////////
    
     /**
     * Insere um elemento em uma determinada posicao da lista
     *
     * @param index a posicao da lista onde o elemento sera inserido
     * @param element elemento a ser inserido
     * @throws IndexOutOfBoundsException se (index < 0 || index > size())
     */
    public void add(int index, Integer element) {
         //Primeiro verifica-se index é valido
         if(index < 0 || index >=size() ) {
            throw new IndexOutOfBoundsException(); //erro
        }
        // Tem espaço ?
        if(count == data.length){
            setCapacity(count*2);
        }
        //Verifica se eh pra inserir na ultima posição
        if(index == count){
            data[count] = element;
            count++;
        } else {
            //"empurra" os elementos para o lado para "Abrir espaço"
            for(int i = count; i>index; i--) {
                
            data[i] = data[i-1];
            }
            count--;
        }
    }
    /* 
     * public void add2 (int index, Integer element) {
        //primeiro verifica se index é válido
        if(index < 0 || index > size())
            throw new IndexOutOfSoundsExeption(); //erro
        
        // cria nodo
    
        Node n = new Node(element);
        if(index == 0) { //Iserção no iníco
            tail = n;
        } else{
            n.next = head; // Conecta o nodo na lista
            head = n; 
        }
        else if(index == count) { //Iserção no fim
            tail.next = n; // conecta o nodo na lista
            tail = n; //atualiza tail
        }

        else { //Iserção no meio
            Node ant = head;
            for(int i = 0; i< index-1;i++){ //Caminha até a posição index-1
                ant = ant.next;
            }
        }

    }
     * 
    */
    
	
	
    /**
     * Remove o elemento de uma determinada posicao da lista
     *
     * @param index a posição da lista
     * @return o elemento que foi removido da lista
     * @throws IndexOutOfBoundsException se (index < 0 || index >= size())
     */
    public int removeByIndex(int index) {
         // Verifica se index é valido
         if(index < 0 || index >=size() ) {
            throw new IndexOutOfBoundsException(); //erro
        }
         // guarda o elemento da posicao index
        Integer aux1 = data[index];
        
         // Faz o for para empurrar os elementos para a esquerda
         for(int j=index; j<count-1; j++) {
            data[j] = data[j+1];
        }
         //Atualiza o count 
         count--;
         
         //retorna o elemento removido
        
         return aux1;
    }
	
    
    /**
     * Retorna o indice da primeira ocorrencia do elemento na lista, ou -1 se a
     * lista nao contem o elemento
     *
     * @param element o elemento a ser buscado
     * @return o indice da primeira ocorrencia do elemento na lista, ou -1 se a
     * lista nao contem o elemento
     */
    public int indexOf(Integer element) {
        // apresente a implementacao
        return -1;
    }

    
    /**
     * Procura pelo elemento passado por parametro na lista e retorna true se a 
     * lista contem este elemento.
     * @param element o elemento a ser procurado
     * @return true se a lista contem o elemento passado por parametro
     */    
    public boolean containsRecursivo(Integer element) {
        return containsRecursivoAux(element, 0);
    }    
    private boolean containsRecursivoAux(Integer element, int i) {
        
        if(i==count){ //se chegou no fim da lista e não achou
            return false;
        }
        
        if(data[i].equals(element)){ //se achou
            return true;
        }


        return containsRecursivoAux(element,i+1); // continua a procura
    }

    
    /**
     * Retorna um arranjo que contem os elementos da lista original entre 
     * fromIndex (inclusivo) e toIndex (exclusivo).
     * @param fromIndex posicao a partir da qual os elementos serao inseridos no
     * arranjo a ser retornado
     * @param toIndex indica a posicao final dos elementos que devem ser inseridos
     * @return Um arranjo com um subconjunto dos elementos da lista.
     * @throws IndexOutOfBoundsException se (fromIndex < 0 || toIndex > size)
     * @throws IllegalArgumentException se (fromIndex > toIndex)
     */   
    public Integer[] subList (int fromIndex , int toIndex) {
        
        //verifica se os indices sao validos
        
        if(fromIndex < 0 || toIndex > size()) {
            throw new IndexOutOfBoundsException();
        }
        if(fromIndex > toIndex) {
            throw new IllegalArgumentException();
        }
        
        //cria o array a ser retornado
        
        Integer subLista[] = new Integer[toIndex - fromIndex];

        //colocar os elementos
        int j=0;
        for(int i= fromIndex; i<toIndex; i++) {
            subLista[j] = data[i];
            j++;
        }
        return subLista;
    }

    public Integer[] reverse(){
        Integer listareversa[] = new Integer[count];
        for (int i=1; i<=count; i++){
        listareversa[i-1]=data[count-i];
        }
        return listareversa;
        }
    public int countOccurrences(Integer element){

     int contador = 0;
            
            for(int i=0; i<count; i++) {
            
            if(data[i].equals(element))
            contador++;
            
            }
            return contador;
            }
            
            
}

