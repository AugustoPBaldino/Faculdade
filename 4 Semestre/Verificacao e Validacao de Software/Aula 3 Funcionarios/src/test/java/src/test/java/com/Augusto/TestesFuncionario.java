package src.test.java.com.Augusto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.Augusto.Funcionario;

public class TestesFuncionario {

    @Test
    void testSalarioBrutoInvalido() {
        Funcionario f = new Funcionario(1000);
        assertThrows(IllegalArgumentException.class, () -> f.setSalarioBruto(-1000));
        // Testa se a exceção IllegalArgumentException é lançada ao criar um funcionário
        // com salário bruto inválido (negativo).
    }

    @Test
    void testSalarioLiquidoDentroLimiteINSS() {
        Funcionario f = new Funcionario(3200);
        assertEquals((3200 * 0.045), f.getINSS(), 0.001);
        // Testa se o cálculo do salário líquido é correto para um salário bruto dentro do limite INSS.
    }

    @Test
    void testSalarioLiquidoAcimaLimiteINSS() {
        Funcionario f = new Funcionario(5500);
        assertEquals((5000 * 0.045), f.getINSS(), 0.001);
        // Testa se o cálculo do salário líquido é correto para um salário bruto acima do limite INSS
        // e abaixo do limite IRPF.
    }

    @Test
    void testSalarioLiquidoAcimaLimiteIRPF() {
        Funcionario f = new Funcionario(7000);
        assertEquals(((7000-2500) * 0.12), f.getIRPF(), 0.001);
        // Testa se o cálculo do salário líquido é correto para um salário bruto acima do limite IRPF.
    }

    @Test
    void testSalarioLiquidoAbaixoLimiteIRPF() {
        Funcionario f = new Funcionario(2500);
        assertEquals(((2500-2500) * 0.12), f.getIRPF(), 0.001);
        // Testa se o cálculo do salário líquido é correto para um salário bruto acima do limite IRPF.
    }
}

