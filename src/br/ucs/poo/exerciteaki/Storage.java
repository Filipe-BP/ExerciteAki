package br.ucs.poo.exerciteaki;

import java.util.ArrayList;
import java.util.List;

abstract class Storage {
	
	private static List<Pessoa> pessoas = new ArrayList<>();
	
	private Storage() {}
	
	public static void addPessoa(Pessoa pessoa) {
		pessoas.add(pessoa);
	}
	
	public static Usuario findUsuario(String login, String password) {
		for (int i = 0; i < pessoas.size(); i++) {
			if (pessoas.get(i) != null && login.equalsIgnoreCase(pessoas.get(i).getLogin())
					&& password.equalsIgnoreCase(pessoas.get(i).getPassword())) {
				return pessoas.get(i);
			}
		}
		return null; // Usuário não encontrado
	}

	public static void removePessoa(Pessoa pessoa) {
		pessoas.remove(pessoa);
	}
	
	public static String getPessoas() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < pessoas.size(); i++) {
			if (i > 0) {
				s.append(", ");
			}
			s.append(pessoas.get(i).toString());
		}
		return s.toString();
	}
}
