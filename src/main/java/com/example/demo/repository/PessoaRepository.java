package com.example.demo.repository;

import com.example.demo.model.PessoaModelo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PessoaRepository extends JpaRepository<PessoaModelo, Long>{
    
    @Query(value = "SELECT * FROM pessoa WHERE nome LIKE %:nome%", nativeQuery = true)
    List<PessoaModelo> findByNome(@Param("nome") String nome);
}
