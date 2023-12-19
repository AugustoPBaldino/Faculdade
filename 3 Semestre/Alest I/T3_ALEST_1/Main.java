import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void addPalavras(WordTree t) {
        List<Palavra> lista = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("dicionario.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String pal = parts[0];
                String sig = parts[1];
                Palavra p = new Palavra(pal, sig);
                lista.add(p);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        for (Palavra palavra : lista) {
            t.addChild(palavra.getPalavra(), palavra.getSignificado());
        }
    }

    public static void main(String[] args) {
        WordTree t = new WordTree();
        addPalavras(t);
        Menu(t);
    }

    public static String menuSelect() {
        StringBuilder sb = new StringBuilder();
        sb.append("====================================================================\n");
        sb.append(" 1 para imprimir os nodos da árvore\n");
        sb.append(" 2 para imprimir todas as palavras\n");
        sb.append(" 3 para pesquisar um prefixo\n");
        sb.append("\n");
        sb.append(" 9 para encerrar a aplicação\n");
        sb.append("====================================================================\n");
        return sb.toString();
    }

    public static String capitalizeFirstLetter(String str) {
        if (str.isEmpty()) {
            return str;
        }

        String result = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        return result;
    }

    public static void prefix(WordTree t) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o prefixo:");
        String prefixo = scanner.nextLine();
        prefixo = capitalizeFirstLetter(prefixo);
        System.out.println();

        boolean temPrefixo = t.findAll(prefixo);

        if (temPrefixo) {
            System.out.println("Entre as palavras acima, qual palavra você gostaria de saber o significado:");
            prefixo = scanner.nextLine();
            prefixo = capitalizeFirstLetter(prefixo);
            System.out.println();
            t.findSingleWord(prefixo);
        }

    }

    public static void Menu(WordTree t) {
        Scanner scanner = new Scanner(System.in);
        int numSelect;

        do {
            System.out.println(menuSelect());
            numSelect = scanner.nextInt();

            switch (numSelect) {
                case 1:
                    t.printNodes();
                    break;
                case 2:
                    t.printTree();
                    break;
                case 3:
                    prefix(t);
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (numSelect != 0);

        scanner.close();
    }
}
