package com.halgo.municipalpfe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView forget_pwd;
    private TextView inscription;
    private EditText email;
    private EditText pwd;
    private Button connexion;
    private TextView warning;


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
        Button b = (Button) findViewById(R.id.connexion);

        String s1 = email.getText().toString();
        String s2 = pwd.getText().toString();

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
        setContentView(R.layout.activity_main);

        forget_pwd = findViewById(R.id.mdp_oublie);
        inscription = findViewById(R.id.inscription);
        email = findViewById(R.id.login);
        pwd = findViewById(R.id.password);
        connexion = findViewById(R.id.connexion);
        warning = findViewById(R.id.warn);

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (email.getText().toString().isEmpty()) {
                    warning.setText("*Login obligatoire!!");
                } else {
                    warning.setText("");
                }
            }
        });


        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (email.getText().toString().isEmpty()) {
                    warning.setText("*Login obligatoire!!");
                } else {
                    warning.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        pwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (pwd.getText().toString().isEmpty()) {
                    warning.setText("*Mot de passe obligatoire!!");
                } else {
                    warning.setText("");
                }
            }
        });

        pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (pwd.getText().toString().isEmpty()) {
                    warning.setText("*Mot de passe obligatoire!!");
                } else {
                    warning.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // set listeners
        email.addTextChangedListener(mTextWatcher);
        pwd.addTextChangedListener(mTextWatcher);

        // run once to disable if empty
        checkFieldsForEmptyValues();


        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, OffresActivity.class);
                intent.putExtra("email", email.getText());
                intent.putExtra("password", pwd.getText());
                startActivity(intent);
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





}
