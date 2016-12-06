package com.example.joaod.jsonormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by joaod on 06/11/2016.
 */
@DatabaseTable(tableName = "jsonOrmLiteMoeda")
public class Moeda {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private String nome;
    @DatabaseField
    private double valor;
    @DatabaseField
    private int ultima_consulta;
    @DatabaseField
    private String fonte;

    public Moeda() {
    }

    public Moeda(Integer id, String nome, double valor, int ultima_consulta, String fonte) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.ultima_consulta = ultima_consulta;
        this.fonte = fonte;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getUltima_consulta() {
        return ultima_consulta;
    }

    public void setUltima_consulta(int ultima_consulta) {
        this.ultima_consulta = ultima_consulta;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    @Override
    public String toString() {
        return "Moeda{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", ultima_consulta=" + ultima_consulta +
                ", fonte='" + fonte + '\'' +
                '}';
    }
}


