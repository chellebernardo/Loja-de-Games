package com.minhaLojaDeGame.eCommerce.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhaLojaDeGame.eCommerce.Model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	
	public List <Produto> findAllByNomeContainingIgnoreCase (String nome);
	
	public Optional <Object> findByNomeContainingIgnoreCase (String nome); 
	
	public Optional<Produto> findByNome (String nome);

}
