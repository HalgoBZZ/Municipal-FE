package com.halgo.municipalpfe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsContratActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private ImageButton delete_btn;
    private ImageButton update_btn;
    private TextView titre;
    private TextView debut;
    private TextView fin;
    private TextView prix;
    private TextView ajout;
    private TextView modification;
    private boolean isOpen;
    //private String url ="http://10.0.3.2:8080/tournees/byreleveur";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_contrat);
        setNavigationViewListner();


        menu_icon = findViewById(R.id.menu_details_contrat);
        notification_icon = findViewById(R.id.notification_details_contrat);
        help_icon = findViewById(R.id.help_details_contrat);
        logout_icon = findViewById(R.id.logout_details_contrat);
        mDrawerlayout = findViewById(R.id.draw_details_contrat);
        delete_btn = findViewById(R.id.delete_contrat_btn);
        update_btn = findViewById(R.id.update_contrat_btn);
        titre = findViewById(R.id.titre_details_contrat);
        debut = findViewById(R.id.debut_details_contrat);
        fin = findViewById(R.id.fin_details_contrat);
        prix = findViewById(R.id.prix_details_contrat);
        ajout = findViewById(R.id.ajout_details_contrat);
        modification = findViewById(R.id.modification_details_contrat);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());


        NavigationView navigationView = findViewById(R.id.nav_view_details_client);
        navigationView.setNavigationItemSelectedListener(this);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsContratActivity.this, NotificationsActivity.class));
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsContratActivity.this, HelpActivity.class));
            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsContratActivity.this, MainActivity.class));
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
        switch (item.getItemId()) {
            case R.id.client_admin:
                startActivity(new Intent(DetailsContratActivity.this, Clients.class));
                break;
            case R.id.properties_admin:
                startActivity(new Intent(DetailsContratActivity.this, PropertiesActivity.class));
                break;
            case R.id.offres_admin:
                startActivity(new Intent(DetailsContratActivity.this, OffresActivity.class));
                break;
            case R.id.payements_admin:
                startActivity(new Intent(DetailsContratActivity.this, Payements.class));
                break;
            case R.id.contrats_admin:
                startActivity(new Intent(DetailsContratActivity.this, Contrats.class));
                break;
            case R.id.configs_admin:
                startActivity(new Intent(DetailsContratActivity.this, Configurations.class));
                break;
            default:
                break;
        }
        mDrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListner() {
        NavigationView navigationView = findViewById(R.id.nav_view_details_contrat);
        navigationView.setNavigationItemSelectedListener(this);
    }
}

