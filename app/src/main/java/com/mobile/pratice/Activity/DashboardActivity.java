package com.mobile.pratice.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.mobile.pratice.Adapter.BannerSliderAdapter;
import com.mobile.pratice.Adapter.FotoSliderAdapter;
import com.mobile.pratice.Adapter.KategoriAdapter;
import com.mobile.pratice.Model.BannerSlider;
import com.mobile.pratice.Model.FotoSlider;
import com.mobile.pratice.Model.Kategori;
import com.mobile.pratice.R;
import com.mobile.pratice.databinding.ActivityDashboardBinding;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private ActivityDashboardBinding binding;
    List<BannerSlider> bannerSliders = new ArrayList<>();
    List<Kategori> kategoris = new ArrayList<>();
    List<FotoSlider> fotoSliders = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Banner Slider
        bannerSlider();
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        binding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        binding.imageSlider.setScrollTimeInSec(3);
        binding.imageSlider.setAutoCycle(true);
        binding.imageSlider.startAutoCycle();

        binding.imageSlider.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + binding.imageSlider.getCurrentPagePosition());
            }
        });
        BannerSliderAdapter adapter = new BannerSliderAdapter(this);
        binding.imageSlider.setSliderAdapter(adapter);
        adapter.renewItems(bannerSliders);

        //Kategori
        binding.recyclerKategori.setHasFixedSize(true);
        binding.recyclerKategori.setLayoutManager(new LinearLayoutManager(this, GridLayoutManager.HORIZONTAL, false));
        KategoriAdapter adapt = new KategoriAdapter(DashboardActivity.this, kategoris);
        binding.recyclerKategori.setAdapter(adapt);
        dataKategori();

        //Foto
        binding.recyclerFoto.setHasFixedSize(true);
        binding.recyclerFoto.setLayoutManager(new LinearLayoutManager(this, GridLayoutManager.HORIZONTAL, false));
        FotoSliderAdapter adap = new FotoSliderAdapter(DashboardActivity.this, fotoSliders);
        binding.recyclerFoto.setAdapter(adap);
        dataFoto();

        binding.pasClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, PasCleanActivity.class);
                startActivity(i);
            }
        });

    }

    private void dataFoto() {
        fotoSliders.add(new FotoSlider(1, R.drawable.kasur, "Adi Cleaning", 60000, 100));
        fotoSliders.add(new FotoSlider(2, R.drawable.cuci_mobil, "Adi Pest", 45000, 150));
        fotoSliders.add(new FotoSlider(3, R.drawable.baju, "Adi Laundry", 35000, 88));
    }

    private void dataKategori() {
        kategoris.add(new Kategori(1, "Recomendation"));
        kategoris.add(new Kategori(2, "Near"));
        kategoris.add(new Kategori(3, "Discount"));
        kategoris.add(new Kategori(4, "All"));
    }

    private void bannerSlider() {
        bannerSliders.add(new BannerSlider(1, R.drawable.garden));
        bannerSliders.add(new BannerSlider(2, R.drawable.nail));
        bannerSliders.add(new BannerSlider(3, R.drawable.spa));
    }

}