//Dominio

import java.util.LinkedList;
import java.util.List;


public class AcervoCidades implements AcervoCidadesRepository {

    private List<Cidade> cidades;

    public AcervoCidades() {

        cidades = new LinkedList<>();

        cidades.add(new Cidade("Porto Alegre", 25.0 , "1234567890"));
        cidades.add(new Cidade("Florianópolis", 20.0 , "2345678901"));
        cidades.add(new Cidade("Curitiba", 15.0 , "3456789012" ));
        cidades.add(new Cidade("São Paulo", 10.0 , "4567890123"));

    }

    @Override
    public List<Cidade> getAll() {
        return cidades;
    }

    @Override
    public boolean cadastraCidade(Cidade cidade) {
        cidades.add(cidade);
        return true;
    }

    @Override
    public boolean removeCidade(String cep) {
        List<Cidade> tmp = cidades.stream()
                .filter(cidade -> cidade.getCep() == cep)
                .toList();
        return tmp.removeAll(tmp);
    }

    @Override
    public boolean atualizaCidade(Cidade cidade) {
        for(Cidade c : cidades){
            if(c.getCep().equalsIgnoreCase(cidade.getCep())){
                cidades.remove(c);
                cidades.add(cidade);
                return true;
            }
        }
        return false;
    }

    @Override
    public Cidade getPorId(String cep) {
        return cidades.stream()
        .filter(cidade -> cidade.getCep() == cep)
        .findFirst()
        .orElse(null);
    }

    @Override
    public double getCustoBasico(String cep) {
        for(Cidade c : cidades){
            if(c.getCep().equalsIgnoreCase(cep)){
                return c.getCustoBasico();
            }
        }
        return -1;
    }

    @Override
    public boolean verificaCidadeAntendida(String cep){
        for(Cidade c : cidades){
            if(c.getCep().equalsIgnoreCase(cep)){
                return true;
            }
        }
        return false;
    }

    

}