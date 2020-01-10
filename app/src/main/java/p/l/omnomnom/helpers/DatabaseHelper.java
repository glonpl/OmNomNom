package p.l.omnomnom.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "OmNomNom4.db";

    private static final String SQL_CREATE_RECIPE =
            "CREATE TABLE Recipe (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "time TEXT," +
                    "serving INTEGER)";

    private static final String SQL_CREATE_STEP =
            "CREATE TABLE Step (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "number INTEGER," +
                    "recipe_id INTEGER," +
                    "FOREIGN KEY(recipe_id) REFERENCES Recipe(id))";

    private static final String SQL_CREATE_INGREDIENT =
            "CREATE TABLE Ingredient (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT)";

    private static final String SQL_CREATE_INGREDIENT_RECIPE =
            "CREATE TABLE Ingredient_Recipe (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "recipe_id INTEGER," +
                    "ingredient_id INTEGER," +
                    "amount TEXT," +
                    "FOREIGN KEY(recipe_id) REFERENCES Recipe(id)," +
                    "FOREIGN KEY(ingredient_id) REFERENCES Ingredient(id))";

    private static final String SQL_DELETE_RECIPE =
            "DROP TABLE IF EXISTS Recipe";

    private static final String SQL_DELETE_STEP =
            "DROP TABLE IF EXISTS Step";

    private static final String SQL_DELETE_INGREDIENT=
            "DROP TABLE IF EXISTS Ingredient";

    private static final String SQL_DELETE_INGREDIENT_RECIPE=
            "DROP TABLE IF EXISTS Ingredient_Recipe";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_RECIPE);
        db.execSQL(SQL_CREATE_STEP);
        db.execSQL(SQL_CREATE_INGREDIENT);
        db.execSQL(SQL_CREATE_INGREDIENT_RECIPE);

        loadIngredients(db);
        loadRecipes(db);
        loadSteps(db);
        loadIngredientRecipes(db);
//        db.close();
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_RECIPE);
        db.execSQL(SQL_DELETE_STEP);
        db.execSQL(SQL_DELETE_INGREDIENT);
        db.execSQL(SQL_DELETE_INGREDIENT_RECIPE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void loadIngredients(SQLiteDatabase db){
        db.execSQL("INSERT INTO Ingredient (name) VALUES('Jajka');");
        db.execSQL("INSERT INTO Ingredient (name) VALUES('Mąka');");
        db.execSQL("INSERT INTO Ingredient (name) VALUES('Cukier');");
    }

    public void loadRecipes(SQLiteDatabase db){
        db.execSQL("INSERT INTO Recipe (name, time, serving) VALUES('Jajecznica', '5', 1);");
    }

    public void loadSteps(SQLiteDatabase db){
        db.execSQL("INSERT INTO Step (name, number, recipe_id) VALUES('Na patelni rozpuszczamy masło', 1, 1);");
        db.execSQL("INSERT INTO Step (name, number, recipe_id) VALUES('Wbijamy jajka na patelnię', 2, 1);");
        db.execSQL("INSERT INTO Step (name, number, recipe_id) VALUES('Przyprawiamy', 3, 1);");
    }

    public void loadIngredientRecipes(SQLiteDatabase db){
        db.execSQL("INSERT INTO Ingredient_Recipe (recipe_id, ingredient_id, amount) VALUES(1, 1, 3);");
    }
}
