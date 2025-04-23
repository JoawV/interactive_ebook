package personagem;

import mundo.Arcano;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Personagem implements Serializable {

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public int getTesouro() {
        return tesouro;
    }

    public void setTesouro(int tesouro) {
        this.tesouro = tesouro;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getCena() { return cena; }

    public void setCena(int cena) { this.cena = cena; }

    public void adicionarMagia(Arcano magias) {
        magiaEquipadas.add(magias);
    }

    public void adicionarEquipamentoPrincipal(Item item) {
        itemEquipado.add(item);
    }

    public void adicionarEquipamentoExtra(Item item) {
        equipamentosExtras.add(item);
    }

    public void adicionarTesouro(int quantidade) { this.tesouro += quantidade; }

    public void adicionarProvisao(Item provisao) { this.provisoes.add(provisao); }

    public void exibirStatus() {
        System.out.println("------- STATUS DO PERSONAGEM -------");
        System.out.println("Nome: " + nome);
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

    public String statusComoTexto() {
        StringBuilder sb = new StringBuilder();
        sb.append("------- STATUS DO PERSONAGEM -------\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Classe: ").append(classe).append("\n\n");
        sb.append("HABILIDADE: ").append(habilidade).append("\n");
        sb.append("ENERGIA: ").append(energia).append("\n");
        sb.append("SORTE: ").append(sorte).append("\n\n");

        sb.append("MÁGIAS: ").append(magiaEquipadas.isEmpty() ? "Nenhum\n" : "\n");
        for (Arcano m : magiaEquipadas) {
            sb.append("- ").append(m).append("\n");
        }

        sb.append("\nItens Equipados: ").append(itemEquipado.isEmpty() ? "Nenhum\n" : "\n");
        for (Item e : itemEquipado) {
            sb.append("- ").append(e).append("\n");
        }

        sb.append("\nMochila: ").append(equipamentosExtras.isEmpty() ? "Nenhum\n" : "\n");
        for (Item x : equipamentosExtras) {
            sb.append("- ").append(x).append("\n");
        }

        return sb.toString();
    }

    public Personagem(String nome, int habilidade, int energia, int sorte, int tesouro, String classe) {
        this.nome = nome;
        this.habilidade = habilidade;
        this.energia = energia;
        this.sorte = sorte;
        this.tesouro = tesouro;
        this.classe = classe;
        this.magiaEquipadas = new ArrayList<>();
        this.itemEquipado = new ArrayList<>();
        this.equipamentosExtras = new ArrayList<>();
        this.provisoes = new ArrayList<>();
    }

    public  Personagem() {
    }

    private String nome;
    private int habilidade;
    private int energia;
    private int sorte;
    private int tesouro;
    private String classe;
    private int cena;
    private List<Arcano> magiaEquipadas;
    private List<Item> itemEquipado;
    private List<Item> equipamentosExtras;
    private List<Item> provisoes;
}
