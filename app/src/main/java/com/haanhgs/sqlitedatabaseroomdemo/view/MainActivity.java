package com.haanhgs.sqlitedatabaseroomdemo.view;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.view.View;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.haanhgs.sqlitedatabaseroomdemo.R;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;

    private void setupDrawer(){
        DrawerLayout drawer = findViewById(R.id.dlMain);
        NavigationView navigationView = findViewById(R.id.nvMain);
        mAppBarConfiguration = new AppBarConfiguration
                .Builder(R.id.nav_home, R.id.mniInsert, R.id.mniSearch)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.frMain);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void setupToolbar(){
        Toolbar toolbar = findViewById(R.id.tbrMain);
        setSupportActionBar(toolbar);
    }

    private void openFragmentDetail(){
        Bundle bundle = new Bundle();
        bundle.putBoolean("isNew", true);
        navController.navigate(R.id.action_nav_home_to_mniInsert, bundle);
    }

    private void setupFAB(){
        FloatingActionButton fab = findViewById(R.id.fbnMain);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragmentDetail();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupDrawer();
        setupFAB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.frMain);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
