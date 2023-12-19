public class app1{

public static int potencia(int base, int expoente){
    if(expoente ==0){
        return 1;
    }
    int resultado = base;
    for(int i=expoente; i>1; i++){
        resultado = resultado + base;
    }
    return resultado;
}
public static int potenciaRec(int base, int expoente) {
    if(expoente == 0) {
        return 1;
    }
    return base * potenciaRec(base, expoente-1);
}
}


