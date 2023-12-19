package com.bcopstein.endpointsdemo1.Repository;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AcervoRepositoryMemory implements AcervoRepository {

    private List<Livro> livros = new ArrayList<>();

    @Override
    public List<Livro> getLivros() {
        return livros;
    }

    @Override
    public Livro getLivroById(int codigo) {
        // Implemente a lógica para buscar um livro pelo código
        for (Livro livro : livros) {
            if (livro.codigo() == codigo) {
                return livro;
            }
        }
        return null; // Retorne null se o livro não for encontrado
    }

    @Override
    public void addLivro(Livro livro) {
        livros.add(livro);
    }

    @Override
    public void updateLivro(int codigo, Livro livro) {
        // Implemente a lógica para atualizar um livro pelo código
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).codigo() == codigo) {
                livros.set(i, livro);
                return;
            }
        }
        // Trate o caso em que o livro não foi encontrado (lançar exceção, por exemplo)
    }

    @Override
    public void deleteLivro(int codigo) {
        // Implemente a lógica para excluir um livro pelo código
        livros.removeIf(livro -> livro.codigo() == codigo);
    }
}




 
