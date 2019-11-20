package p.l.omnomnom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import p.l.omnomnom.recipe.Recipe;
import p.l.omnomnom.recipe.RecipeAdapter;

public class MainActivity extends AppCompatActivity implements RecipeFragment.OnFragmentInteractionListener,
ConverterFragment.OnFragmentInteractionListener{
    static final String EXTRA_MESSAGE = "message";
    private TextView mTextMessage;
    Fragment fragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_recepies);
                    fragment = new RecipeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_converter);
                    fragment = new ConverterFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_notifications:
//                    fragment = new RecipeFragment();
//                    loadFragment(fragment);
//                    mTextMessage.setText(R.string.title_calories);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //DatabaseHelper db = new DatabaseHelper(this);

        //db.addRecipe(new Recipe(1, "testowe name"));
        //List<Recipe> contacts = db.getAllRecipes();

//        for (Recipe cn : contacts) {
//            String log = "Id: " + cn.getId() + " ,Name: " + cn.getName();
//            Log.d("Name: ", log);
//            System.out.println("Name");
//        }

        //System.out.println("aa");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
//        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new RecipeFragment());
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof RecipeFragment) {
            RecipeFragment headlinesFragment = (RecipeFragment) fragment;
            headlinesFragment.setOnHeadlineSelectedListener(this);
        }
    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.nameEditText);
        String recipeName = editText.getText().toString();

        RecipeAdapter recipeAdapter = new RecipeAdapter(this);
        recipeAdapter.addRecipe(new Recipe(recipeName));
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        intent.putExtra(EXTRA_MESSAGE, recipeName);
        startActivity(intent);
    }

    public void showAll(View view){
        RecipeAdapter recipeAdapter = new RecipeAdapter(this);
        List<Recipe> recipes = recipeAdapter.getAllRecipes();
        String string = "";
        for(Recipe recipe: recipes){
            string += recipe.getName()+"\n";
        }
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        intent.putExtra(EXTRA_MESSAGE, string);
        startActivity(intent);
    }
}
