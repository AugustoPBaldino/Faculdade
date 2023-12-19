package com.Augusto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BarcaTest {
    
    Barca barca = new Barca();
    
    @Test
    public void testOcupaLugarInvalido() {
        //Os resultados deviam ser caso 0, ele está esperando um número de 0 a 8 com dois caracteres
        //O outro erro é que se o assento for 20 ou se a fila ou o assento for 0.
        assertEquals(0, barca.ocupaLugar("F00A01")); 
        assertEquals(0, barca.ocupaLugar("F01A00"));
        assertEquals(0, barca.ocupaLugar("F61A01"));
        assertEquals(0, barca.ocupaLugar("F20A21"));
    }

    @Test
    public void testOcupaLugarOcupado() {
     //O caso de teste está esperando o valor 1, assento ocupado, porém a classe barco verifica se 
     //o assento está ocupado e logo envia uma nova mensagem que o assento foi atribuído
        barca.ocupaLugar(5, 10);
        assertEquals(1, barca.ocupaLugar("F05A10"));
        barca.ocupaLugar(45, 5);
        assertEquals(1, barca.ocupaLugar("F45A05"));
    }

    @Test
    //O erro para o caso 2 só ocorrerá quando a quantidade de assentos ocupados for maior que 100, 
    // menor ou igual a 200 e a fila for menor que 40, o código acaba deixando o priemiro passageiro sentar numa fila acima da 40. 
    public void testOcupaLugarBloqueado() {
        assertEquals(2, barca.ocupaLugar("F25A01"));
        assertEquals(2, barca.ocupaLugar("F30A01"));
        assertEquals(2, barca.ocupaLugar("F35A01"));
        assertEquals(2, barca.ocupaLugar("F40A01"));
    }


    //Este caso está correto, porém os outros casos comprometem o funcionamento do teste, como o caso 1, que
    // retorna o valor 3 ao invés do 1. 
    @Test
    public void testOcupaLugarValido() {
        assertEquals(3, barca.ocupaLugar("F21A01"));
        assertEquals(3, barca.ocupaLugar("F50A01"));
        assertEquals(3, barca.ocupaLugar("F55A01"));
        assertEquals(3, barca.ocupaLugar("F60A01"));
    }
}
