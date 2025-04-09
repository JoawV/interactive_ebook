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

    public void adicionarMagia(String magias) {
        this.magia.add(magias);
    }

    public void adicionarEquipamentoPrincipal(Equipamento equipamento) {
        equipamentoEquipado.add(equipamento);
    }

    public void adicionarEquipamentoExtra(Equipamento equipamento) {
        equipamentosExtras.add(equipamento);
    }

    public void exibirStatus() {
        System.out.println("------- STATUS DO PERSONAGEM -------");
        System.out.println("HABILIDADE: " + habilidade);
        System.out.println("ENERGIA: " + energia);
        System.out.println("SORTE: " + sorte);
        System.out.println("\nMÁGIA: " + (magia.isEmpty() ? "Nenhum" : ""));
        for (String m : magia) {
            System.out.println("- " + m);
        }
        System.out.println("");
        System.out.println("Itens Equipados: " + (equipamentoEquipado.isEmpty() ? "Nenhum" : ""));
        for (Equipamento e : equipamentoEquipado) {
            System.out.println("- " + e);
        }
        System.out.println("");
        System.out.println("Itens Extras: " + (equipamentosExtras.isEmpty() ? "Nenhum" : ""));
        for (Equipamento x : equipamentosExtras) {
            System.out.println("- " + x);
        }
        System.out.println("");
    }

    public Personagem(int habilidade, int energia, int sorte) {
        this.habilidade = habilidade;
        this.energia = energia;
        this.sorte = sorte;
        this.magia = new ArrayList<>();
        this.equipamentoEquipado = new ArrayList<>();
        this.equipamentosExtras = new ArrayList<>();
    }

    private int habilidade;
    private int energia;
    private int sorte;
    private ArrayList<String> magia;
    private List<Equipamento> equipamentoEquipado;
    private List<Equipamento> equipamentosExtras;
}
