//Dominio

import java.util.List;

public interface AcervoCidadesRepository{
    List<Cidade> getAll();
    boolean cadastraCidade(Cidade cidade);
    boolean removeCidade(String id);
    boolean atualizaCidade(Cidade cidade);
    Cidade getPorId(String id);
    double getCustoBasico(String id);
    boolean verificaCidadeAntendida(String id);
}