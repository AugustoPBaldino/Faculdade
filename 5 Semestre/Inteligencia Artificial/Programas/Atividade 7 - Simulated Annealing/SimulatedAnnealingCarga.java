import java.util.Arrays;
import java.util.Random;

public class SimulatedAnnealingCarga {
    
    private static final int NUM_VEICULOS = 3;
    private static final int[] CARGAS = {10, 20, 30, 40, 50, 60};
    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] melhorSolucao = simulatedAnnealing();
        System.out.println("Melhor distribuição: " + Arrays.toString(melhorSolucao));
    }

    public static int[] simulatedAnnealing() {
        double temperatura = 10000;
        double resfriamento = 0.003;

        // Solução inicial
        int[] solucaoAtual = gerarSolucaoInicial();
        int[] melhorSolucao = solucaoAtual.clone();
        double melhorCusto = calcularCusto(melhorSolucao);

        while (temperatura > 1) {
            // Gera uma solução vizinha
            int[] novaSolucao = gerarVizinho(solucaoAtual);

            // Calcula o custo das soluções
            double custoAtual = calcularCusto(solucaoAtual);
            double custoVizinho = calcularCusto(novaSolucao);

            // Decide se aceita a nova solução
            if (aceitarSolucao(custoAtual, custoVizinho, temperatura)) {
                solucaoAtual = novaSolucao;
            }

            // Atualiza a melhor solução encontrada
            if (calcularCusto(solucaoAtual) < melhorCusto) {
                melhorSolucao = solucaoAtual.clone();
                melhorCusto = calcularCusto(melhorSolucao);
            }

            // Reduz a temperatura
            temperatura *= 1 - resfriamento;
        }

        return melhorSolucao;
    }

    private static int[] gerarSolucaoInicial() {
        int[] solucao = new int[CARGAS.length];
        for (int i = 0; i < CARGAS.length; i++) {
            solucao[i] = random.nextInt(NUM_VEICULOS);
        }
        return solucao;
    }

    private static int[] gerarVizinho(int[] solucaoAtual) {
        int[] novaSolucao = solucaoAtual.clone();
        int indiceCarga = random.nextInt(CARGAS.length);
        novaSolucao[indiceCarga] = random.nextInt(NUM_VEICULOS);
        return novaSolucao;
    }

    private static double calcularCusto(int[] solucao) {
        int[] somaCargas = new int[NUM_VEICULOS];
        for (int i = 0; i < CARGAS.length; i++) {
            somaCargas[solucao[i]] += CARGAS[i];
        }

        int maxCarga = Arrays.stream(somaCargas).max().orElse(0);
        int minCarga = Arrays.stream(somaCargas).min().orElse(0);

        return maxCarga - minCarga;
    }

    private static boolean aceitarSolucao(double custoAtual, double custoVizinho, double temperatura) {
        if (custoVizinho < custoAtual) {
            return true;
        }
        double probabilidade = Math.exp((custoAtual - custoVizinho) / temperatura);
        return probabilidade > random.nextDouble();
    }
}
