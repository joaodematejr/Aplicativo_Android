package com.example.joaod.jsonormlite;

/**
 * Created by Aluno_2 on 07/11/2016.
 */
public class Valores {
    Moeda USD;
    Moeda EUR;
    Moeda BTC;

    public Valores() {
    }

    public Valores(Moeda USD, Moeda EUR, Moeda BTC) {
        this.USD = USD;
        this.EUR = EUR;
        this.BTC = BTC;
    }

    public Moeda getUSD() {
        return USD;
    }

    public void setUSD(Moeda USD) {
        this.USD = USD;
    }

    public Moeda getEUR() {
        return EUR;
    }

    public void setEUR(Moeda EUR) {
        this.EUR = EUR;
    }

    public Moeda getBTC() {
        return BTC;
    }

    public void setBTC(Moeda BTC) {
        this.BTC = BTC;
    }

    @Override
    public String toString() {
        return "Valores{" +
                "USD=" + USD +
                ", EUR=" + EUR +
                ", BTC=" + BTC +
                '}';
    }
}
