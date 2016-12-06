package com.example.joaod.jsonormlite;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.sql.SQLException;

import cz.msebera.android.httpclient.Header;

public class CotacoesActivity extends Activity {
    ListView listViewCotacao;
    TextView tvEuro;
    TextView tvDolar;
    TextView tvBitcoin;
    Dao<Moeda, Integer> moedaDao = null;
    ArrayAdapter<Moeda> adapterMoeda;
    Cotacao cotacao;
    Valores valores;
    Moeda moeda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotacoes);
        ListView listViewCotacao = (ListView) findViewById(R.id.listViewCotacao);
        cotacao = new Cotacao();
        moeda = new Moeda();
        tvEuro = (TextView) findViewById(R.id.tvEuro);
        tvDolar = (TextView) findViewById(R.id.tvDolar);
        tvBitcoin = (TextView) findViewById(R.id.tvBitcoin);
        listViewCotacao = (ListView) findViewById(R.id.listViewCotacao);
        try {
            moedaDao = MyORMLiteHelper.getmInstance(this).getMoedaDao();
            adapterMoeda = new ArrayAdapter<Moeda>(this, android.R.layout.simple_list_item_1, moedaDao.queryForAll());
            listViewCotacao.setAdapter(adapterMoeda);
            listViewCotacao.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    moedaDao = (Dao<Moeda, Integer>) adapterMoeda.getItem(i);
                    android.support.v7.app.AlertDialog.Builder alerta = new android.support.v7.app.AlertDialog.Builder(CotacoesActivity.this);
                    alerta.setTitle("Você tem certeza que deseja Excluir");
                    alerta.setMessage("Certeza mesmo ? " + valores.getBTC().getNome());
                    alerta.setNeutralButton("Não", null);
                    alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try {
                                moedaDao.delete(moeda);
                                adapterMoeda.remove(moeda);
                                moeda = new Moeda();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    alerta.show();
                    return true;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void atualizarCotacao(View v) throws SQLException{
        moeda.setNome(tvDolar.getText().toString());
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("user-agent", "Mozilla Chorme");
        client.get("http://api.promasters.net.br/cotacao/v1/valores", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String s = new String(bytes);
                Gson gson = new Gson();
                Cotacao e = gson.fromJson(s, Cotacao.class);
                String cotacaoJson = gson.toJson(e);
                String resposta = new String(bytes);
                String vEuro = Double.toString(e.getValores().getEUR().getValor());
                String vDolar = Double.toString(e.getValores().getUSD().getValor());
                String vBit = Double.toString(e.getValores().getBTC().getValor());
                moeda.setNome(tvDolar.getText().toString());
                tvEuro.setText(vEuro);
                tvDolar.setText(vDolar);
                tvBitcoin.setText(vBit);
                Toast.makeText(CotacoesActivity.this, "Atualizado Com sucesso", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(CotacoesActivity.this, "Erro Ao fazer Download dos Valores ", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void voltar(View v) {
        Intent it = new Intent(this, InicioActivity.class);
        Toast.makeText(CotacoesActivity.this, "Retornando...", Toast.LENGTH_SHORT).show();
        startActivity(it);
    }


}

