package com.example.app09.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.app09.Constantes;
import com.example.app09.R;
import com.example.app09.model.Confronto;

public class MainActivity extends AppCompatActivity {

    private Spinner mSpinner;
    private Button mButton;
    private EditText nome1EditText;
    private EditText nome2EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome1EditText = findViewById(R.id.edittext_jogador1);
        nome2EditText = findViewById(R.id.edittext_jogador2);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras.getString(Constantes.KEY_MODO_JOGO).equals("singlePlayer")){
            nome2EditText.setVisibility(View.INVISIBLE);
        }


        ActionBar toolbar = getSupportActionBar();
        if (toolbar != null){
            toolbar.hide();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.tipos_jogos)
        );

        mSpinner = findViewById(R.id.spinner_jogadas);
        mSpinner.setAdapter(adapter);


        mButton = findViewById(R.id.button_iniciar);
        mButton.setOnClickListener( view -> iniciarJogo(extras.getString(Constantes.KEY_MODO_JOGO)));
    }

    private void iniciarJogo(String modoJogo) {
        String player1, player2;
        int batalhas = 1;

        if (modoJogo.equals("singlePlayer")){
            player1 = nome1EditText.getText().toString();
            player2 = "Computer One";
        }else {
            player1 = nome1EditText.getText().toString();
            player2 = nome2EditText.getText().toString();
        }


        switch (mSpinner.getSelectedItemPosition()){
            case 0:
                batalhas = 1;
                break;
            case 1:
                batalhas = 3;
                break;
            case 2:
                batalhas = 5;
                break;
        }
        Intent intent = new Intent(this, ConfrontoActivity.class);
        Bundle args = new Bundle();
        args.putString(Constantes.KEY_JOGADOR_1, player1);
        args.putString(Constantes.KEY_JOGADOR_2, player2);
        args.putInt(Constantes.KEY_RODADAS, batalhas);
        args.putString(Constantes.KEY_MODO_JOGO, modoJogo);
        intent.putExtras(args);
        startActivity(intent);
    }
}