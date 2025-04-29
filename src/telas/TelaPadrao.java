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
    private TelaIventario telaIventario;
    private Monstro monstro;
    private Item item;
    private final Scanner sc = new Scanner(System.in);
    public void setTelaIventario(TelaIventario telaIventario) { this.telaIventario = telaIventario; }
    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }
    public void iniciarJogo() { lerCena(personagem.getCena()); }

    public void lerCena(int numCena) {
        personagem.setCena(numCena); // atualiza a cena no personagem
        personagem.adicionarCena(numCena);
        Save save = new Save(personagem);
        save.salvarJogo();
        try {
            File arquivo = new File("src\\cenas\\" + numCena + ".txt");
            Scanner leitor = new Scanner(arquivo);
            List<Integer> opcoes = new ArrayList<>();
            List<String> textosOpcoes = new ArrayList<>();

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
                    String textoOpcao = linha.substring(indice + 1).trim();
                    textosOpcoes.add(textoOpcao);
                } else {
                    System.out.println(linha);
                }
            }

            leitor.close();

            //abrir o inventario ou seguir a cena
            if (!opcoes.isEmpty()) {
                while (true) {
                    for (int i = 0; i < textosOpcoes.size(); i++) {
                        System.out.println(textosOpcoes.get(i));
                    }
                    System.out.println("[" + (opcoes.size() + 1) + "] - Abrir Inventário");
                    System.out.print("Escolha uma opção: ");
                    int escolha = sc.nextInt();
                    sc.nextLine();

                    if (escolha >= 1 && escolha <= opcoes.size()) {
                        lerCena(opcoes.get(escolha - 1));
                        break;
                    } else if (escolha == opcoes.size() + 1) {
                        telaIventario.abrirInventario();
                    } else {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }

    //pegar o monstro e iniciar o combate
    private void cenaMonstro(Scanner leitor) {
        try {
            StringBuilder narrativa = new StringBuilder();
            String nome = "", tipo = "";
            int habilidade = 0, energia = 0, sorte = 0, tesouro = 0, provisoes = 0;
            Item item = null;
            int cenaVitoria = -1, cenaDerrota = -1;

            //pegando o monstro
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

            //iniciando o combate - TelaCombate
            TelaCombate combate = new TelaCombate();
            boolean venceu = combate.executarCombate(personagem, monstro);

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