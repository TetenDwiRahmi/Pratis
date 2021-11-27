package com.mobile.pratice.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;
import com.mobile.pratice.databinding.ActivityDetailCleanBinding;

import java.util.ArrayList;

public class DetailCleanActivity extends AppCompatActivity {
    private ActivityDetailCleanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailCleanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}