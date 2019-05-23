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
import com.halgo.municipalpfe.api.ApiPropriete;
import com.halgo.municipalpfe.api.ApiUtils;
import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Propriete;
import com.halgo.municipalpfe.modals.Type;

import java.time.LocalDate;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPropertie extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private ImageView profile_icon;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private boolean isOpen;
    private Button ajouter;
    private EditText surface_field;
    private EditText adresse_field;
    private Spinner type_field;
    private String adresse;
    private double surface;
    private String type;

    private Client connectedUser;
    private TextView connectedUser_name;
    private ApiPropriete service;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_proprtie);
        setNavigationViewListner();

        mDrawerlayout = findViewById(R.id.draw_new_propertie);
        menu_icon = findViewById(R.id.menu_new_propriete);
        notification_icon = findViewById(R.id.notification_new_propriete);
        help_icon = findViewById(R.id.help_new_propriete);
        logout_icon = findViewById(R.id.logout_new_propriete);
        profile_icon = findViewById(R.id.user_new_propriete);
        connectedUser_name = findViewById(R.id.connected_user_new_propriete);
        ajouter = findViewById(R.id.btn_new_propriete);
        adresse_field = findViewById(R.id.adresse_new_propriete);
        surface_field = findViewById(R.id.surface_new_propriete);
        type_field = findViewById(R.id.type_new_propriete);

        connectedUser = (Client) getIntent().getSerializableExtra("connectedUser");
        connectedUser_name.setText(connectedUser.getNom_client() + " " + connectedUser.getPrenom_client() + " ");
        service = ApiUtils.getProprieteService();

        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewPropertie.this, NotificationsActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewPropertie.this, HelpActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);

            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(NewPropertie.this, MainActivity.class));
            }
        });

        profile_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewPropertie.this, ProfileActivity.class);
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


        String action = getIntent().getExtras().getString("action");
        if (action.equals("create")) {

            ajouter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    surface = Double.parseDouble(surface_field.getText().toString().trim());
                    adresse = adresse_field.getText().toString().trim();
                    type = String.valueOf(type_field.getSelectedItem());

                    if (surface_field.getText().toString().trim().isEmpty()) {
                        surface_field.setError("Champ obligatoire!");
                        surface_field.requestFocus();
                    } else if (adresse.isEmpty()) {
                        adresse_field.setError("Champ obligatoire!");
                        adresse_field.requestFocus();
                    } else {

                        Propriete propriete = new Propriete();
                        propriete.setAdresse(adresse);
                        propriete.setSurface_prop(surface);
                        if (type.equals("Boutique")) {
                            propriete.setType("Boutique");
                        } else if (type.equals("Jardin publique")) {
                            propriete.setType("Jardin_publique");
                        } else if (type.equals("Magasin industiel")) {
                            propriete.setType("magasin_industriel");
                        } else if (type.equals("Parc")) {
                            propriete.setType("parc");
                        } else {
                            propriete.setType("terrain");
                        }
                        savePropriete(propriete);

                    }

                }
            });
        } else {
            ajouter.setText("Modifer");
            final Propriete propriete1 = (Propriete) getIntent().getSerializableExtra("propriete");
            surface_field.setText(propriete1.getSurface_prop()+"");
            adresse_field.setText(propriete1.getAdresse());

            ajouter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    surface = Double.parseDouble(surface_field.getText().toString().trim());
                    adresse = adresse_field.getText().toString().trim();
                    type = String.valueOf(type_field.getSelectedItem());

                    if (surface_field.getText().toString().trim().isEmpty()) {
                        surface_field.setError("Champ obligatoire!");
                        surface_field.requestFocus();
                    } else if (adresse.isEmpty()) {
                        adresse_field.setError("Champ obligatoire!");
                        adresse_field.requestFocus();
                    } else {

                        Propriete propriete = new Propriete();
                        propriete.setAdresse(adresse);
                        propriete.setSurface_prop(surface);
                        if (type.equals("Boutique")) {
                            propriete.setType("Boutique");
                        } else if (type.equals("Jardin publique")) {
                            propriete.setType("Jardin_publique");
                        } else if (type.equals("Magasin industiel")) {
                            propriete.setType("magasin_industriel");
                        } else if (type.equals("Parc")) {
                            propriete.setType("parc");
                        } else {
                            propriete.setType("terrain");
                        }
                        propriete.setId_prop(propriete1.getId_prop());
                        updatePropriete(propriete);

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
                Intent intent = new Intent(NewPropertie.this, Clients.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                break;
            case R.id.properties_admin:
                Intent intent1 = new Intent(NewPropertie.this, PropertiesActivity.class);
                intent1.putExtra("connectedUser", connectedUser);
                startActivity(intent1);
                break;
            case R.id.offres_admin:
                Intent intent2 = new Intent(NewPropertie.this, OffresActivity.class);
                intent2.putExtra("connectedUser", connectedUser);
                startActivity(intent2);
                break;
            case R.id.payements_admin:
                Intent intent3 = new Intent(NewPropertie.this, Payements.class);
                intent3.putExtra("connectedUser", connectedUser);
                startActivity(intent3);
                break;
            case R.id.contrats_admin:
                Intent intent4 = new Intent(NewPropertie.this, Contrats.class);
                intent4.putExtra("connectedUser", connectedUser);
                startActivity(intent4);
                break;
            case R.id.configs_admin:
                Intent intent5 = new Intent(NewPropertie.this, Configurations.class);
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
        NavigationView navigationView = findViewById(R.id.nav_view_new_propriete);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void savePropriete(Propriete propriete) {
        Call<ResponseBody> call = service.savePropriete(propriete);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(NewPropertie.this, "Proprieté ajouté avec succés", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewPropertie.this, PropertiesActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(NewPropertie.this, "Une erreur s'est produite lors d'envoi des données", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updatePropriete(Propriete propriete) {
        Call<ResponseBody> call = service.updatePropriete(propriete);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(NewPropertie.this, "Proprieté modifié avec succés", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewPropertie.this, PropertiesActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(NewPropertie.this, "Une erreur s'est produite lors d'envoi des données", Toast.LENGTH_SHORT).show();
            }
        });
    }
}