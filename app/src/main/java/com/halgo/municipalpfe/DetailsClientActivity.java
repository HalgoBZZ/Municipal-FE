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

import com.halgo.municipalpfe.api.ApiClientInterface;
import com.halgo.municipalpfe.api.ApiUtils;
import com.halgo.municipalpfe.modals.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailsClientActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private ImageView profile_icon;

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private ImageButton delete_btn;
    private ImageButton update_btn;
    private TextView nom_prenom;
    private TextView naissance;
    private TextView cin;
    private TextView ajout;
    private TextView modification;
    private TextView connectedUser_name;
    private boolean isOpen;
    private Client connectedUser;
    private Client client;
    private ApiClientInterface service;
    //private String url ="http://10.0.3.2:8080/tournees/byreleveur";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_clients);
        setNavigationViewListner();


        menu_icon = findViewById(R.id.menu_details_client);
        notification_icon = findViewById(R.id.notification_details_client);
        help_icon = findViewById(R.id.help_details_client);
        logout_icon = findViewById(R.id.logout_details_client);
        profile_icon = findViewById(R.id.user_details_client);
        mDrawerlayout = findViewById(R.id.draw_details_client);
        delete_btn = findViewById(R.id.delete_client_btn);
        update_btn = findViewById(R.id.update_client_btn);
        nom_prenom = findViewById(R.id.nom_prenom_details_client);
        naissance = findViewById(R.id.naissance_details_client);
        cin = findViewById(R.id.cin_details_client);
        ajout = findViewById(R.id.ajout_details_client);
        modification = findViewById(R.id.modification_details_client);
        connectedUser_name = findViewById(R.id.connected_user_details_client);

        connectedUser = (Client)getIntent().getSerializableExtra("connectedUser");
        connectedUser_name.setText(connectedUser.getNom_client()+" "+connectedUser.getPrenom_client()+" ");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        service = ApiUtils.getUserService();

        client = (Client)getIntent().getSerializableExtra("client");
        if (!isNullOrEmpty(client.getNom_client()) && !isNullOrEmpty(client.getPrenom_client())) {
            nom_prenom.setText(client.getNom_client() + " " + client.getPrenom_client());
        }
        if (!isNullOrEmpty(client.getDate_naissance())) {
            naissance.setText("Né le " + client.getDate_naissance());
        }
        if (client.getCin()>0) {
            cin.setText("CIN: " + client.getCin());
        }
        if (!isNullOrEmpty(client.getDate_ajout())) {
            ajout.setText("Ajouté le " + client.getDate_ajout());
        }
        if (!isNullOrEmpty(client.getDate_modification())) {
            modification.setText("modifié le " + client.getDate_modification());
        }

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
                Intent intent = new Intent(DetailsClientActivity.this, NotificationsActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                //startActivity(new Intent(DetailsClientActivity.this, NotificationsActivity.class));
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsClientActivity.this, HelpActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                //startActivity(new Intent(DetailsClientActivity.this, HelpActivity.class));
            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsClientActivity.this, MainActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                //startActivity(new Intent(DetailsClientActivity.this, MainActivity.class));
            }
        });
        profile_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsClientActivity.this, ProfileActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                //startActivity(new Intent(DetailsClientActivity.this, ProfileActivity.class));
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

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsClientActivity.this, NewClient.class);
                intent.putExtra("connectedUser", connectedUser);
                intent.putExtra("action", "update");
                intent.putExtra("client", client);
                startActivity(intent);

            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Void> deleteRequest = service.deleteClient(client.getId_compte());
                deleteRequest.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(DetailsClientActivity.this, "Opération effectué avec succés!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DetailsClientActivity.this, Clients.class);
                        intent.putExtra("connectedUser", connectedUser);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(DetailsClientActivity.this, "Une erreur s'est produite lors de suppression!", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.client_admin :
                Intent intent = new Intent(DetailsClientActivity.this, Clients.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                //startActivity(new Intent(DetailsClientActivity.this, Clients.class));
                break;
            case R.id.properties_admin:
                Intent intent5 = new Intent(DetailsClientActivity.this, PropertiesActivity.class);
                intent5.putExtra("connectedUser", connectedUser);
                startActivity(intent5);
                //startActivity(new Intent(DetailsClientActivity.this, PropertiesActivity.class));
                break;
            case R.id.offres_admin:
                Intent intent1 = new Intent(DetailsClientActivity.this, OffresActivity.class);
                intent1.putExtra("connectedUser", connectedUser);
                startActivity(intent1);
                //startActivity(new Intent(DetailsClientActivity.this, OffresActivity.class));
                break;
            case R.id.payements_admin:
                Intent intent2 = new Intent(DetailsClientActivity.this, Payements.class);
                intent2.putExtra("connectedUser", connectedUser);
                startActivity(intent2);
                //startActivity(new Intent(DetailsClientActivity.this, Payements.class));
                break;
            case R.id.contrats_admin:
                Intent intent3 = new Intent(DetailsClientActivity.this, Contrats.class);
                intent3.putExtra("connectedUser", connectedUser);
                startActivity(intent3);
               // startActivity(new Intent(DetailsClientActivity.this, Contrats.class));
                break;
            case R.id.configs_admin:
                Intent intent4 = new Intent(DetailsClientActivity.this, Configurations.class);
                intent4.putExtra("connectedUser", connectedUser);
                startActivity(intent4);
               // startActivity(new Intent(DetailsClientActivity.this, Configurations.class));
                break;
            default:
                break;
        }
        mDrawerlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListner() {
        NavigationView navigationView = findViewById(R.id.nav_view_details_client);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

}