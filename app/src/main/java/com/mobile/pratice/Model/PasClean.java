package com.mobile.pratice.Model;

public class PasClean {
    private  int id_clean;
    private String layanan;
    private String alamat;
    private double jarak;
    private int foto_layanan;

    public PasClean(int id_clean, String layanan, String alamat, double jarak, int foto_layanan) {
        this.id_clean = id_clean;
        this.layanan = layanan;
        this.alamat = alamat;
        this.jarak = jarak;
        this.foto_layanan = foto_layanan;
    }

    public int getId_clean() {
        return id_clean;
    }

    public void setId_clean(int id_clean) {
        this.id_clean = id_clean;
    }

    public String getLayanan() {
        return layanan;
    }

    public void setLayanan(String layanan) {
        this.layanan = layanan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public double getJarak() {
        return jarak;
    }

    public void setJarak(double jarak) {
        this.jarak = jarak;
    }

    public int getFoto_layanan() {
        return foto_layanan;
    }

    public void setFoto_layanan(int foto_layanan) {
        this.foto_layanan = foto_layanan;
    }
}
