package personagem;

public class Equipamento {
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | Tipo: " + tipo + " | Bônus: " + bonus;
    }

    public Equipamento(String nome, String tipo, double bonus) {
        this.nome = nome;
        this.tipo = tipo;
        this.bonus = bonus;
    }

    private String nome;
    private String tipo;
    private double bonus;
}
