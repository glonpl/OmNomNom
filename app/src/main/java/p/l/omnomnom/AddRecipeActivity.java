package p.l.omnomnom;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import p.l.omnomnom.igredient.Ingredient;
import p.l.omnomnom.igredient.IngredientAdapter;
import p.l.omnomnom.recipe.Recipe;
import p.l.omnomnom.recipe.RecipeAdapter;

public class AddRecipeActivity extends AppCompatActivity implements  
        AdapterView.OnItemSelectedListener {
    String[] ingredients2 = { "Jajka", "Mąka", "Sól"};
    List<String> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Dodaj przepis");

        NumberPicker np = findViewById(R.id.numberPicker);

        np.setMinValue(1);
        np.setMaxValue(5);

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);


        //Creating the ArrayAdapter instance having the country list
        IngredientAdapter ingredientAdapter = new IngredientAdapter(this);
        ingredients = ingredientAdapter.getAllIngredientsNames();
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ingredients);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);


//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    NumberPicker.OnValueChangeListener onValueChangeListener =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    Toast.makeText(AddRecipeActivity.this,
                            "selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT);
                }
            };

    public void onAddRecipe(View view) {
        EditText editText = findViewById(R.id.editTextName);
        String recipeName = editText.getText().toString();

        RecipeAdapter recipeAdapter = new RecipeAdapter(this);
        recipeAdapter.addRecipe(new Recipe(recipeName));

        Intent intent = new Intent(this, MainActivity.class);

        //intent.putExtra("", recipeName);
        startActivity(intent);
    }

    LinearLayout layout;

    public void onAddIngredient(View view) {

        layout = (LinearLayout)findViewById(R.id.linear);

        TextView txtName = new TextView(this);
        //txtName.setId(28);
        LinearLayout linearlayout = new LinearLayout(this);
        txtName.setText("new text");
//
        linearlayout.addView(txtName);
        layout.addView(linearlayout);
    }

    public void onAddStep(View view) {

        layout = (LinearLayout)findViewById(R.id.linear);

        TextView txtName = new TextView(this);
        //txtName.setId(28);
        LinearLayout linearlayout = new LinearLayout(this);
        txtName.setText("new text");
//
        linearlayout.addView(txtName);
        layout.addView(linearlayout);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
