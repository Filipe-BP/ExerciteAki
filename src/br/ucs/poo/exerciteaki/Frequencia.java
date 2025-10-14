package br.ucs.poo.exerciteaki;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Frequencia {
	
	private LocalDate data;
	private LocalDateTime dataHoraEntrada;
	private LocalDateTime dataHoraSaida;
	
	public Frequencia() {}
	
	public Frequencia(LocalDate data, LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaida) {
		super();
		this.data = data;
		this.dataHoraEntrada = dataHoraEntrada;
		this.dataHoraSaida = dataHoraSaida;
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
	
	
}
