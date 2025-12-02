package br.ucs.poo.exerciteaki;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Treino implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private Integer diaSemana;
	private String nome;
	private List<Exercicio> exercicios;
	
	@JsonIgnore
	private Aluno aluno;
	
	public Treino() {}
	
	public Treino(int id, Integer diaSemana, String nome, Aluno aluno) {
		this.id = id;
		this.diaSemana = diaSemana;
		this.nome = nome;
		this.aluno = aluno;
		this.exercicios = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(Integer diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Aluno getAluno() {
	    return aluno;
	}

	public void setAluno(Aluno aluno) {
	    this.aluno = aluno;
	}

	// Métodos padrão para lista de exercícios
	public List<Exercicio> getExercicios() {
		return exercicios;
	}

	public void adicionarExercicio(Exercicio exercicio) {
		exercicios.add(exercicio);
	}

	public void removerExercicio(Exercicio exercicio) {
		exercicios.remove(exercicio);
	}

	public void alterarExercicio(int index, Exercicio novoExercicio) {
		if (index >= 0 && index < exercicios.size()) {
			exercicios.set(index, novoExercicio);
		}
	}

}
