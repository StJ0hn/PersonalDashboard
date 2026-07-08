package repository;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.Task;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class TaskJsonRepository {

    private static final String FILE_PATH = "src/main/java/tasks.json";
    private Gson gson;

    public TaskJsonRepository(){
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context) -> new JsonPrimitive(src.toString()))
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) -> LocalDate.parse(json.getAsString()))
                .setPrettyPrinting()
                .create();
    }

    public void saveTasks(List<Task> tasks) {
        try (Writer writer = new FileWriter(FILE_PATH)){
            gson.toJson(tasks, writer);
        } catch (IOException ioException){
            System.out.println("Error at store tasks: " + ioException.getMessage());
        }
    }

    public List<Task> loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()){
            return new ArrayList<>();
        }
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Task>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            System.out.println("Error at load as tasks: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}