package com.halgo.municipalpfe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.halgo.municipalpfe.R;
import com.halgo.municipalpfe.adapters.ContratAdapter;
import com.halgo.municipalpfe.adapters.PropertiesAdapter;
import com.halgo.municipalpfe.modals.Contrat;
import com.halgo.municipalpfe.modals.Propertie;

import java.util.ArrayList;
import java.util.List;

public class Contrats extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private boolean isOpen;
    private RecyclerView recyclerView;
    private ContratAdapter mAdapter;
    private FloatingActionButton add_button;
    //private String url ="http://10.0.3.2:8080/tournees/byreleveur";
    private List<Contrat> contrats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contrats);
        setNavigationViewListner();


        menu_icon = findViewById(R.id.menu_contrats);
        notification_icon = findViewById(R.id.notification_contrats);
        help_icon = findViewById(R.id.help_contrats);
        logout_icon = findViewById(R.id.logout_contrats);
        mDrawerlayout = findViewById(R.id.draw_contrats);
        recyclerView = findViewById(R.id.recycler_view_contrats);
        add_button = findViewById(R.id.add_new_contrats);
        mAdapter = new ContratAdapter(contrats);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        NavigationView navigationView = findViewById(R.id.nav_view_contrats);
        navigationView.setNavigationItemSelectedListener(this);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        this.contrats.add(new Contrat("contrat1", 1700));
        this.contrats.add(new Contrat("contrat1", 1700));
        this.contrats.add(new Contrat("contrat1", 1700));
        this.contrats.add(new Contrat("contrat1", 1700));
        this.contrats.add(new Contrat("contrat1", 1700));


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Contrats.this, NewContrat.class));
            }
        });

        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Contrats.this, NotificationsActivity.class));
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Contrats.this, HelpActivity.class));
            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Contrats.this, MainActivity.class));
            }
        });


        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isOpen) {
                    mDrawerlayout.openDrawer(Gravity.START);
                    isOpen = true;
                } else {
                    mDrawerlayout.closeDrawer(Gravity.START);
                    isOpen = false;
                }
            }
        });
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.client_admin :
                startActivity(new Intent(Contrats.this, Clients.class));
                break;
            case R.id.properties_admin:
                startActivity(new Intent(Contrats.this, PropertiesActivity.class));
                break;
            case R.id.offres_admin:
                startActivity(new Intent(Contrats.this, OffresActivity.class));
                break;
            case R.id.payements_admin:
                startActivity(new Intent(Contrats.this, Payements.class));
                break;
            case R.id.contrats_admin:
                startActivity(new Intent(Contrats.this, Contrats.class));
                break;
            case R.id.configs_admin:
                startActivity(new Intent(Contrats.this, Configurations.class));
                break;
            default:
                break;
        }
        mDrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListner() {
        NavigationView navigationView = findViewById(R.id.nav_view_contrats);
        navigationView.setNavigationItemSelectedListener(this);
    }

}
