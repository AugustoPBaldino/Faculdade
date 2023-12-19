public class Tipos_Carro {
    public class Esportivo extends Carro {
        public Esportivo() {
            super("Esportivo", TipoCombustivel.GASOLINA, 6, 45);
        }
    }
    
    public class Utilitario extends Carro {
        public Utilitario() {
            super("Utilitario", TipoCombustivel.ALCOOL, 5, 70);
        }
    }
    
    
    
    public class SUVFlex extends Carro {
        public SUVFlex() {
            super("SUVFlex", TipoCombustivel.GASOLINA, 8, 65);
        }
    }
    
}
