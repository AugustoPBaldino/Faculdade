public interface EstatisticasService {
    int contarObrasPorAutor(String autor);
    int contarObrasMaisRecentes(int ano);
    double calcularMediaObrasPorAutor();
}

/*
 * 
 * EstatisticasService:

Essa interface define os métodos que serão responsáveis por calcular as 
estatísticas do acervo de livros. Ela atua como um contrato que os 
serviços de estatísticas devem seguir. Os métodos definidos nesta 
interface são os que você listou: contarObrasPorAutor, contarObrasMaisRecentes 
e calcularMediaObrasPorAutor.
 */