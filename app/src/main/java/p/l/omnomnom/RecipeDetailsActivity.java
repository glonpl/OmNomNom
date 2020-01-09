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
import android.widget.TextView;

import p.l.omnomnom.recipe.Recipe;

public class RecipeDetailsActivity extends AppCompatActivity {

    Recipe recipe;
    int recipeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //setTitle("aa");


        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null)
        {
            int j = (int) b.get("recipeId");
            Recipe a = (Recipe) b.getSerializable("edit");
            TextView textView = findViewById(R.id.textView);
            textView.setText("przepis " + a.getName());
            setTitle(a.getName());
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
                //Todo EDYCJA I USUWANIE
                //editRecipe();
                return true;
            case R.id.delete_recipe:
                //deleteRecipe();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
