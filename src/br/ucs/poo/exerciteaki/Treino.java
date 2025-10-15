package br.ucs.poo.exerciteaki;

import java.util.ArrayList;
import java.util.List;

public class Treino {
	
	private int id;
	private Integer diaSemana;
	private String nome;
	private List<Exercicio> exercicios;
	
	public Treino() {}
	
	public Treino(int id, Integer diaSemana, String nome) {
		this.id = id;
		this.diaSemana = diaSemana;
		this.nome = nome;
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

	public List<Exercicio> getExercicios() {
		return exercicios;
	}

	public void adicionarExercicio(Exercicio exercicio) {
		this.exercicios.add(exercicio);
	}

}
