import java.util.ArrayList;
import java.util.List;

public class CalculadoraCusto {

    private Encomenda encomenda;
    private AcervoCidades acervoCidades;

    public CalculadoraCusto(Encomenda encomenda, AcervoCidades acervoCidades){
        this.encomenda = encomenda;
        this.acervoCidades = acervoCidades;
        calcularCusto();
    }
    
    public double calcularCusto() {
        if(!verificaCidadeAtendida(encomenda.getCidadeDestino()) || !verificaCidadeAtendida(encomenda.getCidadeOrigem())){
            return 0;
        }

        double precoEmbalagem = calculaEmbalagem();
        double custoBasico = calculaCustoBasico();
        double custoTotal = precoEmbalagem + custoBasico;
        double custoImpostos = calculaImpostos(encomenda, custoTotal); 
        
        return custoTotal + custoImpostos;
    }

    public double calculaEmbalagem(){
        int peso = encomenda.getPeso();
        peso = peso/1000;
        double mult=0;
        double mult2 =0;
        if(peso==2) return 0;
        else if(peso>2){
            mult = peso-2;
            if(peso>11){
                mult2 = peso-12;
            }
        }
        return mult*10+mult2*15;
    }

    public double calculaCustoBasico(){
        String cepOrigem = encomenda.getCidadeOrigem().getCep();
        String cepDestino = encomenda.getCidadeDestino().getCep();
        if(cepOrigem == "4567890123"){
            return acervo.getCustoBasico(cepDestino);
        }
        Cidadeselse {
            return acervo.getCustoBasico(cepOrigem)
            +acervo.getCustoBasico(cepDestino);
        }

    }


    public boolean verificaCidadeAtendida(Cidade cidade) {
        String cep = cidade.getCep();
        if(acervoCidades.verificaCidadeAntendida(cep)) return true;
        return false;
    }

    public double calculaImpostos(Encomenda encomendacepOrigem, double custoTotal, Encomenda encomenda){
        String cepDestino = encomenda.getCidadeDestino().getCep();
        double impostoDest = encomenda.getCidadeDestino().getImpostoBase();
        double impostoOrig = encomenda.getCidadeOrigem().getImpostoBase();
        if(cepDestino == "4567890123"){
            return impostoDest;
        }
        else{
            return impostoOrig + acervoCidades.getPorId("4567890123").getImpostoBase();
        }
        double percentualImposto = getPercentualImposto(cepOrigem);
        return custoTotal * (percentualImposto / 100.0);
        
    }

    public double getPercentualImposto(String cepOrigem){
        String cidade = getCidadeByCEP(cepOrigem);

        if (cidade.equals("Porto Alegre")) {
            return 5.0;
        } else if (cidade.equals("Florianópolis")) {
            return 5.0; 
        } else if (cidade.equals("Curitiba")) {
            return 5.0; 
        } else if (cidade.equals("São Paulo")) {
            return 5.0; 
        } else {
            return 5.0; 
        }
        
    }

    private String getCidadeByCEP(String cep) {
        // Implemente a lógica real para mapear CEP para cidade.
        // Este é apenas um exemplo fictício. Substitua pelo mapeamento real.
        if (cep.equals("1234567890")) {
            return "Porto Alegre";
        } else if (cep.equals("2345678901")) {
            return "Florianópolis";
        } else if (cep.equals("3456789012")) {
            return "Curitiba";
        } else if (cep.equals("4567890123")) {
            return "São Paulo";
        } else {
            return "Cidade Desconhecida"; // Substitua por tratamento de erro real.
        }
    }

    public String getPromocaoVigente() {
        // Implemente a lógica para determinar a promoção vigente
        // Retorne o nome ou detalhes da promoção atual.
    
    }


}
