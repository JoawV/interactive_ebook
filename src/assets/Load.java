package assets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import personagem.Personagem;
import telas.TelaPadrao;

public class Load {
    TelaPadrao telaPadrao = new TelaPadrao();
    private String jsonArquivo;
    public Load() {};
    private final String pastaSaves = "C:\\Users\\João\\Desktop\\interactive_ebook\\src\\assets\\saves";

    public Personagem mostrarMenuCarregamento() {
        File pasta = new File(pastaSaves);
        File[] arquivos = pasta.listFiles((dir, name) -> name.endsWith(".json"));

        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Nenhum arquivo de salvamento encontrado.");
            return null;
        }

        System.out.println("=== Arquivos salvos encontrados ===");
        for (int i = 0; i < arquivos.length; i++) {
            System.out.println((i + 1) + " - " + arquivos[i].getName());
        }

        Scanner sc = new Scanner(System.in);
        int escolha;

        do {
            System.out.print("Escolha o número do arquivo para carregar: ");
            escolha = sc.nextInt();
            sc.nextLine(); // consumir o Enter

            if (escolha < 1 || escolha > arquivos.length) {
                System.out.println("Escolha inválida. Tente novamente.");
            }
        } while (escolha < 1 || escolha > arquivos.length);

        File arquivoEscolhido = arquivos[escolha - 1];
        return carregarJson(arquivoEscolhido.getPath());
    }

    public Personagem carregarJson(String caminho) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileReader reader = new FileReader(caminho)) {
            Personagem p = gson.fromJson(reader, Personagem.class);
            telaPadrao.setPersonagem(p);
            telaPadrao.lerCena(p.getCena());
            System.out.println("Jogo carregado com sucesso!");
            return p;
        } catch (IOException e) {
            System.out.println("Erro ao carregar o jogo.");
            e.printStackTrace();
            return null;
        }
    }
}
