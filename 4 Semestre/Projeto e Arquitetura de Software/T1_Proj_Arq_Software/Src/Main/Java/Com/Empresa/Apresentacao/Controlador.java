package Src.Main.Java.Com.Empresa.Apresentacao;

import java.util.Scanner;

public class Controlador {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Sistema de Cálculo de Custo de Transporte de Encomendas!");
        System.out.println("Por favor, insira o CEP de origem (cidade de origem):");
        String origem = scanner.nextLine();

        System.out.println("Agora, insira o CEP de destino (cidade de destino):");
        String destino = scanner.nextLine();

        System.out.println("Insira o peso da encomenda em gramas:");
        int pesoGramas = scanner.nextInt();

        // Chame a camada de aplicação para calcular o custo
        double custo = CamadaAplicacao.calcularCustoTransporte(origem, destino, pesoGramas);

        System.out.println("O custo estimado de transporte é: R$" + custo);

        scanner.close();
    }
}

