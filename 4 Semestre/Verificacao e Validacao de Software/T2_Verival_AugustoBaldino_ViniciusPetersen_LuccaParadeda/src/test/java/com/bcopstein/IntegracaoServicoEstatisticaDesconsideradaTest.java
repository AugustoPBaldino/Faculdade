package com.bcopstein;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;

public class IntegracaoServicoEstatisticaDesconsideradaTest {

    private ServicoEstatistica servicoEstatistica;
    private IEventoRepository eventoRepository;

    @BeforeEach
    public void setUp() {
        // Cria um mock para IEventoRepository
        eventoRepository = Mockito.mock(IEventoRepository.class);
        servicoEstatistica = new ServicoEstatistica(eventoRepository, new EstatisticaDesconsidera(eventoRepository));
    }

    @Test
    public void testCalculaEstatisticas() {
        // Configura o mock para retornar uma lista de eventos fictícia
        List<Evento> eventos = Arrays.asList(
                new Evento(0, "Evento1", 10, 0, 0, 1, 0, 0, 0),
                new Evento(1, "Evento2", 11, 0, 0, 1, 0, 0, 0),
                new Evento(2, "Evento3", 12, 0, 0, 1, 0, 0, 0));
        Mockito.when(eventoRepository.todos()).thenReturn(eventos);

        // Chama o método que queremos testar
        EstatisticasDTO estatisticas = servicoEstatistica.calculaEstatisticas(1);

        // Verifica os resultados
        assertEquals(0, estatisticas.getMedia());
        assertEquals(0, estatisticas.getMediana());
        assertEquals(0.0, estatisticas.getDesvioPadrao());
    }

    @Test
    public void testCalculaAumentoPerformance() {
        // Configura o mock para retornar uma lista de eventos fictícia
        List<Evento> eventos = Arrays.asList(
                new Evento(0, "Evento1", 10, 1, 2023, 1, 1, 1, 1),
                new Evento(1, "Evento2", 10, 1, 2023, 1, 1, 1, 1),
                new Evento(2, "Evento3", 12, 1, 2023, 1, 1, 1, 1),
                new Evento(3, "Evento4", 9, 1, 2023, 1, 1, 1, 1),
                new Evento(4, "Evento5", 15, 1, 2023, 1, 1, 1, 1),
                new Evento(5, "Evento6", 11, 1, 2023, 30, 1, 1, 1));
        Mockito.when(eventoRepository.todos()).thenReturn(eventos);

        // Chama o método que queremos testar
        PerformanceDTO performance = servicoEstatistica.calculaAumentoPerformance(1, 2023);

        // Verifica os resultados
        assertEquals("Evento1", performance.getProva1());
        assertEquals("Evento2", performance.getProva2());
        assertEquals(0, performance.getReducao());
    }
}
