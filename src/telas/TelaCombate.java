package telas;

import mundo.Arcano;
import mundo.Monstro;
import personagem.Item;
import personagem.Personagem;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TelaCombate {
    private final Scanner sc = new Scanner(System.in);

    public boolean executarCombate(Personagem personagem, Monstro monstro) {
        //iniciais
        Random rand = new Random();
        int sorte = 0;

        while (true) {
            System.out.println("=== Combate ===");
            System.out.printf("Monstro: " + monstro.getRaca() + "\n");
            System.out.printf("Habilidade: " + monstro.getHabilidade() + " | Energia: " + monstro.getEnergia() + " | Sorte: " + monstro.getSorte() + "\n");
            if (monstro.getItem() != null) {
                System.out.println("Item carregado: " + monstro.getItem());
            }

            System.out.println("\nO que você deseja fazer?");
            System.out.println("[1] Atacar");
            System.out.println("[2] Usar Magia");
            System.out.println("[3] Usar Sorte");
            System.out.println("[4] Fugir");
            System.out.print("Escolha: ");

            int escolha = sc.nextInt();

            switch (escolha) {
                case 1:
                    int faBase = personagem.getHabilidade() + rand.nextInt(10) + 1;
                    int faBaseMonstro = monstro.getHabilidade() + rand.nextInt(10) + 1;
                    int bonusItemFa = 0;
                    int bonusItemFaM = 0;
                    int danoExtra = 0;
                    int danoExtraM = 0;
                    int monstroTank = 0;
                    int personagemTank = 0;

                    //pegando item do personagem
                    for (Item item : personagem.getItemEquipado()) {
                        if ("Ataque".equalsIgnoreCase(item.getTipo())) {
                            bonusItemFa += item.getFa();
                            danoExtra += item.getBonus();
                        }
                        if("Defesa".equalsIgnoreCase(item.getTipo())) {
                            bonusItemFa += item.getFa();
                            personagemTank += item.getBonus();
                        }
                    }
                    int faPersonagem = faBase + bonusItemFa;

                    // pegando o item do monstro
                    Item itemDoMonstro = monstro.getItem();
                    if("w".equalsIgnoreCase(itemDoMonstro.getTipo())) {
                        bonusItemFaM += itemDoMonstro.getFa();
                        danoExtraM += itemDoMonstro.getBonus();
                    }
                    if("r".equalsIgnoreCase(itemDoMonstro.getTipo())) {
                        bonusItemFaM += itemDoMonstro.getFa();
                        monstroTank += itemDoMonstro.getBonus();
                    }
                    int faMonstro = faBaseMonstro + bonusItemFaM;

                    System.out.printf("\nFA %s: %d | FA %s: %d\n", personagem.getNome(), faPersonagem, monstro.getRaca(), faMonstro);

                    if (faPersonagem > faMonstro) {
                        int dano = 2 + danoExtra - monstroTank + sorte;
                        monstro.setEnergia(monstro.getEnergia() - dano);
                        System.out.printf("Você acertou o monstro e causou %d de dano! Extra %d | Tankou: %d | Sorte: %d\n", dano, danoExtra, monstroTank, sorte);
                        sorte = 0;
                    } else if (faMonstro > faPersonagem) {
                        int dano = 2 + danoExtraM - personagemTank - sorte;
                        personagem.setEnergia(personagem.getEnergia() - dano);
                        System.out.printf("O monstro te acertou e causou %d de dano! Extra: %d | Tankou: %d\n", dano, danoExtraM, personagemTank);
                        sorte = 0;
                    } else {
                        System.out.println("Empate! Ninguém acerta.");
                    }
                    break;

                case 2:
                    if ("Guerreiro".equalsIgnoreCase(personagem.getClasse())) {
                        System.out.println("Você é um Guerreiro e não sabe magia");
                        break;
                    }
                    List<Arcano> magias = personagem.getMagiaEquipadas();
                    if (magias.isEmpty()) {
                        System.out.println("Você não possui magias.");
                        break;
                    }

                    //verifições ^^^^^^^^^^
                    //é as mesmas variaveis, mas adicionei "ma" (magia), porque java é uma bosta

                    int faBaseMa = personagem.getHabilidade() + rand.nextInt(10) + 1;
                    int faBaseMonstroMa = monstro.getHabilidade() + rand.nextInt(10) + 1;
                    int bonusItemFaMa = 0;
                    int bonusItemFaMonstroMa = 0;
                    int danoExtraMa = 0;
                    int danoExtraMonstroMa = 0;
                    int monstroTankMa = 0;
                    int personagemTankMa = 0;

                    for (Item item : personagem.getItemEquipado()) {
                        if ("Ataque".equalsIgnoreCase(item.getTipo())) {
                            bonusItemFaMa += item.getFa();
                            danoExtraMa += item.getBonus();
                        }
                        if("Defesa".equalsIgnoreCase(item.getTipo())) {
                            bonusItemFaMa += item.getFa();
                            personagemTankMa += item.getBonus();
                        }
                    }
                    int faPersonagemMa = faBaseMa + bonusItemFaMa;

                    // pegando o item do monstro
                    Item itemDoMonstroMa = monstro.getItem();
                    if("w".equalsIgnoreCase(itemDoMonstroMa.getTipo())) {
                        bonusItemFaMonstroMa += itemDoMonstroMa.getFa();
                        danoExtraMonstroMa += itemDoMonstroMa.getBonus();
                    }
                    if("r".equalsIgnoreCase(itemDoMonstroMa.getTipo())) {
                        bonusItemFaMonstroMa += itemDoMonstroMa.getFa();
                        monstroTankMa += itemDoMonstroMa.getBonus();
                    }
                    int faMonstroMa = faBaseMonstroMa + bonusItemFaMonstroMa;

                    System.out.println("\nEscolha uma magia para usar:");
                    for (int i = 0; i < magias.size(); i++) {
                        System.out.printf("[%d] %s\n", i + 1, magias.get(i));
                    }
                    System.out.print("Escolha: ");
                    int escolhaMagia = sc.nextInt();

                    if (escolhaMagia < 1 || escolhaMagia > magias.size()) {
                        System.out.println("Escolha inválida.");
                        break;
                    }

                    Arcano magiaEscolhida = magias.get(escolhaMagia - 1);

                    System.out.printf("\nFA %s: %d | FA %s: %d\n", personagem.getNome(), faPersonagemMa, monstro.getRaca(), faMonstroMa);

                    if (faPersonagemMa > faMonstroMa) {
                        int dano = 2 + danoExtraMa - monstroTankMa + sorte + (int)magiaEscolhida.getBonus();
                        monstro.setEnergia(monstro.getEnergia() - dano);
                        System.out.printf(magiaEscolhida.getNome() + " usada! ");
                        System.out.printf("Você acertou o monstro e causou %d de dano! | Magia %d | Extra %d | Tankou: %d | Sorte: %d\n", dano, (int)magiaEscolhida.getBonus(), danoExtraMa, monstroTankMa, sorte);
                        sorte = 0;
                    } else if (faMonstroMa > faPersonagemMa) {
                        int dano = 2 + danoExtraMonstroMa - personagemTankMa - sorte;
                        personagem.setEnergia(personagem.getEnergia() - dano);
                        System.out.printf("O monstro te acertou e causou %d de dano! Extra: %d | Tankou: %d\n", dano, danoExtraMa, personagemTankMa);
                        sorte = 0;
                    } else {
                        System.out.println("Empate! Ninguém acerta.");
                    }
                    break;

                case 3:
                    if (personagem.getSorte() <= 0) {
                        System.out.println("Você não tem mais pontos de sorte!");
                        break;
                    }
                    if(sorte > 0) {
                        System.out.println("Já usou sua sorte nesta rodada");
                        break;
                    }

                    int testeSorte = rand.nextInt(personagem.getSorte()) + personagem.getSorte();
                    // Aqui fiz o seguinte, pega a sorte base do personagem duas vezes, ou seja se ele tirar pelo menos metade do que ele tem
                    // ele tem sorte, e mais um random é gerado pra ver quanto dano a mais que ele vai dar, o valor do dano é diminuido na sorte dele
                    System.out.printf("Testando sua sorte... Tirou %d, sua sorte atual é %d\n", testeSorte, personagem.getSorte());

                    if (testeSorte <= personagem.getSorte()) {
                        System.out.println("Você teve SORTE!");
                        int sorteQntd = rand.nextInt(personagem.getSorte()) + 1;
                        sorte += sorteQntd;
                        System.out.println("Ataque foi aumentado em: "+sorte);
                        personagem.setSorte(personagem.getSorte() - sorte);
                        //nao deixar a sorte cair abaixo de zero
                        if(personagem.getSorte() < 0) {
                            personagem.setSorte(0);
                        }
                        System.out.println("Sua sorte restante foi: "+personagem.getSorte());
                    } else {
                        System.out.println("Você foi AZARADO e perdeu 1 de sorte.");
                        personagem.setSorte(personagem.getSorte() - 1);
                    }
                    break;

                case 4:
                    System.out.println("Você tentou fugir, mas o monstro te alcançou. Você perdeu a batalha...");
                    return false;

                default: System.out.println("Opção inválida.");
            }

            System.out.printf("Sua Energia: %d | Energia do Monstro: %d\n",
                    personagem.getEnergia(), monstro.getEnergia());

            if (personagem.getEnergia() <= 0 || monstro.getEnergia() <= 0)
                break;
        }

        return personagem.getEnergia() > 0;
    }
}