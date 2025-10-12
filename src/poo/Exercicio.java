
public class Exercicio {
    private Aparelho aparelho;
    private float carga;
    private int nroRepeticoes;
    private int ordem;

    public Exercicio(Aparelho aparelho, float carga, int nroRepeticoes, int ordem) {
        this.aparelho = aparelho;
        this.carga = carga;
        this.nroRepeticoes = nroRepeticoes;
        this.ordem = ordem;
    }

    
    public Aparelho getAparelho() { 
    	return aparelho; 
    }
    
    public void setAparelho(Aparelho aparelho) {
    	this.aparelho = aparelho; 
    }

    public float getCarga() {
    	return carga; 
    }
    
    public void setCarga(float carga) {
    	this.carga = carga; 
    }

    public int getNroRepeticoes() {
    	return nroRepeticoes; 
    }
    
    public void setNroRepeticoes(int nroRepeticoes) {
    	this.nroRepeticoes = nroRepeticoes;
    }

    public int getOrdem() { 
    	return ordem; 
    }
    
    public void setOrdem(int ordem) { 
    	this.ordem = ordem; 
    }

    @Override
    public String toString() {
        return "Ordem: " + ordem + " | Aparelho: " + aparelho.getNome() +
               " | Carga: " + carga + "kg | Repetições: " + nroRepeticoes;
    }
}