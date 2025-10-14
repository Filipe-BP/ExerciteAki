package br.ucs.poo.exerciteaki;

public class Aparelho {
	
	private String nome;
	private String descricao;
	private String funcao;
	private Academia academia;
	
	public Aparelho() {}
	
	public Aparelho(String nome, String descricao, String funcao, Academia academia) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.funcao = funcao;
		this.academia = academia;
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
	
}
