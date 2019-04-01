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

public class NewPassword extends AppCompatActivity {

    private Button retour;
    private Button envoyer;
    private EditText pwd;
    private EditText confirmation;
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
        Button b = (Button) findViewById(R.id.change);

        String s1 = confirmation.getText().toString();
        String s2 = pwd.getText().toString();

        if(!(s1.equals(s2))){
            b.setEnabled(false);
        } else {
            b.setEnabled(true);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.new_password);

        retour = findViewById(R.id.backtocode);
        envoyer = findViewById(R.id.change);
        pwd = findViewById(R.id.new_pwd);
        confirmation = findViewById(R.id.pwd_conf);
        warning = findViewById(R.id.pwd_warn);

        pwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if ((pwd.getText().toString().isEmpty()) || (pwd.getText().toString().length()<5) ) {
                    warning.setText("Mot de passe trés courte!!");
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
                    warning.setText("Mot de passe trés courte!!");
                } else {
                    warning.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        confirmation.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (confirmation.getText().toString().isEmpty() || !(confirmation.getText().toString().equals(pwd.getText().toString()))) {
                    warning.setText("La confirmation ne correspond pas!!");
                } else {
                    warning.setText("");
                }
            }
        });

        confirmation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (confirmation.getText().toString().isEmpty() || !(confirmation.getText().toString().equals(pwd.getText().toString()))) {
                    warning.setText("La confirmation ne correspond pas!!");
                } else {
                    warning.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // set listeners
        confirmation.addTextChangedListener(mTextWatcher);
        pwd.addTextChangedListener(mTextWatcher);

        // run once to disable if empty
        checkFieldsForEmptyValues();

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(NewPassword.this, SendCode.class);
                startActivity(intent);
            }
        });
        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(NewPassword.this, SendCode.class);
                startActivity(intent);
            }
        });

    }
}
