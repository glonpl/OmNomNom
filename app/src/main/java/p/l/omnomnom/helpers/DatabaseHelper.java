package p.l.omnomnom.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "OmNomNom.db";

    private static final String SQL_CREATE_RECIPE =
            "CREATE TABLE Recipe (id INTEGER PRIMARY KEY," +
                    "name TEXT)";

    private static final String SQL_CREATE_STEP =
            "CREATE TABLE Step (id INTEGER PRIMARY KEY," +
                    "name TEXT," +
                    "number INTEGER," +
                    "recipe_id INTEGER)";

    private static final String SQL_DELETE_RECIPE =
            "DROP TABLE IF EXISTS Recipe";

    private static final String SQL_DELETE_STEP =
            "DROP TABLE IF EXISTS Step";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_RECIPE);
        db.execSQL(SQL_CREATE_STEP);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_RECIPE);
        db.execSQL(SQL_DELETE_STEP);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
