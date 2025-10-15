package br.ucs.poo.exerciteaki;

import java.util.ArrayList;
import java.util.List;

public class Academia {
	private int id;
	private String nome;
	private String telefone;
	private String website;
	private Endereco endereco;  //associacao 1-1
	private List<Horario> horarios; // associacao 1..n

	public Academia () {}
	
	public Academia(int id, String nome, String telefone, String website, Endereco endereco) {
		this.id = id;
		this.nome=nome;
		this.telefone=telefone;
		this.website=website;
		this.endereco=endereco;
		this.horarios = new ArrayList<>();
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
	
	public void setHorarios(Horario horario) {
        if (horarios.size() < 7) {
            horarios.add(horario);
        } else {
            System.out.println("A academia já possui 7 horários cadastrados.");
        }
    }
	
	public List<Horario> getHorarios() { 
		return horarios; 
	}
	
	@Override
    public String toString() {
        return "Id: " + id +
               "\nAcademia: " + nome +
               "\nTelefone: " + telefone +
               "\nWebsite: " + website +
               "\nEndereço: " + endereco.toString() +
               "\nHorários: " + horarios.toString();
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
