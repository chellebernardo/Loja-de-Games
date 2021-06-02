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

import com.minhaLojaDeGame.eCommerce.Model.Produto;
import com.minhaLojaDeGame.eCommerce.Repository.ProdutoRepository;
import com.minhaLojaDeGame.eCommerce.Service.ProdutoService;

@RestController
@RequestMapping ("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	@Autowired
	private ProdutoService service;
	
	@GetMapping ("/todes")
	private ResponseEntity<List<Produto>> buscarTodes (){
		List <Produto> listaProdutos = repository.findAll();
		
		if (listaProdutos.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaProdutos);
		}
	}
	
	@PostMapping ("/cadastrar")
	public ResponseEntity<Object> cadastrarCategoria (@Valid @RequestBody Produto novoProduto){
		return service.cadastrarProduto(novoProduto)
				.map(produtoCadastrado -> ResponseEntity.status(201).body(produtoCadastrado))
				.orElse(ResponseEntity.status(200).body("Ooops.. parece que esse produto já está cadastrado "
						+ "em nosso sistema!"));
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> buscarPorNome (@PathVariable (value = "nome") String nome){
		return ResponseEntity.status(200).body(repository.findAllByNomeContainingIgnoreCase(nome));
				
	}
	
	@PutMapping ("/{id}/atualizar")
	public ResponseEntity <Produto> atualizarProduto (@Valid @PathVariable  (value = "id") Long id,
			@Valid @RequestBody Produto atualizacaoProduto) {
		return service.atualizarProduto(id, atualizacaoProduto)
				.map(produtoAtualizado -> ResponseEntity.status(201).body(produtoAtualizado))
				.orElse(ResponseEntity.status(304).build());
	}
	
	@DeleteMapping("/{id}/deletar")
	public ResponseEntity<Object> deletarCategoria (@PathVariable Long id){
		Optional<Produto> produtoExistente = repository.findById(id);
		
		if(produtoExistente.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(200).body("Produto deletado");
		} else {
			return ResponseEntity.status(200).body("Parece que o produto que você está tentando deletar não existe!");
		}
	}
}
