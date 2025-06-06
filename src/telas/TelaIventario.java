package telas;

import java.util.Random;

import assets.Save;
import mundo.Arcano;
import personagem.Item;
import personagem.Personagem;
import java.util.Scanner;

public class TelaIventario {
    private Personagem personagem;
    private TelaPadrao telaPadrao;
    private Item item;
    private Arcano arcano;
    private int tesouro = 0;

    Scanner sc = new Scanner(System.in);
    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }
    public TelaIventario(TelaPadrao telaPadrao) { this.telaPadrao = telaPadrao; }

    public Personagem configurarPersonagem() {
        Random random = new Random();

        int pontosDisponiveis = 12;
        int habilidade = 6;
        int energia = 12;
        int sorte = 6;

        int pontosDeHabilidade = 0;
        int pontosDeEnergia = 0;
        int pontosDeSorte = 0;


        //construir o personagem
        System.out.print("\n------- CONFIGURAÇÃO DO PERSONAGEM -------\n");
        System.out.print("Insira seu nome: ");
        String nomePersonagem = sc.nextLine();
        System.out.println("\nVocê tem " + pontosDisponiveis + " pontos para distribuir entre os atributos!");
        while (true) {
            System.out.print("Defina a quantidade de pontos de HABILIDADE adicionais (6-12): ");
            pontosDeHabilidade = sc.nextInt();
            if (pontosDeHabilidade >= 0 && pontosDeHabilidade <= 6 && pontosDeHabilidade <= pontosDisponiveis) {
                break;
            }
            System.out.println("Valor inválido!.");
        }
        habilidade += pontosDeHabilidade;
        pontosDisponiveis -= pontosDeHabilidade;

        while (true) {
            System.out.println("\nVocê tem " + pontosDisponiveis + " pontos para distribuir!");
            System.out.print("Defina a quantidade de pontos de ENERGIA adicionais (12-24): ");
            pontosDeEnergia = sc.nextInt();
            if (pontosDeEnergia >= 0 && pontosDeEnergia <= 12 && pontosDeEnergia <= pontosDisponiveis) {
                break;
            }
            System.out.println("Valor inválido!");
        }
        energia += pontosDeEnergia;
        pontosDisponiveis -= pontosDeEnergia;

        while (true) {
            System.out.println("\nVocê tem " + pontosDisponiveis + " pontos para distribuir!");
            System.out.print("Defina a quantidade de pontos de SORTE adicionais (6-12): ");
            pontosDeSorte = sc.nextInt();
            if (pontosDeSorte >= 0 && pontosDeSorte <= 6 && pontosDeSorte <= pontosDisponiveis) {
                break;
            }
            System.out.println("Valor inválido!");
        }
        sorte += pontosDeSorte;
        pontosDisponiveis -= pontosDeSorte;
        sc.nextLine();

        System.out.println("\nPersonagem configurado com sucesso!");

        String classeEscolhida = "";
        boolean classeValida = false;

        //escolher a classe (Guerreiro ou Mago)
        while (!classeValida) {
            System.out.println("\nEscolha sua Classe:");
            System.out.println("Guerreiro - Focado em Ataque (Recebe um equipamento extra)");
            System.out.println("Mago - Focado em Magia (Recebe uma magia gratuita)");
            System.out.print("Escreva qual classe você deseja: ");
            classeEscolhida = sc.nextLine().trim();

            if (classeEscolhida.equals("Guerreiro") || classeEscolhida.equals("Mago")) {
                classeValida = true;
            } else {
                System.out.println("\nClasse inválida! Digite exatamente 'Guerreiro' ou 'Mago'");
            }
        }

        personagem = new Personagem(nomePersonagem, habilidade, energia, sorte, 0, classeEscolhida);

        //constroi uma magia extra pro mago
        if (classeEscolhida.equalsIgnoreCase("Mago")) {
            System.out.println("\nVocê escolheu a classe Mago e recebeu uma magia adicional");
            System.out.println("1* Magia de Fogo");
            double bonus = 1 + random.nextInt(5);
            arcano = new Arcano("Feitiço", "Mágia de Fogo", bonus);
            personagem.adicionarMagia(arcano);
        }

        System.out.println("\nVocê tem direito a um equipamento gratuito!");
        String tipo = "";
        boolean tipoValido = false;


        while (!tipoValido) {
            System.out.print("Digite o tipo do equipamento (Ataque/Defesa): ");
            tipo = sc.nextLine().trim();

            if (tipo.equals("Ataque") || tipo.equals("Defesa")) {
                tipoValido = true;
            } else {
                System.out.println("\nTipo inválido! Digite exatamente 'Ataque' ou 'Defesa'");
            }
        }

        double bonus = 1 + random.nextInt(6);

        int combateBoolean = 0;
        double faMomentanea = bonus;
        String nome = "";
        String extra = "";
        String extra2 = "";

        if (tipo.equalsIgnoreCase("Ataque")) {
            combateBoolean = 1;
            nome = "Espada";
            extra = "Armadura";
            extra2 = "Defesa";
        } else if (tipo.equalsIgnoreCase("Defesa")) {
            combateBoolean = 1;
            nome = "Armadura";
            extra = "Espada";
            extra2 = "Ataque";
        }

        //constroi o item pelo o que o usuario escolheu
        //se o usuario for guerreiro, recebe um extra, com o tipo contrario do primario
        item = new Item(nome, tipo, combateBoolean, faMomentanea, bonus);
        System.out.println("\nParabéns! Você adquiriu uma '" + nome + "'");

        System.out.print("Deseja equipar este item? (s/n): ");
        String equiparItemResposta = sc.nextLine();
        if (!equiparItemResposta.isEmpty()) {
            char equiparItem = equiparItemResposta.charAt(0);
            if (equiparItem == 's') {
                personagem.adicionarEquipamentoPrincipal(item);
                System.out.println("Item equipado!\n");
            } else {
                personagem.adicionarEquipamentoExtra(item);
                System.out.println("Item transferido para Mochila!\n");
            }
        }

        if(classeEscolhida.equalsIgnoreCase("Guerreiro")) {
            System.out.println("Por ser da Classe Guerreiro você recebeu um equipamento extra!");
            System.out.println("O Equipamento foi equipado automaticamente!\n");
            bonus = 1 + random.nextInt(6);
            item = new Item(extra, extra2, combateBoolean, faMomentanea, bonus);
            personagem.adicionarEquipamentoPrincipal(item);
        }
        personagem.exibirStatus();
        return personagem;
    }

    public void abrirInventario() {
        System.out.println("\n===== INVENTÁRIO =====");
        personagem.exibirStatus();
        System.out.println("\nO que deseja fazer?");
        System.out.println("[1] - Trocar item equipado");
        System.out.println("[2] - Usar uma provisão");
        System.out.println("[3] - Voltar para o jogo");

        int opcao = sc.nextInt();
        sc.nextLine();
        switch (opcao) {
            case 1:
                personagem.trocarItemEquipado();
                break;
            case 2:
                personagem.usarProvisao();
                break;
            case 3:
                System.out.println("Voltando para a aventura...");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    public Personagem getPersonagem() {
        return personagem;
    }
    private static String getMagias() {
        return "magias";
    }
}
