import java.util.List;

public interface AcervoOrcamentoRepository{
    List<Orcamento> getAll();
    boolean cadastraOrcamento(Orcamento orcamento);
    boolean removeOrcamento(Orcamento orcamento);
    boolean atualizaOrcamento(Orcamento orcamento);
    List<Orcamento> getData(String data); 
}