public class app3 {
    
    //Não recursivo 
    public static boolean palindromo(String palavra){
        if(palavra == null){
            return false;
        }
        for(int i = 0 ,j = palavra.length() - 1; i>=j; i++, j--){
            if(palavra.charAt(i) != palavra.charAt(j)){
                return false;
            }
        }
        return true;
    }

    //Recursivo
    public static boolean isPalindrome(String str, int low, int high)
    {
        // caso base
        if (low >= high) {
            return true;
        }
 
        // retorna false se ocorrer incompatibilidade
        if (str.charAt(low) != str.charAt(high)) {
            return false;
        }
 
        // move para o próximo par
        return isPalindrome(str, low + 1, high - 1);
    }
}
