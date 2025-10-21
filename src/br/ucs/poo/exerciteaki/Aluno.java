package br.ucs.poo.exerciteaki;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa {
	private Date dataNascimento;
    private float altura;
    
    // Associações
    private List<Frequencia> frequencias;
    private List<Evolucao> evolucoes;
    private List<Treino> treinos;
    
    public Aluno() {
        super();
        this.frequencias = new ArrayList<>();
        this.evolucoes = new ArrayList<>();
        this.treinos = new ArrayList<>();
    }

    public Aluno(String login, String password, Boolean administrador, int id, Academia academia, String nome, String email, String telefone,
            Date dataNascimento, float altura) {
        super(academia, login, password, administrador, id, nome, email, telefone);
        this.dataNascimento = dataNascimento;
        this.altura = altura;
        this.frequencias = new ArrayList<>();
        this.evolucoes = new ArrayList<>();
        this.treinos = new ArrayList<>();
    }
    
    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    // Métodos para Treinos
    
    public List<Treino> getTreinos() {
        return treinos;
    }
    
    public void adicionarTreino(Treino treino) {
        treinos.add(treino);
    }
    
    public void removerTreino(Treino treino) {
        treinos.remove(treino);
    }
    // O aluno só pode consultar os treinos

    // Métodos para Frequencias
    public List<Frequencia> getFrequencias() {
        return frequencias;
    }

    public void adicionarFrequencia(Frequencia frequencia) {
        frequencias.add(frequencia);
    }

    public void removerFrequencia(Frequencia frequencia) {
        frequencias.remove(frequencia);
    }

    public void alterarFrequencia(int index, Frequencia novaFrequencia) {
        if (index >= 0 && index < frequencias.size()) {
            frequencias.set(index, novaFrequencia);
        }
    }

    // Métodos para Evolucoes
    public List<Evolucao> getEvolucoes() {
        return evolucoes;
    }

    public void adicionarEvolucao(Evolucao evolucao) {
        evolucoes.add(evolucao);
    }

    public void removerEvolucao(Evolucao evolucao) {
        evolucoes.remove(evolucao);
    }

    public void alterarEvolucao(int index, Evolucao novaEvolucao) {
        if (index >= 0 && index < evolucoes.size()) {
            evolucoes.set(index, novaEvolucao);
        }
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nData de Nascimento: " + dataNascimento +
               "\nAltura: " + altura +
               "\nTreinos: " + treinos;
    }
    
}
