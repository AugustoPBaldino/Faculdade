package com.example.API.Repository;

import com.example.API.Domain.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClassRepository extends JpaRepository<Classe, Long>, JpaSpecificationExecutor<Classe> {

}
