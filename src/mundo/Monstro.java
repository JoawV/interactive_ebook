package mundo;

import personagem.Item;

import java.util.List;

public class Monstro {
    public Monstro(String raca, int habilidade, int energia, int sorte, Item provisao, Item item, int tesouro) {
        this.raca = raca;
        this.habilidade = habilidade;
        this.energia = energia;
        this.sorte = sorte;
        this.provisao = provisao;
        this.item = item;
        this.tesouro = tesouro;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public int getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(int habilidade) {
        this.habilidade = habilidade;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getSorte() {
        return sorte;
    }

    public void setSorte(int sorte) {
        this.sorte = sorte;
    }

    public Item getProvisao() {
        return provisao;
    }

    public void setProvisao(Item provisao) {
        this.provisao = provisao;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getTesouro() {
        return tesouro;
    }

    public void setTesouro(int tesouro) {
        this.tesouro = tesouro;
    }

    private String raca;
    private int habilidade;
    private int energia;
    private int sorte;
    private Item provisao;
    private Item item;
    private int tesouro;
}
