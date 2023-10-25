package com.example.app09.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.app09.Constantes;
import com.example.app09.R;

public class ModoJogoActivity extends AppCompatActivity {

    private RadioButton radioButtonOne;
    private RadioButton radioButtonTwo;

    private Button buttonIniciar;

    private String modoJogo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_jogo);
        radioButtonOne = findViewById(R.id.id_radiobutton_one);
        radioButtonTwo = findViewById(R.id.id_radiobutton_two);
        buttonIniciar = findViewById(R.id.id_button_iniciar);


        radioButtonOne.setOnClickListener(
                view -> definirModoJogo("singlePlayer")
        );
        radioButtonTwo.setOnClickListener(
                view -> definirModoJogo("multiPlayer")
        );
        buttonIniciar.setOnClickListener(
                view -> iniciarJogo()
        );
    }

    private void iniciarJogo() {
        if (modoJogo.equals("")){
            Toast.makeText(this, "Escolha o modo de jogo", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            Bundle args = new Bundle();
            args.putString(Constantes.KEY_MODO_JOGO, modoJogo );
            intent.putExtras(args);
            startActivity(intent);
        }
    }

    private void definirModoJogo(String modoJogo) {this.modoJogo = modoJogo;}
}