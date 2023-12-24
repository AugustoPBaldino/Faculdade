import java.time.LocalDateTime;
import java.util.List;

public class RegistroOrcamento {
    private int identificador;
    private LocalDateTime dataHora;
    private Encomenda encomenda;
    private double custoBasico;
    private double custoAdicional;
    private double valorImpostos;
    private double desconto;
    private double valorFinal;

    public RegistroOrcamento(int identificador, Encomenda encomenda, double custoBasico,
                             double custoAdicional, double valorImpostos, double desconto, double valorFinal) {
        this.identificador = identificador;
        this.dataHora = LocalDateTime.now();
        this.encomenda = encomenda;
        this.custoBasico = custoBasico;
        this.custoAdicional = custoAdicional;
        this.valorImpostos = valorImpostos;
        this.desconto = desconto;
        this.valorFinal = valorFinal;
    }

    public int getIdentificador() {
        return this.identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public LocalDateTime getDataHora() {
        return this.dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Encomenda getEncomenda() {
        return this.encomenda;
    }

    public void setEncomenda(Encomenda encomenda) {
        this.encomenda = encomenda;
    }

    public double getCustoBasico() {
        return this.custoBasico;
    }

    public void setCustoBasico(double custoBasico) {
        this.custoBasico = custoBasico;
    }

    public double getCustoAdicional() {
        return this.custoAdicional;
    }

    public void setCustoAdicional(double custoAdicional) {
        this.custoAdicional = custoAdicional;
    }

    public double getValorImpostos() {
        return this.valorImpostos;
    }

    public void setValorImpostos(double valorImpostos) {
        this.valorImpostos = valorImpostos;
    }

    public double getDesconto() {
        return this.desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorFinal() {
        return this.valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    // Métodos getters para acessar os campos.

    public List<RegistroOrcamento> getOrcamentosPorData(LocalDateTime data) {
    // Implemente a lógica para recuperar os orçamentos fornecidos em uma determinada data.
    // Você pode criar uma lista de orçamentos e filtrá-la com base na data fornecida.
}

}
