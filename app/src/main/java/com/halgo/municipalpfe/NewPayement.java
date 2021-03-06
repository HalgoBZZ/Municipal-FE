package com.halgo.municipalpfe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.halgo.municipalpfe.api.ApiPayement;
import com.halgo.municipalpfe.api.ApiUtils;
import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Payement;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPayement extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private ImageView profile_icon;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private boolean isOpen;
    private Button ajouter;
    private EditText debut_field;
    private EditText fin_field;
    private Spinner etat_field;
    private String debut;
    private String etat;
    private String fin;

    private Client connectedUser;
    private TextView connectedUser_name;
    private ApiPayement service;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_payement);
        setNavigationViewListner();

        mDrawerlayout = findViewById(R.id.draw_new_payement);
        menu_icon = findViewById(R.id.menu_new_payement);
        notification_icon = findViewById(R.id.notification_new_payement);
        help_icon = findViewById(R.id.help_new_payement);
        logout_icon = findViewById(R.id.logout_new_payement);
        profile_icon = findViewById(R.id.user_new_payement);
        connectedUser_name = findViewById(R.id.connected_user_new_payement);
        ajouter = findViewById(R.id.btn_new_payement);
        debut_field = findViewById(R.id.debut_new_payement);
        fin_field = findViewById(R.id.fin_new_payement);
        etat_field = findViewById(R.id.etat_new_payement);


        connectedUser = (Client) getIntent().getSerializableExtra("connectedUser");
        connectedUser_name.setText(connectedUser.getNom_client() + " " + connectedUser.getPrenom_client() + " ");
        service = ApiUtils.getPayementService();

        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewPayement.this, NotificationsActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewPayement.this, HelpActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);

            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(NewPayement.this, MainActivity.class));
            }
        });

        profile_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewPayement.this, ProfileActivity.class);
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


        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                debut = debut_field.getText().toString().trim();
                fin = fin_field.getText().toString().trim();
                etat = String.valueOf(etat_field.getSelectedItem());
                //prix = Double.parseDouble(prix_field.getText().toString().trim());


                if (debut.isEmpty()) {
                    debut_field.setError("Champ obligatoire!");
                    debut_field.requestFocus();
                } else if (fin.isEmpty()) {
                    fin_field.setError("Champ obligatoire!");
                    fin_field.requestFocus();
                } else {
                    Payement payement = new Payement();
                    payement.setDate_debut(debut);
                    payement.setDate_fin(fin);
                    if(etat.equals("Payé")){
                        payement.setEtat("Paye");
                    } else {
                        payement.setEtat("Non_Paye");
                    }

                    savePayement(payement);

                }

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.client_admin:
                Intent intent = new Intent(NewPayement.this, Clients.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                break;
            case R.id.properties_admin:
                Intent intent1 = new Intent(NewPayement.this, PropertiesActivity.class);
                intent1.putExtra("connectedUser", connectedUser);
                startActivity(intent1);
                break;
            case R.id.offres_admin:
                Intent intent2 = new Intent(NewPayement.this, OffresActivity.class);
                intent2.putExtra("connectedUser", connectedUser);
                startActivity(intent2);
                break;
            case R.id.payements_admin:
                Intent intent3 = new Intent(NewPayement.this, Payements.class);
                intent3.putExtra("connectedUser", connectedUser);
                startActivity(intent3);
                break;
            case R.id.contrats_admin:
                Intent intent4 = new Intent(NewPayement.this, Contrats.class);
                intent4.putExtra("connectedUser", connectedUser);
                startActivity(intent4);
                break;
            case R.id.configs_admin:
                Intent intent5 = new Intent(NewPayement.this, Configurations.class);
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
        NavigationView navigationView = findViewById(R.id.nav_view_new_payement);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void savePayement(Payement payement) {
        Call<ResponseBody> call = service.savePayement(payement);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(NewPayement.this, "Contrat ajouté avec succés", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewPayement.this, Payements.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(NewPayement.this, "Une erreur s'est produite lors d'envoi des données", Toast.LENGTH_SHORT).show();
            }
        });
    }
}