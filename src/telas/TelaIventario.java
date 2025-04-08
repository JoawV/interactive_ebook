package telas;

import personagem.Equipamento;
import personagem.Personagem;

import java.util.Scanner;

public class TelaIventario {
    private Personagem personagem;
    private Equipamento equipamento;
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
        System.out.print("Defina a HABILIDADE (6-12): ");
        int pontosDeHabilidade = sc.nextInt();
        if (pontosDeHabilidade > 6) {
            System.out.println("Valor inválido!\nEscolha entre 6 e 12, sem ultrapassar os pontos disponíveis.");
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
            System.out.println("Valor inválido!\nEscolha entre 12 e 24, sem ultrapassar os pontos disponíveis.");
            System.out.print("Defina a ENERGIA (12-24): ");
            pontosDeEnergia = sc.nextInt();
        }
        energia += pontosDeEnergia;
        pontosDisponiveis -= pontosDeEnergia;

        System.out.println("");
        System.out.println("Você tem " + pontosDisponiveis + " pontos para distribuir entre os atributos!");
        System.out.print("Defina a quantidade de pontos de SORTE (6-12): ");
        int pontosDeSorte = sc.nextInt();
        if (pontosDeSorte > 6) {
            System.out.println("Valor inválido!\nEscolha entre 6 e 12, sem ultrapassar os pontos disponíveis.");
            System.out.print("Defina a SORTE (6-12): ");
            pontosDeSorte = sc.nextInt();
        }
        sorte += pontosDeSorte;
        pontosDisponiveis -= pontosDeSorte;
        sc.nextLine();

        personagem = new Personagem(habilidade, energia, sorte);
        System.out.println("\nPersonagem configurado com sucesso!");


        //TESTE ADICIONAR EQUIPAMENTOS
        System.out.print("Nome do equipamento: ");
        String nome = sc.nextLine();

        System.out.print("Digite o tipo do equipamento (Ataque/Defesa): ");
        String tipo = sc.nextLine();

        System.out.print("Digite o bônus do equipamento: ");
        double bonus = sc.nextDouble();
        sc.nextLine();

        equipamento = new Equipamento(nome, tipo, bonus);
        personagem.adicionarEquipamentoExtra(equipamento);
        System.out.println("Equipamento adicionado ao inventário!");


        personagem.exibirStatus();
    }
}
