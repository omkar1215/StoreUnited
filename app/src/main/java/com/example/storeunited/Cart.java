package com.example.storeunited;

public class Cart {

    private String iidd;
    private String nnamee;
    private String iimgg;
    private int ssizee;
    private int ppricee;

    public Cart(String iidd, String nnamee, String iimgg, int ssizee, int ppricee) {
        this.iidd = iidd;
        this.nnamee = nnamee;
        this.iimgg = iimgg;
        this.ssizee = ssizee;
        this.ppricee = ppricee;
    }
    public Cart(){}

    public String getIidd() {
        return iidd;
    }

    public void setIidd(String iidd) {
        this.iidd = iidd;
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

    public int getSsizee() {
        return ssizee;
    }

    public void setSsizee(int ssizee) {
        this.ssizee = ssizee;
    }

    public int getPpricee() {
        return ppricee;
    }

    public void setPpricee(int ppricee) {
        this.ppricee = ppricee;
    }
}
