package com.mobile.pratice.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.mobile.pratice.R;
import com.mobile.pratice.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    boolean isPasswordVisible;
    RelativeLayout layout_loading;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        layout_loading = findViewById(R.id.layout_loading);

        binding.daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HpActivity.class);
                startActivity(i);
            }
        });

        binding.masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_loading.setVisibility(View.VISIBLE);
                Intent i = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(i);
            }
        });

        binding.password.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (binding.password.getRight() - binding.password.getCompoundDrawables()[RIGHT].getBounds().width())) {
                        int selection = binding.password.getSelectionEnd();
                        if (isPasswordVisible) {
                            binding.password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_off, 0);
                            binding.password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            isPasswordVisible = false;
                        } else {
                            binding.password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_visibility_on, 0);
                            binding.password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            isPasswordVisible = true;
                        }
                        binding.password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });


    }
}