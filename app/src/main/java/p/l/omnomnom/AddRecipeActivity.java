package p.l.omnomnom;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import p.l.omnomnom.igredient.Ingredient;
import p.l.omnomnom.igredient.IngredientAdapter;
import p.l.omnomnom.igredient.IngredientInRecipe;
import p.l.omnomnom.recipe.Recipe;
import p.l.omnomnom.recipe.RecipeAdapter;
import p.l.omnomnom.step.Step;

public class AddRecipeActivity extends AppCompatActivity implements  
        AdapterView.OnItemSelectedListener {
    List<String> ingredients;
    int ingredientsCount = 0;
    int stepsCount = 0;
    Recipe recipe;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ingredientsCount = 1;
        stepsCount = 1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null)
        {
            recipe = (Recipe) b.getSerializable("edit");
        }

        if(recipe == null){
            setTitle("Dodaj przepis");
        }
        else{
            setTitle("Edytuj przepis");
            setValues(recipe);
        }

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

    }

    NumberPicker.OnValueChangeListener onValueChangeListener =
            new 	NumberPicker.OnValueChangeListener(){
                @Override
                public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    Toast.makeText(AddRecipeActivity.this,
                            "selected number "+numberPicker.getValue(), Toast.LENGTH_SHORT);
                }
            };

    public void setValues(Recipe recipe){
        EditText editTextName = findViewById(R.id.editTextName);
        editTextName.setText(recipe.getName());

        EditText editTextTime = findViewById(R.id.editTextTime);
        editTextTime.setText(recipe.getTime());

        NumberPicker numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setValue(recipe.getServing());

        RecipeAdapter adapter = new RecipeAdapter(this);
        List<IngredientInRecipe> ingredients = adapter.getIngredientsByRecipeId(recipe.getId());
        List<Step> steps = adapter.getStepsByRecipeId(recipe.getId());

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setSelection((int)ingredients.get(0).getIngredientId());

        EditText editTextIngredient = findViewById(R.id.editTextIgredientValue);
        editTextIngredient.setText(ingredients.get(0).getAmount());

        EditText editTextStep = findViewById(R.id.editTextStep);
        editTextStep.setText(steps.get(0).getName());
    }

    public void onAddRecipe(View view) {
        EditText editTextName = findViewById(R.id.editTextName);
        String recipeName = editTextName.getText().toString();

        EditText editTextTime = findViewById(R.id.editTextTime);
        String recipeTime = editTextTime.getText().toString();

        NumberPicker numberPicker = findViewById(R.id.numberPicker);
        int numberPickerValue = numberPicker.getValue();

        Spinner spinner = findViewById(R.id.spinner);
        int spinnerValue = spinner.getSelectedItemPosition();

        EditText editTextIngredient = findViewById(R.id.editTextIgredientValue);
        String recipeIngredientValue = editTextIngredient.getText().toString();

        EditText editTextStep = findViewById(R.id.editTextStep);
        String recipeStepValue = editTextStep.getText().toString();

        RecipeAdapter recipeAdapter = new RecipeAdapter(this);
        long id = recipeAdapter.addRecipe(new Recipe(recipeName, recipeTime, numberPickerValue));

        List<IngredientInRecipe> ingredients = new ArrayList<>();
        IngredientInRecipe ingredient = new IngredientInRecipe(id, spinnerValue+1, recipeIngredientValue);
        ingredients.add(ingredient);
        //recipeAdapter.addIngredientsInRecipe(ingredients);

        layout = (LinearLayout)findViewById(R.id.linear);
        int count = layout.getChildCount();
        for(int i = 0; i < count; i++){
            View child = layout.getChildAt(i);
            if(child instanceof LinearLayout ) {
                //Support for RadioGroups
                LinearLayout radio = (LinearLayout)child;
                Spinner s = (Spinner) radio.getChildAt(0);
                spinnerValue = s.getSelectedItemPosition();
                EditText t = (EditText) radio.getChildAt(1);
                recipeIngredientValue = t.getText().toString();

                ingredient = new IngredientInRecipe(id, spinnerValue+1, recipeIngredientValue);
                ingredients.add(ingredient);
            }
        }
        recipeAdapter.addIngredientsInRecipe(ingredients);


        int stepNumber = 1;
        List<Step> steps = new ArrayList<>();
        Step step = new Step(recipeStepValue, stepNumber, id);
        stepNumber++;
        steps.add(step);

        layout2 = (LinearLayout)findViewById(R.id.linear2);
        int count2 = layout2.getChildCount();
        for(int i = 0; i < count2; i++){
            View child = layout2.getChildAt(i);
            if(child instanceof LinearLayout ) {
                //Support for RadioGroups
                LinearLayout radio = (LinearLayout)child;
                EditText t = (EditText) radio.getChildAt(0);
                recipeStepValue = t.getText().toString();

                step = new Step(recipeStepValue, stepNumber, id);
                steps.add(step);
            }
            stepNumber++;
        }


        recipeAdapter.addSteps(steps);

        Intent intent = new Intent(this, MainActivity.class);

        //intent.putExtra("", recipeName);
        startActivity(intent);
    }

    public void onAddIngredient(View view) {

        layout = (LinearLayout)findViewById(R.id.linear);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout l = new LinearLayout(this);
        Spinner s = new Spinner(this, Spinner.MODE_DIALOG);
        s.setOnItemSelectedListener(this);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        float factor = this.getResources().getDisplayMetrics().density;

        s.setPadding((int)(10 * factor), (int)(10 * factor), (int)(10 * factor), (int)(10 * factor));
        params2.width = (int)(163 * factor);
        s.setLayoutParams(params2);

        IngredientAdapter ingredientAdapter = new IngredientAdapter(this);
        ingredients = ingredientAdapter.getAllIngredientsNames();
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ingredients);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(aa);


        EditText t = new EditText(this);
        t.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        l.addView(s);
        l.addView(t);

        layout.addView(l);
        params.height = 145 * getIngredientsNumber();
        ingredientsCount++;
        layout.setLayoutParams(params);
    }

    public int getIngredientsNumber(){
        return ingredientsCount;
    }

    public int getStepsNumber(){
        return stepsCount;
    }

    LinearLayout layout2;
    public void onAddStep(View view) {

        layout2 = (LinearLayout)findViewById(R.id.linear2);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout l = new LinearLayout(this);
        EditText t = new EditText(this);
        t.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

        l.addView(t);

        stepsCount++;
        params.height = 100 * getStepsNumber();

        layout2.setLayoutParams(params);
//
        layout2.addView(l);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
