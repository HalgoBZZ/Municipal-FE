package com.halgo.municipalpfe;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class OffresActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ImageView menu_icon;
    private ImageView notification_icon;
    private ImageView help_icon;
    private ImageView logout_icon;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    private boolean isOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offres);


        menu_icon=findViewById(R.id.menu_tour);
        notification_icon=findViewById(R.id.notification_tour);
        help_icon=findViewById(R.id.help_tour);
        logout_icon=findViewById(R.id.logout_tour);
        mDrawerlayout=findViewById(R.id.draw_tour);
        NavigationView navigationView = findViewById(R.id.nav_view_tour);
        navigationView.setNavigationItemSelectedListener(this);
        mToggle=new ActionBarDrawerToggle(this, mDrawerlayout, R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        notification_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(OffresActivity.this, NotificationsActivity.class));
            }
        });

        help_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(OffresActivity.this, HelpActivity.class));
            }
        });

        logout_icon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(OffresActivity.this, MainActivity.class));
            }
        });



        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isOpen) {
                    mDrawerlayout.openDrawer(Gravity.START);
                    isOpen = true;
                }
                else {
                    mDrawerlayout.closeDrawer(Gravity.START);
                    isOpen = false;
                }
            }
        });
    }




    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        MenuItem tournee=findViewById(R.id.tourne);
        MenuItem trajectoire=findViewById(R.id.trajectoir);
        MenuItem planing =findViewById(R.id.planin);

       /* int id = item.getItemId();
        switch (id){
            case R.id.tournee :
                startActivity(new Intent(TourneesActivity.this, TourneesActivity.class));
                break;
            case R.id.trajectoire:
                startActivity(new Intent(TourneesActivity.this, TrajectoireActivity.class));
                break;
            case R.id.planing:
                startActivity(new Intent(TourneesActivity.this, PlanningActivity.class));
                break;
            default:
                break;
        }*/
       /* if(item==tournee){
            startActivity(new Intent(TourneesActivity.this, TourneesActivity.class));
            this.mDrawerlayout.closeDrawer(GravityCompat.START);
            return true;
        }else if(item==trajectoire){
            startActivity(new Intent(TourneesActivity.this, TrajectoireActivity.class));
            this.mDrawerlayout.closeDrawer(GravityCompat.START);
            return true;
        }else if(item==planing){
            startActivity(new Intent(TourneesActivity.this, PlanningActivity.class));
            this.mDrawerlayout.closeDrawer(GravityCompat.START);
            return true;
        }
        this.mDrawerlayout.closeDrawer(GravityCompat.START);*/

        return true;
    }
}
