package p.l.omnomnom.step;

public class Step {
    private long id;
    private String name;
    private int number;
    private long recipeId;

    public Step(){

    }

    public Step(String name, int number, long recipeId){
        this.name = name;
        this.number = number;
        this.recipeId = recipeId;
    }

    public Step(int id, String name, int number, long recipeId){
        this.id = id;
        this.name = name;
        this.number = number;
        this.recipeId = recipeId;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }
}
