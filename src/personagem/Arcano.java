package personagem;

public class Arcano {

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(float bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | Tipo: " + tipo + " | Bônus: " + bonus;
    }

    public Arcano(String tipo, String nome, double bonus) {
        this.tipo = tipo;
        this.nome = nome;
        this.bonus = bonus;
    }

    private String tipo;
    private String nome;
    private double bonus;
}
