package p.l.omnomnom.step;

public class Step {
    private int id;
    private String name;
    private int number;
    private int recipeId;

    public Step(){

    }

    public Step(String name, int number, int recipeId){
        this.name = name;
        this.number = number;
        this.recipeId = recipeId;
    }

    public Step(int id, String name, int number, int recipeId){
        this.id = id;
        this.name = name;
        this.number = number;
        this.recipeId = recipeId;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}
