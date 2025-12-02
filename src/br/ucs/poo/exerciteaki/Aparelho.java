package br.ucs.poo.exerciteaki;

import java.io.Serializable;

public class Aparelho implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nome;
	private String descricao;
	private String funcao;
	private Academia academia;
	
	public Aparelho() {}
	
	public Aparelho(int id,String nome, String descricao, String funcao, Academia academia) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.funcao = funcao;
		this.academia = academia;
	}
	public Aparelho(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	public String getFuncao() {
		return funcao;
	}
	
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	
	public Academia getAcademia() {
		return academia;
	}
	
	public void setAcademia(Academia academia) {
		this.academia = academia;
	}
	
	@Override
	public String toString() {
	    return String.format(
	        "ID: %d%n" +
	        "Nome: %s%n" +
	        "Função: %s%n" +
	        "Descrição: %s",
	        this.id,
	        this.nome,
	        this.funcao,
	        this.descricao
	    );
	}
	
	public String toStringSimples() {
	    return nome;
	}

}
