package com.bcopstein;

public class MockCalculoEstatistica implements ICalculoEstatistica {
    private EstatisticasDTO estatisticas;
    
    public MockCalculoEstatistica(EstatisticasDTO estatisticas) {
        this.estatisticas = estatisticas;
    }

    @Override
    public EstatisticasDTO calculaEstatisticas(int distancia) {
        return estatisticas;
    }
}

