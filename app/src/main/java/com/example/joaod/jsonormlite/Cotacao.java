package com.example.joaod.jsonormlite;

/**
 * Created by Aluno_2 on 07/11/2016.
 */
public class Cotacao {
    public boolean status;
    public Valores valores;

    public Cotacao() {
    }

    public Cotacao(boolean status, Valores valores) {
        this.status = status;
        this.valores = valores;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Valores getValores() {
        return valores;
    }

    public void setValores(Valores valores) {
        this.valores = valores;
    }


}
