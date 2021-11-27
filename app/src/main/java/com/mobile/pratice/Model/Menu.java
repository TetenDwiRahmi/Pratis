package com.mobile.pratice.Model;

public class Menu {
    private  int id_menu;
    private String menu;

    public Menu(int id_menu, String menu) {
        this.id_menu = id_menu;
        this.menu = menu;
    }

    public int getId_menu() {
        return id_menu;
    }

    public void setId_menu(int id_menu) {
        this.id_menu = id_menu;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
