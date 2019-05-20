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

import com.halgo.municipalpfe.adapters.PropertiesAdapter;
import com.halgo.municipalpfe.api.ApiOffre;
import com.halgo.municipalpfe.api.ApiPropriete;
import com.halgo.municipalpfe.api.ApiUtils;
import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Offre;
import com.halgo.municipalpfe.modals.Propriete;
import com.halgo.municipalpfe.modals.Type;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertiesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private ImageView profile_icon;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private boolean isOpen;
    private RecyclerView recyclerView;
    private PropertiesAdapter mAdapter;
    private FloatingActionButton add_button;

    private Client connectedUser ;
    private TextView connectedUser_name;
    private ApiPropriete service;


    private List<Propriete> properties = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proprieties);
        setNavigationViewListner();


        menu_icon = findViewById(R.id.menu_properties);
        notification_icon = findViewById(R.id.notification_properties);
        help_icon = findViewById(R.id.help_properties);
        logout_icon = findViewById(R.id.logout_properties);
        profile_icon = findViewById(R.id.user_properties);
        mDrawerlayout = findViewById(R.id.draw_properties);
        recyclerView = findViewById(R.id.recycler_view_properties);
        add_button = findViewById(R.id.add_new_properties);

        connectedUser_name = findViewById(R.id.connected_user_properties);
        service = ApiUtils.getProprieteService();
        connectedUser = (Client)getIntent().getSerializableExtra("connectedUser");
        connectedUser_name.setText(connectedUser.getNom_client()+" "+connectedUser.getPrenom_client()+" ");


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        NavigationView navigationView = findViewById(R.id.nav_view_properties);
        navigationView.setNavigationItemSelectedListener(this);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();


        Call<List<Propriete>> call = service.getAllProprietes();
        call.enqueue(new Callback<List<Propriete>>() {
            @Override
            public void onResponse(Call<List<Propriete>> call, Response<List<Propriete>> response) {
                if (response.isSuccessful()) {
                    properties = response.body();
                    mAdapter = new PropertiesAdapter(properties);
                    recyclerView.setAdapter(mAdapter);
                    //Log.d("resultat:", client.getId().toString());
                    if (properties.size()<1) {

                        Toast.makeText(PropertiesActivity.this, "Aucune Proprieté à afficher", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PropertiesActivity.this, "Une erreur s'est produite lors de chargement des données!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(PropertiesActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PropertiesActivity.this, NewOffre.class));
            }
        });

        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PropertiesActivity.this, NotificationsActivity.class));
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PropertiesActivity.this, HelpActivity.class));
            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PropertiesActivity.this, MainActivity.class));
            }
        });

        profile_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PropertiesActivity.this, ProfileActivity.class));
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
                Intent intent = new Intent(PropertiesActivity.this, Clients.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                break;
            case R.id.properties_admin:
                Intent intent1 = new Intent(PropertiesActivity.this, PropertiesActivity.class);
                intent1.putExtra("connectedUser", connectedUser);
                startActivity(intent1);
                break;
            case R.id.offres_admin:
                Intent intent2 = new Intent(PropertiesActivity.this, OffresActivity.class);
                intent2.putExtra("connectedUser", connectedUser);
                startActivity(intent2);
                break;
            case R.id.payements_admin:
                Intent intent3 = new Intent(PropertiesActivity.this, Payements.class);
                intent3.putExtra("connectedUser", connectedUser);
                startActivity(intent3);
                break;
            case R.id.contrats_admin:
                Intent intent4 = new Intent(PropertiesActivity.this, Contrats.class);
                intent4.putExtra("connectedUser", connectedUser);
                startActivity(intent4);
                break;
            case R.id.configs_admin:
                Intent intent5 = new Intent(PropertiesActivity.this, Configurations.class);
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
        NavigationView navigationView = findViewById(R.id.nav_view_properties);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void getAllProprietes() {
        Call<List<Propriete>> call = service.getAllProprietes();
        call.enqueue(new Callback<List<Propriete>>() {
            @Override
            public void onResponse(Call<List<Propriete>> call, Response<List<Propriete>> response) {
                if (response.isSuccessful()) {
                    properties = response.body();
                    //Log.d("resultat:", client.getId().toString());
                    if (properties.size()<1) {

                        Toast.makeText(PropertiesActivity.this, "Aucune Proprieté à afficher", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PropertiesActivity.this, "Une erreur s'est produite lors de chargement des données!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(PropertiesActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
