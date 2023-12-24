public class Encomenda {
    private Cidade cidadeOrigem;
    private Cidade cidadeDestino;
    private int peso;

    public Encomenda(Cidade cidadeOrigem, Cidade cidadeDestino, int peso) {
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.peso = peso;
    }

    public Cidade getCidadeOrigem() {
        return cidadeOrigem;
    }

    public Cidade getCidadeDestino() {
        return cidadeDestino;
    }

    public int getPeso() {
        return peso;
    }
    
}
