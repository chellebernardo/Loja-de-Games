package com.minhaLojaDeGame.eCommerce.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minhaLojaDeGame.eCommerce.Model.Categoria;
import com.minhaLojaDeGame.eCommerce.Repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;
	
	/**
	 * Metodo utilizado para cadastrar uma nova categoria no sistema, validando sua
	 * existencia.
	 * 
	 * @param novaCategoria - String
	 * @since 1.0
	 * @author Chelle
	 * @return Optional com entidade Categoria dentro ou Optional vazio.
	 */
	public Optional<Object> cadastrarCategoria(Categoria novaCategoria) {
		Optional<Categoria> categoriaExistente = repository.findByNome(novaCategoria.getNome());

		if (categoriaExistente.isPresent()) {
			return Optional.empty();
		} else {
			return Optional.ofNullable(repository.save(novaCategoria));
		}
	}
	/**
	 * Método utilizado para atualizar uma categoria existente.
	 * 
	 * @param id - Long
	 * @param atualizacaoCategoria - representando a Entidade Categoria
	 * @since 1.0
	 * @author Chelle
	 * @return Retorna um Optional com entidade Categoria caso o mesmo exista, do
	 *         contrário um Optional vazio
	 */
	public Optional<Categoria> atualizarCategoria(Long id, Categoria atualizacaoCategoria) {
		Optional<Categoria> categoriaExistente = repository.findById(id);

		if (categoriaExistente.isPresent()) {
			categoriaExistente.get().setNome(atualizacaoCategoria.getNome());
			return Optional.ofNullable(repository.save(categoriaExistente.get()));
		} else {
			return Optional.empty();
		}
	}
}
