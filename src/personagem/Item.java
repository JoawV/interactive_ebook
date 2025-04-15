package personagem;

public class Item {
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

    public int getCombate() {
        return combate;
    }

    public void setCombate(int combate) {
        this.combate = combate;
    }

    public double getFa() {
        return fa;
    }

    public void setFa(float fa) {
        this.fa = fa;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | Tipo: " + tipo + " | Combate: " + combate + " | Força de ataque: " + fa + " | Bônus: " + bonus;
    }

    public Item(String nome, String tipo, int combate, double fa, double bonus) {
        this.nome = nome;
        this.tipo = tipo;
        this.combate = combate;
        this.fa = fa;
        this.bonus = bonus;
    }

    private String nome;
    private String tipo;
    private int combate;
    private double fa;
    private double bonus;
}
