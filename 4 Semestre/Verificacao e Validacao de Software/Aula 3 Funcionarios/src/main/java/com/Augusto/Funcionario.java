package com.Augusto;

public class Funcionario {
    private static final double limINSS = 5000.0;
    private static final double aliquotaINSS = 0.045;
    private static final double limIRPF = 2500.0;
    private static final double aliquotaIRPF = 0.12;
    private double salarioBruto;

    public Funcionario(double salarioBruto){
        this.setSalarioBruto(salarioBruto);
    }

    public double getSalarioBruto(){
        return this.salarioBruto;
    }

    public void setSalarioBruto(double salarioBruto) {
        if (salarioBruto <= 0.0){
            throw new IllegalArgumentException("Salario invalido");
        }
        this.salarioBruto = salarioBruto;
    }

    public double getINSS(){
        if (this.salarioBruto <= limINSS){ 
            return this.salarioBruto * aliquotaINSS;
        }else{
            return limINSS * aliquotaINSS;
        }
    }

    public double getIRPF(){
        if (this.salarioBruto <= limIRPF){
            return 0.0;
        }else{
            return ((this.salarioBruto-limIRPF)*aliquotaIRPF);
        }
    }

    public double getSalarioLiquido(){
        return (this.salarioBruto - this.getINSS() - this.getIRPF());
    }
}