package br.ucs.poo.exerciteaki;

import java.util.Date;

public class Horario {
	private String diaSemana;
	private Date horaAbertura;
	private Date horaFechamento;
	
	public Horario() {
		
	}
	
	public Horario(String diaSemana, Date horaAbertura, Date horaFechamento) {
		this.diaSemana=diaSemana;
		this.horaAbertura=horaAbertura;
		this.horaFechamento=horaFechamento;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Date getHoraAbertura() {
		return horaAbertura;
	}

	public void setHoraAbertura(Date horaAbertura) {
		this.horaAbertura = horaAbertura;
	}

	public Date getHoraFechamento() {
		return horaFechamento;
	}

	public void setHoraFechamento(Date horaFechamento) {
		this.horaFechamento = horaFechamento;
	}
	
	@Override
    public String toString() {
        return diaSemana + ": abre às " + horaAbertura + ", fecha às " + horaFechamento;
    }
	
}
