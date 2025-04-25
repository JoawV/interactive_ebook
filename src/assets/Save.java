package assets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import personagem.Personagem;

public class Save {
    Personagem personagem;
    public Save(Personagem personagem) {
        this.personagem = personagem;
    }

    public void salvarJogo() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(personagem);

        try (FileWriter writer = new FileWriter("src\\assets\\saves\\" + personagem.getNome().replaceAll("[^a-zA-Z0-9\\-_]", "_") + ".json")) {
            writer.write(json);
            System.out.println("Jogo salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o jogo...");
            e.printStackTrace();
        }
    }
}
