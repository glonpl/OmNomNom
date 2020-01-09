package p.l.omnomnom.igredient;

public class IngredientInRecipe {
    private long id;
    private long recipeId;
    private long ingredientId;
    private String amount;

    public IngredientInRecipe(){

    }

    public IngredientInRecipe(long recipeId, long ingredientId, String amount){
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.amount = amount;
    }

    public IngredientInRecipe(long recipeId, long ingredientId){
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }
}
