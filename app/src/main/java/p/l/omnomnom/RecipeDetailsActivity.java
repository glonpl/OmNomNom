package p.l.omnomnom;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import p.l.omnomnom.igredient.IngredientInRecipe;
import p.l.omnomnom.recipe.Recipe;
import p.l.omnomnom.recipe.RecipeAdapter;
import p.l.omnomnom.step.Step;

public class RecipeDetailsActivity extends AppCompatActivity {

    Recipe recipe;
    long recipeId;
    RecipeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null)
        {
            recipeId = (int) b.get("recipeId");
            recipe = (Recipe) b.getSerializable("edit");
            TextView textView = findViewById(R.id.textView);
            setTitle(recipe.getName());

            adapter = new RecipeAdapter(this);
            List<Step> steps = adapter.getStepsByRecipeId(recipe.getId());
            List<IngredientInRecipe> ingredients = adapter.getIngredientsByRecipeId(recipe.getId());
            StringBuilder napis = new StringBuilder();
            napis.append(recipe.getName());
            for (Step s : steps) {
                napis.append(s.getName()+ " \n");
            }
            for (IngredientInRecipe s : ingredients) {
                napis.append(s.getIngredientId() + " ");
                napis.append(s.getRecipeId() + " ");
                napis.append(s.getAmount() + " \n");
            }


            textView.setText("przepis " + napis);
        }

        //String message = intent.getStringExtra("recipeId");

        // Capture the layout's TextView and set the string as its text
//        TextView textView = findViewById(R.id.textView);
//        textView.setText("przepis " + message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.recipe_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.edit_recipe:
                //Todo EDYCJA
                //editRecipe();
                return true;
            case R.id.delete_recipe:
                adapter.removeRecipe(recipe.getId());
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
