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
import android.widget.TextView;
import android.widget.Toast;

import com.halgo.municipalpfe.adapters.ContratAdapter;
import com.halgo.municipalpfe.api.ApiClientInterface;
import com.halgo.municipalpfe.api.ApiContrat;
import com.halgo.municipalpfe.api.ApiUtils;
import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Contrat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Contrats extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private ImageView profile_icon;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private boolean isOpen;
    private RecyclerView recyclerView;
    private ContratAdapter mAdapter;
    private FloatingActionButton add_button;
    private List<Contrat> contrats = new ArrayList<>();

    private Client connectedUser ;
    private TextView connectedUser_name;
    private ApiContrat service;

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
        profile_icon = findViewById(R.id.user_contrats);

        connectedUser_name = findViewById(R.id.connected_user_contrats);
        service = ApiUtils.getContratService();
        connectedUser = (Client)getIntent().getSerializableExtra("connectedUser");
        connectedUser_name.setText(connectedUser.getNom_client()+" "+connectedUser.getPrenom_client()+" ");


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));



        NavigationView navigationView = findViewById(R.id.nav_view_contrats);
        navigationView.setNavigationItemSelectedListener(this);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        Call<List<Contrat>> call = service.getAllContrats();
        call.enqueue(new Callback<List<Contrat>>() {
            @Override
            public void onResponse(Call<List<Contrat>> call, Response<List<Contrat>> response) {
                if (response.isSuccessful()) {
                    contrats = response.body();
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    mAdapter = new ContratAdapter(contrats);
                    recyclerView.setAdapter(mAdapter);
                    //Log.d("resultat:", client.getId().toString());
                    if (contrats.size()<1) {

                        Toast.makeText(Contrats.this, "Aucune contrat à afficher", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Contrats.this, "Une erreur s'est produite lors de chargement des données!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(Contrats.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Contrats.this, NewContrat.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
            }
        });

        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Contrats.this, NotificationsActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Contrats.this, HelpActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Contrats.this, MainActivity.class);
                new Intent(Contrats.this, NewContrat.class);
                startActivity(intent);
            }
        });

        profile_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Contrats.this, ProfileActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
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
                Intent intent = new Intent(Contrats.this, Clients.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                break;
            case R.id.properties_admin:
                Intent intent1 = new Intent(Contrats.this, PropertiesActivity.class);
                intent1.putExtra("connectedUser", connectedUser);
                startActivity(intent1);
                break;
            case R.id.offres_admin:
                Intent intent2 = new Intent(Contrats.this, OffresActivity.class);
                intent2.putExtra("connectedUser", connectedUser);
                startActivity(intent2);
                break;
            case R.id.payements_admin:
                Intent intent3 = new Intent(Contrats.this, Payements.class);
                intent3.putExtra("connectedUser", connectedUser);
                startActivity(intent3);
                break;
            case R.id.contrats_admin:
                Intent intent4 = new Intent(Contrats.this, Contrats.class);
                intent4.putExtra("connectedUser", connectedUser);
                startActivity(intent4);
                break;
            case R.id.configs_admin:
                Intent intent5 = new Intent(Contrats.this, Configurations.class);
                intent5.putExtra("connectedUser", connectedUser);
                startActivity(intent5);
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

    public void getAllContrats() {
        Call<List<Contrat>> call = service.getAllContrats();
        call.enqueue(new Callback<List<Contrat>>() {
            @Override
            public void onResponse(Call<List<Contrat>> call, Response<List<Contrat>> response) {
                if (response.isSuccessful()) {
                    contrats = response.body();
                    //Log.d("resultat:", client.getId().toString());
                    if (contrats.size()<1) {

                        Toast.makeText(Contrats.this, "Aucune contrat à afficher", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Contrats.this, "Une erreur s'est produite lors de chargement des données!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(Contrats.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
