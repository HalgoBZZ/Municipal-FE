package com.halgo.municipalpfe;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.halgo.municipalpfe.api.ApiClientInterface;
import com.halgo.municipalpfe.api.ApiOffre;
import com.halgo.municipalpfe.api.ApiUtils;
import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Offre;

import java.time.LocalDate;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewClient extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Spinner etat;
    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private ImageView profile_icon;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private boolean isOpen;
    private Button ajouter;
    private EditText nom_field;
    private EditText prenom_field;
    private EditText cin_field;
    private EditText naissance_field;
    private EditText email_field;
    private EditText pwd_field;
    private String nom;
    private String prenom;
    private int cin = 0;
    private LocalDate naissance;
    private String email;
    private String pwd;
    private String action;

    private Client connectedUser;
    private TextView connectedUser_name;
    private ApiClientInterface service;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_client);
        setNavigationViewListner();

        mDrawerlayout = findViewById(R.id.draw_new_client);
        menu_icon = findViewById(R.id.menu_new_client);
        notification_icon = findViewById(R.id.notification_new_client);
        help_icon = findViewById(R.id.help_new_client);
        logout_icon = findViewById(R.id.logout_new_client);
        profile_icon = findViewById(R.id.user_new_client);
        connectedUser_name = findViewById(R.id.connected_user_new_client);
        ajouter = findViewById(R.id.btn_new_client);
        nom_field = findViewById(R.id.nom_new_client);
        prenom_field = findViewById(R.id.prenom_new_client);
        cin_field = findViewById(R.id.cin_new_client);
        email_field = findViewById(R.id.email_new_client);
        naissance_field = findViewById(R.id.naissance_new_client);
        pwd_field = findViewById(R.id.pwd_new_client);

        connectedUser = (Client) getIntent().getSerializableExtra("connectedUser");
        connectedUser_name.setText(connectedUser.getNom_client() + " " + connectedUser.getPrenom_client() + " ");
        service = ApiUtils.getUserService();

        action = getIntent().getExtras().getString("action");
        Log.i("action: ", action);

        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewClient.this, NotificationsActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewClient.this, HelpActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);

            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(NewClient.this, MainActivity.class));
            }
        });

        profile_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewClient.this, ProfileActivity.class);
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

        if (action.equals("create")) {

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
                        saveClient(client);

                    }

                }
            });

        }else {
            ajouter.setText("Modifier");
            final Client client = (Client) getIntent().getSerializableExtra("client");
            nom_field.setText(client.getNom_client());
            prenom_field.setText(client.getPrenom_client());
            email_field.setText(client.getEmail());
            pwd_field.setText(client.getPwd());
            cin_field.setText(client.getCin()+"");
            naissance_field.setText((client.getDate_naissance()));
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
                        Client client1 = new Client();
                        client1.setNom_client(nom);
                        client1.setPrenom_client(prenom);
                        client1.setCin(cin);
                        client1.setEmail(email);
                        client1.setPwd(pwd);
                        client1.setDate_naissance(naissance_field.getText().toString().trim());
                        client1.setId_compte(client.getId_compte());
                        updateClient(client1);

                    }

                }
            });

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.client_admin:
                Intent intent = new Intent(NewClient.this, Clients.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                break;
            case R.id.properties_admin:
                Intent intent1 = new Intent(NewClient.this, PropertiesActivity.class);
                intent1.putExtra("connectedUser", connectedUser);
                startActivity(intent1);
                break;
            case R.id.offres_admin:
                Intent intent2 = new Intent(NewClient.this, OffresActivity.class);
                intent2.putExtra("connectedUser", connectedUser);
                startActivity(intent2);
                break;
            case R.id.payements_admin:
                Intent intent3 = new Intent(NewClient.this, Payements.class);
                intent3.putExtra("connectedUser", connectedUser);
                startActivity(intent3);
                break;
            case R.id.contrats_admin:
                Intent intent4 = new Intent(NewClient.this, Contrats.class);
                intent4.putExtra("connectedUser", connectedUser);
                startActivity(intent4);
                break;
            case R.id.configs_admin:
                Intent intent5 = new Intent(NewClient.this, Configurations.class);
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
        NavigationView navigationView = findViewById(R.id.nav_view_new_client);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void saveClient(Client client) {
        Call<ResponseBody> call = service.saveClient(client);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(NewClient.this, "Client ajouté avec succés", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewClient.this, Clients.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(NewClient.this, "Une erreur s'est produite lors d'envoi des données", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateClient(Client client) {
        Call<ResponseBody> call = service.updateClient(client);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(NewClient.this, "Client modifié avec succés", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewClient.this, Clients.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(NewClient.this, "Une erreur s'est produite lors d'envoi des données", Toast.LENGTH_SHORT).show();
            }
        });
    }
}