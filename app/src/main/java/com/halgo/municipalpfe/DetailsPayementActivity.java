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

public class DetailsPayementActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private ImageButton delete_btn;
    private ImageButton update_btn;
    private TextView etat;
    private TextView duree;
    private TextView ajout;
    private TextView modification;
    private boolean isOpen;
    //private String url ="http://10.0.3.2:8080/tournees/byreleveur";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_payement);
        setNavigationViewListner();


        menu_icon = findViewById(R.id.menu_details_payement);
        notification_icon = findViewById(R.id.notification_details_payement);
        help_icon = findViewById(R.id.help_details_payement);
        logout_icon = findViewById(R.id.logout_details_payement);
        mDrawerlayout = findViewById(R.id.draw_details_payement);
        delete_btn = findViewById(R.id.delete_payement_btn);
        update_btn = findViewById(R.id.update_payement_btn);
        etat = findViewById(R.id.etat_details_payement);
        duree = findViewById(R.id.debut_details_payement);
        ajout = findViewById(R.id.ajout_details_payement);
        modification = findViewById(R.id.modification_details_payement);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());



        NavigationView navigationView = findViewById(R.id.nav_view_details_payement);
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
                startActivity(new Intent(DetailsPayementActivity.this, NotificationsActivity.class));
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsPayementActivity.this, HelpActivity.class));
            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsPayementActivity.this, MainActivity.class));
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
            case R.id.properties_client:
                startActivity(new Intent(DetailsPayementActivity.this, PropertiesClientActivity.class));
                break;
            case R.id.offres_client:
                startActivity(new Intent(DetailsPayementActivity.this, OffreClientActivity.class));
                break;
            case R.id.payements_client:
                startActivity(new Intent(DetailsPayementActivity.this, PayementClientActivity.class));
                break;
            case R.id.contrats_client:
                startActivity(new Intent(DetailsPayementActivity.this, ContratsClientActivity.class));
                break;
            default:
                break;
        }
        mDrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListner() {
        NavigationView navigationView = findViewById(R.id.nav_view_details_payement);
        navigationView.setNavigationItemSelectedListener(this);
    }

}