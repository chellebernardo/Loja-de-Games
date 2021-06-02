package com.minhaLojaDeGame.eCommerce.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_categoria")
public class Categoria {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idCategoria;
	
	@NotNull 
	@Size (min = 5, max = 30, message = "Tem que ter entre 5 e 30 caracteres!")
	private String nome;
	
	@OneToMany (mappedBy = "categoria", cascade = CascadeType.ALL)
	@JsonIgnoreProperties ("categoria")
	private List <Produto> meusProdutos;

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Produto> getMeusProdutos() {
		return meusProdutos;
	}

	public void setMeusProdutos(List<Produto> meusProdutos) {
		this.meusProdutos = meusProdutos;
	}
	
	
	
}
