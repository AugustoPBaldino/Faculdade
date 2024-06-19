package com.example.API.Service;

import com.example.API.Repository.ClassRepository;
import com.example.API.Domain.Classe;
import com.example.API.Repository.ClasseSpecification;
import com.example.API.DTO.ClassDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasseService {

    @Autowired
    private ClassRepository repository;

    public Optional<Classe> getById(Long id) {
        return repository.findById(id);
    }

    public Classe updatePartial(Long id, ClassDTO requestClass) throws Exception {
        Optional<Classe> optionalClasse = repository.findById(id);
        if (optionalClasse.isPresent()) {
            Classe classe = optionalClasse.get();
            if (requestClass.Disciplina() != null) {
                classe.setDisciplina(requestClass.Disciplina());
            }
            if (requestClass.Professor() != null) {
                classe.setProfessor(requestClass.Professor());
            }
            if (requestClass.Horario() != null) {
                classe.setHorario(requestClass.Horario());
            }
            if (requestClass.Sala() != null) {
                classe.setSala(requestClass.Sala());
            }
            return repository.save(classe);
        }
        throw new Exception("Classe not found with id " + id);
    }

    public List<Classe> getBySimpleQuery(String disciplina, String professor, String horario, String sala) {
        Specification<Classe> spec = Specification.where(null);

        if (disciplina != null) {
            spec = spec.and(ClasseSpecification.hasAttribute("Disciplina", disciplina));
        }
        if (professor != null) {
            spec = spec.and(ClasseSpecification.hasAttribute("Professor", professor));
        }
        if (horario != null) {
            spec = spec.and(ClasseSpecification.hasAttribute("Horario", horario));
        }
        if (sala != null) {
            spec = spec.and(ClasseSpecification.hasAttribute("Sala", sala));
        }

        return repository.findAll(spec);
    }

    public List<Classe> getByComplexQuery(List<Specification<Classe>> specifications) {
        Specification<Classe> combinedSpec = specifications.stream().reduce(Specification::and).orElse(null);
        return repository.findAll(combinedSpec);
    }
    public Classe findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public void saveClasse(Classe classe) {
        repository.save(classe);
    }

}


