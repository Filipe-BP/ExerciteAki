package br.ucs.poo.exerciteaki;

abstract class Usuario {
	
	private String login;
	private String password;
	private Boolean administrador;
	
	protected Usuario() {}

	protected Usuario(String login, String password, Boolean administrador) {
		super();
		this.login = login;
		this.password = password;
		this.administrador = administrador;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdministrador() {
		return Boolean.TRUE.equals(administrador);
	}

	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}

}
