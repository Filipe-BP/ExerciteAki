package br.ucs.poo.exerciteaki;

public class Pessoa extends Usuario {

	private int id;
	private Academia academia;
	private String nome;
	private String email;
	private String telefone;
	
	public Pessoa() {}
	
	public Pessoa(Academia academia, String login, String password, Boolean administrador, int id, String nome, String email, String telefone) {
		super(login, password, administrador);
		this.id = id;
		this.nome=nome;
		this.email=email;
		this.telefone=telefone;
		this.academia=academia;
	}
	
	public Pessoa(String login, String password, Boolean administrador) {
		super(login, password, administrador);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public Academia getAcademia() {
		return academia;
	}

	public void setAcademia(Academia academia) {
		this.academia = academia;
	}
	
	@Override
    public String toString() {
        return "Id: " + id +
			   "\nNome: " + nome +
               "\nEmail: " + email +
               "\nTelefone: " + telefone;
    }
}
