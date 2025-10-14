package br.ucs.poo.exerciteaki;

public class Contexto {
	
	private static final String USER_DEFAULT = "admin";
	private static final String PWD_DEFAULT = "012345678";
	
	private Contexto(){}
	
	private static Usuario usuarioLogado;

	public static void login(String login, String password) {
		if (usuarioLogado != null) {
			return; // já possui usuário logado, efetuar logout
		}
		if (USER_DEFAULT.equalsIgnoreCase(login) && PWD_DEFAULT.equalsIgnoreCase(password)) {
			Pessoa userDefault = new Pessoa(USER_DEFAULT, PWD_DEFAULT, true);
			Storage.addPessoa(userDefault);
		}
		Usuario user = Storage.findUsuario(login, password);
		if (user != null) {
			usuarioLogado = user;
		}
	}
	
	public static Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public static void logout() {
		usuarioLogado = null;
	}
	
}
