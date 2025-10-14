package br.ucs.poo.exerciteaki;

public class Exercicio {
	
	private Integer ordem;
	private Float carga;
	private Integer numeroRepeticoes;
	private Aparelho aparelho;
	
	public Exercicio() {}
	
	public Exercicio(Integer ordem, Float carga, Integer numeroRepeticoes, Aparelho aparelho) {
		super();
		this.ordem = ordem;
		this.carga = carga;
		this.numeroRepeticoes = numeroRepeticoes;
		this.aparelho = aparelho;
	}
	
	public Integer getOrdem() {
		return ordem;
	}
	
	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}
	
	public Float getCarga() {
		return carga;
	}
	
	public void setCarga(Float carga) {
		this.carga = carga;
	}
	
	public Integer getNumeroRepeticoes() {
		return numeroRepeticoes;
	}
	
	public void setNumeroRepeticoes(Integer numeroRepeticoes) {
		this.numeroRepeticoes = numeroRepeticoes;
	}

	public Aparelho getAparelho() {
		return aparelho;
	}

	public void setAparelho(Aparelho aparelho) {
		this.aparelho = aparelho;
	}
	
	

}
