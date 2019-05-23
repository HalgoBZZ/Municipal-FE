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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.halgo.municipalpfe.api.ApiPropriete;
import com.halgo.municipalpfe.api.ApiUtils;
import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Contrat;
import com.halgo.municipalpfe.modals.Propriete;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsPropertieActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private ImageView profile_icon;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private ImageButton delete_btn;
    private ImageButton update_btn;
    private TextView adresse;
    private TextView surface;
    private TextView type;
    private TextView ajout;
    private TextView modification;
    private boolean isOpen;
    private Propriete propriete;
    private Client connectedUser;
    private TextView connectedUser_name;
    private ApiPropriete service;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_properties);
        setNavigationViewListner();


        menu_icon = findViewById(R.id.menu_details_propertie);
        notification_icon = findViewById(R.id.notification_details_propertie);
        help_icon = findViewById(R.id.help_details_propertie);
        profile_icon = findViewById(R.id.user_details_propertie);
        logout_icon = findViewById(R.id.logout_details_propertie);
        mDrawerlayout = findViewById(R.id.draw_details_propertie);
        delete_btn = findViewById(R.id.delete_propertie_btn);
        update_btn = findViewById(R.id.update_propertie_btn);
        adresse = findViewById(R.id.adresse_details_propertie);
        surface = findViewById(R.id.surface_details_propertie);
        type = findViewById(R.id.type_details_propertie);
        ajout = findViewById(R.id.ajout_details_propertie);
        modification = findViewById(R.id.modification_details_propertie);
        connectedUser_name = findViewById(R.id.connected_user_details_propertie);

        service = ApiUtils.getProprieteService();

        connectedUser = (Client)getIntent().getSerializableExtra("connectedUser");
        connectedUser_name.setText(connectedUser.getNom_client()+" "+connectedUser.getPrenom_client()+" ");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        propriete = (Propriete) getIntent().getSerializableExtra("propertie");

        if(!isNullOrEmpty(propriete.getAdresse())){
            adresse.setText("Adresse: "+propriete.getAdresse());
        }
        if(propriete.getSurface_prop()>0){
            surface.setText("Surface: "+propriete.getSurface_prop()+" m²");
        }
        if(!isNullOrEmpty(propriete.getType())){
            type.setText("Type: "+propriete.getType());
        }
        if(!isNullOrEmpty(propriete.getDate_ajout())){
            adresse.setText("Ajouté le: "+propriete.getDate_ajout());
        }
        if(!isNullOrEmpty(propriete.getAdresse())){
            adresse.setText("Modifié le: "+propriete.getDate_modification());
        }

        NavigationView navigationView = findViewById(R.id.nav_view_details_propertie);
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
                Intent intent = new Intent(DetailsPropertieActivity.this, NotificationsActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                //startActivity(new Intent(DetailsContratActivity.this, NotificationsActivity.class));
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsPropertieActivity.this, HelpActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                //startActivity(new Intent(DetailsContratActivity.this, HelpActivity.class));
            }
        });

        profile_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsPropertieActivity.this, ProfileActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                //startActivity(new Intent(DetailsContratActivity.this, ProfileActivity.class));
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
                Call<Void> deleteRequest = service.deletePropriete(propriete.getId_prop());
                deleteRequest.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(DetailsPropertieActivity.this, "Opération effectué avec succés!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DetailsPropertieActivity.this, PropertiesActivity.class);
                        intent.putExtra("connectedUser", connectedUser);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(DetailsPropertieActivity.this, "Une erreur s'est produite lors de suppression!", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsPropertieActivity.this, NewPropertie.class);
                intent.putExtra("connectedUser", connectedUser);
                intent.putExtra("action", "update");
                intent.putExtra("propriete", propriete);
                startActivity(intent);
            }
        });
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.client_admin:
                Intent intent = new Intent(DetailsPropertieActivity.this, Clients.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                //startActivity(new Intent(DetailsContratActivity.this, Clients.class));
                break;
            case R.id.properties_admin:
                Intent intent1 = new Intent(DetailsPropertieActivity.this, PropertiesActivity.class);
                intent1.putExtra("connectedUser", connectedUser);
                startActivity(intent1);
                //startActivity(new Intent(DetailsContratActivity.this, PropertiesActivity.class));
                break;
            case R.id.offres_admin:
                Intent intent2 = new Intent(DetailsPropertieActivity.this, OffresActivity.class);
                intent2.putExtra("connectedUser", connectedUser);
                startActivity(intent2);
                // startActivity(new Intent(DetailsContratActivity.this, OffresActivity.class));
                break;
            case R.id.payements_admin:
                Intent intent3 = new Intent(DetailsPropertieActivity.this, Payements.class);
                intent3.putExtra("connectedUser", connectedUser);
                startActivity(intent3);
                //startActivity(new Intent(DetailsContratActivity.this, Payements.class));
                break;
            case R.id.contrats_admin:
                Intent intent4 = new Intent(DetailsPropertieActivity.this, Contrats.class);
                intent4.putExtra("connectedUser", connectedUser);
                startActivity(intent4);
                //startActivity(new Intent(DetailsPropertieActivity.this, Contrats.class));
                break;
            case R.id.configs_admin:
                Intent intent5 = new Intent(DetailsPropertieActivity.this, Configurations.class);
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
        NavigationView navigationView = findViewById(R.id.nav_view_details_propertie);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }

}
