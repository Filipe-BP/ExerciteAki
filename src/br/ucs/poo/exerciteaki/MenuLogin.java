package br.ucs.poo.exerciteaki;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuLogin {

    private static List<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // usuários de exemplo
        usuarios.add(new UsuarioPadrao("admin", "1234", true));
        usuarios.add(new UsuarioPadrao("vinicius", "senha123", false));

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Login");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa buffer

            switch (opcao) {
                case 1:
                    realizarLogin(scanner);
                    break;
                case 2:
                    continuar = false;
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }

    private static void realizarLogin(Scanner scanner) {
        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = autenticar(login, senha);

        if (usuario != null) {
            System.out.println("Login bem-sucedido!");
            System.out.println("Administrador: " + usuario.isAdministrador());
        } else {
            System.out.println("Login ou senha incorretos.");
        }
    }

    private static Usuario autenticar(String login, String senha) {
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login) && u.getPassword().equals(senha)) {
                return u;
            }
        }
        return null;
    }
}