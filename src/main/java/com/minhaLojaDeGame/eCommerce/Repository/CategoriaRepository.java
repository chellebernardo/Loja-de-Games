package com.minhaLojaDeGame.eCommerce.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhaLojaDeGame.eCommerce.Model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	
	public List <Categoria> findAllByNomeContainingIgnoreCase (String nome);
	
	public Optional <Object> findByNomeContainingIgnoreCase (String nome); 
	
	public Optional<Categoria> findByNome (String nome);
}
