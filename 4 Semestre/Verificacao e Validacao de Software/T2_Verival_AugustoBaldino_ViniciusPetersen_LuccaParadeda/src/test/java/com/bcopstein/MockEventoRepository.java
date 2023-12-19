package com.bcopstein;

import java.util.ArrayList;
import java.util.List;

public class MockEventoRepository implements IEventoRepository {
    private List<Evento> eventos;

    public MockEventoRepository() {
        eventos = new ArrayList<>();
    }

    public void adicionarEvento(Evento evento) {
        eventos.add(evento);
    }

    @Override
    public List<Evento> todos() {
        return eventos;
    }

    

    @Override
    public boolean cadastra(Evento evento) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastra'");
    }

    // Outros métodos da interface IEventoRepository não usados nos testes podem ser implementados aqui conforme a necessidade.
}
