package br.ucs.poo.exerciteaki;

import java.util.Random;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Academia main;
		int opcao;
		
		System.out.println("Seja bem-vindo ao ExerciteAki!\n\n");
		System.out.println("Selecione a opção que deseja:");
		System.out.println("1 - Logar em academia");
		System.out.println("2 - Cadastrar academia");
		opcao = sc.nextInt();
		
		if (opcao == 1) {
			System.out.println("Selecione o id da academia desejada:");
			System.out.println(Storage.getAcademias());
			int id = sc.nextInt();
			main = Storage.findAcademia(id);
		} else {
			String login;
			String senha;
			String nome;
			String telefone;
			String website;
			
			System.out.println("Login: ");
			login = sc.nextLine();
			
			System.out.println("Senha: ");
			senha = sc.nextLine();
			
			System.out.println("Nome: ");
			nome = sc.nextLine();
			
			System.out.println("Telefone: ");
			telefone = sc.nextLine();
			
			System.out.println("Website: ");
			website = sc.nextLine();
			
			Random seq = new Random();
			Academia academiaCriada = new Academia(login, senha, seq.nextInt(), nome, telefone, website, null, null);
			Storage.addAcademia(academiaCriada);
			
			System.out.println("Academia cadastrada com sucesso!");
			main = academiaCriada;
		}
		
		System.out.println("Academia selecionada:\n " + main.toString());
		
		System.out.println("Informe o login: ");
		String login = sc.nextLine();
		
		System.out.println("Informe a senha: ");
		String senha = sc.nextLine();

		main.login(login, senha);
		System.out.println("\nUsuário logado:\n" + main.getUsuarioLogado());
		
		
		sc.close();
	}

}
