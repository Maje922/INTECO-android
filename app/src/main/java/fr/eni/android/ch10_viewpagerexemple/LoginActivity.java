package fr.eni.android.ch10_viewpagerexemple;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eljoprifti.EljoPriftiEditText;

public class LoginActivity extends AppCompatActivity {

    private TextView tvError;
    private CardView cardView;
    private EditText etPass;

    private EstadoLogin conf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        conf = new EstadoLogin(this);


        logueado();

        //recuepramos los elementos del view
        etPass = findViewById(R.id.etPass);
        tvError = findViewById(R.id.tvError);
        cardView = findViewById(R.id.cardView);

        //comprobamos si la constraseña es correcta
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String encript = Hash.md5(etPass.getText().toString());


                if (encript.equals(getResources().getString(R.string.hash))) {
                    conf.setLog(true);
                    logueado();
                } else {
                    conf.setLog(false);
                    tvError.setText("Password incorrecto");
                }
            }
        });

    }

    private void logueado(){
        if(conf.getLog() != false) {
            Intent intent = new Intent(LoginActivity.this, ViewPagerActivity.class);
            startActivity(intent);
        }
    }
}
