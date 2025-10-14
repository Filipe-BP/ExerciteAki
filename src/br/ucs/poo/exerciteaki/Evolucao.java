package br.ucs.poo.exerciteaki;

import java.time.LocalDate;

public class Evolucao {
	
	private LocalDate data;
	private Float peso;
	private Float massaMuscular;
	private Aluno aluno;
	
	public Evolucao() {}
	
	public Evolucao(LocalDate data, Float peso, Float massaMuscular, Aluno aluno) {
		super();
		this.data = data;
		this.peso = peso;
		this.massaMuscular = massaMuscular;
		this.aluno = aluno;
	}

	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public Float getPeso() {
		return peso;
	}
	
	public void setPeso(Float peso) {
		this.peso = peso;
	}
	
	public Float getMassaMuscular() {
		return massaMuscular;
	}
	
	public void setMassaMuscular(Float massaMuscular) {
		this.massaMuscular = massaMuscular;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}
