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

import p.l.omnomnom.MainActivity;
import p.l.omnomnom.R;
import p.l.omnomnom.RecipeDetailsActivity;
import p.l.omnomnom.helpers.DatabaseHelper;

public class RecipeAdapter extends
        RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    DatabaseHelper helper;
//    private ItemClickListener mClickListener;
    private RecyclerView mRecyclerView;
    public RecipeAdapter(Context context)
    {
        helper = new DatabaseHelper(context);
        recipes = getAllRecipes();
    }

    public RecipeAdapter(Context context, RecyclerView view)
    {
        helper = new DatabaseHelper(context);
        recipes = getAllRecipes();
        mRecyclerView = view;
    }

    public RecipeAdapter(Context context, ArrayList<Recipe> recipes)
    {
        helper = new DatabaseHelper(context);
        this.recipes = recipes;
    }
    List<Recipe> recipes;

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

                context.startActivity(intent);
            }
        });

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(recipe.getName());
        Button button = holder.messageButton;
        button.setText("Pokaż");
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
            messageButton = (Button) itemView.findViewById(R.id.recipe_button);
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
