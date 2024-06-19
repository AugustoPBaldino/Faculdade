package com.example.API.Service;

import com.example.API.Domain.Avaliacao;
import com.example.API.Domain.Classe;
import com.example.API.Repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final ClasseService classeService;

    @Autowired
    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, ClasseService classeService) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.classeService = classeService;
    }

    public Avaliacao createAvaliacao(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> getAvaliacoesByClasseId(Long idClasse) {
        Classe classe = classeService.findById(idClasse);
        if (classe == null) {
            return Collections.emptyList();
        }
        return classe.getAvaliacoes();
    }

    public Avaliacao getAvaliacaoByClasseIdAndAvaliacaoId(Long idClasse, Long idAvaliacao) {
        Classe classe = classeService.findById(idClasse);
        if (classe == null) {
            return null;
        }
        return classe.getAvaliacoes().stream()
                .filter(avaliacao -> avaliacao.getId_Avaliacao().equals(idAvaliacao))
                .findFirst()
                .orElse(null);
    }

    public Avaliacao updateAvaliacaoByClasseIdAndAvaliacaoId(Long idClasse, Long idAvaliacao, Avaliacao updatedAvaliacao) {
        Classe classe = classeService.findById(idClasse);
        if (classe == null) {
            return null;
        }
        Avaliacao avaliacao = classe.getAvaliacoes().stream()
                .filter(a -> a.getId_Avaliacao().equals(idAvaliacao))
                .findFirst()
                .orElse(null);
        if (avaliacao == null) {
            return null;
        }
        // Atualiza os campos da avaliação com os valores do objeto atualizado
        avaliacao.setTipoAvaliacao(updatedAvaliacao.getTipoAvaliacao());
        avaliacao.setData(updatedAvaliacao.getData());
        avaliacao.setNotaAvaliacao(updatedAvaliacao.getNotaAvaliacao());
        avaliacao.setDataEntrega(updatedAvaliacao.getDataEntrega());
        // Salva a avaliação atualizada
        return avaliacaoRepository.save(avaliacao);
    }

    public Avaliacao partialUpdateAvaliacaoByClasseIdAndAvaliacaoId(Long idClasse, Long idAvaliacao, Map<String,Object> updates) {
        Classe classe = classeService.findById(idClasse);
        if (classe == null) {
            return null;
        }
        Avaliacao avaliacao = classe.getAvaliacoes().stream()
                .filter(a -> a.getId_Avaliacao().equals(idAvaliacao))
                .findFirst()
                .orElse(null);
        if (avaliacao == null) {
            return null;
        }
        // Atualiza os campos da avaliação com os valores fornecidos no corpo da requisição
        updates.forEach((key, value) -> {
            switch (key) {
                case "tipoAvaliacao":
                    avaliacao.setTipoAvaliacao((String) value);
                    break;
                case "data":
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse((String) value);
                        avaliacao.setData(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "notaAvaliacao":
                    avaliacao.setNotaAvaliacao((Integer) value);
                    break;
                case "dataEntrega":
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date dateEntrega = sdf.parse((String) value);
                        avaliacao.setDataEntrega(dateEntrega);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        });
        // Salva a avaliação atualizada
        return avaliacaoRepository.save(avaliacao);
    }

    public boolean deleteAvaliacaoByClasseIdAndAvaliacaoId(Long idClasse, Long idAvaliacao) {
        Classe classe = classeService.findById(idClasse);
        if (classe == null) {
            return false;
        }
        Avaliacao avaliacao = classe.getAvaliacoes().stream()
                .filter(a -> a.getId_Avaliacao().equals(idAvaliacao))
                .findFirst()
                .orElse(null);
        if (avaliacao == null) {
            return false;
        }
        classe.getAvaliacoes().remove(avaliacao);
        classeService.saveClasse(classe);
        return true;
    }

}