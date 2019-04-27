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
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.halgo.municipalpfe.adapters.OffreAdapter;
import com.halgo.municipalpfe.modals.Offre;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class Payements extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private boolean isOpen;
    //private String url ="http://10.0.3.2:8080/tournees/byreleveur";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payements);
        setNavigationViewListner();


        menu_icon = findViewById(R.id.menu_payements);
        notification_icon = findViewById(R.id.notification_payements);
        help_icon = findViewById(R.id.help_payements);
        logout_icon = findViewById(R.id.logout_payements);
        mDrawerlayout = findViewById(R.id.draw_payements);

        PieChartView pieChartView = findViewById(R.id.chart);
        List< SliceValue > pieData = new ArrayList<>();
        pieData.add(new SliceValue(15, Color.BLUE));
        pieData.add(new SliceValue(25, Color.GRAY));
        pieData.add(new SliceValue(10, Color.RED));
        pieData.add(new SliceValue(60, Color.MAGENTA));
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setHasCenterCircle(true).setCenterText1("Sales in million")
                .setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);






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
                startActivity(new Intent(Payements.this, Clients.class));
                break;
            case R.id.properties_admin:
                startActivity(new Intent(Payements.this, PropertiesActivity.class));
                break;
            case R.id.offres_admin:
                startActivity(new Intent(Payements.this, OffresActivity.class));
                break;
            case R.id.payements_admin:
                startActivity(new Intent(Payements.this, Payements.class));
                break;
            case R.id.contrats_admin:
                startActivity(new Intent(Payements.this, Contrats.class));
                break;
            case R.id.configs_admin:
                startActivity(new Intent(Payements.this, Configurations.class));
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
}
