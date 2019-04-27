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

import com.halgo.municipalpfe.adapters.ClientAdapter;

import com.halgo.municipalpfe.modals.Client;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Clients extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private boolean isOpen;
    private RecyclerView recyclerView;
    private ClientAdapter mAdapter;
    private FloatingActionButton add_button;
    //private String url ="http://10.0.3.2:8080/tournees/byreleveur";
    private List<Client> clients = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clients);
        setNavigationViewListner();


        menu_icon = findViewById(R.id.menu_clients);
        notification_icon = findViewById(R.id.notification_clients);
        help_icon = findViewById(R.id.help_clients);
        logout_icon = findViewById(R.id.logout_clients);
        mDrawerlayout = findViewById(R.id.draw_clients);
        recyclerView = findViewById(R.id.recycler_view_clients);
        add_button = findViewById(R.id.add_new_clients);
        mAdapter = new ClientAdapter(clients);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        NavigationView navigationView = findViewById(R.id.nav_view_clients);
        navigationView.setNavigationItemSelectedListener(this);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        this.clients.add(new Client("Baazouzi", "Hafedh", 12604555));
        this.clients.add(new Client("Baazouzi", "Hafedh", 12604555));
        this.clients.add(new Client("Baazouzi", "Hafedh", 12604555));

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Clients.this, NewClient.class));
            }
        });

        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Clients.this, NotificationsActivity.class));
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Clients.this, HelpActivity.class));
            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Clients.this, MainActivity.class));
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
                startActivity(new Intent(Clients.this, Clients.class));
                break;
            case R.id.properties_admin:
                startActivity(new Intent(Clients.this, PropertiesActivity.class));
                break;
            case R.id.offres_admin:
                startActivity(new Intent(Clients.this, OffresActivity.class));
                break;
            case R.id.payements_admin:
                startActivity(new Intent(Clients.this, Payements.class));
                break;
            case R.id.contrats_admin:
                startActivity(new Intent(Clients.this, Contrats.class));
                break;
            case R.id.configs_admin:
                startActivity(new Intent(Clients.this, Configurations.class));
                break;
            default:
                break;
        }
        mDrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListner() {
        NavigationView navigationView = findViewById(R.id.nav_view_clients);
        navigationView.setNavigationItemSelectedListener(this);
    }
}
