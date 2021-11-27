package com.mobile.pratice.Model;

public class BannerSlider {
    private  int id_slide;
    private int gambar;

    public BannerSlider(int id_slide, int gambar) {
        this.id_slide = id_slide;
        this.gambar = gambar;
    }

    public int getId_slide() {
        return id_slide;
    }

    public void setId_slide(int id_slide) {
        this.id_slide = id_slide;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }
}
