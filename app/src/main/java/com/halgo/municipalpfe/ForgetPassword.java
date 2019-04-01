package com.halgo.municipalpfe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgetPassword extends AppCompatActivity {

    private EditText email;
    private Button retour;
    private Button envoyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        retour = findViewById(R.id.backtologin);
        envoyer = findViewById(R.id.send);
        email = findViewById(R.id.emailsender);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(ForgetPassword.this, MainActivity.class);
                intent.putExtra("email", email.getText());
                startActivity(intent);
            }
        });

        envoyer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(ForgetPassword.this, SendCode.class);
                startActivity(intent);
            }
        });


    }
}
