package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "endereco")
@Getter
@Setter
public class EnderecoModelo {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long endereco_id;
    @NotNull(message = "O campo CEP não pode ser vázio")
    private Long CEP;
    private boolean principal;
    @NotBlank(message = "O campo logradouro não pode ser vázio")
    private String logradouro;
    @NotNull(message = "O campo numero não pode ser vázio")
    private Long numero;
    @NotBlank(message = "O campo cidade não pode ser vázio")
    private String cidade;
   
    
}
