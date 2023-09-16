package com.example.preservacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MinhaAPI extends AppCompatActivity {

    EditText edt, texto_json;

    Button search, retornar;

    Preservador preservador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minha_api);

        edt = findViewById(R.id.uri_api);
        texto_json = findViewById(R.id.editTextTextMultiLine);

        search = findViewById(R.id.search_api);
        retornar = findViewById(R.id.api_retorne);

        Bundle preserv = getIntent().getExtras();
        if ((preserv!= null) && (preserv.containsKey("preservador"))) {
            preservador  = (Preservador) preserv.getSerializable("preservador");

        }else {

            Intent intent = new Intent(MinhaAPI.this, MainActivity.class);
            Toast.makeText(MinhaAPI.this,
                    "Conta não autorizada", Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();

        }

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edt.getText().toString().trim().isEmpty()){
                    SearchAPI api = new SearchAPI(texto_json);
                    //https://rickandmortyapi.com/api/episode/1
                    api.execute(edt.getText().toString());

                }else {
                    edt.setError("Url inválida");
                }
            }
        });

        retornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MinhaAPI.this, TelaUsuario.class);
                intent.putExtra("preservador", preservador);
                startActivity(intent);
                finish();
            }
        });


    }
}