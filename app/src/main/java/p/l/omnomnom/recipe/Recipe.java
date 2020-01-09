package p.l.omnomnom.recipe;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
    private long id;
    private String name;
    private String time;
    private int serving;

    public Recipe(){

    }

    public Recipe(String name){
        this.name = name;
    }

    public Recipe(String name, String time, int serving){
        this.name = name;
        this.time = time;
        this.serving = serving;
    }

    public Recipe(long id, String name, String time, int serving){
        this.id = id;
        this.name = name;
        this.time = time;
        this.serving = serving;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<Recipe> createRecipesList(int numContacts) {
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        for (int i = 1; i <= numContacts; i++) {
            recipes.add(new Recipe("Nazwa " + i));
        }

        return recipes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getServing() {
        return serving;
    }

    public void setServing(int serving) {
        this.serving = serving;
    }
}
