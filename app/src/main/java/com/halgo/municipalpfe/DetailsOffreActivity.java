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

import com.halgo.municipalpfe.api.ApiOffre;
import com.halgo.municipalpfe.api.ApiUtils;
import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Contrat;
import com.halgo.municipalpfe.modals.Offre;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsOffreActivity  extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
        private TextView description;
        private TextView etat;
        private TextView prix;
        private TextView ajout;
        private TextView modification;
        private boolean isOpen;
        private TextView connectedUser_name;
        private Client connectedUser;
        private Offre offre;
        private ApiOffre service;
        //private String url ="http://10.0.3.2:8080/tournees/byreleveur";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.details_offres);
                setNavigationViewListner();


                menu_icon = findViewById(R.id.menu_details_offre);
                notification_icon = findViewById(R.id.notification_details_offre);
                help_icon = findViewById(R.id.help_details_offre);
                logout_icon = findViewById(R.id.logout_details_offre);
                profile_icon =findViewById(R.id.user_details_offre);
                mDrawerlayout = findViewById(R.id.draw_details_offre);
                delete_btn = findViewById(R.id.delete_offre_btn);
                update_btn = findViewById(R.id.update_offre_btn);
                titre = findViewById(R.id.titre_details_offre);
                description = findViewById(R.id.description_details_offre);
                etat = findViewById(R.id.etat_details_offre);
                prix = findViewById(R.id.prix_details_offre);
                ajout = findViewById(R.id.ajout_details_offre);
                modification = findViewById(R.id.modification_details_offre);
                connectedUser_name = findViewById(R.id.connected_user_details_offre);

                service = service = ApiUtils.getOffreService();

                connectedUser = (Client)getIntent().getSerializableExtra("connectedUser");
                connectedUser_name.setText(connectedUser.getNom_client()+" "+connectedUser.getPrenom_client()+" ");

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

                offre = (Offre)getIntent().getSerializableExtra("offre");

                if(!isNullOrEmpty(offre.getTitre_offre())){
                        titre.setText(offre.getTitre_offre());
                }
                if(!isNullOrEmpty(offre.getDescription_offre())){
                        description.setText(offre.getDescription_offre());
                }

                etat.setText(offre.getEtat().toString());
                if(offre.getPrix_offre()>0){
                        prix.setText("Prix: "+offre.getPrix_offre());
                }

                if(!isNullOrEmpty(offre.getDate_ajout())){
                        ajout.setText("Ajouté le: "+offre.getDate_ajout());
                }

                if(!isNullOrEmpty(offre.getDate_modification())){
                        ajout.setText("Modifié le: "+offre.getDate_modification());
                }

                NavigationView navigationView = findViewById(R.id.nav_view_details_offre);
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
                                Intent intent = new Intent(DetailsOffreActivity.this, NotificationsActivity.class);
                                intent.putExtra("connectedUser", connectedUser);
                                startActivity(intent);
                                //startActivity(new Intent(DetailsOffreActivity.this, NotificationsActivity.class));
                        }
                });

                help_icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Intent intent = new Intent(DetailsOffreActivity.this, HelpActivity.class);
                                intent.putExtra("connectedUser", connectedUser);
                                startActivity(intent);
                               // startActivity(new Intent(DetailsOffreActivity.this, HelpActivity.class));
                        }
                });

                profile_icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Intent intent = new Intent(DetailsOffreActivity.this, ProfileActivity.class);
                                intent.putExtra("connectedUser", connectedUser);
                                startActivity(intent);
                                //startActivity(new Intent(DetailsOffreActivity.this, ProfileActivity.class));
                        }
                });

                logout_icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                startActivity(new Intent(DetailsOffreActivity.this, MainActivity.class));
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
                                Log.i("id: ", offre.getId_offre()+"");
                                Call<Void> deleteRequest = service.deleteOffre(offre.getId_offre());
                                deleteRequest.enqueue(new Callback<Void>() {
                                        @Override
                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                                Toast.makeText(DetailsOffreActivity.this, "Opération effectué avec succés!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(DetailsOffreActivity.this, OffresActivity.class);
                                                intent.putExtra("connectedUser", connectedUser);
                                                startActivity(intent);
                                        }

                                        @Override
                                        public void onFailure(Call<Void> call, Throwable t) {
                                                Toast.makeText(DetailsOffreActivity.this, "Une erreur s'est produite lors de suppression!", Toast.LENGTH_SHORT).show();

                                        }
                                });



                        }
                });
                update_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Intent intent = new Intent(DetailsOffreActivity.this, NewOffre.class);
                                intent.putExtra("connectedUser", connectedUser);
                                intent.putExtra("action", "update");
                                intent.putExtra("offre", offre);
                                startActivity(intent);
                        }
                });
        }


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (item.getItemId()) {
                        case R.id.client_admin:
                                Intent intent = new Intent(DetailsOffreActivity.this, Clients.class);
                                intent.putExtra("connectedUser", connectedUser);
                                startActivity(intent);
                                //startActivity(new Intent(DetailsOffreActivity.this, Clients.class));
                                break;
                        case R.id.properties_admin:
                                Intent intent1 = new Intent(DetailsOffreActivity.this, PropertiesActivity.class);
                                intent1.putExtra("connectedUser", connectedUser);
                                startActivity(intent1);
                               // startActivity(new Intent(DetailsOffreActivity.this, PropertiesActivity.class));
                                break;
                        case R.id.offres_admin:
                                Intent intent2 = new Intent(DetailsOffreActivity.this, OffresActivity.class);
                                intent2.putExtra("connectedUser", connectedUser);
                                startActivity(intent2);
                                //startActivity(new Intent(DetailsOffreActivity.this, OffresActivity.class));
                                break;
                        case R.id.payements_admin:
                                Intent intent3 = new Intent(DetailsOffreActivity.this, Payements.class);
                                intent3.putExtra("connectedUser", connectedUser);
                                startActivity(intent3);
                                //startActivity(new Intent(DetailsOffreActivity.this, Payements.class));
                                break;
                        case R.id.contrats_admin:
                                Intent intent4 = new Intent(DetailsOffreActivity.this, Contrats.class);
                                intent4.putExtra("connectedUser", connectedUser);
                                startActivity(intent4);
                                //(new Intent(DetailsOffreActivity.this, Contrats.class));
                                break;
                        case R.id.configs_admin:
                                Intent intent5 = new Intent(DetailsOffreActivity.this, Configurations.class);
                                intent5.putExtra("connectedUser", connectedUser);
                                startActivity(intent5);
                                //startActivity(new Intent(DetailsOffreActivity.this, Configurations.class));
                                break;
                        default:
                                break;
                }
                mDrawerlayout.closeDrawer(GravityCompat.START);
                return true;
        }

        private void setNavigationViewListner() {
                NavigationView navigationView = findViewById(R.id.nav_view_details_offre);
                navigationView.setNavigationItemSelectedListener(this);
        }

        public static boolean isNullOrEmpty(String str) {
                if(str != null && !str.isEmpty())
                        return false;
                return true;
        }
}

