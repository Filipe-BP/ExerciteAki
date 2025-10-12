
import java.util.ArrayList;
import java.util.List;

public class Treino {
    private String nome;
    private int diaSemana; 
    private List<Exercicio> exercicios;

    public Treino(String nome, int diaSemana) {
        this.nome = nome;
        this.diaSemana = diaSemana;
        this.exercicios = new ArrayList<>();
    }

    
    public String getNome() {
    	return nome; 
    }
    public void setNome(String nome) {
    	this.nome = nome; 
    }

    public int getDiaSemana() {
    	return diaSemana; 
    }
    
    public void setDiaSemana(int diaSemana) {
    	this.diaSemana = diaSemana; 
    }

    public List<Exercicio> getExercicios() { 
    	return exercicios; 
    }

    
    public void adicionarExercicio(Exercicio exercicio) {
        exercicios.add(exercicio);
    }

    public void removerExercicioPorOrdem(int ordem) {
        for (int i = 0; i < exercicios.size(); i++) {
            if (exercicios.get(i).getOrdem() == ordem) {
                exercicios.remove(i);
                break;
            }
        }
    }

    public void listarExercicios() {
        System.out.println("Treino: " + nome + " | Dia da semana: " + diaSemana);
        for (Exercicio e : exercicios) { // -> laco for each, percorre a lista toda.
            System.out.println(e);
        }
    }

    @Override
    public String toString() {
        return "Treino: " + nome + " | Dia da semana: " + diaSemana + " | Total de exercícios: " + exercicios.size();
    }
}