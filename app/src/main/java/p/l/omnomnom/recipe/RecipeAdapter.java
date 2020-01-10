package p.l.omnomnom.recipe;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import p.l.omnomnom.MainActivity;
import p.l.omnomnom.R;
import p.l.omnomnom.RecipeDetailsActivity;
import p.l.omnomnom.helpers.DatabaseHelper;
import p.l.omnomnom.igredient.Ingredient;
import p.l.omnomnom.igredient.IngredientInRecipe;
import p.l.omnomnom.step.Step;

public class RecipeAdapter extends
        RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    DatabaseHelper helper;
    List<Recipe> recipes;
//    private ItemClickListener mClickListener;
    private RecyclerView mRecyclerView;
    public RecipeAdapter(Context context)
    {
        helper = new DatabaseHelper(context);
        Recipe r = new Recipe("aaaaaa");
        recipes = new ArrayList<>();
//        recipes = getAllRecipes();
        recipes.add(r);
    }

    public RecipeAdapter(Context context, RecyclerView view)
    {
        helper = new DatabaseHelper(context);
        recipes = getAllRecipes();
        mRecyclerView = view;
    }


    public long addRecipe(Recipe recipe){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", recipe.getName());
        values.put("time", recipe.getTime());
        values.put("serving", recipe.getServing());

        // Inserting Row
        long id = db.insert("Recipe", null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection

        return id;
    }

    public void updateRecipe(Recipe recipe){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", recipe.getName());
        values.put("time", recipe.getTime());
        values.put("serving", recipe.getServing());

        String whereClause = "id=?";
        String[] whereArgs = new String[] { String.valueOf(recipe.getId()) };

        db.update("Recipe", values, whereClause, whereArgs);

        db.close();
    }

    public void removeRecipe(long id){
        SQLiteDatabase db = helper.getWritableDatabase();

        String whereClause = "id=?";
        String[] whereArgs = new String[] { String.valueOf(id) };

        db.delete("Recipe", whereClause, whereArgs);
        db.close();
    }

    public void removeIngredientsByRecipeId(long id){
        SQLiteDatabase db = helper.getWritableDatabase();

        String whereClause = "recipe_id=?";
        String[] whereArgs = new String[] { String.valueOf(id) };

        db.delete("Ingredient_Recipe", whereClause, whereArgs);
        db.close();
    }

    public void removeStepsByRecipeId(long id){
        SQLiteDatabase db = helper.getWritableDatabase();

        String whereClause = "recipe_id=?";
        String[] whereArgs = new String[] { String.valueOf(id) };

        db.delete("Step", whereClause, whereArgs);
        db.close();
    }

    public void addSteps(List<Step> steps){
        SQLiteDatabase db = helper.getWritableDatabase();

        for (Step s: steps) {
            ContentValues values = new ContentValues();
            values.put("name", s.getName());
            values.put("number", s.getNumber());
            values.put("recipe_id", s.getRecipeId());

            db.insert("Step", null, values);
        }
        db.close();
    }

    public void addIngredientsInRecipe(List<IngredientInRecipe> ingredients){
        SQLiteDatabase db = helper.getWritableDatabase();

        for (IngredientInRecipe i: ingredients) {
            ContentValues values = new ContentValues();
            values.put("recipe_id", i.getRecipeId());
            values.put("ingredient_id", i.getIngredientId());
            values.put("amount", i.getAmount());

            db.insert("Ingredient_Recipe", null, values);
        }
        db.close();
    }

    public List<Recipe> getAllRecipes(){
        List<Recipe> recipeList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM Recipe";

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setId(Integer.parseInt(cursor.getString(0)));
                recipe.setName(cursor.getString(1));
                recipe.setTime(cursor.getString(2));
                recipe.setServing(Integer.parseInt(cursor.getString(3)));
                // Adding contact to list
                recipeList.add(recipe);
            } while (cursor.moveToNext());
        }

        // return contact list
        return recipeList;
    }

    public List<Step> getStepsByRecipeId(long id){
        List<Step> steps = new ArrayList<>();

        String selectQuery = "SELECT  * FROM Step WHERE recipe_id = ?";

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(id)});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Step step = new Step();
                step.setId(Integer.parseInt(cursor.getString(0)));
                step.setName(cursor.getString(1));
                step.setNumber(Integer.parseInt(cursor.getString(2)));
                // Adding contact to list
                steps.add(step);
            } while (cursor.moveToNext());
        }

        return steps;
    }

    public List<IngredientInRecipe> getIngredientsByRecipeId(long id){
        List<IngredientInRecipe> ingredients = new ArrayList<>();

        String selectQuery = "SELECT  * FROM Ingredient_Recipe WHERE recipe_id = ?";

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(id)});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                IngredientInRecipe ingredient = new IngredientInRecipe();
                ingredient.setId(Integer.parseInt(cursor.getString(0)));
                ingredient.setRecipeId(Integer.parseInt(cursor.getString(1)));
                ingredient.setIngredientId(Integer.parseInt(cursor.getString(2)));
                ingredient.setAmount(cursor.getString(3));
                // Adding contact to list
                ingredients.add(ingredient);
            } while (cursor.moveToNext());
        }

        return ingredients;
    }

    public Ingredient getIngredientsByIngredientId(long id){
        List<IngredientInRecipe> ingredients = new ArrayList<>();

        String selectQuery = "SELECT  * FROM Ingredient WHERE id = ?";

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(id)});

        Ingredient ingredient = new Ingredient();;
        if (cursor.moveToFirst()) {
            do {
                ingredient.setId(Integer.parseInt(cursor.getString(0)));
                ingredient.setName(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        return ingredient;
    }

    public List<Recipe> getRecipesByQuery(String query){
        List<Recipe> recipeList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM Recipe WHERE lower(name) LIKE ?";

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(query+"%")});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Recipe recipe = new Recipe();
                recipe.setId(Integer.parseInt(cursor.getString(0)));
                recipe.setName(cursor.getString(1));
                recipe.setTime(cursor.getString(2));
                recipe.setServing(Integer.parseInt(cursor.getString(3)));
                // Adding contact to list
                recipeList.add(recipe);
            } while (cursor.moveToNext());
        }

        // return contact list
        return recipeList;
    }

    public Recipe getItem(int id) {
        return recipes.get(id);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_recipe, parent, false);

        contactView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // odnajdujemy indeks klikniętego elementu
                int positionToDelete = mRecyclerView.getChildAdapterPosition(v);
                // usuwamy element ze źródła danych
                Recipe recipe = recipes.get(positionToDelete);
                //recipes.remove(positionToDelete);
                // poniższa metoda w animowany sposób usunie element z listy
                //notifyItemRemoved(positionToDelete);

                Intent intent = new Intent(context, RecipeDetailsActivity.class);
                intent.putExtra("recipeId", positionToDelete);
                intent.putExtra("edit", recipe);
                context.startActivity(intent);
            }
        });

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        recipes.clear();
        if (charText.length() == 0) {
            recipes.addAll(getAllRecipes());
        } else {
            List<Recipe> rec = getRecipesByQuery(charText);
            recipes.addAll(rec);
            recipes = getRecipesByQuery(charText);
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(recipe.getName());
        //Button button = holder.messageButton;
        //button.setText("Pokaż");
        //button.setEnabled(contact.isOnline());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public Button messageButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.recipe_name);
            //messageButton = (Button) itemView.findViewById(R.id.recipe_button);
        }

//        @Override
//        public void onClick(View v) {
//            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());
//        }
    }

//    public void setClickListener(ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }

    // parent activity will implement this method to respond to click events
//    public interface ItemClickListener {
//        void onItemClick(View view, int position);
//    }
}
