package com.minhaLojaDeGame.eCommerce.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.minhaLojaDeGame.eCommerce.Model.Categoria;
import com.minhaLojaDeGame.eCommerce.Repository.CategoriaRepository;
import com.minhaLojaDeGame.eCommerce.Service.CategoriaService;

@RestController
@RequestMapping ("/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	@Autowired
	private CategoriaService service;
	
	@GetMapping ("/todes")
	private ResponseEntity<List<Categoria>> buscarTodes (){
		return ResponseEntity.status(200).body(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarPorId (@PathVariable (value = "id") Long id){
		return repository.findById(id)
				.map(categoriaExistente -> ResponseEntity.status(200).body(categoriaExistente))
				.orElse(ResponseEntity.status(400).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Categoria>> buscarPorNome (@PathVariable (value = "nome") String nome){
		return ResponseEntity.status(200).body(repository.findAllByNomeContainingIgnoreCase(nome));
				
	}
	
	@PostMapping ("/cadastrar")
	public ResponseEntity<Object> cadastrarCategoria (@Valid @RequestBody Categoria novaCategoria){
		return service.cadastrarCategoria(novaCategoria)
				.map(categoriaCadastrada -> ResponseEntity.status(201).body(categoriaCadastrada))
				.orElse(ResponseEntity.status(200).body("Ooops.. parece que essa categoria já existe!"));
	}
	
	@PutMapping ("/{id}/atualizar")
	public ResponseEntity <Categoria> atualizarCategoria (@Valid @PathVariable  (value = "id") Long id,
			@Valid @RequestBody Categoria atualizacaoUsuario) {
		return service.atualizarCategoria(id, atualizacaoUsuario)
				.map(usuarioAtualizado -> ResponseEntity.status(201).body(usuarioAtualizado))
				.orElse(ResponseEntity.status(304).build());
	}
	
	@DeleteMapping("/{id}/deletar")
	public ResponseEntity<Object> deletarCategoria (@PathVariable Long id){
		Optional<Categoria> produtoExistente = repository.findById(id);
		
		if(produtoExistente.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(200).body("Categoria deletada");
		} else {
			return ResponseEntity.status(200).body("Essa Categoria ainda não existe!");
		}
	}
	
}
