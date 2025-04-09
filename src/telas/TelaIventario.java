package telas;

import java.io.IOException;
import personagem.Equipamento;
import personagem.Personagem;

import java.util.Scanner;

public class TelaIventario {
    private Personagem personagem;
    private Equipamento equipamento;
    private TelaPadrao telaPadrao;
    private int tesouro = 0;
    
    Scanner sc = new Scanner(System.in);

    public Personagem getPersonagem() {
        return personagem;
    }

    public void configurarPersonagem() {
        int pontosDisponiveis = 12;
        int habilidade = 6;
        int energia = 12;
        int sorte = 6;

        System.out.println("------- CONFIGURAÇÃO DO PERSONAGEM -------");
        System.out.println("");
        System.out.println("Você tem " + pontosDisponiveis + " pontos para distribuir entre os atributos!");
        System.out.print("Defina a quantidade de pontos de HABILIDADE (6-12): ");
        int pontosDeHabilidade = sc.nextInt();
        if (pontosDeHabilidade > 6) {
            System.out.println("Valor inválido!");
            System.out.print("Defina a quantidade de pontos de HABILIDADE (6-12): ");
            pontosDeHabilidade = sc.nextInt();
        }
        habilidade += pontosDeHabilidade;
        pontosDisponiveis -= pontosDeHabilidade;

        System.out.println("");
        System.out.println("Você tem " + pontosDisponiveis + " pontos para distribuir entre os atributos!");
        System.out.print("Defina a quantidade de pontos de ENERGIA (Min 12 - Max 24): ");
        int pontosDeEnergia = sc.nextInt();
        if (pontosDeEnergia > 12) {
            System.out.println("Valor inválido!");
            System.out.print("Defina a quantidade de pontos de ENERGIA (Min 12 - Max 24): ");
            pontosDeEnergia = sc.nextInt();
        }
        energia += pontosDeEnergia;
        pontosDisponiveis -= pontosDeEnergia;

        System.out.println("");
        System.out.println("Você tem " + pontosDisponiveis + " pontos para distribuir entre os atributos!");
        System.out.print("Defina a quantidade de pontos de SORTE (6-12): ");
        int pontosDeSorte = sc.nextInt();
        if (pontosDeSorte > 6) {
            System.out.println("Valor inválido!");
            System.out.print("Defina a quantidade de pontos de SORTE (6-12): ");
            pontosDeSorte = sc.nextInt();
        }
        sorte += pontosDeSorte;
        pontosDisponiveis -= pontosDeSorte;
        sc.nextLine();

        personagem = new Personagem(habilidade, energia, sorte);
        System.out.println("\nPersonagem configurado com sucesso!");

        System.out.print("\nDeseja adicionar uma magia inicial ao seu personagem? (s/n): ");
        String respostaMagia = sc.nextLine();

        if (!respostaMagia.isEmpty() && respostaMagia.charAt(0) == 's') {
            System.out.print("Insira a magia: ");
            String magias = sc.nextLine();
            personagem.adicionarMagia(magias);
            System.out.println("Magia adicionada com sucesso!");
        }


        //TESTE ADICIONAR EQUIPAMENTOS
        System.out.println("\nInsira seu EQUIPAMENTO base!");
        char adicionarMaisEquipamento = 's';
        char equiparItem = 's';
        while (adicionarMaisEquipamento != 'n') {
            System.out.print("Nome do equipamento: ");
            String nome = sc.nextLine();

            System.out.print("Digite o tipo do equipamento (Ataque/Defesa): ");
            String tipo = sc.nextLine();

            System.out.print("Digite o bônus do equipamento: ");
            double bonus = sc.nextDouble();
            sc.nextLine();

            equipamento = new Equipamento(nome, tipo, bonus);
            System.out.print("Deseja equipar este item?");
            String equiparItemResposta = sc.nextLine();
            if (!equiparItemResposta.isEmpty()) {
                equiparItem = equiparItemResposta.charAt(0);
                if (equiparItem == 's') {
                    personagem.adicionarEquipamentoPrincipal(equipamento);
                } else personagem.adicionarEquipamentoExtra(equipamento);
            }

            System.out.print("Deseja adicionar mais equipamentos?(s/n): ");
            String resposta = sc.nextLine();
            if (!resposta.isEmpty()) {
                adicionarMaisEquipamento = resposta.charAt(0);
            }
            System.out.println("Equipamento adicionado ao inventário!");
            System.out.println("");
        }
        personagem.exibirStatus();
    }
}
