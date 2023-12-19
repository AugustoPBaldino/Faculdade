package com.bcopstein.endpointsdemo1.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AcervoRepositoryJdbc implements AcervoRepository {

    private final JDBCType jdbcTemplate;

    @Autowired
public AcervoRepositoryJdbc(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
}

    @Override
    public List<Livro> getLivros() {
        // Implemente a busca no banco de dados usando jdbcTemplate
        return jdbcTemplate.query("SELECT * FROM livros", new LivroRowMapper());
    }

    @Override
    public Livro getLivroById(int codigo) {
        // Implemente a lógica para buscar um livro pelo código no banco de dados
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM livros WHERE codigo = ?",
                    new LivroRowMapper(),
                    codigo
            );
        } catch (DataAccessException e) {
            return null; // Trate caso o livro não seja encontrado
        }
    }

    @Override
    public void addLivro(Livro livro) {
        // Implemente a inserção no banco de dados usando jdbcTemplate
        final String sql = "INSERT INTO livros (titulo, autor, ano) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"codigo"});
                ps.setString(1, livro.titulo());
                ps.setString(2, livro.autor());
                ps.setInt(3, livro.ano());
                return ps;
            }
        }, keyHolder);

        livro.setCodigo(keyHolder.getKey().intValue());
    }

    @Override
    public void updateLivro(int codigo, Livro livro) {
        // Implemente a lógica para atualizar um livro pelo código no banco de dados
        final String sql = "UPDATE livros SET titulo = ?, autor = ?, ano = ? WHERE codigo = ?";

        jdbcTemplate.update(sql, livro.titulo(), livro.autor(), livro.ano(), codigo);
    }

    @Override
    public void deleteLivro(int codigo) {
        // Implemente a lógica para excluir um livro pelo código no banco de dados
        jdbcTemplate.update("DELETE FROM livros WHERE codigo = ?", codigo);
    }
    
    // Implemente uma classe interna para mapear as linhas do ResultSet para objetos Livro
    private static final class LivroRowMapper implements RowMapper<Livro> {
        public Livro mapRow(ResultSet rs, int rowNum) throws SQLException {
            Livro livro = new Livro(rowNum, null, null, rowNum);
            livro.setCodigo(rs.getInt("codigo"));
            livro.setTitulo(rs.getString("titulo"));
            livro.setAutor(rs.getString("autor"));
            livro.setAno(rs.getInt("ano"));
            return livro;
        }
    }
}


