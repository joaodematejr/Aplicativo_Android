package com.example.joaod.jsonormlite;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.j256.ormlite.dao.Dao;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.sql.SQLException;

import cz.msebera.android.httpclient.Header;

public class EnderecoActivity extends Activity {
    ListView listViewEndereco;
    EditText editCep;
    EditText tvEndereco;
    EditText tvLocalidade;
    Dao<Endereco, Integer> enderecoDao = null;
    ArrayAdapter<Endereco> adapterEndereco;
    Endereco endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        endereco = new Endereco();
        editCep = (EditText) findViewById(R.id.editCep);
        tvEndereco = (EditText) findViewById(R.id.tvEndereco);
        tvLocalidade = (EditText) findViewById(R.id.tvLocalidade);
        listViewEndereco = (ListView) findViewById(R.id.listViewEndereco);
        try {
            enderecoDao = MyORMLiteHelper.getmInstance(this).getEnderecoDao();
            adapterEndereco = new ArrayAdapter<Endereco>(this, android.R.layout.simple_list_item_1, enderecoDao.queryForAll());
            listViewEndereco.setAdapter(adapterEndereco);
            listViewEndereco.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    endereco = adapterEndereco.getItem(i);
                    AlertDialog.Builder alerta = new AlertDialog.Builder(EnderecoActivity.this);
                    alerta.setTitle("Você tem certeza que deseja Excluir");
                    alerta.setMessage("Certeza mesmo ? " + endereco.getLogradouro());
                    alerta.setNeutralButton("Não", null);
                    alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try {
                                enderecoDao.delete(endereco);
                                adapterEndereco.remove(endereco);
                                endereco = new Endereco();
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

    public void pesquisar(View v) {
        final EditText editCep = (EditText) findViewById(R.id.editCep);
        String cep = editCep.getText().toString();
        final TextView tvEndereco = (TextView) findViewById(R.id.tvEndereco);
        final TextView tvLocalidade = (TextView) findViewById(R.id.tvLocalidade);
        final TextView tvUf = (TextView) findViewById(R.id.tvUf);
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://viacep.com.br/ws/" + cep + "/json/";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                Toast.makeText(EnderecoActivity.this, "Fazendo Busca pelo CEP " + editCep.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRetry(int retryNo) {
                super.onRetry(retryNo);
                Toast.makeText(EnderecoActivity.this, "Tentativa" + retryNo, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String resposta = new String(bytes);
                Gson gson = new Gson();
                Endereco endereco = gson.fromJson(resposta, Endereco.class);
                tvEndereco.setText(endereco.getLogradouro());
                tvLocalidade.setText(endereco.getLocalidade());
                tvUf.setText(endereco.getUf());
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(EnderecoActivity.this, "Não foi Localizado Nenhuma Rua com CEP " + editCep.getText(), Toast.LENGTH_SHORT).show();
            }


        });
    }

    public void salvar(View v) throws SQLException {
        endereco.setCep(editCep.getText().toString());
        endereco.setLogradouro(tvEndereco.getText().toString());
        endereco.setLocalidade(tvLocalidade.getText().toString());
        Dao.CreateOrUpdateStatus res = enderecoDao.createOrUpdate(endereco);
        adapterEndereco.add(endereco);
        Toast.makeText(EnderecoActivity.this, "Endereço Cadastrado Com Sucesso", Toast.LENGTH_SHORT).show();
    }

    public void encerrar(View v) {
        Toast.makeText(this, "Encerrando a Aplicação", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void voltar(View v) {
        Toast.makeText(EnderecoActivity.this, "Retornando...", Toast.LENGTH_SHORT).show();
        Intent it = new Intent(this, InicioActivity.class);
        startActivity(it);
    }

}

