package com.bcopstein;



import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;

public class ServicoEstatisticaTest {

    @Test
    public void testCalculaEstatisticas() {
        // Crie um Mock do IEventoRepository
        IEventoRepository eventoRepository = mock(IEventoRepository.class);

        // Configure o Mock para retornar eventos de teste
        List<Evento> eventos = new ArrayList<>();
        eventos.add(new Evento(1,"Evento1", 1, 10, 2023, 1, 2, 3, 0)); // Distância 1, com valores de tempo
        eventos.add(new Evento(2,"Evento2", 3, 10, 2023, 4, 5, 6, 0)); // Distância 1, com outros valores de tempo
        eventos.add(new Evento(3,"Evento3", 2, 10, 2023, 2, 4, 5, 0)); // Distância 1, com valores de tempo
        eventos.add(new Evento(4,"Evento4", 1, 10, 2023, 3, 3, 4, 0)); // Distância 1, com outros valores de tempo
        when(eventoRepository.todos()).thenReturn(eventos);

        // Crie uma instância real de EstatisticaNormal
        EstatisticaNormal estatisticaNormal = new EstatisticaNormal(eventoRepository);

        // Crie uma instância de ServicoEstatistica com EstatisticaNormal
        ServicoEstatistica servicoEstatistica = new ServicoEstatistica(eventoRepository, estatisticaNormal);

        // Chame o método calculaEstatisticas do ServicoEstatistica
        EstatisticasDTO estatisticas = servicoEstatistica.calculaEstatisticas(1);

        // Valide as estatísticas com base nos valores esperados
        assertNotNull(estatisticas);
        assertTrue(estatisticas.getMedia() >= 0);
        assertTrue(estatisticas.getMediana() >= 0);
        assertTrue(estatisticas.getDesvioPadrao() >= 0);
    }

    @Test
    public void testCalculaAumentoPerformance() {
        // Crie um Mock do IEventoRepository
        IEventoRepository eventoRepository = mock(IEventoRepository.class);

        // Configure o Mock para retornar eventos de teste
        List<Evento> eventos = new ArrayList<>();
        eventos.add(new Evento(1,"Evento1", 1, 10, 2023, 1, 2, 3, 0)); // Distância 1, com valores de tempo
        eventos.add(new Evento(2,"Evento2", 3, 10, 2023, 4, 5, 6, 0)); // Distância 1, com outros valores de tempo
        eventos.add(new Evento(3,"Evento3", 2, 10, 2023, 2, 4, 5, 0)); // Distância 1, com valores de tempo
        eventos.add(new Evento(4,"Evento4", 1, 10, 2023, 3, 3, 4, 0)); // Distância 1, com outros valores de tempo
        when(eventoRepository.todos()).thenReturn(eventos);

        // Crie uma instância real de EstatisticaNormal
        EstatisticaNormal estatisticaNormal = new EstatisticaNormal(eventoRepository);

        // Crie uma instância de ServicoEstatistica com EstatisticaNormal
        ServicoEstatistica servicoEstatistica = new ServicoEstatistica(eventoRepository, estatisticaNormal);

        // Chame o método calculaAumentoPerformance do ServicoEstatistica
        PerformanceDTO performance = servicoEstatistica.calculaAumentoPerformance(1, 2023);

        // Valide o desempenho com base nos valores esperados
       assertNotNull(performance);
        assertNotNull(performance.getProva1());
        assertNotNull(performance.getProva2());
        assertTrue(performance.getReducao() >= 0);
    }
}


