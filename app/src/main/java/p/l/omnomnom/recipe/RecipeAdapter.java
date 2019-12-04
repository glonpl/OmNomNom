package p.l.omnomnom.recipe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import p.l.omnomnom.helpers.DatabaseHelper;

public class RecipeAdapter {

    DatabaseHelper helper;
    public RecipeAdapter(Context context)
    {
        helper = new DatabaseHelper(context);
    }

    public void addRecipe(Recipe recipe){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", recipe.getName()); // Contact Name

        // Inserting Row
        db.insert("Recipe", null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
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
                // Adding contact to list
                recipeList.add(recipe);
            } while (cursor.moveToNext());
        }

        // return contact list
        return recipeList;
    }
}
