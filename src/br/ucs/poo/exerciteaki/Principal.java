package br.ucs.poo.exerciteaki;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String login;
		String senha;
		
		System.out.println("Informe o login: ");
		login = sc.nextLine();
		
		System.out.println("Informe a senha: ");
		senha = sc.nextLine();
		
		Contexto.login(login, senha);
		System.out.println("\nUsuário logado:" + Contexto.getUsuarioLogado());
		
		Pessoa administrador = new Pessoa("diogenes123", "12345", true, "DONO", "diogenes@gmail.com", "54987345678");
		administrador.cadastrar();
		System.out.println("\n\nPessoas cadastradas: " + Storage.getPessoas());
		
		System.out.println("\n\nUsuário administrador: " + administrador);
		
		Contexto.logout();
		System.out.println("\n\nUsuário após logout: " + Contexto.getUsuarioLogado());
		
		sc.close();
	}

}
