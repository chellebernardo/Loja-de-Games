package com.minhaLojaDeGame.eCommerce.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhaLojaDeGame.eCommerce.Model.Produto;
import com.minhaLojaDeGame.eCommerce.Repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	/**
	 * Método utulizado para cadastrar um novo produto no sistema caso o mesmo ainda não exista.
	 * @param novoProduto - String 
	 * @author Chelle
	 * @since 1.0
	 * @return Retorna um Optional com entidade Produto caso o mesmo ainda não tenha sido registrado,
	 * senão retorna um Optional vazio.
	 */
	public Optional<Object> cadastrarProduto (Produto novoProduto) {
		Optional<Produto> produtoExistente = repository.findByNome(novoProduto.getNome());

		if (produtoExistente.isPresent()) {
			return Optional.empty();
		} else {
			return Optional.ofNullable(repository.save(novoProduto));
		}
	}
	/**
	 * Método utilizado para atualizar um produto existente (nome, descricao e categoria).
	 * @param id - Long
	 * @param atualizacaoProduto - representando entidade Produto.
	 * @author Chelle
	 * @since 1.0
	 * @return Retorna um Optional com entidade Produto caso o mesmo exista,
	 *         senão retorna um Optional vazio.
	 */
	public Optional<Produto> atualizarProduto(Long id, Produto atualizacaoProduto) {
		Optional<Produto> produtoExistente = repository.findById(id);

		if (produtoExistente.isPresent()) {
			produtoExistente.get().setNome(atualizacaoProduto.getNome());
			produtoExistente.get().setDescricao(atualizacaoProduto.getDescricao());
			produtoExistente.get().setCategoria(atualizacaoProduto.getCategoria());
			return Optional.ofNullable(repository.save(produtoExistente.get()));
		} else {
			return Optional.empty();
		}
	}
}
