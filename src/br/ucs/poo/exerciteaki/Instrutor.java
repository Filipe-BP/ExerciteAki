package br.ucs.poo.exerciteaki;
import java.util.ArrayList;
import java.util.List;

public class Instrutor extends Pessoa {
	
	private Formacao formacao;
	private List<Treino> treinos;
	
	public Instrutor(String login, String password, Boolean administrador, int id, String nome, String email, String telefone,
			Formacao formacao, Academia academia) {
		super(academia, login, password, administrador,id, nome, email, telefone);
		this.formacao = formacao;
		this.treinos = new ArrayList<>();
	}

	public Formacao getFormacao() {
		return formacao;
	}

	public void setFormacao(Formacao formacao) {
		this.formacao = formacao;
	}

		// Métodos para Treinos
	public void adicionarTreino(Treino treino) {
		treinos.add(treino);
	}

	public void removerTreino(Treino treino) {
		treinos.remove(treino);
	}

	public void alterarTreino(int index, Treino novoTreino) {
		if (index >= 0 && index < treinos.size()) {
			treinos.set(index, novoTreino);
		}
	}

	public List<Treino> getTreinos() {
		return treinos;
	}

	// Método para vincular treino a um aluno
	public void vincularTreinoAoAluno(Treino treino, Aluno aluno) {
		if (!treinos.contains(treino)) {
			treinos.add(treino);
		}
		List<Treino> treinosDoAluno = aluno.getTreinos();
		if (!treinosDoAluno.contains(treino)) {
			treinosDoAluno.add(treino);
		}
	}

}
