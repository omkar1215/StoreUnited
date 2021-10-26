package com.example.storeunited;

public class Cart2 {

    String iimgg;
    String nnamee;
    int ppricee;

    public Cart2(){}

    public Cart2(String iimgg, String nnamee, int ppricee) {
        this.iimgg = iimgg;
        this.nnamee = nnamee;
        this.ppricee = ppricee;
    }

    public String getNnamee() {
        return nnamee;
    }

    public void setNnamee(String nnamee) {
        this.nnamee = nnamee;
    }

    public String getIimgg() {
        return iimgg;
    }

    public void setIimgg(String iimgg) {
        this.iimgg = iimgg;
    }

    public int getPpricee() {
        return ppricee;
    }

    public void setPpricee(int ppricee) {
        this.ppricee = ppricee;
    }
}
