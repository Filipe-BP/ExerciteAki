package br.ucs.poo.exerciteaki;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Horario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String diaSemana;
	private Date horaAbertura;
	private Date horaFechamento;
	private Academia academia;
	
	public Horario() {
		
	}
	
	public Horario(String diaSemana, Date horaAbertura, Date horaFechamento, Academia academia) {
		this.diaSemana=diaSemana;
		this.horaAbertura=horaAbertura;
		this.horaFechamento=horaFechamento;
		this.academia = academia;
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

	public Academia getAcademia() {
    	return academia;
	}

	public void setAcademia(Academia academia) {
		this.academia = academia;
	}
	
	@Override
	public String toString() {
	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    return diaSemana + ": abre às " + sdf.format(horaAbertura) + ", fecha às " + sdf.format(horaFechamento);
	}
}