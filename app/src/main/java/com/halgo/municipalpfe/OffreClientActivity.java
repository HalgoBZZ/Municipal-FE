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
import android.widget.TextView;
import android.widget.Toast;

import com.halgo.municipalpfe.adapters.OffreAdapter;
import com.halgo.municipalpfe.api.ApiOffre;
import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.EtatOffre;
import com.halgo.municipalpfe.modals.Offre;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private Client connectedUser ;
    private TextView connectedUser_name;
    private ApiOffre service;

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
        connectedUser_name = findViewById(R.id.connected_user_offre_client);
        connectedUser = (Client)getIntent().getSerializableExtra("connectedUser");
        connectedUser_name.setText(connectedUser.getNom_client()+" "+connectedUser.getPrenom_client()+" ");
        mDrawerlayout = findViewById(R.id.draw_offres_client);
        recyclerView = findViewById(R.id.recycler_view_offres_client);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //mAdapter = new OffreAdapter(offres, connectedUser);
        Call<List<Offre>> call = service.getAllOffres();
        call.enqueue(new Callback<List<Offre>>() {
            @Override
            public void onResponse(Call<List<Offre>> call, Response<List<Offre>> response) {
                if (response.isSuccessful()) {
                    offres = response.body();
                    mAdapter = new OffreAdapter(offres, connectedUser);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);

                    if (offres.size()<1) {
                        Toast.makeText(OffreClientActivity.this, "Aucun offre à afficher", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OffreClientActivity.this, "Une erreur s'est produite lors de chargement des données!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(OffreClientActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(mAdapter);
        NavigationView navigationView = findViewById(R.id.nav_view_offres_client);
        navigationView.setNavigationItemSelectedListener(this);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        getOffres();


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
                Intent intent = new Intent(OffreClientActivity.this, PropertiesClientActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                break;
            case R.id.offres_client:
                Intent intent1 = new Intent(OffreClientActivity.this, OffreClientActivity.class);
                intent1.putExtra("connectedUser", connectedUser);
                startActivity(intent1);
                break;
            case R.id.payements_client:
                Intent intent2 = new Intent(OffreClientActivity.this, PayementClientActivity.class);
                intent2.putExtra("connectedUser", connectedUser);
                startActivity(intent2);
                break;
            case R.id.contrats_client:
                Intent intent3 = new Intent(OffreClientActivity.this, ContratsClientActivity.class);
                intent3.putExtra("connectedUser", connectedUser);
                startActivity(intent3);
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

    public void getOffres() {
        Call<List<Offre>> call = service.getAllOffres();
        call.enqueue(new Callback<List<Offre>>() {
            @Override
            public void onResponse(Call<List<Offre>> call, Response<List<Offre>> response) {
                if (response.isSuccessful()) {
                    offres = response.body();
                    //Log.d("resultat:", client.getId().toString());
                    if (offres.size()<1) {

                        Toast.makeText(OffreClientActivity.this, "Aucun offre à afficher", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OffreClientActivity.this, "Une erreur s'est produite lors de chargement des données!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(OffreClientActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}