package com.example.joaod.jsonormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by joaod on 03/11/2016.
 */
@DatabaseTable(tableName = "jsonOrmLite")
public class Endereco {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private String cep;
    @DatabaseField
    private String logradouro;
    @DatabaseField
    private String localidade;
    @DatabaseField
    private String uf;

    public Endereco() {
    }

    public Endereco(Integer id, String cep, String logradouro, String localidade, String uf) {
        this.id = id;
        this.cep = cep;
        this.logradouro = logradouro;
        this.localidade = localidade;
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "Cep: " + cep + "\n" +
                "Rua: " + logradouro + "\n" +
                "Cidade: " + localidade
                ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
