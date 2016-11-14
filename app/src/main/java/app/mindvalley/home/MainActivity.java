package app.mindvalley.home;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import app.mindvalley.R;
import app.mindvalley.home.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        fragmentManager.addOnBackStackChangedListener(this);
        addFragment(new HomeFragment());
    }

    public void addFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.replace(R.id.container,fragment,fragment.getClass().getName());
        fragmentTransaction.commit();
    }

    public void addFragmentWithTransition(Fragment fragment, View transitionElement, String transitionString){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.addSharedElement(transitionElement, transitionString);
        fragmentTransaction.replace(R.id.container,fragment,fragment.getClass().getName());
        fragmentTransaction.commit();
    }

    public void popFragment(){
        if(fragmentManager.getBackStackEntryCount() > 1){
            fragmentManager.popBackStack();
        }else{
            finish();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == android.R.id.home){
            popFragment();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackStackChanged() {
        if(fragmentManager.getBackStackEntryCount() > 1){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }else{
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }
}
