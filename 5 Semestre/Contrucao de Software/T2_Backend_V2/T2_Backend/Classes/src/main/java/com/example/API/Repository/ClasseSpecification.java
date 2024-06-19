package com.example.API.Repository;

import com.example.API.Domain.Classe;
import org.springframework.data.jpa.domain.Specification;

public class ClasseSpecification {

    public static Specification<Classe> hasAttribute(String attributeName, String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(attributeName), value);
    }

    public static Specification<Classe> hasAttributeLike(String attributeName, String value) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(attributeName), "%" + value + "%");
    }
}
