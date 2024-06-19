package com.example.API.Controllers;

import com.example.API.DTO.ClassDTO;
import com.example.API.Repository.ClassRepository;
import com.example.API.Repository.ClasseSpecification;
import com.example.API.Service.ClasseService;
import com.example.API.Domain.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/classe")
public class ClassesController {

    @Autowired
    private ClassRepository repository;

    @Autowired
    private ClasseService service;

    @Operation(summary = "Lista classe com Id específico")
    @GetMapping("/{id}")
    public Optional<Classe> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Lista todas as classes")
    @GetMapping("/all")
    public ResponseEntity<List<Classe>> getAllClasses() {
        List<Classe> classes = repository.findAll();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @Operation(summary = "recuperação de um objeto por uma query string simples ou complexa")
    @GetMapping
    public List<Classe> getByQuery(
            @RequestParam(required = false) String Disciplina,
            @RequestParam(required = false) String Professor,
            @RequestParam(required = false) String Horario,
            @RequestParam(required = false) String Sala,
            @RequestParam(required = false) String DisciplinaLike,
            @RequestParam(required = false) String ProfessorLike,
            @RequestParam(required = false) String HorarioLike,
            @RequestParam(required = false) String SalaLike) {
        List<Specification<Classe>> specifications = new ArrayList<>();

        if (Disciplina != null) {
            specifications.add(ClasseSpecification.hasAttribute("Disciplina", Disciplina));
        }
        if (Professor != null) {
            specifications.add(ClasseSpecification.hasAttribute("Professor", Professor));
        }
        if (Horario != null) {
            specifications.add(ClasseSpecification.hasAttribute("Horario", Horario));
        }
        if (Sala != null) {
            specifications.add(ClasseSpecification.hasAttribute("Sala", Sala));
        }

        if (DisciplinaLike != null) {
            specifications.add(ClasseSpecification.hasAttributeLike("Disciplina", DisciplinaLike));
        }
        if (ProfessorLike != null) {
            specifications.add(ClasseSpecification.hasAttributeLike("Professor", ProfessorLike));
        }
        if (HorarioLike != null) {
            specifications.add(ClasseSpecification.hasAttributeLike("Horario", HorarioLike));
        }
        if (SalaLike != null) {
            specifications.add(ClasseSpecification.hasAttributeLike("Sala", SalaLike));
        }

        return service.getByComplexQuery(specifications);
    }

    @Operation(summary = "Atualiza classe de maneira parcial")
    @PatchMapping("/{id}")
    public Classe updatePartial(@PathVariable Long id, @RequestBody ClassDTO requestClass) throws Exception {
        return service.updatePartial(id, requestClass);
    }

    @Operation(summary = "Registra uma classe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Classe criada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar a criação de uma classe"),
    })
    @PostMapping("/register")
    public ResponseEntity<Classe> registerClass(@RequestBody Classe classe) {
        Classe savedClasse = repository.save(classe);
        return new ResponseEntity<>(savedClasse, HttpStatus.CREATED);
    }
//        @PutMapping("/{id}")
//    @Transactional
//    public ResponseEntity<Classe> updateClass(@PathVariable Long id, @RequestBody @Valid ClassDTO requestClass) {
//        Optional<Classe> optionalClass = repository.findById(id);
//        if (optionalClass.isPresent()) {
//            Classe classe = optionalClass.get();
//            classe.setDisciplina(requestClass.getDisciplina());
//            classe.setHorario(requestClass.getHorario());
//            classe.setSala(requestClass.getSala());
//            classe.setProfessor(requestClass.getProfessor());
//            Classe updatedClasse = repository.save(classe);
//            return ResponseEntity.ok(updatedClasse);
//        } else {
//            throw new EntityNotFoundException("Classe not found with id " + id);
//        }
// }
    @Operation(summary = "Remove uma classe passando o seu Id")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        Optional<Classe> optionalClasse = repository.findById(id);
        if (optionalClasse.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new EntityNotFoundException("Classe not found with id " + id);
        }
    }
}
