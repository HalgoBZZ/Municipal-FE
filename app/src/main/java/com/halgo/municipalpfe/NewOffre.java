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
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.halgo.municipalpfe.adapters.OffreAdapter;
import com.halgo.municipalpfe.api.ApiOffre;
import com.halgo.municipalpfe.api.ApiUtils;
import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Offre;
import com.halgo.municipalpfe.modals.Propriete;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewOffre extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

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
    private EditText titre_field;
    private EditText description_field;
    private EditText prix_field;
    private String titre;
    private double prix;
    private String description;

    private Client connectedUser ;
    private TextView connectedUser_name;
    private ApiOffre service;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_offre);
        setNavigationViewListner();

        mDrawerlayout = findViewById(R.id.draw_new_offre);
        menu_icon = findViewById(R.id.menu_new_offre);
        notification_icon = findViewById(R.id.notification_new_offre);
        help_icon = findViewById(R.id.help_new_offre);
        logout_icon = findViewById(R.id.logout_new_offre);
        profile_icon = findViewById(R.id.user_new_offre);
        connectedUser_name = findViewById(R.id.connected_user_new_offre);
        ajouter = findViewById(R.id.btn_new_offre);
        titre_field = findViewById(R.id.titre_new_offre);
        description_field = findViewById(R.id.description_new_offre);
        prix_field = findViewById(R.id.prix_new_offre);

        connectedUser = (Client)getIntent().getSerializableExtra("connectedUser");
        connectedUser_name.setText(connectedUser.getNom_client()+" "+connectedUser.getPrenom_client()+" ");
        service = ApiUtils.getOffreService();

        notification_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewOffre.this, NotificationsActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewOffre.this, HelpActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);

            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(NewOffre.this, MainActivity.class));
            }
        });

        profile_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewOffre.this, ProfileActivity.class);
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

        service = ApiUtils.getOffreService();
        String action = getIntent().getExtras().getString("action");
        if (action.equals("create")) {
            ajouter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    titre = titre_field.getText().toString().trim();
                    description = description_field.getText().toString().trim();
                    String prix_text = prix_field.getText().toString().trim();

                    if (titre.isEmpty()) {
                        titre_field.setError("Champ obligatoire!");
                        titre_field.requestFocus();
                    } else if (description.isEmpty()) {
                        description_field.setError("Champ obligatoire!");
                        description_field.requestFocus();
                    } else if (prix_text.isEmpty()) {
                        prix_field.getText().toString().trim();
                        prix_field.requestFocus();
                    } else {
                        prix = Double.parseDouble(prix_text);
                        Offre offre = new Offre();
                        offre.setTitre_offre(titre);
                        offre.setDescription_offre(description);
                        offre.setPrix_offre(prix);
                        saveOffre(offre);
                    }

                }
            });
        } else {
            ajouter.setText("Modifer");
            final Offre offre1 = (Offre) getIntent().getSerializableExtra("offre");
            titre_field.setText(offre1.getTitre_offre());
            description_field.setText(offre1.getDescription_offre());
            prix_field.setText(offre1.getPrix_offre()+"");
            ajouter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    titre = titre_field.getText().toString().trim();
                    description = description_field.getText().toString().trim();
                    String prix_text = prix_field.getText().toString().trim();

                    if (titre.isEmpty()) {
                        titre_field.setError("Champ obligatoire!");
                        titre_field.requestFocus();
                    } else if (description.isEmpty()) {
                        description_field.setError("Champ obligatoire!");
                        description_field.requestFocus();
                    } else if (prix_text.isEmpty()) {
                        prix_field.getText().toString().trim();
                        prix_field.requestFocus();
                    } else {
                        prix = Double.parseDouble(prix_text);
                        Offre offre = new Offre();
                        offre.setTitre_offre(titre);
                        offre.setDescription_offre(description);
                        offre.setPrix_offre(prix);
                        offre.setId_offre(offre1.getId_offre());
                        updateOffre(offre);
                    }

                }
            });
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.client_admin :
                Intent intent = new Intent(NewOffre.this, Clients.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                break;
            case R.id.properties_admin:
                Intent intent1 = new Intent(NewOffre.this, PropertiesActivity.class);
                intent1.putExtra("connectedUser", connectedUser);
                startActivity(intent1);
                break;
            case R.id.offres_admin:
                Intent intent2 = new Intent(NewOffre.this, OffresActivity.class);
                intent2.putExtra("connectedUser", connectedUser);
                startActivity(intent2);
                break;
            case R.id.payements_admin:
                Intent intent3 = new Intent(NewOffre.this, Payements.class);
                intent3.putExtra("connectedUser", connectedUser);
                startActivity(intent3);
                break;
            case R.id.contrats_admin:
                Intent intent4 = new Intent(NewOffre.this, Contrats.class);
                intent4.putExtra("connectedUser", connectedUser);
                startActivity(intent4);
                break;
            case R.id.configs_admin:
                Intent intent5 = new Intent(NewOffre.this, Configurations.class);
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
        NavigationView navigationView = findViewById(R.id.nav_view_new_offre);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void saveOffre(Offre offre){
        Call<ResponseBody> call= service.saveOffre(offre);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(NewOffre.this, "Offre ajouté avec succés", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewOffre.this, OffresActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(NewOffre.this, "Une erreur s'est produite lors d'envoi des données", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateOffre(Offre offre){
        Call<ResponseBody> call= service.updateOffre(offre);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(NewOffre.this, "Offre modifié avec succés", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewOffre.this, OffresActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(NewOffre.this, "Une erreur s'est produite lors d'envoi des données", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
