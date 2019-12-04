package p.l.omnomnom.igredient;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import p.l.omnomnom.helpers.DatabaseHelper;

public class IgredientAdapter {
    DatabaseHelper helper;
    public IgredientAdapter(Context context)
    {
        helper = new DatabaseHelper(context);
    }

    public void addIgredient(Igredient igredient){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", igredient.getName()); // Contact Name

        // Inserting Row
        db.insert("Igredient", null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public List<Igredient> getIgredientByRecipeId(){
        List<Igredient> ingredientList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM Igredient WHERE recipe_id = ?";

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Igredient igredient = new Igredient();
                igredient.setId(Integer.parseInt(cursor.getString(0)));
                igredient.setName(cursor.getString(1));
                // Adding contact to list
                ingredientList.add(igredient);
            } while (cursor.moveToNext());
        }

        // return contact list
        return ingredientList;
    }
}
