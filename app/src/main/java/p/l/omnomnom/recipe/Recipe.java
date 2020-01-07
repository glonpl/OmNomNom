package p.l.omnomnom.recipe;

import java.util.ArrayList;

public class Recipe {
    private int id;
    private String name;

    public Recipe(){

    }

    public Recipe(String name){
        this.name = name;
    }

    public Recipe(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
