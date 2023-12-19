@Service
public class EstatisticasServiceImpl implements EstatisticasService {

    private final List<Livro> livros; // Você precisa injetar a lista de livros aqui

    @Autowired
    public EstatisticasServiceImpl(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public int contarObrasPorAutor(String autor) {
        return (int) livros.stream()
                .filter(livro -> livro.autor().equalsIgnoreCase(autor))
                .count();
    }

    @Override
    public int contarObrasMaisRecentes(int ano) {
        return (int) livros.stream()
                .filter(livro -> livro.ano() >= ano)
                .count();
    }

    @Override
    public double calcularMediaObrasPorAutor() {
        Map<String, Long> obrasPorAutor = livros.stream()
                .collect(Collectors.groupingBy(Livro::autor, Collectors.counting()));

        double totalAutores = obrasPorAutor.size();
        double totalObras = obrasPorAutor.values().stream().mapToLong(Long::intValue).sum();

        return totalObras / totalAutores;
    }
}


/*
 * EstatisticasServiceImpl:

Essa classe implementa a interface EstatisticasService. Ela contém a 
lógica real para calcular as estatísticas do acervo de livros com base nos 
métodos definidos na interface.Cada método na implementação realiza as 
operações de filtragem, contagem e cálculo necessárias para retornar as 
estatísticas desejadas.
 */