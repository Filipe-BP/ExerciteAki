package br.ucs.poo.exerciteaki;

import java.util.Random;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		System.out.println("Seja bem-vindo ao ExerciteAki!\n\n");
		
		Scanner sc = new Scanner(System.in);
		Academia main = null;
		int opcao = 0;
		
		while (opcao != 1 && opcao != 4) {
			System.out.println("Selecione a opção que deseja:");
			System.out.println("1 - Logar em academia\n2 - Cadastrar academia\n3 - Voltar\n4 - Sair");
			opcao = sc.nextInt();
			
			switch (opcao) {
			case 1: {
				main = selecionarAcademia(sc);
				break;
			}
			case 2: {
				main = cadastrarAcademia(sc);
				break;
			}
			case 3: {
				break;
			}
			case 4: {
				System.out.println("Até logo!");
				break;
			}
			default:
				sc.close();
				throw new RuntimeException("Opção selecionada inválida!");
			}
		}
		
		if (main == null || opcao == 4) {
			sc.close();
			return;
		}
		
		System.out.println("Academia selecionada:\n " + main.toString());
		System.out.println("\n\n");
		
		sc.nextLine();
		System.out.println("Informe o login: ");
		String login = sc.nextLine();
		
		System.out.println("Informe a senha: ");
		String senha = sc.nextLine();

		main.login(login, senha);
		System.out.println("\nUsuário logado:\n" + main.getUsuarioLogado());
		
		sc.close();
	}

	private static Academia selecionarAcademia(Scanner sc) {
		Academia main;
		System.out.println("Selecione o id da academia desejada:");
		System.out.println(Storage.getAcademias());
		int id = sc.nextInt();
		main = Storage.findAcademia(id);
		return main;
	}

	private static Academia cadastrarAcademia(Scanner sc) {
		System.out.println("*******************************************\n");
		
		sc.nextLine();
		System.out.println("Login: ");
		String login = sc.nextLine();
		
		System.out.println("Senha: ");
		String senha = sc.nextLine();
		
		System.out.println("Nome: ");
		String nome = sc.nextLine();
		
		System.out.println("Telefone: ");
		String telefone = sc.nextLine();
		
		System.out.println("Website: ");
		String website = sc.nextLine();
		
		Random seq = new Random();
		Academia academia = new Academia(login, senha, seq.nextInt(), nome, telefone, website, null, null);
		
		System.out.println("Deseja cadastrar endereço? (s/n)");
		String resp = sc.nextLine();
		if ("s".equalsIgnoreCase(resp)) {
			cadastrarEndereco(sc, login, seq, academia);
		}
		
		System.out.println("Deseja cadastrar administrador?");
		String res = sc.nextLine();
		if ("s".equalsIgnoreCase(res)) {
			cadastrarAdministrador(sc, academia, seq);
		}
		
		Storage.addAcademia(academia);
		
		System.out.println("Academia cadastrada com sucesso!\n");
		System.out.println("*******************************************\n");
		return academia;
	}

	private static void cadastrarEndereco(Scanner sc, String login, Random seq, Academia academia) {
		System.out.println("Logradouro: ");
		String rua = sc.nextLine();
		
		System.out.println("Número: ");
		String numero = sc.nextLine();
		
		System.out.println("Complemento: ");
		String complemento = sc.nextLine();
		
		System.out.println("Bairro: ");
		String bairro = sc.nextLine();
		
		System.out.println("CEP: ");
		String cep = sc.nextLine();
		
		System.out.println("Cidade: ");
		String cidade = sc.nextLine();
		
		System.out.println("Estado: ");
		String estado = sc.nextLine();
		
		Endereco endereco = new Endereco(seq.nextInt(), login, numero, complemento, bairro, cep, cidade, estado);
		academia.setEndereco(endereco);
	}

	private static void cadastrarAdministrador(Scanner sc, Academia academia, Random seq) {
		System.out.println("Login: ");
		String login = sc.nextLine();
		
		System.out.println("Senha: ");
		String senha = sc.nextLine();
		
		System.out.println("Nome: ");
		String nome = sc.nextLine();
		
		System.out.println("Email: ");
		String email = sc.nextLine();
		
		System.out.println("Telefone: ");
		String telefone = sc.nextLine();
		
		Pessoa admin = new Pessoa(academia, login, senha, true, seq.nextInt(), nome, email, telefone);
		academia.setAdministrador(admin);
	}

}
