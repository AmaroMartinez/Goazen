package com.example.goazen.Trabajador;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.goazen.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity_Trabajador extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfigurationt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__trabajador);
        Toolbar toolbar = findViewById(R.id.toolbar_trabajador);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_trabajador);
        NavigationView navigationView = findViewById(R.id.nav_view_trabajador);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfigurationt = new AppBarConfiguration.Builder(
                R.id.nav_perfil, R.id.nav_calendario_trabajador, R.id.nav_nominas_trabajador, R.id.nav_vacaciones_trabajador)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_trabajador);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfigurationt);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity__trabajador, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_trabajador);
        return NavigationUI.navigateUp(navController, mAppBarConfigurationt)
                || super.onSupportNavigateUp();
    }
}
