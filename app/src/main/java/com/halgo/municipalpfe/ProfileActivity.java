package com.halgo.municipalpfe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.halgo.municipalpfe.api.ApiClientInterface;
import com.halgo.municipalpfe.api.ApiOffre;
import com.halgo.municipalpfe.api.ApiUtils;
import com.halgo.municipalpfe.modals.Client;

import java.time.LocalDate;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private boolean isOpen;
    private EditText nom_field;
    private EditText prenom_field;
    private EditText naissance_field;
    private EditText cin_field;
    private EditText email_field;
    private EditText pwd_field;
    private ApiClientInterface service;
    private Button ajouter;
    private String nom;
    private String prenom;
    private int cin = 0;
    private LocalDate naissance;
    private String email;
    private String pwd;
    private String action;


    private Client connectedUser;
    private TextView connectedUser_name;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        setNavigationViewListner();

        nom_field = findViewById(R.id.nom_profil);
        prenom_field = findViewById(R.id.prenom_profil);
        cin_field = findViewById(R.id.cin_profil);
        email_field = findViewById(R.id.email_profil);
        naissance_field = findViewById(R.id.date_naiss_profil);
        pwd_field = findViewById(R.id.pwd_profil);
        ajouter = findViewById(R.id.btn_profil);


        mDrawerlayout = findViewById(R.id.draw_profil);
        menu_icon = findViewById(R.id.menu_profil);
        notification_icon = findViewById(R.id.notification_profil);
        help_icon = findViewById(R.id.help_profil);
        logout_icon = findViewById(R.id.logout_profil);
        connectedUser_name = findViewById(R.id.connected_user_profil);


        connectedUser = (Client) getIntent().getSerializableExtra("connectedUser");
        connectedUser_name.setText(connectedUser.getNom_client() + " " + connectedUser.getPrenom_client() + " ");
        service = ApiUtils.getUserService();

        nom_field.setText(connectedUser.getNom_client());
        prenom_field.setText(connectedUser.getPrenom_client());
        if(connectedUser.getCin()>0) {
            cin_field.setText(connectedUser.getCin());
        }
        if(connectedUser.getEmail()!=null) {
            email_field.setText(connectedUser.getEmail());
        }
        if(connectedUser.getDate_naissance()!=null) {
            naissance_field.setText(connectedUser.getDate_naissance().toString());
        }
        pwd_field.setText(connectedUser.getPwd());
        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, NotificationsActivity.class));
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, HelpActivity.class));
            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
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
                nom = nom_field.getText().toString().trim();
                prenom = prenom_field.getText().toString().trim();
                email = email_field.getText().toString().trim();
                pwd = pwd_field.getText().toString().trim();

                if (nom.isEmpty()) {
                    nom_field.setError("Champ obligatoire!");
                    nom_field.requestFocus();
                } else if (prenom.isEmpty()) {
                    prenom_field.setError("Champ obligatoire!");
                    prenom_field.requestFocus();
                } else if (cin < 0 || (cin_field.getText().toString().trim()).length() != 8) {
                    cin_field.setError("Format CIN incorrecte");
                    cin_field.requestFocus();
                } else if (email.isEmpty()) {
                    email_field.setError("Champ obligatoire!");
                    email_field.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    email_field.setError("Adresse email invalide!");
                    email_field.requestFocus();
                } else if (naissance_field.getText().toString().trim().isEmpty()) {
                    naissance_field.setError("Champ Obligatoire!");
                    naissance_field.requestFocus();
                } else if (pwd.isEmpty()) {
                    pwd_field.setError("Champ obligatoire!");
                    pwd_field.requestFocus();
                } else if (pwd.length() < 6) {
                    pwd_field.setError("Mot de passe trés courte!");
                    pwd_field.requestFocus();
                } else {
                    cin = Integer.parseInt(cin_field.getText().toString().trim());
                    naissance = LocalDate.parse(naissance_field.getText().toString().trim());
                    Client client = new Client();
                    client.setNom_client(nom);
                    client.setPrenom_client(prenom);
                    client.setCin(cin);
                    client.setEmail(email);
                    client.setPwd(pwd);
                    client.setDate_naissance(naissance_field.getText().toString().trim());
                    client.setId_compte(connectedUser.getId_compte());
                    updateClient(client);

                }

            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.client_admin:
                Intent intent = new Intent(ProfileActivity.this, Clients.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                break;
            case R.id.properties_admin:
                Intent intent1 = new Intent(ProfileActivity.this, PropertiesActivity.class);
                intent1.putExtra("connectedUser", connectedUser);
                startActivity(intent1);
                break;
            case R.id.offres_admin:
                Intent intent2 = new Intent(ProfileActivity.this, OffresActivity.class);
                intent2.putExtra("connectedUser", connectedUser);
                startActivity(intent2);
                break;
            case R.id.payements_admin:
                Intent intent3 = new Intent(ProfileActivity.this, Payements.class);
                intent3.putExtra("connectedUser", connectedUser);
                startActivity(intent3);
                break;
            case R.id.contrats_admin:
                Intent intent4 = new Intent(ProfileActivity.this, Contrats.class);
                intent4.putExtra("connectedUser", connectedUser);
                startActivity(intent4);
                break;
            case R.id.configs_admin:
                Intent intent5 = new Intent(ProfileActivity.this, Configurations.class);
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
        NavigationView navigationView = findViewById(R.id.nav_view_profil);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void updateClient(Client client) {
        Call<ResponseBody> call = service.updateClient(client);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(ProfileActivity.this, "Votre profil est mis à jour avec succés", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Une erreur s'est produite lors d'envoi des données", Toast.LENGTH_SHORT).show();
            }
        });
    }

}