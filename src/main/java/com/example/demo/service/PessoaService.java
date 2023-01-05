
package com.example.demo.service;

import com.example.demo.model.EnderecoModelo;
import com.example.demo.model.PessoaModelo;
import com.example.demo.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pr;
    
    //metodo para listar todas as pessoas
    public Iterable<PessoaModelo> listarPessoas(){
        return pr.findAll();
    }
    
    //metodo para criar pessoa
    public ResponseEntity<PessoaModelo> criarPessoa(PessoaModelo pm){
        return new ResponseEntity<PessoaModelo>(pr.save(pm), HttpStatus.CREATED);
    }
    
    //metodo para editar pessoa
    public ResponseEntity<PessoaModelo> editarPessoa(PessoaModelo pm){
        return new ResponseEntity<PessoaModelo>(pr.save(pm), HttpStatus.OK);
    }
    
    //metodo para buscar pessoa
    public Iterable<PessoaModelo> buscarPessoa(String nome){
       return pr.findByNome(nome);
    }
    
    //metodo para criar o endereço
   public ResponseEntity<PessoaModelo> criarEndereco(Long pessoa_id, EnderecoModelo em){
        PessoaModelo pm = pr.getReferenceById(pessoa_id);
        pm.addEndereco(em);
        return new ResponseEntity<PessoaModelo>(pr.save(pm), HttpStatus.OK);
    }
    
    //metodo para visualizar os enderecos
    public Iterable<EnderecoModelo> MostrarEnderecos(Long pessoa_id){
        return pr.getReferenceById(pessoa_id).getEnderecos();
    }
    
    //metodo para mudar endereço principal
    public ResponseEntity<PessoaModelo> tornarEnderecoPrincipal(Long pessoa_id, Long endereco_id){
        PessoaModelo pm = pr.getReferenceById(pessoa_id);
        
        pm.mudarPrincipal(endereco_id);
        return new ResponseEntity<PessoaModelo>(pr.save(pm), HttpStatus.OK);
    }
    
}
