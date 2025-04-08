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

    public void equiparItem(Equipamento equipamento) {
        this.equipamentoNovo = equipamento;
    }

    public void adicionarEquipamentoExtra(Equipamento equipamento) {
        equipamentosExtras.add(equipamento);
    }

    public void exibirStatus() {
        System.out.println("\n------- STATUS DO PERSONAGEM -------");
        System.out.println("HABILIDADE: " + habilidade);
        System.out.println("ENERGIA: " + energia);
        System.out.println("SORTE: " + sorte);
        System.out.println("Equipamento principal: " + (equipamentoNovo != null ? equipamentoNovo : "Nenhum"));
        System.out.println("Equipamentos Extras: " + (equipamentosExtras.isEmpty() ? "Nenhum" : ""));
        for (Equipamento e : equipamentosExtras) {
            System.out.println("- " + e);
        }
//        System.out.println("-----------------------------------");
        System.out.println("");
    }

    public Personagem(int habilidade, int energia, int sorte) {
        this.habilidade = habilidade;
        this.energia = energia;
        this.sorte = sorte;
        this.equipamentosExtras = new ArrayList<>();
    }

    private int habilidade;
    private int energia;
    private int sorte;
    private Equipamento equipamentoNovo;
    private List<Equipamento> equipamentosExtras;
}
