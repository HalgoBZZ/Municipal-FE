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

import com.halgo.municipalpfe.adapters.ContratAdapter;
import com.halgo.municipalpfe.adapters.PayementAdapter;
import com.halgo.municipalpfe.modals.Contrat;
import com.halgo.municipalpfe.modals.Etat;
import com.halgo.municipalpfe.modals.Payement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayementClientActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private boolean isOpen;
    private RecyclerView recyclerView;
    private PayementAdapter mAdapter;

    //private String url ="http://10.0.3.2:8080/tournees/byreleveur";
    private List<Payement> payements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payements_client);
        setNavigationViewListner();


        menu_icon = findViewById(R.id.menu_payements_client);
        notification_icon = findViewById(R.id.notification_payements_client);
        help_icon = findViewById(R.id.help_payements_client);
        logout_icon = findViewById(R.id.logout_payements_client);
        mDrawerlayout = findViewById(R.id.draw_payements_client);
        recyclerView = findViewById(R.id.recycler_view_payements_client);
        mAdapter = new PayementAdapter(payements);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        NavigationView navigationView = findViewById(R.id.nav_view_payements_client);
        navigationView.setNavigationItemSelectedListener(this);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        this.payements.add(new Payement(LocalDate.now(),LocalDate.now(), "Paye"));

        //this.payements.add(new Payement(LocalDate.of(2018,02,02),LocalDate.of(2018,02,02), "Payé"));
        //this.payements.add(new Payement(LocalDate.of(2018,02,02),LocalDate.of(2018,02,02), "Payé"));




        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(PayementClientActivity.this, NotificationsActivity.class));
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PayementClientActivity.this, HelpActivity.class));
            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PayementClientActivity.this, MainActivity.class));
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
            case R.id.properties_client:
                startActivity(new Intent(PayementClientActivity.this, PropertiesClientActivity.class));
                break;
            case R.id.offres_client:
                startActivity(new Intent(PayementClientActivity.this, OffreClientActivity.class));
                break;
            case R.id.payements_client:
                startActivity(new Intent(PayementClientActivity.this, PayementClientActivity.class));
                break;
            case R.id.contrats_client:
                startActivity(new Intent(PayementClientActivity.this, ContratsClientActivity.class));
                break;
            default:
                break;
        }
        mDrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListner() {
        NavigationView navigationView = findViewById(R.id.nav_view_payements_client);
        navigationView.setNavigationItemSelectedListener(this);
    }

}