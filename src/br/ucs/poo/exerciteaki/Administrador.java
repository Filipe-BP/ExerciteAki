package br.ucs.poo.exerciteaki;

public class Administrador extends Pessoa {

    public Administrador(String login, String password, Boolean administrador, int id, String nome, String email, String telefone, Academia academia) {
        super(academia, login, password, administrador, id, nome, email, telefone);
    }

    public void acessarPainelAdmin() {
        System.out.println("Painel do administrador acessado.");
    }
}