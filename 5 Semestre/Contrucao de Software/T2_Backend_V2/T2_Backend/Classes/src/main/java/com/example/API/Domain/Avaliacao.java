package com.example.API.Domain;

import com.example.API.DTO.AvaliacaoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "avaliacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_Avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Avaliacao;

    private String tipoAvaliacao;

    @Temporal(TemporalType.DATE)
    private Date data;

    private int notaAvaliacao;

    @Temporal(TemporalType.DATE)
    private Date dataEntrega;

    @ManyToOne
    @JoinColumn(name = "classe_id")
    private Classe classe;

    public Avaliacao(AvaliacaoDTO avaliacaoDTO) {
        this.tipoAvaliacao = avaliacaoDTO.tipoAvaliacao();
        this.data = avaliacaoDTO.data();
        this.notaAvaliacao = avaliacaoDTO.notaAvaliacao();
        this.dataEntrega = avaliacaoDTO.dataEntrega();
    }
}
