package com.minhaLojaDeGame.eCommerce.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name = "tb_produto")
public class Produto {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotNull
		@Size (min = 5, max = 20, message = "titulo min 5, max 20")
		private String nome;

		@NotNull
		@Column(name = "Descricao", length = 600)
		private String descricao;
		
		@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
		@JoinColumn(name = "produtos")
		private Categoria categoria;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public Categoria getCategoria() {
			return categoria;
		}

		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
		}

}

