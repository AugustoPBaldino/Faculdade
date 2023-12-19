package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;



import java.util.ArrayList;
import java.util.List;

public class EstatisticaDesconsideraTest {

     class StubEventoRepository implements IEventoRepository {
        @Override
        public List<Evento> todos() {
            // Retorna uma lista de eventos de teste
            List<Evento> eventos = new ArrayList<>();
            eventos.add(new Evento(1,"Evento1", 1, 9, 2023, 1, 2, 3, 0)); // Distância 1, com valores de tempo
            eventos.add(new Evento(2,"Evento2", 3, 2, 2021, 4, 5, 6, 0)); // Distância 1, com outros valores de tempo
            eventos.add(new Evento(3,"Evento3", 2, 6, 2020, 2, 4, 5, 0)); // Distância 1, com valores de tempo
            eventos.add(new Evento(4,"Evento4", 1, 10, 2022, 3, 3, 4, 0)); // Distância 1, com outros valores de tempo
            return eventos;
        }

        @Override
        public boolean cadastra(Evento evento) {
            
            throw new UnsupportedOperationException("Unimplemented method 'cadastra'");
        }
    }

   @Test
    public void testCalculaEstatisticas1() {
        // Crie uma instância de EstatisticaDesconsidera com o StubEventoRepository
        EstatisticaDesconsidera estatisticaDesconsidera = new EstatisticaDesconsidera(new StubEventoRepository());

        // Chame o método calculaEstatisticas para a distância 1
        EstatisticasDTO estatisticas = estatisticaDesconsidera.calculaEstatisticas(1);

         // Valores esperados com base nos eventos do StubEventoRepository
        double mediaEsperada = (3600 + 10 * 60 + 40 + 3600 + 9 * 60 + 50) / 2.0; // Média das horas, minutos e segundos
        double medianaEsperada = 3600 + 10 * 60 + 40; // Valor do meio
        double desvioPadraoEsperado = 0.0; // Não há variação

        // Verifique se as estatísticas são calculadas corretamente
        assertEquals(mediaEsperada, estatisticas.getMedia(), 0.001);
        assertEquals(medianaEsperada, estatisticas.getMediana(), 0.001);
        assertEquals(desvioPadraoEsperado, estatisticas.getDesvioPadrao(), 0.001);

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

    // Crie uma instância de EstatisticaDesconsidera com o mock do IEventoRepository
    EstatisticaDesconsidera estatisticaDesconsidera = new EstatisticaDesconsidera(eventoRepository);

    // Chame o método calculaEstatisticas para a distância 1
    EstatisticasDTO estatisticas = estatisticaDesconsidera.calculaEstatisticas(1);

    // Verifique se as estatísticas são calculadas corretamente
    assertEquals(1260.0, estatisticas.getDesvioPadrao(), 0.001);
    assertEquals(10802.0, estatisticas.getMediana(), 0.001);
    assertEquals(9000.0, estatisticas.getMedia(), 0.001); // Média ajustada com base nos novos valores
    
    

    // Verifique se o método todos() do mock foi chamado
    verify(eventoRepository, times(1)).todos();
}




}
