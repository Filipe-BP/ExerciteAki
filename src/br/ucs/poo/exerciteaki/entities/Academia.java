package br.ucs.poo.exerciteaki.entities;

import static br.ucs.poo.exerciteaki.utils.Utils.equalsIgnoreCase;
import static br.ucs.poo.exerciteaki.utils.Utils.isEmpty;
import static br.ucs.poo.exerciteaki.utils.Utils.isNotEmpty;
import static br.ucs.poo.exerciteaki.utils.Utils.isNotNull;

import java.io.Serializable;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.ucs.poo.exerciteaki.exception.AcessoNegadoException;
import br.ucs.poo.exerciteaki.file.Storage;

public class Academia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String USER_DEFAULT = "admin";
	public static final String PWD_DEFAULT = "1234";
	
	@JsonIgnore
	private transient Usuario usuarioLogado;
	
	private int id;
	private String nome;
	private String telefone;
	private String website;
	private Endereco endereco;
	private List<Horario> horarios;
	private List<Aluno> alunos;
	private List<Instrutor> instrutores;
	private List<Aparelho> aparelhos;
	private Administrador administrador;
	
	public Academia() {}
    
	public Academia(String login, String password, int id, String nome, String telefone, String website, Endereco endereco) throws AcessoNegadoException {
		if (validaUsuarioAdminPadrao(login, password)) {
			this.id = id;
			this.nome=nome;
			this.telefone=telefone;
			this.website=website;
			this.endereco=endereco;
			this.horarios = new ArrayList<>();
			this.alunos = new ArrayList<>();
			this.instrutores = new ArrayList<>();
			this.aparelhos = new ArrayList<>();
		} else {
			throw new AcessoNegadoException("Usuário não possui acesso a cadastrar academia");
		}
	}
	
	public Academia(int id, String nome, String telefone, String website, Endereco endereco, Administrador administrador) throws AcessoNegadoException {
		if (isNotNull(administrador)) {
			this.id = id;
			this.nome=nome;
			this.telefone=telefone;
			this.website=website;
			this.endereco=endereco;
			this.horarios = new ArrayList<>();
			this.alunos = new ArrayList<>();
			this.instrutores = new ArrayList<>();
			this.aparelhos = new ArrayList<>();
		} else {
			throw new AcessoNegadoException("Usuário não possui acesso a cadastrar academia");
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

	public void setAdministrador(Administrador administrador) {
		if (validaUsuarioAdminPadrao(administrador.getLogin(), administrador.getPassword()) 
				|| (isNotNull(this.usuarioLogado) && this.usuarioLogado.isAdministrador())) {
			this.administrador = administrador;			
		}
	}
	
	//Métodos para login/logout
	public void login(String login, String password) throws AcessoNegadoException {
		if (this.usuarioLogado != null) {
			return; // já possui usuário logado, efetuar logout
		}
		Usuario user = getUsuario(login, password);
		if (user != null) {
			this.usuarioLogado = user;
		}
	}
	
	public Object getPessoa(String login, String password) throws AcessoNegadoException {
		if (isEmpty(password) || isEmpty(login)) {
			throw new AcessoNegadoException("Usuário ou senha inválidos");
		}
		
		if (isNotNull(this.administrador) 
				&& equalsIgnoreCase(login, this.administrador.getLogin()) 
				&& equalsIgnoreCase(password, this.administrador.getPassword())) {
			return this.administrador;
		}
		
		if (isNotEmpty(this.alunos)) {
			Aluno existent = getAlunoByLoginAndPass(login, password);
			if (isNotNull(existent)) return existent;
		}
		
		if (isNotEmpty(this.instrutores)) {
			Instrutor existent = getInstrutorByLoginAndPass(login, password);
			if (isNotNull(existent)) return existent;
		}
		
		throw new AcessoNegadoException("Usuário não encontrado");
	}
	
	public Usuario getUsuario(String login, String password) throws AcessoNegadoException {
		return (Usuario) this.getPessoa(login, password);
	}
	
	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}
	
	public void logout() {
		usuarioLogado = null;
		Storage.salvarDados(this);
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
	
	public Aluno getAlunoByLoginAndPass(String login, String password) {
		for (int i = 0; i < this.alunos.size(); i++) {
			if (this.alunos.get(i) != null 
					&& equalsIgnoreCase(login, this.alunos.get(i).getLogin())
					&& equalsIgnoreCase(password, this.alunos.get(i).getPassword())) {
				return this.alunos.get(i);
			}
		}	
		return null;
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
	
	public Instrutor getInstrutorByLoginAndPass(String login, String password) {
		for (int i = 0; i < this.instrutores.size(); i++) {
			if (this.instrutores.get(i) != null 
					&& equalsIgnoreCase(login, this.instrutores.get(i).getLogin())
					&& equalsIgnoreCase(password, this.instrutores.get(i).getPassword())) {
				return this.instrutores.get(i);
			}
		}
		return null;
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
			Aparelho atual = aparelhos.get(index);
			if (atual != null && novoAparelho != null) {
				atual.setId(novoAparelho.getId());
				atual.setNome(novoAparelho.getNome());
				atual.setDescricao(novoAparelho.getDescricao());
				atual.setFuncao(novoAparelho.getFuncao());
				atual.setAcademia(novoAparelho.getAcademia());
			}
		}
	}
	
	public void alterarAparelhoPorId(int id, Aparelho novoAparelho) {
	    for (int i = 0; i < aparelhos.size(); i++) {
	        if (aparelhos.get(i).getId() == id) {
	            aparelhos.set(i, novoAparelho);
	            System.out.println("Aparelho com ID " + id + " alterado com sucesso.");
	            return;
	        }
	    }
	    System.out.println("Aparelho com ID " + id + " não encontrado.");
	}


	public List<Aparelho> getAparelhos() {
		return aparelhos;
	}
	
	public Aparelho buscarAparelhoPorId(int id) {
	    for (Aparelho aparelho : aparelhos) {
	        if (aparelho.getId() == id) {
	            return aparelho;
	        }
	    }
	    return null;
	}

	public List<Aparelho> buscarAparelhosPorNome(String nomeQuery) {
	    List<Aparelho> resultados = new ArrayList<>();
	    
	    String queryNormalizada = normalizarString(nomeQuery);
	    
	    for (Aparelho aparelho : aparelhos) {
	        String nomeAparelhoNormalizado = normalizarString(aparelho.getNome());
	        
	        if (nomeAparelhoNormalizado.contains(queryNormalizada)) {
	            resultados.add(aparelho);
	        }
	    }
	    return resultados;
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
	    if (this.usuarioLogado == null) {
	        return false;
	    }	    
	    return this.usuarioLogado.isAdministrador() || this.usuarioLogado instanceof Instrutor;
	}
	
	public Aluno buscarAlunoPorId(int id) {
	    for (Aluno aluno : alunos) {
	        if (aluno.getId() == id) {
	            return aluno;
	        }
	    }
	    return null;
	}

	public List<Aluno> buscarAlunosPorNome(String nomeQuery) {
	    List<Aluno> resultados = new ArrayList<>();
	    String queryNormalizada = normalizarString(nomeQuery);
	    for (Aluno aluno : alunos) {
	        String nomeAlunoNormalizado = normalizarString(aluno.getNome());
	        if (nomeAlunoNormalizado.contains(queryNormalizada)) {
	            resultados.add(aluno);
	        }
	    }
	    return resultados;
	}
	
	private String normalizarString(String str) {
	    
	    String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
	    String semAcento = normalized.replaceAll("\\p{M}", ""); // \p{M} é uma classe de caracteres que representa marcas de combinação (diacríticos)
	    return semAcento.toLowerCase();
	}
	
	public boolean registrarSaida() {
	    if (usuarioLogado instanceof Aluno) {
	    	Aluno alunoLogado = (Aluno) usuarioLogado;
	        Frequencia pendente = alunoLogado.getFrequenciaPendente();
	        
	        if (pendente != null) {
	            LocalDateTime now = LocalDateTime.now();
	            pendente.setDataHoraSaida(now); 	        
	            String horaSaida = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	            System.out.println("Saída registrada com sucesso às: " + horaSaida);
	            return true;
	        } else {
	            System.out.println("Aviso: Não havia entrada pendente. Apenas deslogando.");
	            return false;
	        }
	    }
	    return true; 
	}

	private boolean validaUsuarioAdminPadrao(String login, String password) {
		return equalsIgnoreCase(login, USER_DEFAULT) && equalsIgnoreCase(password, PWD_DEFAULT);
	}
}
