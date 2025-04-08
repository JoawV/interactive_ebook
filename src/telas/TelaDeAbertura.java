package telas;

import personagem.Personagem;

import java.util.Scanner;

public class TelaDeAbertura {
    Scanner sc = new Scanner(System.in);
    TelaIventario telaIventario = new TelaIventario();

    public void exibirMenu() {
        int opcao;

         do {
             System.out.println("-------  TELA DE ABERTURA  -------");
             System.out.println("1 - NOVO JOGO");
             System.out.println("2 - CARREGAR JOGO");
             System.out.println("3 - EXIBIR CRÉDITOS");
             System.out.println("4 - ENCERRAR");
             System.out.print("Selecione sua opção: ");
             opcao = sc.nextInt();
             sc.nextLine();

             switch (opcao) {
                 case 1:
                     telaIventario.configurarPersonagem();
                     break;
                 case 2:
                     //carregarJogo();
                     break;
                 case 3:
                    exibirCreditos();
                     break;
                 case 4:
                     System.out.println("\nEncerrando sessão!");
                     break;
                 default:
                     System.out.println("Opção inválida! Tente novamente.");
             }
         } while (opcao != 4);
    }

    private void exibirCreditos() {
        System.out.println("------- DESENVOLVIDO POR -------");
        System.out.println("João");
        System.out.println("Paulo");
    }

}
