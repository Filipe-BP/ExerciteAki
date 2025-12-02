package br.ucs.poo.exerciteaki;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Frequencia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private LocalDate data;
	private LocalDateTime dataHoraEntrada;
	private LocalDateTime dataHoraSaida;
	
	@JsonIgnore
	private Aluno aluno;
	
	public Frequencia() {}
	
	public Frequencia(LocalDate data, LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaida, Aluno aluno) {
		super();
		this.data = data;
		this.dataHoraEntrada = dataHoraEntrada;
		this.dataHoraSaida = dataHoraSaida;
		this.aluno = aluno;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public LocalDateTime getDataHoraEntrada() {
		return dataHoraEntrada;
	}
	
	public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
		this.dataHoraEntrada = dataHoraEntrada;
	}
	
	public LocalDateTime getDataHoraSaida() {
		return dataHoraSaida;
	}
	
	public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
		this.dataHoraSaida = dataHoraSaida;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
}
