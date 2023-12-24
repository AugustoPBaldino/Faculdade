public class Cidade {
    private String nome;
    private double custoBasico;
    private String cep;
    private double impostoBase=0;

    public Cidade(String nome, double custoBasico, String cep) {
        this.nome = nome;
        this.custoBasico = custoBasico;
        this.cep = cep;
    }

    public Cidade(String nome, double custoBasico, String cep, double impostoBase) {
        this.nome = nome;
        this.custoBasico = custoBasico;
        this.cep = cep;
        this.impostoBase=impostoBase;
    }

    public String getNome() {
        return nome;
    }

    public double getCustoBasico() {
        return custoBasico;
    }

    public String getCep(){
        return cep;
    }

    public double getImpostoBase(){
        if(impostoBase==0) return getCustoBasico()*0.05;
        else return impostoBase;
    }
}
