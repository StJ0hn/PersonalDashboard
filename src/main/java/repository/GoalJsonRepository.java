package repository;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.Goal;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GoalJsonRepository {
    private static final String FILE_PATH = "data/goals.json";
    private Gson gson;

    public GoalJsonRepository(){
        java.io.File directory = new java.io.File("data");
        if (!directory.exists()) {
            directory.mkdir(); //cria pasta física 'data' na raiz do projeto
        }

        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context) -> new JsonPrimitive(src.toString()))
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) -> LocalDate.parse(json.getAsString()))
                .setPrettyPrinting()
                .create();
    }

    public void saveGoals(List<Goal> goals){
        try (Writer writer = new FileWriter(FILE_PATH)){
            gson.toJson(goals, writer);
        } catch (IOException ioException){
            System.out.println("Error at store goals: " + ioException.getMessage());
        }
    }

    public List<Goal> loadGoals(){
        File file = new File(FILE_PATH);
        if (!file.exists()){
            return new ArrayList<>();
        }
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Goal>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Error at load goals: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
