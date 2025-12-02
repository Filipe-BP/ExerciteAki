package br.ucs.poo.exerciteaki.entities;

import java.io.Serializable;

public class Exercicio implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer ordem;            // ordem do exercício no treino
    private Float carga;              // carga em kg
    private Integer numeroRepeticoes; // número de repetições
    private Aparelho aparelho;        // aparelho usado
    
    public Exercicio() {}
    
    public Exercicio(Integer ordem, Float carga, Integer numeroRepeticoes, Aparelho aparelho) {
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

    @Override
    public String toString() {
        return "Exercício " + ordem + ": " 
             + (aparelho != null ? aparelho.getNome() : "Sem aparelho") 
             + " | Carga: " + carga + "kg"
             + " | Repetições: " + numeroRepeticoes;
    }
}
