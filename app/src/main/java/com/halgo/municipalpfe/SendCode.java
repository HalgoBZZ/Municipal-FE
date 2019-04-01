package com.halgo.municipalpfe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class SendCode extends AppCompatActivity {

    private EditText code;
    private Button envoyer;
    private Button retour;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.send_code);

        retour = findViewById(R.id.backtosend);
        envoyer = findViewById(R.id.sendcode);
        code = findViewById(R.id.code);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(SendCode.this, ForgetPassword.class);
                startActivity(intent);
            }
        });

        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(SendCode.this, NewPassword.class);
                intent.putExtra("code", code.getText());
                startActivity(intent);
            }
        });

    }
}
