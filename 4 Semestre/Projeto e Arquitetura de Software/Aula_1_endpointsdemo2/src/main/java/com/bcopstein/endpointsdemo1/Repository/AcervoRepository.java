package com.bcopstein.endpointsdemo1.Repository;

import java.util.List;

public interface AcervoRepository {
    List<Livro> getLivros();
    Livro getLivroById(int codigo);
    void addLivro(Livro livro);
    void updateLivro(int codigo, Livro livro);
    void deleteLivro(int codigo);
}

