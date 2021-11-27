package com.mobile.pratice.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.mobile.pratice.Adapter.FotoSliderAdapter;
import com.mobile.pratice.Adapter.KategoriAdapter;
import com.mobile.pratice.Adapter.MenuAdapter;
import com.mobile.pratice.Adapter.PasCleanAdapter;
import com.mobile.pratice.Model.Kategori;
import com.mobile.pratice.Model.Menu;
import com.mobile.pratice.Model.PasClean;
import com.mobile.pratice.R;
import com.mobile.pratice.databinding.ActivityPasCleanBinding;

import java.util.ArrayList;
import java.util.List;

public class PasCleanActivity extends AppCompatActivity {
    private ActivityPasCleanBinding binding;
    List<PasClean> pasCleans = new ArrayList<>();
    List<Kategori> kategoris = new ArrayList<>();
    List<Menu> menus = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPasCleanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Kategori
        binding.recycler2.setHasFixedSize(true);
        binding.recycler2.setLayoutManager(new LinearLayoutManager(this, GridLayoutManager.HORIZONTAL, false));
        KategoriAdapter adapt = new KategoriAdapter(PasCleanActivity.this, kategoris);
        binding.recycler2.setAdapter(adapt);
        dataKategori();

        //LAYANAN
        binding.recycler3.setHasFixedSize(true);
        binding.recycler3.setLayoutManager(new LinearLayoutManager(this));
        PasCleanAdapter adap = new PasCleanAdapter(PasCleanActivity.this, pasCleans);
        binding.recycler3.setAdapter(adap);
        dataLayanan();

        //Menu
        binding.recycler1.setHasFixedSize(true);
        binding.recycler1.setLayoutManager(new LinearLayoutManager(this, GridLayoutManager.HORIZONTAL, false));
        MenuAdapter adp = new MenuAdapter(PasCleanActivity.this, menus);
        binding.recycler1.setAdapter(adp);
        dataMenu();

    }

    private void dataMenu() {
        menus.add(new Menu(1, "Cleaning"));
        menus.add(new Menu(2, "Gardener"));
        menus.add(new Menu(3, "Oto Clean"));
        menus.add(new Menu(4, "Laundry"));
        menus.add(new Menu(5, "Special Clean"));
    }

    private void dataLayanan() {
        pasCleans.add(new PasClean(1, "Nama Layanan/Jasa", "Alamat", 0.5, R.drawable.kasur));
        pasCleans.add(new PasClean(2, "Nama Layanan/Jasa", "Alamat", 0.5, R.drawable.kasur));
        pasCleans.add(new PasClean(3, "Nama Layanan/Jasa", "Alamat", 0.5, R.drawable.kasur));
        pasCleans.add(new PasClean(3, "Nama Layanan/Jasa", "Alamat", 0.5, R.drawable.kasur));
    }

    private void dataKategori() {
        kategoris.add(new Kategori(1, "Recomendation"));
        kategoris.add(new Kategori(2, "Near"));
        kategoris.add(new Kategori(3, "Discount"));
        kategoris.add(new Kategori(4, "All"));
    }

}