package p.l.omnomnom.step;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import p.l.omnomnom.DatabaseHelper;
import p.l.omnomnom.recipe.Recipe;
import p.l.omnomnom.step.Step;

public class StepAdapter {
    DatabaseHelper helper;
    public StepAdapter(Context context)
    {
        helper = new DatabaseHelper(context);
    }

    public void addStep(Step step){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", step.getName()); // Contact Name
        values.put("number", step.getNumber()); // Contact Name
        values.put("recipe_id", step.getRecipeId()); // Contact Name

        // Inserting Row
        db.insert("Step", null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public List<Step> getStepByRecipeId(){
        List<Step> stepList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM Step WHERE recipe_id = ?";

        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Step step = new Step();
                step.setId(Integer.parseInt(cursor.getString(0)));
                step.setName(cursor.getString(1));
                step.setNumber(cursor.getInt(2));
                // Adding contact to list
                stepList.add(step);
            } while (cursor.moveToNext());
        }

        // return contact list
        return stepList;
    }
}
