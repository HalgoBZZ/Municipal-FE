package com.halgo.municipalpfe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.halgo.municipalpfe.adapters.OffreAdapter;
import com.halgo.municipalpfe.modals.Offre;

import java.util.ArrayList;
import java.util.List;

public class OffreClientActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private boolean isOpen;
    private RecyclerView recyclerView;
    private OffreAdapter mAdapter;

    //private String url ="http://10.0.3.2:8080/tournees/byreleveur";
    private List<Offre> offres = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offres_for_client);
        setNavigationViewListner();


        menu_icon = findViewById(R.id.menu_offres_client);
        notification_icon = findViewById(R.id.notification_offres_client);
        help_icon = findViewById(R.id.help_offres_client);
        logout_icon = findViewById(R.id.logout_offres_client);
        mDrawerlayout = findViewById(R.id.draw_offres_client);
        recyclerView = findViewById(R.id.recycler_view_offres_client);
        mAdapter = new OffreAdapter(offres);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        NavigationView navigationView = findViewById(R.id.nav_view_offres_client);
        navigationView.setNavigationItemSelectedListener(this);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        this.offres.add(new Offre("offre1", "description1", "etat1"));
        this.offres.add(new Offre("offre1", "description1", "etat1"));
        this.offres.add(new Offre("offre1", "description1", "etat1"));
        this.offres.add(new Offre("offre1", "description1", "etat1"));


        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OffreClientActivity.this, NotificationsActivity.class));
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OffreClientActivity.this, HelpActivity.class));
            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OffreClientActivity.this, MainActivity.class));
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
                startActivity(new Intent(OffreClientActivity.this, PropertiesClientActivity.class));
                break;
            case R.id.offres_client:
                startActivity(new Intent(OffreClientActivity.this, OffreClientActivity.class));
                break;
            case R.id.payements_client:
                startActivity(new Intent(OffreClientActivity.this, PayementClientActivity.class));
                break;
            case R.id.contrats_client:
                startActivity(new Intent(OffreClientActivity.this, ContratsClientActivity.class));
                break;
            default:
                break;
        }
        mDrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListner() {
        NavigationView navigationView = findViewById(R.id.nav_view_offres_client);
        navigationView.setNavigationItemSelectedListener(this);
    }
}