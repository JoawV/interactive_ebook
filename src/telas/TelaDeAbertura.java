package telas;

import assets.Load;
import assets.Save;
import personagem.Personagem;

import java.util.Scanner;

public class TelaDeAbertura {
    Scanner sc = new Scanner(System.in);
    Personagem personagem = new Personagem();
    TelaPadrao telaPadrao = new TelaPadrao();
    TelaIventario telaIventario = new TelaIventario(telaPadrao);
    public TelaDeAbertura() { telaPadrao.setTelaIventario(telaIventario); }
    public void exibirMenu() {
        System.out.println("-------  TELA DE ABERTURA  -------");
        System.out.println("1 - NOVO JOGO");
        System.out.println("2 - CARREGAR JOGO");
        System.out.println("3 - EXIBIR CRÉDITOS");
        System.out.println("4 - ENCERRAR");
        System.out.print("Selecione sua opção: ");
        int opcao;
        opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                personagem = telaIventario.configurarPersonagem();
                personagem.setCena(1);
                Save save = new Save(personagem);
                save.salvarJogo();
                telaPadrao.setPersonagem(personagem);
                telaPadrao.iniciarJogo();
                break;
            case 2:
                carregarJogo();
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
    }

    private void carregarJogo() {
        Load load = new Load();
        personagem = load.mostrarMenuCarregamento();

        if (personagem != null) {
            telaPadrao.setPersonagem(personagem);
            telaIventario.setPersonagem(personagem);
            telaPadrao.setTelaIventario(telaIventario);
            personagem.exibirStatus();
            telaPadrao.lerCena(personagem.getCena());
        } else {
            System.out.println("Não foi possivel carregar o jogo!");
            System.out.println("Voltando ao menu principal.");
            exibirMenu();
        }
    }

    private void exibirCreditos() {
        System.out.println("\nDESENVOLVIDO POR:");
        System.out.println("- João Vítor Silva dos Santos");
        System.out.println("- Paulo Willian Joner Cassel");
        System.out.println("");
        exibirMenu();
    }

}
