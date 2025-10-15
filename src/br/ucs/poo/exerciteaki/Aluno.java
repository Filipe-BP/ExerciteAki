package br.ucs.poo.exerciteaki;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Aluno extends Pessoa {
	private Date dataNascimento;
    private float altura;
    
    // Associações
    private Academia academia;
    private List<Frequencia> frequencias;
    private List<Evolucao> evolucoes;
    private List<Instrutor> instrutores;
    private List<Treino> treinos;
    
    public Aluno() {
        super();
        this.frequencias = new ArrayList<>();
        this.evolucoes = new ArrayList<>();
        this.instrutores = new ArrayList<>();
        this.treinos = new ArrayList<>();
    }

	public Aluno(String login, String password, Boolean administrador, int id, String nome, String email, String telefone,
			Date dataNascimento, float altura, Academia academia) {
        super(login, password, administrador, id, nome, email, telefone);
        this.dataNascimento = dataNascimento;
        this.altura = altura;
        this.academia = academia;
        this.frequencias = new ArrayList<>();
        this.evolucoes = new ArrayList<>();
        this.instrutores = new ArrayList<>();
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

    public Academia getAcademia() {
        return academia;
    }

    public void setAcademia(Academia academia) {
        this.academia = academia;
    }

    public List<Frequencia> getFrequencias() {
        return frequencias;
    }

    public void adicionarFrequencia(Frequencia frequencia) {
        this.frequencias.add(frequencia);
    }

    public List<Evolucao> getEvolucoes() {
        return evolucoes;
    }

    public void adicionarEvolucao(Evolucao evolucao) {
        this.evolucoes.add(evolucao);
    }

    public List<Instrutor> getInstrutores() {
        return instrutores;
    }

    public void adicionarInstrutor(Instrutor instrutor) {
        this.instrutores.add(instrutor);
    }

    public List<Treino> getTreinos() {
        return treinos;
    }

    public void adicionarTreino(Treino treino) {
        this.treinos.add(treino);
    }

    @Override
    public String toString() {
        return super.toString() +
               "\nData de Nascimento: " + dataNascimento +
               "\nAltura: " + altura;
    }
    
}
