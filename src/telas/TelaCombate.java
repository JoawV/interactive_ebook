//package telas;
//
//import personagem.Personagem;
//
//import java.util.Random;
//import java.util.Scanner;
//
//public class TelaCombate {
//    Scanner sc = new Scanner(System.in);
//    Random random = new Random();
//    private Personagem personagem;
//
//    public TelaCombate(Personagem personagem) {
//        this.personagem = personagem;
//    }
//
//    public boolean iniciarCombate(String nome, int habilidade, int energia, int tesouro, int provisao, int cenaSucesso, int cenaDerrota) {
//        System.out.println("Você encontrou um monstro: " + nome);
//        while (energia > 0 && personagem.getEnergia() > 0) {
//            int faMonstro = habilidade + random.nextInt(10) + 1;
//            int faJogador = personagem.getHabilidade() + random.nextInt(10) + 1;
//
//            System.out.println("FA Jogador: " + faJogador + " | FA Monstro: " + faMonstro);
//
//            if (faJogador > faMonstro) {
//                energia -= 2;
//                System.out.println("Você acertou o monstro! Energia dele: " + energia);
//            } else if (faMonstro > faJogador) {
//                personagem.setEnergia(personagem.getEnergia() - 2);
//                System.out.println("O monstro te acertou! Sua energia: " + personagem.getEnergia());
//            } else {
//                System.out.println("Empate! Ninguém se acertou.");
//            }
//        }
//
//        if (energia <= 0) {
//            System.out.println("Você venceu o combate!");
//            personagem.setTesouro(tesouro);
//            personagem.adicionarEquipamentoExtra(provisao);
//            personagem.setCena(cenaSucesso);
//            return true;
//        } else {
//            System.out.println("Você foi derrotado.");
//            personagem.setCena(cenaDerrota);
//            return false;
//        }
//    }
//}
