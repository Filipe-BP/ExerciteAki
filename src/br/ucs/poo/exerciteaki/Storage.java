package br.ucs.poo.exerciteaki;

import java.util.ArrayList;
import java.util.List;

abstract class Storage {
	
	private static List<Academia> academias = new ArrayList<>();
	private static List<Pessoa> pessoas = new ArrayList<>();
	private static List<Instrutor> instrutores = new ArrayList<>();
	private static List<Pessoa> alunos = new ArrayList<>();
	
	private Storage() {}
	
	public static void addAcademia(Academia academia) {
		academias.add(academia);
	}
	
	public static void addPessoa(Pessoa pessoa) {
		pessoas.add(pessoa);
	}
	
	public static Object findUsuario(String login, String password) {
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
	
	public static Academia findAcademia(int id) {
		for (int i = 0; i < academias.size(); i++) {
			Academia academia = academias.get(i);
			if (academia != null && academia.getId() == id) {
				return academia;
			}
		}
		System.out.println("Academia com o id " + id + " não encontrada!");
		return null;
	}
	
	public static String getAcademias() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < academias.size(); i++) {
			if (i > 0) {
				s.append(", ");
			}
			s.append(academias.get(i).toString());
		}
		return s.toString();
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
