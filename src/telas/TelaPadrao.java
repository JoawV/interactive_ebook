package telas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

import assets.Save;
import mundo.Monstro;
import personagem.Item;
import personagem.Personagem;

public class TelaPadrao {
    private Personagem personagem;
    private Monstro monstro;
    private Item item;
    private final Scanner sc = new Scanner(System.in);
    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public void iniciarJogo() { lerCena(personagem.getCena()); }

    public void lerCena(int numCena) {
        personagem.setCena(numCena); // atualiza a cena no personagem
        try {
            File arquivo = new File("src\\cenas\\" + numCena + ".txt");
            Scanner leitor = new Scanner(arquivo);
            List<Integer> opcoes = new ArrayList<>();

            if (leitor.hasNextLine()) {
                String linha = leitor.nextLine().trim();
                if (linha.equalsIgnoreCase("m")) {
                    cenaMonstro(leitor);
                    return;
                }
            }

            // Lê o restante da narrativa e opções
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();

                if (linha.startsWith("I:")) {
                    Item item = criarItem(linha.substring(2).trim());
                    personagem.adicionarEquipamentoExtra(item);
                    System.out.println("Você encontrou um item: " + item);
                } else if (linha.matches("#\\d+:?.*")) {
                    int indice = linha.indexOf(":");
                    int proximaCena = Integer.parseInt(linha.substring(1, indice != -1 ? indice : linha.length()).trim());
                    opcoes.add(proximaCena);
                    System.out.println(linha.substring(indice + 1).trim());
                } else {
                    System.out.println(linha);
                }
            }

            leitor.close();

            if (!opcoes.isEmpty()) {
//                for (int i = 0; i < opcoes.size(); i++) {
//                    System.out.printf("[%d] Ir para a cena %d\n", i + 1, opcoes.get(i));
//                }
                System.out.println("\nEscolha uma opção:");
                int escolha = sc.nextInt();
                sc.nextLine(); // limpar buffer
                lerCena(opcoes.get(escolha - 1));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }

    private void cenaMonstro(Scanner leitor) {
        try {
            StringBuilder narrativa = new StringBuilder();
            String nome = "", tipo = "";
            int habilidade = 0, energia = 0, sorte = 0, tesouro = 0, provisoes = 0;
            Item item = null;
            int cenaVitoria = -1, cenaDerrota = -1;

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine().trim();
                if (linha.startsWith("N:")) nome = linha.substring(2).trim();
                else if (linha.startsWith("H:")) habilidade = Integer.parseInt(linha.substring(2).trim());
                else if (linha.startsWith("E:")) energia = Integer.parseInt(linha.substring(2).trim());
                else if (linha.startsWith("S:")) sorte = Integer.parseInt(linha.substring(2).trim());
                else if (linha.startsWith("T:")) tesouro = Integer.parseInt(linha.substring(2).trim());
                else if (linha.startsWith("P:")) provisoes = Integer.parseInt(linha.substring(2).trim());
                else if (linha.startsWith("I:")) item = criarItem(linha.substring(2).trim());
                else if (linha.contains(";") && cenaVitoria == -1) {
                    String[] partes = linha.split(";");
                    cenaVitoria = Integer.parseInt(partes[0].trim());
                    cenaDerrota = Integer.parseInt(partes[1].trim());
                } else {
                    narrativa.append(linha).append("\n");
                }
            }

            System.out.println(narrativa);
            Monstro monstro = new Monstro(nome, habilidade, energia, sorte,
                    provisoes > 0 ? new Item("Provisão", "c", 0, 0, 0) : null,
                    item, tesouro);

            boolean venceu = executarCombate(monstro);

            if (venceu) {
                System.out.println("Você venceu o combate!");
                if (monstro.getItem() != null) {
                    personagem.adicionarEquipamentoExtra(monstro.getItem());
                    System.out.println("Você pegou um item: " + monstro.getItem());
                }
                if (monstro.getTesouro() > 0) {
                    personagem.adicionarTesouro(monstro.getTesouro());
                    System.out.println("Você ganhou " + monstro.getTesouro() + " moedas de ouro!");
                }
                if (monstro.getProvisao() != null) {
                    personagem.adicionarProvisao(monstro.getProvisao());
                    System.out.println("Você recebeu uma provisão!");
                }
                lerCena(cenaVitoria);
            } else {
                System.out.println("Você foi derrotado...");
                lerCena(cenaDerrota);
            }

        } catch (Exception e) {
            System.out.println("Erro ao processar monstro: " + e.getMessage());
        }
    }

    private boolean executarCombate(Monstro monstro) {
        while (personagem.getEnergia() > 0 && monstro.getEnergia() > 0) {
            int faPersonagem = personagem.getHabilidade() + (int) (Math.random() * 10 + 1);
            int faMonstro = monstro.getHabilidade() + (int) (Math.random() * 10 + 1);

            System.out.printf("");
            System.out.printf("FA " + personagem.getNome() + ": " + faPersonagem + " | FA " + monstro.getRaca() + ": " + faMonstro + "\n");

            if (faPersonagem > faMonstro) {
                monstro.setEnergia(monstro.getEnergia() - 2);
                System.out.println("Você acertou o monstro!");
            } else if (faMonstro > faPersonagem) {
                personagem.setEnergia(personagem.getEnergia() - 2);
                System.out.println("O monstro te acertou!");
            } else {
                System.out.println("Empate! Ninguém acerta.");
            }

            System.out.printf("Sua Energia: %d | Energia do Monstro: %d\n\n",
                    personagem.getEnergia(), monstro.getEnergia());
        }

        return personagem.getEnergia() > 0;
    }

    private Item criarItem(String linha) { //cria o item encontrado na cena
        String[] dados = linha.split(";");
        String nome = dados[0].trim();
        String tipo = dados[1].trim();
        int combate = Integer.parseInt(dados[2].trim());
        int fa = Integer.parseInt(dados[3].trim());
        int dano = Integer.parseInt(dados[4].trim());
        return new Item(nome, tipo, combate, fa, dano);
    }
}
