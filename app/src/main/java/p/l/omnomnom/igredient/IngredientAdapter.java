package p.l.omnomnom.igredient;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import p.l.omnomnom.helpers.DatabaseHelper;

public class IngredientAdapter {
    DatabaseHelper helper;
    public IngredientAdapter(Context context)
    {
        helper = new DatabaseHelper(context);
    }

    public void addIgredient(Ingredient igredient){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", igredient.getName()); // Contact Name

        // Inserting Row
        db.insert("Ingredient", null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public List<Ingredient> getIgredientByRecipeId(){
        List<Ingredient> ingredientList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM Igredient WHERE recipe_id = ?";

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Ingredient igredient = new Ingredient();
                igredient.setId(Integer.parseInt(cursor.getString(0)));
                igredient.setName(cursor.getString(1));
                // Adding contact to list
                ingredientList.add(igredient);
            } while (cursor.moveToNext());
        }

        // return contact list
        return ingredientList;
    }

    public List<Ingredient> getAllIngredients(){
        List<Ingredient> ingredientsList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT id, name FROM Ingredient";

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(Integer.parseInt(cursor.getString(0)));
                ingredient.setName(cursor.getString(1));
                // Adding contact to list
                ingredientsList.add(ingredient);
            } while (cursor.moveToNext());
        }

        // return contact list
        return ingredientsList;
    }

    public List<String> getAllIngredientsNames(){
        ArrayList<String> ingredientsList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT id, name FROM Ingredient";

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Ingredient ingredient = new Ingredient();
                ingredient.setId(Integer.parseInt(cursor.getString(0)));
                ingredient.setName(cursor.getString(1));
                // Adding contact to list
                ingredientsList.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // return contact list
        return ingredientsList;
    }
}
