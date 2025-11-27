package br.ucs.poo.exerciteaki;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
		throw new RuntimeException("Usuário não encontrado!");
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
		throw new RuntimeException("Academia com o id " + id + " não encontrada!");
	}
	
	public static Academia findAcademia(String nomeArquivo) {
		Academia data = (Academia) lerArquivoObj(nomeArquivo);
		if (data == null) {
			throw new RuntimeException("Academia não encontrada!");	
		}
		academias.add(data);
		return data;
	}
	
	public static String getAcademias() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < academias.size(); i++) {
			if (i > 0) {
				s.append(", ");
			}
			Academia acad = academias.get(i);
			if (acad != null) {
				s.append(acad.getId() + " " + acad.getNome());
			}
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
	
	public Academia carregarDados() {
//	    ObjectMapper mapper = new ObjectMapper();
	    try {
//	        return mapper.readValue(new File("academia.json"), Academia.class);
	    } catch (Exception e) {
	        e.printStackTrace();
//	        return new Academia(); 
	    }
	    return null;
	}
	
	public static void gravarArquivo(String nomeArquivo, List<?> list) {
		try {
			FileOutputStream fi = new FileOutputStream(nomeArquivo);
			ObjectOutputStream ou = new ObjectOutputStream(fi);
			
			ou.writeObject(list);
			ou.close();
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void gravarArquivo(String nomeArquivo, Object obj) {
		try {
			FileOutputStream fi = new FileOutputStream(nomeArquivo);
			ObjectOutputStream ou = new ObjectOutputStream(fi);
			
			ou.writeObject(obj);
			ou.close();
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<?> lerArquivo(String nomeArquivo) {
		List<?> data = new ArrayList<>();
		File f = new File(nomeArquivo);
		
		if (f.exists()) {
			try {
				FileInputStream fi = new FileInputStream(nomeArquivo);
				ObjectInputStream oi = new ObjectInputStream(fi);
				Object o = oi.readObject();
				data = (List<?>) o;
				
				oi.close();
				fi.close();
				
				return data;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public static Object lerArquivoObj(String nomeArquivo) {
		Object data = null;
		File f = new File(nomeArquivo);
		
		if (f.exists()) {
			try {
				FileInputStream fi = new FileInputStream(nomeArquivo);
				ObjectInputStream oi = new ObjectInputStream(fi);
				Object o = oi.readObject();
				data = (Object) o;
				
				oi.close();
				fi.close();
				
				return data;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
	public static boolean arquivoExiste(String nomeArquivo) {
		File f = new File(nomeArquivo);
		return f.exists();
	}
	
}
