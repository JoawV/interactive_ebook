package personagem;

import mundo.Arcano;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public List<Item> getItemEquipado() {
        return itemEquipado;
    }

    public List<Arcano> getMagiaEquipadas() {
        return magiaEquipadas;
    }

    public void adicionarCena(int cena) {
        if (!cenas.contains(cena)) {
            cenas.add(cena);
        }
    }

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

    public void trocarItemEquipado() {
        if(equipamentosExtras.isEmpty()) {
            System.out.println("Você não possui itens para equipar.");
            return;
        }

        System.out.print("\nItens disponíveis:");
        for (int i = 0; i < equipamentosExtras.size(); i++) {
            System.out.println((i + 1) + " - " + equipamentosExtras.get(i));
        }

        System.out.print("\nEscolha o número do item que deseja equipar: ");
        Scanner sc = new Scanner(System.in);
        int escolha = sc.nextInt();
        sc.nextLine();
        if (escolha < 1 || escolha > equipamentosExtras.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        if (itemEquipado.size() < 3) {
            Item itemEscolhido = equipamentosExtras.remove(escolha - 1); //retira item da mochila
            itemEquipado.add(itemEscolhido); //equipa o novo item
            System.out.println("Você equipou: " + itemEscolhido);
            //Item itemAntigo = itemEquipado.remove(0); //remove o item equipado atual
            //equipamentosExtras.add(itemAntigo); //devolve para a mochila
            //System.out.println("Você guardou: " + itemAntigo);
        } else {
            System.out.println("Você atingiu o limite de itens equipados!");
        }
    }

    public void usarProvisao() {
        if (provisoes.isEmpty()) {
            System.out.print("Você não possui provisões para usar.");
            return;
        }

        System.out.print("\nProvisões disponíveis:");
        for (int i = 0; i < provisoes.size(); i++) {
            System.out.println((i + 1) + " - " + provisoes.get(i));
        }

        System.out.print("\nEscolha o número da provisão que deseja usar: ");
        Scanner sc = new Scanner(System.in);
        int escolha = sc.nextInt();
        sc.nextLine();

        if (escolha < 1 || escolha > provisoes.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        Item provisao = provisoes.remove(escolha - 1);
        int energiaRecuperada = 4; //cada provisão recupera 4 pontos de energia
        energia += energiaRecuperada;
        if (energia > 24) {
            energia = 24;
        }
        System.out.println("Você usou uma provisão e recuperou " + energiaRecuperada + " pontos de energia!");
    }

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
        this.cenas = new ArrayList<>();
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
    private List<Integer> cenas;
}

