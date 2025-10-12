
public class Aparelho {
    private String nome;
    private String descricao;
    private String funcao;

    public Aparelho(String nome, String descricao, String funcao) {
        this.nome = nome;
        this.descricao = descricao;
        this.funcao = funcao;
    }

   
    public String getNome() { 
    	return nome; 
    }
    
    public void setNome(String nome) { 
    	this.nome = nome; 
    }

    public String getDescricao() { 
    	return descricao; 
    }
    
    public void setDescricao(String descricao) {
    	this.descricao = descricao; 
    }

    public String getFuncao() {
    	return funcao; ]
    }
    
    public void setFuncao(String funcao) { 
    	this.funcao = funcao; 
    }

    @Override
    public String toString() {
        return "Aparelho: " + nome + " | Função: " + funcao + " | Descrição: " + descricao;
    }
}
