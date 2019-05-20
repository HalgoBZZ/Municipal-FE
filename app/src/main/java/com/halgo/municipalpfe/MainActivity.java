package com.halgo.municipalpfe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.halgo.municipalpfe.api.ApiClientInterface;
import com.halgo.municipalpfe.api.ApiUtils;
import com.halgo.municipalpfe.modals.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView forget_pwd;
    private TextView inscription;
    private EditText email_field;
    private EditText pwd_field;
    private Button connexion;
    private TextView warning;
    private String role;
    ApiClientInterface service;


    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // check Fields For Empty Values
            checkFieldsForEmptyValues();
        }
    };

    void checkFieldsForEmptyValues(){
        Button b = findViewById(R.id.connexion);

        String s1 = email_field.getText().toString();
        String s2 = pwd_field.getText().toString();

        if(s1.equals("")|| s2.equals("")){
            b.setEnabled(false);
        } else {
            b.setEnabled(true);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.authentication);

        forget_pwd = findViewById(R.id.mdp_oublie);
        inscription = findViewById(R.id.inscription);
        email_field = findViewById(R.id.email_field);
        pwd_field = findViewById(R.id.password_field);
        connexion = findViewById(R.id.connexion);
        warning = findViewById(R.id.warn);
        service = ApiUtils.getUserService();



        email_field.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (email_field.getText().toString().isEmpty()) {
                    warning.setText("*Champ obligatoire!!");
                } else {
                    warning.setText("");
                }
            }
        });


        email_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (email_field.getText().toString().isEmpty()) {
                    warning.setText("*Champ obligatoire!!");
                } else {
                    warning.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pwd_field.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (pwd_field.getText().toString().isEmpty()) {
                    warning.setText("*Mot de passe obligatoire!!");
                } else {
                    warning.setText("");
                }
            }
        });

        pwd_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (pwd_field.getText().toString().isEmpty()) {
                    warning.setText("*Champ obligatoire!!");
                } else {
                    warning.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // set listeners
        email_field.addTextChangedListener(mTextWatcher);
        pwd_field.addTextChangedListener(mTextWatcher);

        // run once to disable if empty
        checkFieldsForEmptyValues();


        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin(email_field.getText().toString(), pwd_field.getText().toString());

            }
        });

        inscription.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, InscriptionActivity.class);
                startActivity(intent);
            }
        });

        forget_pwd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, ForgetPassword.class);
                startActivity(intent);
            }
        });
    }

    private void doLogin(final String email,final String pwd){
        Call<Client> call = service.authentication(email,pwd);
        call.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (response.isSuccessful()) {
                    Client client = response.body();
                    if (client != null) {
                        final Intent intent = new Intent(MainActivity.this, OffresActivity.class);
                        final Intent intent2 = new Intent(MainActivity.this, OffreClientActivity.class);
                        intent.putExtra("connectedUser", client);
                        intent2.putExtra("connectedUser", client);
                        role = client.getRole();
                        if(role=="Client"){
                            startActivity(intent2);
                        } else {
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Mot de passe ou email incorrect", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Erreur! Essayez de nouveau!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }





}
