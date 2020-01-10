package p.l.omnomnom;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import p.l.omnomnom.igredient.Ingredient;
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
            setTitle(recipe.getName());

            adapter = new RecipeAdapter(this);

            TextView textViewServing = findViewById(R.id.textViewServing);
            textViewServing.setText(String.valueOf(recipe.getServing()));

            TextView textViewTime = findViewById(R.id.textViewTime);
            textViewTime.setText(recipe.getTime());

            List<IngredientInRecipe> ingredients = adapter.getIngredientsByRecipeId(recipe.getId());
            StringBuilder ingredientsDescription = new StringBuilder();
            for (IngredientInRecipe s : ingredients) {
                Ingredient i = adapter.getIngredientsByIngredientId(s.getIngredientId());
                ingredientsDescription.append(s.getAmount()).append(" ");
                ingredientsDescription.append(i.getName()).append(" \n");
            }
            TextView textViewIngredients = findViewById(R.id.textViewIngredients);
            textViewIngredients.setText(ingredientsDescription);


            List<Step> steps = adapter.getStepsByRecipeId(recipe.getId());
            StringBuilder stepsDescription = new StringBuilder();

            for (Step s : steps) {
                stepsDescription.append(s.getNumber()).append(".").append(" ");
                stepsDescription.append(s.getName()).append(" \n");
            }
            TextView textViewSteps = findViewById(R.id.textViewSteps);
            textViewSteps.setText(stepsDescription);
        }
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
        Intent intent;
        switch (item.getItemId()) {
            case R.id.edit_recipe:
                intent = new Intent(this, AddRecipeActivity.class);
                intent.putExtra("edit", recipe);
                intent.putExtra("isEdit", true);
                this.startActivity(intent);
                return true;
            case R.id.delete_recipe:
                adapter.removeRecipe(recipe.getId());
                intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
