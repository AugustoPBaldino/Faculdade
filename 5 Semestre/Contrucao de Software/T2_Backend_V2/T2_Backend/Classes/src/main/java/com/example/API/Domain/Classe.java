package com.example.API.Domain;

import com.example.API.DTO.ClassDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name="classe")
@Entity(name="classe")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id_Classe")
public class Classe {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_Classe;
    private String Disciplina;
    private String Professor;
    private String Horario;
    private String Sala;
    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Avaliacao> avaliacoes;

    public Classe(ClassDTO classDTO) {
        this.Disciplina = classDTO.Disciplina();
        this.Professor = classDTO.Professor();
        this.Horario = classDTO.Horario();
        this.Sala = classDTO.Sala();
    }






}