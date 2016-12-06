package com.example.joaod.jsonormlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class InicioActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }

    public void encerrar(View v) {
        Toast.makeText(this, "Encerrando a Aplicação", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void abrirEndereco(View v) {
        Intent it = new Intent(this, EnderecoActivity.class);
        Toast.makeText(InicioActivity.this, "Iniciando App Endereço", Toast.LENGTH_SHORT).show();
        startActivity(it);

    }

    public void abrirCotacoes(View v) {
        Intent it = new Intent(this, CotacoesActivity.class);
        Toast.makeText(InicioActivity.this, "Iniciando App Cotações", Toast.LENGTH_SHORT).show();
        startActivity(it);
    }


}
