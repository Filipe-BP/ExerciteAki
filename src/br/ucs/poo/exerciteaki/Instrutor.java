package br.ucs.poo.exerciteaki;

public class Instrutor extends Pessoa {
	
	private Formacao formacao;
	
	public Instrutor(String login, String password, Boolean administrador, int id, String nome, String email, String telefone,
			Formacao formacao) {
		super(login, password, administrador,id, nome, email, telefone);
		this.formacao = formacao;
	}

	public Formacao getFormacao() {
		return formacao;
	}

	public void setFormacao(Formacao formacao) {
		this.formacao = formacao;
	}

}
