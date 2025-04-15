package telas;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import personagem.Personagem;

public class TelaPadrao {
    Scanner sc = new Scanner(System.in);
    private Personagem personagem;
    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }


    public void lerCena(int numCena) {
        try {
            String nomeArquivo = "#" + numCena + ".txt";
            File arquivo = new File("src\\cenas\\" + nomeArquivo);

            Scanner leitor = new Scanner(arquivo);

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                System.out.println(linha);
            }
            leitor.close();
        } catch (
                FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }
}
