public class algoritmos{
    // Algoritmo 1

    public int f1( int n ) { 

    int i, j, k, res = 0; 
    int cont_op = 0; 
    for(i = 1; i <= n+1; i += 1) { // n+1
        for(j = 1; j <= i*i; j += i+1 ) { // n 
            for(k = i/2; k <= n+j; k += 2 ) { // n+1 
                res = res + n-1; 
                cont_op++; 
            }
        }
    }
    return cont_op;
    }
    
    // Algoritmo 2

    public int f2( int n ) {
    int i, j, k, res = 0; 
    int cont_op = 0; 
    for( i = n; i <= n; i += i/2+1 ) {  //n
        for( j = i/2; j <= i*i; j += i+1 ) { //n²
            for( k = n; k <= 2*n; k += i+1 ) { //n
                res = res + n; 
                cont_op++; 
                }
        }
    }
    return cont_op;
    }

    // Algoritmo 3
    
    public int f3( int n ) {
    
    int i, j, k, res = 0; // 1
    int cont_op = 0; // 1
    
    for( i = 1; i <= n*n; i += 2 ) { //n²
        for( j = i/2; j <= 2*i; j += i/2+1 ) { //2
            for( k = j+1; k <= n+j; k += k/2+1 ) { // n/2
                res = res + Math.abs(j-i); 
                cont_op++; 
            }
        }
    }
    return cont_op;
    }
    
    // Algoritmo 4
    public int f4( int n ) {
    int i, j, k, res = 0; 
    int cont_op = 0; 
    
    for( i = n; i <= n*n; i += 2 ) { // n²
        for( j = n+1; j <= n*n; j += 2 ) { //n²
            for( k = j; k <= 2*j; k += 2 ) { //(n+1)²
                res = res + 1; 
                cont_op++; 
            }
        }
    }
    return cont_op;
    }
    
    // Algoritmo 5
    
    int f5( int n ) {
    int i, j, k, res = 0; 
    int cont_op = 0; 
    for( i = 1; i <= n*n; i += 1 ){ //n²
        for( j = 1; j <= i; j += 2 ) { //n²
            for( k = n+1; k <= 2*i; k += i*j ) { //2n
                res = res + k+1; 
                cont_op++; 
            }
        }
    }
    return cont_op;
    }
}


