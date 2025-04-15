package telas;

import java.util.Scanner;

import personagem.Personagem;

public class TelaPadrao {
    Scanner sc = new Scanner(System.in);
    private Personagem personagem;
    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    //TESTE DE CENA
    public void cenaIntrodução() {
        System.out.println("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque faucibus, lorem a dignissim\n" +
                "tristique, lorem magna vulputate sem, et tincidunt lectus nulla ut velit. Ut tempor quam tincidunt odio\n" +
                "vehicula in mollis metus varius. Etiam dapibus, mi quis consequat ullamcorper, sem arcu aliquet tortor,\n" +
                "id semper nulla turpis et quam. Praesent vitae felis mi, aliquet fringilla sapien. Praesent molestie\n" +
                "vestibulum mattis. Integer a nisl lectus. Nulla ut urna lacus, vel fermentum metus. Aliquam eros quam,\n" +
                "fringilla id");
        System.out.println("Pressione ENTER para continuar...");
        sc.nextLine();
        cenaAventura1();
    }
    //TESTE DE CENA
    public void cenaAventura1() {
        System.out.println("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque faucibus, lorem a dignissim\n" +
                "tristique, lorem magna vulputate sem, et tincidunt lectus nulla ut velit. Ut tempor quam tincidunt odio\n" +
                "vehicula in mollis metus varius. Etiam dapibus, mi quis consequat ullamcorper, sem arcu aliquet tortor,\n" +
                "id semper nulla turpis et quam. Praesent vitae felis mi, aliquet fringilla sapien. Praesent molestie\n" +
                "vestibulum mattis. Integer a nisl lectus. Nulla ut urna lacus, vel fermentum metus. Aliquam eros quam,\n" +
                "fringilla id");
        System.out.println("\n--------------");
        System.out.println("1 - Caminhar até a ponte.");
        System.out.println("2 - Caminhar até a arvore");
        System.out.println("3 - Falar com NPC");
        System.out.println("4 - Abrir Inventário");
        System.out.println("5 - Sair");
        System.out.print("Selecione sua opção: ");
        int opcao;
        opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                personagem.exibirStatus();
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
    }
}
