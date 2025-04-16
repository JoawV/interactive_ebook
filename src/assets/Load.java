package assets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.io.IOException;
import personagem.Personagem;

public class Load {
    private String jsonArquivo;

    public Load(String jsonArquivo) {
        this.jsonArquivo = jsonArquivo;
    }

    public Personagem carregarJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileReader reader = new FileReader(jsonArquivo)) {
            Personagem p = gson.fromJson(reader, Personagem.class);
            System.out.println("Jogo carregado com sucesso!");
            return p;
        } catch (IOException e) {
            System.out.println("Erro ao carregar o jogo.");
            e.printStackTrace();
            return null;
        }
    }
}
