package com.minhaLojaDeGame.eCommerce.Controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		return ResponseEntity.status(200).body(repository.findAll());
	}
	
	@PostMapping ("/cadastrar")
	public ResponseEntity<Object> cadastrarCategoria (@Valid @RequestBody Produto novoProduto){
		return service.cadastrarProduto(novoProduto)
				.map(produtoCadastrado -> ResponseEntity.status(201).body(produtoCadastrado))
				.orElse(ResponseEntity.status(200).body("Ooops.. parece que esse produto já está cadastrado "
						+ "em nosso sistema!"));
	}
}
