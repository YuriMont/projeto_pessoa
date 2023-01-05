package com.example.demo.controller;

import com.example.demo.model.EnderecoModelo;
import com.example.demo.model.PessoaModelo;
import com.example.demo.service.PessoaService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class PessoaControler {
    
    @Autowired
    private PessoaService ps;
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult().getAllErrors().forEach((error) ->{ 
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            
            errors.put(fieldName, errorMessage);
        });
        
        return errors;
    }
    
    @GetMapping("/all")
    public Iterable<PessoaModelo> listarPessoas(){
       return ps.listarPessoas();
    }
    
    @PostMapping("create")
    public ResponseEntity<PessoaModelo> criarPessoa(@RequestBody @Valid PessoaModelo pm){
        return ps.criarPessoa(pm);
    }
    
    @PutMapping("edit/{pessoa_id}")
    public ResponseEntity<PessoaModelo> editarPessoa(@PathVariable Long pessoa_id, @RequestBody @Valid PessoaModelo pm){
        return ps.editarPessoa(pm);
    }
    
    @GetMapping("find/{nome}")
    public Iterable<PessoaModelo> mostrarPessoa(@PathVariable String nome){
       return ps.buscarPessoa(nome);
    }
    
    @PutMapping("create/{pessoa_id}/address")
    public ResponseEntity<PessoaModelo> criarEndereco(@PathVariable Long pessoa_id, @RequestBody @Valid EnderecoModelo em){
       return ps.criarEndereco(pessoa_id, em);
    }
    
    @GetMapping("{pessoa_id}/address")
    public Iterable<EnderecoModelo> mostrarEnderecos(@PathVariable Long pessoa_id){
       return ps.MostrarEnderecos(pessoa_id);
    }
    
    @PatchMapping("{pessoa_id}/address/main/{endereco_id}")
    public ResponseEntity<PessoaModelo> alterarPrincipalEndereco(@PathVariable Long pessoa_id, @PathVariable Long endereco_id){
       return ps.tornarEnderecoPrincipal(pessoa_id, endereco_id);
    }
    
    
    
    
    
    
    
    
    
}
