package br.ucs.poo.exerciteaki;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Academia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String USER_DEFAULT = "admin";
	private static final String PWD_DEFAULT = "1234";
	
	private Usuario usuarioLogado;
	
	private int id;
	private String nome;
	private String telefone;
	private String website;
	private Endereco endereco;
	private List<Horario> horarios;
	private List<Aluno> alunos;
	private List<Instrutor> instrutores;
	private List<Aparelho> aparelhos;
	private Pessoa administrador;
    
	public Academia(String login, String password, int id, String nome, String telefone, String website, Endereco endereco, Pessoa administrador) {
		if (USER_DEFAULT.equalsIgnoreCase(login) && PWD_DEFAULT.equalsIgnoreCase(password)) {
			this.id = id;
			this.nome=nome;
			this.telefone=telefone;
			this.website=website;
			this.endereco=endereco;
			this.administrador = administrador;
			this.horarios = new ArrayList<>();
			this.alunos = new ArrayList<>();
			this.instrutores = new ArrayList<>();
			this.aparelhos = new ArrayList<>();
		} else {
			throw new RuntimeException("Usuário não possui acesso a cadastrar academia");
		}
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
	
	public Pessoa getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Pessoa administrador) {
		Storage.addPessoa(administrador);
		this.administrador = administrador;
	}
	
	//Métodos para login/logout
	public void login(String login, String password) {
		if (usuarioLogado != null) {
			return; // já possui usuário logado, efetuar logout
		}
		Object user = Storage.findUsuario(login, password);
		if (user != null) {
			usuarioLogado = (Usuario) user;
		}
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void logout() {
		usuarioLogado = null;
	}
	
	// Métodos para Horarios
	public void adicionarHorario(Horario horario) {
		if (this.usuarioLogado.isAdministrador()) {
			if (horarios.size() < 7) {
				horarios.add(horario);
			} else {
				System.out.println("A academia já possui 7 horários cadastrados.");
			}
		}
	}

	public void removerHorario(Horario horario) {
		if (this.usuarioLogado.isAdministrador()) {
			horarios.remove(horario);
		}
	}

	public void alterarHorario(int index, Horario novoHorario) {
		if (this.usuarioLogado.isAdministrador() && index >= 0 && index < horarios.size()) {
			horarios.set(index, novoHorario);
		}
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	// Métodos para Alunos
	public void adicionarAluno(Aluno aluno) {
		if (isAdminOuInstrutor()) {
			alunos.add(aluno);	
		}
	}
	
	public void adicionarAlunos(List<Aluno> alunos) {
		if (isAdminOuInstrutor()) {
//			for (int i = 0; i < alunos.size(); i++) {
//				this.alunos.add(alunos.get(i));
//			}
			this.alunos.addAll(alunos);
		}
	}

	public void removerAluno(Aluno aluno) {
		if(isAdminOuInstrutor()) {
			alunos.remove(aluno);
		}
	}

	public void alterarAluno(int index, Aluno novoAluno) {
		if (isAdminOuInstrutor() && index >= 0 && index < alunos.size()) {
			alunos.set(index, novoAluno);
		}
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	// Métodos para Instrutores
	public void adicionarInstrutor(Instrutor instrutor) {
		if (this.usuarioLogado.isAdministrador()) {
			instrutores.add(instrutor);
		}
	}
	
	public void adicionarInstrutores(List<Instrutor> instrutores) {
		if (this.usuarioLogado.isAdministrador()) {
			this.instrutores.addAll(instrutores);
		}
	}

	public void removerInstrutor(Instrutor instrutor) {
		if (this.usuarioLogado.isAdministrador()) {
			instrutores.remove(instrutor);
		}
	}

	public void alterarInstrutor(int index, Instrutor novoInstrutor) {
		if (this.usuarioLogado.isAdministrador() && index >= 0 && index < instrutores.size()) {
			instrutores.set(index, novoInstrutor);
		}
	}

	public List<Instrutor> getInstrutores() {
		return instrutores;
	}

	// Métodos para Aparelhos
	public void adicionarAparelho(Aparelho aparelho) {
		if (this.usuarioLogado.isAdministrador()) {
			aparelhos.add(aparelho);
		}
	}

	public void removerAparelho(Aparelho aparelho) {
		if (this.usuarioLogado.isAdministrador()) {
			aparelhos.remove(aparelho);
		}
	}

	public void alterarAparelho(int index, Aparelho novoAparelho) {
		if (this.usuarioLogado.isAdministrador() && index >= 0 && index < aparelhos.size()) {
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
			   "\nEndereço: " + (this.endereco != null ? this.endereco.toString() : "") +
			   "\nHorários: " + (horarios != null ? horarios.toString() : "") +
			   "\nAlunos: " + (alunos != null ? alunos.toString() : "") +
			   "\nInstrutores: " + (instrutores != null ? instrutores.toString() : "") +
			   "\nAparelhos: " + (aparelhos != null ? aparelhos.toString() : "");
	}
	
	public String exibirDadosPublicos() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Id: ").append(id);
	    sb.append("\nAcademia: ").append(nome);
	    sb.append("\nTelefone: ").append(telefone);
	    sb.append("\nWebsite: ").append(website);
	    sb.append("\nEndereço: ").append(endereco.toString());

	    sb.append("\nHorários: ").append(horarios.isEmpty() ? "[]" : horarios);

	    sb.append("\nAlunos:");
	    if (alunos.isEmpty()) {
	        sb.append(" Nenhum aluno cadastrado");
	    } else {
	        for (Aluno a : alunos) {
	            sb.append("\n - ").append(a.getNome());
	        }
	    }

	    sb.append("\nInstrutores:");
	    if (instrutores.isEmpty()) {
	        sb.append(" Nenhum instrutor cadastrado");
	    } else {
	        for (Instrutor i : instrutores) {
	            sb.append("\n - ").append(i.getNome());
	            if (i.getFormacao() != null) {
	                sb.append(" (").append(i.getFormacao().getNome()).append(")");
	            }
	        }
	    }

	    return sb.toString();
	}
	
	private boolean isAdminOuInstrutor() {
		return this.usuarioLogado.isAdministrador() || this.usuarioLogado instanceof Instrutor;
	}
}
