package com.mobile.pratice.Model;

public class FotoSlider {
    private  int id_foto;
    private int gambar;
    private String kategori;
    private int harga;
    private int like;

    public FotoSlider(int id_foto, int gambar, String kategori, int harga, int like) {
        this.id_foto = id_foto;
        this.gambar = gambar;
        this.kategori = kategori;
        this.harga = harga;
        this.like = like;
    }

    public int getId_foto() {
        return id_foto;
    }

    public void setId_foto(int id_foto) {
        this.id_foto = id_foto;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
