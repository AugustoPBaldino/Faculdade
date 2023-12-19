public class app2 {
    public static void inverteArray(int vet[]){
        inverteArray(vet, 0,vet.length-1);
    }
    
    public static void inverteArray(int vet[], int i, int j){
        if(i<j){
            int aux = vet[i];
            vet[i] = vet[j];
            vet[j] = aux;
            inverteArray(vet, i+1, j-1);
        }
    }

    public static int somaArray(int vet[]){
        int soma=0;
        for(int n: vet){
            soma+=n;
        }
        return soma;
    }

}
