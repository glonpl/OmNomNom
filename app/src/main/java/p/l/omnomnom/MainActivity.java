package p.l.omnomnom;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import p.l.omnomnom.converter.ConverterFragment;
import p.l.omnomnom.recipe.Recipe;
import p.l.omnomnom.recipe.RecipeAdapter;
import p.l.omnomnom.recipe.RecipeFragment;

public class MainActivity extends AppCompatActivity implements RecipeFragment.OnFragmentInteractionListener,
        ConverterFragment.OnFragmentInteractionListener
{
    static final String EXTRA_MESSAGE = "message";
    Fragment fragment;
    public RecipeAdapter adapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = RecipeFragment.newInstance("jakis", "string");
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_dashboard:
                    fragment = ConverterFragment.newInstance("cos", "costam");
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_notifications:

                    if (fragment != null) {
                        Snackbar.make(fragment.getView(), "Chwała poległym Studentom. Gabryś, pamiętamy [*]", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                    return true;
            }
            return false;
        }
    };

    public static void layout_immersive_sticky(View mylayout) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mylayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        } else {
            mylayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
            );
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        layout_immersive_sticky(getWindow().getDecorView());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        adapter = new RecipeAdapter(this);
        super.onCreate(savedInstanceState);

        layout_immersive_sticky(getWindow().getDecorView());
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        if (savedInstanceState != null) {
            fragment = getSupportFragmentManager().getFragment(savedInstanceState, "fragment");
            loadFragment(fragment);
        } else {
            fragment = RecipeFragment.newInstance("cdo", "sd");
            loadFragment(fragment);
        }
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //todo: zrob obsluge tilt, nie uzywaj konstruktora, tylko new instance
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
    public void onAttachFragment(@NonNull Fragment fragment) {
        if (fragment instanceof RecipeFragment) {
            RecipeFragment headlinesFragment = (RecipeFragment) fragment;
            headlinesFragment.setOnHeadlineSelectedListener(this);
        }
    }

    public void sendMessage(View view) {
//        EditText editText = findViewById(R.id.nameEditText);
//        String recipeName = editText.getText().toString();
//
//        RecipeAdapter recipeAdapter = new RecipeAdapter(this);
//        recipeAdapter.addRecipe(new Recipe(recipeName));
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//
//        intent.putExtra(EXTRA_MESSAGE, recipeName);
//        startActivity(intent);
    }

    public void showAll(View view) {
//        RecipeAdapter recipeAdapter = new RecipeAdapter(this);
//        List<Recipe> recipes = recipeAdapter.getAllRecipes();
//        String string = "";
//        for (Recipe recipe : recipes) {
//            string += recipe.getName() + "\n";
//        }
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//
//        intent.putExtra(EXTRA_MESSAGE, string);
//        startActivity(intent);
    }

    public void addRecipe(View view){
        //RecipeAdapter recipeAdapter = new RecipeAdapter(this);
//        List<Recipe> recipes = recipeAdapter.getAllRecipes();
//        String string = "";
//        for (Recipe recipe : recipes) {
//            string += recipe.getName() + "\n";
//        }
        Intent intent = new Intent(this, AddRecipeActivity.class);

//        intent.putExtra(EXTRA_MESSAGE, string);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "fragment", fragment);
    }

}
