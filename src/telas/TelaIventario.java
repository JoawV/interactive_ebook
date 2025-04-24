package telas;

import java.util.Random;

import assets.Save;
import mundo.Arcano;
import personagem.Item;
import personagem.Personagem;
import java.util.Scanner;

public class TelaIventario {
    TelaPadrao telaPadrao = new TelaPadrao();
    private Personagem personagem;
    private Item item;
    private Arcano arcano;
    private int tesouro = 0;

    Scanner sc = new Scanner(System.in);

    public void configurarPersonagem() {
        Random random = new Random();

        int pontosDisponiveis = 12;
        int habilidade = 6;
        int energia = 12;
        int sorte = 6;

        int pontosDeHabilidade = 0;
        int pontosDeEnergia = 0;
        int pontosDeSorte = 0;

        System.out.println("------- CONFIGURAÇÃO DO PERSONAGEM -------\n");
        System.out.println("Insira seu nome: ");
        String nomePersonagem = sc.nextLine();
        System.out.println("Você tem " + pontosDisponiveis + " pontos para distribuir entre os atributos!");
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

        System.out.println("\nEscolha sua Classe:");
        System.out.println("Guerreiro - Focado em Ataque (Recebe um equipamento extra)");
        System.out.println("Mago - Focado em Magia (Recebe uma magia gratuita)");
        System.out.print("Escreva qual classe você deseja: ");
        String classeEscolhida = sc.nextLine();

        personagem = new Personagem(nomePersonagem, habilidade, energia, sorte, 0, classeEscolhida);

        if (classeEscolhida.equalsIgnoreCase("Mago")) {
            System.out.println("\nVocê escolheu a classe Mago e recebeu uma magia adicional");
            System.out.println("1* Magia de Fogo");
            double bonus = 1 + random.nextInt(6);
            arcano = new Arcano("Feitiço", "Mágia de Fogo", bonus);
            personagem.adicionarMagia(arcano);
        }

        //TESTE ADICIONAR EQUIPAMENTOS

        System.out.println("\nVocê tem direito a um equipamento gratuito!");
        System.out.print("Digite o tipo do equipamento (Ataque/Defesa): ");
        String tipo = sc.nextLine();

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
            System.out.println("O Equipamento foi enviado para a Mochila!\n");
            bonus = 1 + random.nextInt(6);
            item = new Item(extra, extra2, combateBoolean, faMomentanea, bonus);
            personagem.adicionarEquipamentoExtra(item);
        }
        Save save = new Save(personagem);
        save.salvarJogo();
        personagem.exibirStatus();
        personagem.setCena(1);
        telaPadrao.setPersonagem(personagem);
        telaPadrao.iniciarJogo();
    }

    public Personagem getPersonagem() {
        return personagem;
    }
    private static String getMagias() {
        return "magias";
    }
}
