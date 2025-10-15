package br.ucs.poo.exerciteaki;

import java.util.ArrayList;
import java.util.List;

public class Academia {
	private int id;
	private String nome;
	private String telefone;
	private String website;
	private Endereco endereco;
	private List<Horario> horarios;
	private List<Aluno> alunos;
	private List<Instrutor> instrutores;
	private List<Aparelho> aparelhos;

	public Academia () {
		this.horarios = new ArrayList<>();
		this.alunos = new ArrayList<>();
		this.instrutores = new ArrayList<>();
		this.aparelhos = new ArrayList<>();
	}
    
	public Academia(int id, String nome, String telefone, String website, Endereco endereco) {
		this.id = id;
		this.nome=nome;
		this.telefone=telefone;
		this.website=website;
		this.endereco=endereco;
		this.horarios = new ArrayList<>();
		this.alunos = new ArrayList<>();
		this.instrutores = new ArrayList<>();
		this.aparelhos = new ArrayList<>();
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	// Métodos para Horarios
	public void adicionarHorario(Horario horario) {
		if (horarios.size() < 7) {
			horarios.add(horario);
		} else {
			System.out.println("A academia já possui 7 horários cadastrados.");
		}
	}

	public void removerHorario(Horario horario) {
		horarios.remove(horario);
	}

	public void alterarHorario(int index, Horario novoHorario) {
		if (index >= 0 && index < horarios.size()) {
			horarios.set(index, novoHorario);
		}
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	// Métodos para Alunos
	public void adicionarAluno(Aluno aluno) {
		alunos.add(aluno);
	}

	public void removerAluno(Aluno aluno) {
		alunos.remove(aluno);
	}

	public void alterarAluno(int index, Aluno novoAluno) {
		if (index >= 0 && index < alunos.size()) {
			alunos.set(index, novoAluno);
		}
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	// Métodos para Instrutores
	public void adicionarInstrutor(Instrutor instrutor) {
		instrutores.add(instrutor);
	}

	public void removerInstrutor(Instrutor instrutor) {
		instrutores.remove(instrutor);
	}

	public void alterarInstrutor(int index, Instrutor novoInstrutor) {
		if (index >= 0 && index < instrutores.size()) {
			instrutores.set(index, novoInstrutor);
		}
	}

	public List<Instrutor> getInstrutores() {
		return instrutores;
	}

	// Métodos para Aparelhos
	public void adicionarAparelho(Aparelho aparelho) {
		aparelhos.add(aparelho);
	}

	public void removerAparelho(Aparelho aparelho) {
		aparelhos.remove(aparelho);
	}

	public void alterarAparelho(int index, Aparelho novoAparelho) {
		if (index >= 0 && index < aparelhos.size()) {
			aparelhos.set(index, novoAparelho);
		}
	}

	public List<Aparelho> getAparelhos() {
		return aparelhos;
	}
	
	@Override
	public String toString() {
		return "Id: " + id +
			   "\nAcademia: " + nome +
			   "\nTelefone: " + telefone +
			   "\nWebsite: " + website +
			   "\nEndereço: " + endereco.toString() +
			   "\nHorários: " + horarios.toString() +
			   "\nAlunos: " + alunos.toString() +
			   "\nInstrutores: " + instrutores.toString() +
			   "\nAparelhos: " + aparelhos.toString();
	}
	
	public Academia cadastrar(Academia academia) {
		Usuario usuario = Contexto.getUsuarioLogado();
		if (usuario.isAdministrador()) {
			// adicionar academia ao "banco"
			return academia;
		}
		return null;
	}
}
