package personagem;

import java.util.ArrayList;
import java.util.List;

public class Personagem {
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

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void adicionarMagia(Arcano magias) {
        magiaEquipadas.add(magias);
    }

    public void adicionarEquipamentoPrincipal(Item item) {
        itemEquipado.add(item);
    }

    public void adicionarEquipamentoExtra(Item item) {
        equipamentosExtras.add(item);
    }

    public void exibirStatus() {
        System.out.println("------- STATUS DO PERSONAGEM -------");
        System.out.println("Classe: " + classe);
        System.out.println("\nHABILIDADE: " + habilidade);
        System.out.println("ENERGIA: " + energia);
        System.out.println("SORTE: " + sorte);
        System.out.println("\nMÁGIA: " + (magiaEquipadas.isEmpty() ? "Nenhum" : ""));
        for (Arcano m : magiaEquipadas) {
            System.out.println("- " + m);
        }
        System.out.println("");
        System.out.println("Itens Equipados: " + (itemEquipado.isEmpty() ? "Nenhum" : ""));
        for (Item e : itemEquipado) {
            System.out.println("- " + e);
        }
        System.out.println("");
        System.out.println("Mochila: " + (equipamentosExtras.isEmpty() ? "Nenhum" : ""));
        for (Item x : equipamentosExtras) {
            System.out.println("- " + x);
        }
        System.out.println("");
    }

    public Personagem(int habilidade, int energia, int sorte, String classe) {
        this.habilidade = habilidade;
        this.energia = energia;
        this.sorte = sorte;
        this.classe = classe;
        this.magiaEquipadas = new ArrayList<>();
        this.itemEquipado = new ArrayList<>();
        this.equipamentosExtras = new ArrayList<>();
    }

    private int habilidade;
    private int energia;
    private int sorte;
    private String classe;
    private List<Arcano> magiaEquipadas;
    private List<Item> itemEquipado;
    private List<Item> equipamentosExtras;
}
