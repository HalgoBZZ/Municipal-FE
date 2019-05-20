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
import android.widget.TextView;

import com.halgo.municipalpfe.api.ApiOffre;
import com.halgo.municipalpfe.modals.Client;

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


        mDrawerlayout = findViewById(R.id.draw_profil);
        menu_icon = findViewById(R.id.menu_profil);
        notification_icon = findViewById(R.id.notification_profil);
        help_icon = findViewById(R.id.help_profil);
        logout_icon = findViewById(R.id.logout_profil);
        connectedUser_name = findViewById(R.id.connected_user_profil);


        connectedUser = (Client) getIntent().getSerializableExtra("connectedUser");
        connectedUser_name.setText(connectedUser.getNom_client() + " " + connectedUser.getPrenom_client() + " ");

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

}