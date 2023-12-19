public class Main {
    public static void main(String[] args) {
        Carro esportivo = new Esportivo();
        esportivo.abastece(TipoCombustivel.GASOLINA, 30);
        esportivo.viaja(100);
        System.out.println(esportivo.toString());

        Carro utilitario = new Utilitario();
        utilitario.abastece(TipoCombustivel.DIESEL, 50);
        utilitario.viaja(150);
        System.out.println(utilitario.toString());

    
    }
}
