package com.halgo.municipalpfe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.halgo.municipalpfe.api.ApiClientInterface;
import com.halgo.municipalpfe.api.ApiUtils;
import com.halgo.municipalpfe.modals.Client;

import java.net.CacheResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InscriptionActivity extends AppCompatActivity {
    private ApiClientInterface service;

    private EditText nom_client;
    private EditText prenom_client;
    private EditText cin;
    private EditText email;
    private EditText pwd;
    private EditText date_naissance;
    private Button inscrit;
    private Button retour;

    String nom ;
    String prenom;
    int cinn;
    String pass;
    String email_value;
    String naissance;
    String cin_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);

        nom_client = findViewById(R.id.inscrit_nom);
        prenom_client = findViewById(R.id.inscrit_prenom);
        cin = findViewById(R.id.inscrit_cin);
        email = findViewById(R.id.inscrit_email);
        pwd = findViewById(R.id.inscrit_pwd);
        date_naissance = findViewById(R.id.inscrit_date_naiss);
        inscrit = findViewById(R.id.inscrit);
        retour = findViewById(R.id.backtoAoth);




        service = ApiUtils.getUserService();

        inscrit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nom = nom_client.getText().toString().trim();
                prenom = prenom_client.getText().toString().trim();
                cin_value = cin.getText().toString().trim();

                pass = pwd.getText().toString().trim();
                email_value = email.getText().toString().trim();
                naissance = date_naissance.getText().toString().trim();

                if(nom.isEmpty()){
                    nom_client.setError("Champ obligatoire!");
                    nom_client.requestFocus();
                }else
                if(prenom.isEmpty()) {
                    prenom_client.setError("Champ obligatoire!");
                    prenom_client.requestFocus();
                } else

                if(cin_value.isEmpty()){
                    cin.setError("Champ obligatoire!");
                    cin.requestFocus();
                }else
                if(cin_value.length()!=8 ){
                    cin.setError("CIN doint contenir 8 chiffres!");
                    cin.requestFocus();
                }else
                if(email_value.isEmpty()){
                    email.setError("Champ obligatoire!");
                    email.requestFocus();
                }else
                if(!Patterns.EMAIL_ADDRESS.matcher(email_value).matches()){
                    email.setError("Adresse email invalide!");
                    email.requestFocus();
                }else

                if(naissance.isEmpty()){
                    date_naissance.setError("Champ obligatoire!");
                    date_naissance.requestFocus();
                }else

                if(pass.isEmpty()) {
                    pwd.setError("Champ obligatoire!");
                    pwd.requestFocus();
                }if(pass.length()<6){
                    pwd.setError("Mot de passe trés courte!");
                    pwd.requestFocus();
                }else {
                    cinn = Integer.parseInt(cin_value);
                    Client client = new Client();
                    client.setCin(cinn);
                    client.setNom_client(nom);
                    client.setPrenom_client(prenom);
                    client.setEmail(email_value);
                    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    client.setDate_naissance(naissance);
                    client.setPwd(pass);
                    saveClient(client);
                }
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InscriptionActivity.this, MainActivity.class));
            }
        });

    }

    private void saveClient(Client client){
        Call<ResponseBody> call= service.saveClient(client);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(InscriptionActivity.this, "Inscription avec succés", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(InscriptionActivity.this, MainActivity.class));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(InscriptionActivity.this, "Une erreur s'est produite lors d'envoi des données", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
