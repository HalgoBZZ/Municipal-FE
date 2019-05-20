package com.halgo.municipalpfe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.halgo.municipalpfe.adapters.OffreAdapter;
import com.halgo.municipalpfe.api.ApiClientInterface;
import com.halgo.municipalpfe.api.ApiPayement;
import com.halgo.municipalpfe.api.ApiUtils;
import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Offre;
import com.halgo.municipalpfe.modals.Payement;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Payements extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private ImageView profile_icon;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private int payed = 0;
    private boolean isOpen;
    List<Payement> payements = new ArrayList<>();

    private Client connectedUser ;
    private TextView connectedUser_name;
    private ApiPayement service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payements);
        setNavigationViewListner();


        menu_icon = findViewById(R.id.menu_payements);
        notification_icon = findViewById(R.id.notification_payements);
        help_icon = findViewById(R.id.help_payements);
        logout_icon = findViewById(R.id.logout_payements);
        profile_icon = findViewById(R.id.user_payements);
        mDrawerlayout = findViewById(R.id.draw_payements);

        connectedUser_name = findViewById(R.id.connected_user_payements);
        service = ApiUtils.getPayementService();
        connectedUser = (Client)getIntent().getSerializableExtra("connectedUser");
        connectedUser_name.setText(connectedUser.getNom_client()+" "+connectedUser.getPrenom_client()+" ");



        Call<List<Payement>> call = service.getAllPayements();
        call.enqueue(new Callback<List<Payement>>() {
            @Override
            public void onResponse(Call<List<Payement>> call, Response<List<Payement>> response) {
                if (response.isSuccessful()) {
                    payements = response.body();
        if(payements.size()>1) {
            for (int i = 0; i < payements.size(); i++) {
                if(payements.get(i).getEtat().equals("Paye")){
                    payed++;
                }
            }
        }
                    Log.d("resultat:", payed+"");

        PieChartView pieChartView = findViewById(R.id.chart);
        List< SliceValue > pieData = new ArrayList<>();
        if(payed >0){
            pieData.add(new SliceValue((payed*100)/payements.size(), Color.GREEN));
            pieData.add(new SliceValue(100 - ((payed*100)/payements.size()), Color.GRAY));
        } else {
            pieData.add(new SliceValue(payed, Color.GREEN));
            pieData.add(new SliceValue(100 - payed, Color.GRAY));
        }
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setHasCenterCircle(true).setCenterText1("% Payé")
                .setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#008000"));
        pieChartView.setPieChartData(pieChartData);

                } else {
                    Toast.makeText(Payements.this, "Une erreur s'est produite lors de chargement des données!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(Payements.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




        NavigationView navigationView = findViewById(R.id.nav_view_payements);
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
                startActivity(new Intent(Payements.this, NotificationsActivity.class));
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Payements.this, HelpActivity.class));
            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Payements.this, MainActivity.class));
            }
        });

        profile_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Payements.this, ProfileActivity.class));
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
        switch (item.getItemId()){
            case R.id.client_admin :
                Intent intent = new Intent(Payements.this, Clients.class);
                intent.putExtra("connectedUser", connectedUser);
                startActivity(intent);
                break;
            case R.id.properties_admin:
                Intent intent1 = new Intent(Payements.this, PropertiesActivity.class);
                intent1.putExtra("connectedUser", connectedUser);
                startActivity(intent1);
                break;
            case R.id.offres_admin:
                Intent intent2 = new Intent(Payements.this, OffresActivity.class);
                intent2.putExtra("connectedUser", connectedUser);
                startActivity(intent2);
                break;
            case R.id.payements_admin:
                Intent intent3 = new Intent(Payements.this, Payements.class);
                intent3.putExtra("connectedUser", connectedUser);
                startActivity(intent3);
                break;
            case R.id.contrats_admin:
                Intent intent4 = new Intent(Payements.this, Contrats.class);
                intent4.putExtra("connectedUser", connectedUser);
                startActivity(intent4);
                break;
            case R.id.configs_admin:
                Intent intent5 = new Intent(Payements.this, Configurations.class);
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
        NavigationView navigationView = findViewById(R.id.nav_view_payements);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void getAllPayement() {
        Call<List<Payement>> call = service.getAllPayements();
        call.enqueue(new Callback<List<Payement>>() {
            @Override
            public void onResponse(Call<List<Payement>> call, Response<List<Payement>> response) {
                if (response.isSuccessful()) {
                    payements = response.body();
                    //Log.d("resultat:", client.getId().toString());

                } else {
                    Toast.makeText(Payements.this, "Une erreur s'est produite lors de chargement des données!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(Payements.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
