package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "pessoa")
@Getter
@Setter
public class PessoaModelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pessoa_id;
    @NotBlank(message = "O campo nome é obrigatorio")
    private String nome;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "O campo é obrigatorio")
    private Date data_de_nascimento;
    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
    private List<EnderecoModelo> enderecos;
    
    
    public void addEndereco(EnderecoModelo em){
        this.enderecos.add(em);
    }

    public void mudarPrincipal(Long endereco_id){
      for(EnderecoModelo em: enderecos){
          int index = this.getEnderecos().indexOf(em);
          this.enderecos.get(index).setPrincipal(true);
          
          if(!em.getEndereco_id().equals(endereco_id)){
             this.enderecos.get(index).setPrincipal(false); 
          }
      }  
    }
}
