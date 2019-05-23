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
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.halgo.municipalpfe.api.ApiContrat;
import com.halgo.municipalpfe.api.ApiOffre;
import com.halgo.municipalpfe.api.ApiUtils;
import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Contrat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsContratActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private ImageView profile_icon;
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
    private TextView connectedUser_name;
    private boolean isOpen;
    private Client connectedUser;
    private Contrat contrat;
    private ApiContrat service;
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
        profile_icon = findViewById(R.id.user_details_contrat);
        mDrawerlayout = findViewById(R.id.draw_details_contrat);
        delete_btn = findViewById(R.id.delete_contrat_btn);
        update_btn = findViewById(R.id.update_contrat_btn);
        titre = findViewById(R.id.titre_details_contrat);
        debut = findViewById(R.id.debut_details_contrat);
        fin = findViewById(R.id.fin_details_contrat);
        prix = findViewById(R.id.prix_details_contrat);
        ajout = findViewById(R.id.ajout_details_contrat);
        modification = findViewById(R.id.modification_details_contrat);
        connectedUser_name = findViewById(R.id.connected_user_details_contrat);

        service = ApiUtils.getContratService();

        connectedUser = (Client)getIntent().getSerializableExtra("connectedUser");
        connectedUser_name.setText(connectedUser.getNom_client()+" "+connectedUser.getPrenom_client()+" ");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        contrat = (Contrat)getIntent().getSerializableExtra("contrat");

        if(!isNullOrEmpty(contrat.getTitre_contrat())){
            titre.setText(contrat.getTitre_contrat());
        }
        if(!isNullOrEmpty(contrat.getDate_debut())){
            debut.setText("Début de contrat: "+contrat.getDate_debut());
        }
        if(!isNullOrEmpty(contrat.getDate_fin())){
            fin.setText("Fin de contrat: "+contrat.getDate_fin());
        }
        if(!isNullOrEmpty(contrat.getDate_ajout())) {
            ajout.setText("Ajouté le "+contrat.getDate_ajout());
        }
        if(!isNullOrEmpty(contrat.getDate_modification())) {
            modification.setText("Modifié le" +contrat.getDate_modification());
        }
        if(contrat.getPrix()>0){
            prix.setText("Prix: "+contrat.getPrix());
        }

        NavigationView navigationView = findViewById(R.id.nav_view_details_contrat);
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
                Intent intent = new Intent(DetailsContratActivity.this, NotificationsActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                //startActivity(new Intent(DetailsContratActivity.this, NotificationsActivity.class));
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsContratActivity.this, HelpActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                //startActivity(new Intent(DetailsContratActivity.this, HelpActivity.class));
            }
        });

        profile_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsContratActivity.this, ProfileActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                //startActivity(new Intent(DetailsContratActivity.this, ProfileActivity.class));
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

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("id: ", contrat.getId_contrat()+"");
                Call<Void> deleteRequest = service.deleteContrat(contrat.getId_contrat());
                deleteRequest.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(DetailsContratActivity.this, "Opération effectué avec succés!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DetailsContratActivity.this, Contrats.class);
                        intent.putExtra("connectedUser", connectedUser);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(DetailsContratActivity.this, "Une erreur s'est produite lors de suppression!", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsContratActivity.this, NewContrat.class);
                intent.putExtra("connectedUser", connectedUser);
                intent.putExtra("action", "update");
                intent.putExtra("contrat", contrat);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.client_admin:
                Intent intent = new Intent(DetailsContratActivity.this, Clients.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                //startActivity(new Intent(DetailsContratActivity.this, Clients.class));
                break;
            case R.id.properties_admin:
                Intent intent1 = new Intent(DetailsContratActivity.this, PropertiesActivity.class);
                intent1.putExtra("connectedUser", connectedUser);
                startActivity(intent1);
                //startActivity(new Intent(DetailsContratActivity.this, PropertiesActivity.class));
                break;
            case R.id.offres_admin:
                Intent intent2 = new Intent(DetailsContratActivity.this, OffresActivity.class);
                intent2.putExtra("connectedUser", connectedUser);
                startActivity(intent2);
               // startActivity(new Intent(DetailsContratActivity.this, OffresActivity.class));
                break;
            case R.id.payements_admin:
                Intent intent3 = new Intent(DetailsContratActivity.this, Payements.class);
                intent3.putExtra("connectedUser", connectedUser);
                startActivity(intent3);
                //startActivity(new Intent(DetailsContratActivity.this, Payements.class));
                break;
            case R.id.contrats_admin:
                Intent intent4 = new Intent(DetailsContratActivity.this, Contrats.class);
                intent4.putExtra("connectedUser", connectedUser);
                startActivity(intent4);
               // startActivity(new Intent(DetailsContratActivity.this, Contrats.class));
                break;
            case R.id.configs_admin:
                Intent intent5 = new Intent(DetailsContratActivity.this, Configurations.class);
                intent5.putExtra("connectedUser", connectedUser);
                startActivity(intent5);
                //startActivity(new Intent(DetailsContratActivity.this, Configurations.class));
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

    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }
}

