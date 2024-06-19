package com.example.API.Controllers;

import com.example.API.Domain.Avaliacao;
import com.example.API.Service.AvaliacaoService;
import com.example.API.Service.ClasseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;
    private final ClasseService classeService;

    @Autowired
    public AvaliacaoController(AvaliacaoService avaliacaoService, ClasseService classeService) {
        this.avaliacaoService = avaliacaoService;
        this.classeService = classeService;
    }

    @Operation(summary = "Obter avaliações por ID da classe")
    @GetMapping("/{idClasse}/classe")
    public ResponseEntity<List<Avaliacao>> getAvaliacoesByClasseId(@PathVariable Long idClasse) {
        List<Avaliacao> avaliacoes = avaliacaoService.getAvaliacoesByClasseId(idClasse);
        if (avaliacoes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    @Operation(summary = "Obter avaliação por ID da classe e ID da avaliação")
    @GetMapping("/{idClasse}/classe/{idAvaliacao}")
    public ResponseEntity<Avaliacao> getAvaliacaoByClasseIdAndAvaliacaoId(@PathVariable Long idClasse, @PathVariable Long idAvaliacao) {
        Avaliacao avaliacao = avaliacaoService.getAvaliacaoByClasseIdAndAvaliacaoId(idClasse, idAvaliacao);
        if (avaliacao == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(avaliacao, HttpStatus.OK);
    }

    @Operation(summary = "Criar nova avaliação")
    @PostMapping("/{idClasse}/classe")
    public ResponseEntity<Avaliacao> createAvaliacao(@RequestBody Avaliacao avaliacao, @PathVariable Long idClasse) {
        if (!classeService.existsById(idClasse)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        avaliacao.setClasse(classeService.findById(idClasse));
        Avaliacao createdAvaliacao = avaliacaoService.createAvaliacao(avaliacao);
        return new ResponseEntity<>(createdAvaliacao, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar avaliação por ID da classe e ID da avaliação")
    @PutMapping("/{idClasse}/classe/{idAvaliacao}")
    public ResponseEntity<Avaliacao> updateAvaliacaoByClasseIdAndAvaliacaoId(@PathVariable Long idClasse, @PathVariable Long idAvaliacao, @RequestBody Avaliacao updatedAvaliacao) {
        Avaliacao avaliacao = avaliacaoService.updateAvaliacaoByClasseIdAndAvaliacaoId(idClasse, idAvaliacao, updatedAvaliacao);
        if (avaliacao == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(avaliacao, HttpStatus.OK);
    }

    @Operation(summary = "Atualização parcial da avaliação por ID da classe e ID da avaliação")
    @PatchMapping("/{idClasse}/classe/{idAvaliacao}")
    public ResponseEntity<Avaliacao> partialUpdateAvaliacaoByClasseIdAndAvaliacaoId(@PathVariable Long idClasse, @PathVariable Long idAvaliacao, @RequestBody Map<String, Object> updates) {
        Avaliacao avaliacao = avaliacaoService.partialUpdateAvaliacaoByClasseIdAndAvaliacaoId(idClasse, idAvaliacao, updates);
        if (avaliacao == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(avaliacao, HttpStatus.OK);
    }

    @Operation(summary = "Deletar avaliação por ID da classe e ID da avaliação")
    @DeleteMapping("/{idClasse}/classe/{idAvaliacao}")
    public ResponseEntity<Void> deleteAvaliacaoByClasseIdAndAvaliacaoId(@PathVariable Long idClasse, @PathVariable Long idAvaliacao) {
        boolean deleted = avaliacaoService.deleteAvaliacaoByClasseIdAndAvaliacaoId(idClasse, idAvaliacao);
        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
