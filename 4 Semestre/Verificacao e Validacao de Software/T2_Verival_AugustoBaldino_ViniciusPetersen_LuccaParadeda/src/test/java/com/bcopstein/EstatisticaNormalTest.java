package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;



import java.util.ArrayList;
import java.util.List;

public class EstatisticaNormalTest {
   

     class StubEventoRepository implements IEventoRepository {
        @Override
        public List<Evento> todos() {
            // Retorna uma lista de eventos de teste
            List<Evento> eventos = new ArrayList<>();
            eventos.add(new Evento(1,"Evento1", 1, 10, 2023, 1, 2, 3, 0)); // Distância 1, com valores de tempo
            eventos.add(new Evento(2,"Evento2", 3, 10, 2023, 4, 5, 6, 0)); // Distância 1, com outros valores de tempo
            eventos.add(new Evento(3,"Evento3", 2, 10, 2023, 2, 4, 5, 0)); // Distância 1, com valores de tempo
            eventos.add(new Evento(4,"Evento4", 1, 10, 2023, 3, 3, 4, 0)); // Distância 1, com outros valores de tempo
            return eventos;
        }

        @Override
        public boolean cadastra(Evento evento) {
            
            throw new UnsupportedOperationException("Unimplemented method 'cadastra'");
        }
    }

   @Test
    public void testCalculaEstatisticas1() {
        // Crie uma instância de EstatisticaNormal com o StubEventoRepository
        EstatisticaNormal estatisticaNormal = new EstatisticaNormal(new StubEventoRepository());

        // Chame o método calculaEstatisticas para a distância 1
        EstatisticasDTO estatisticas = estatisticaNormal.calculaEstatisticas(1);


        // Verifique se as estatísticas são calculadas corretamente
        assertNotNull(estatisticas);
        assertTrue(estatisticas.getMedia() >= 0);
        assertTrue(estatisticas.getMediana() >= 0);
        assertTrue(estatisticas.getDesvioPadrao() >= 0);

    }
    
    @Test
public void testCalculaEstatisticas2() {
    // Crie um mock do IEventoRepository
    IEventoRepository eventoRepository = mock(IEventoRepository.class);

    // Configure o mock para retornar uma lista de eventos de teste com valores apropriados
    List<Evento> eventos = new ArrayList<>();
    eventos.add(new Evento(1,"Evento1", 1, 10, 2023, 1, 2, 3, 0)); // Distância 1, com valores de tempo
    eventos.add(new Evento(2,"Evento2", 3, 10, 2023, 4, 5, 6, 0)); // Distância 1, com outros valores de tempo
    eventos.add(new Evento(3,"Evento3", 2, 10, 2023, 2, 4, 5, 0)); // Distância 1, com valores de tempo
    eventos.add(new Evento(4,"Evento4", 1, 10, 2023, 3, 3, 4, 0)); // Distância 1, com outros valores de tempo
    when(eventoRepository.todos()).thenReturn(eventos);

    // Crie uma instância de EstatisticaNormal com o mock do IEventoRepository
    EstatisticaNormal estatisticaNormal = new EstatisticaNormal(eventoRepository);

    // Chame o método calculaEstatisticas para a distância 1
    EstatisticasDTO estatisticas = estatisticaNormal.calculaEstatisticas(1);

    // Verifique se as estatísticas são calculadas corretamente
    assertNotNull(estatisticas);
        assertTrue(estatisticas.getMedia() >= 0);
        assertTrue(estatisticas.getMediana() >= 0);
        assertTrue(estatisticas.getDesvioPadrao() >= 0); // Média ajustada com base nos novos valores
    

    // Verifique se o método todos() do mock foi chamado
    verify(eventoRepository, times(1)).todos();
}




}
