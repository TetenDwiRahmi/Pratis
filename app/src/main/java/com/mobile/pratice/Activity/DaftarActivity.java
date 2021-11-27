package com.mobile.pratice.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.pratice.R;
import com.mobile.pratice.databinding.ActivityDaftarBinding;

public class DaftarActivity extends AppCompatActivity {
    private ActivityDaftarBinding binding;
    boolean isPasswordVisible;
    private long exitTime = 0;
    String nohp;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDaftarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DaftarActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        Intent i = new Intent(getIntent());
        nohp = i.getStringExtra("nohp");
        Log.d("tag", "HP : " + nohp);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(DaftarActivity.this)
                        .setTitle("Perhatian")
                        .setIcon(R.drawable.pratis)
                        .setMessage("Apakah anda yakin ingin keluar?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(DaftarActivity.this, MainActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                finish();
                            }
                        })
                        .setNegativeButton("Tidak", null)
                        .show();
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

        binding.daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.nama.getText().toString().length() == 0) {
                    binding.nama.setError("Nama diperlukan!");
                } else if (binding.email.getText().toString().length() == 0) {
                    binding.email.setError("Email diperlukan!");
                } else if (binding.alamat.getText().toString().length() == 0) {
                    binding.alamat.setError("Alamat diperlukan!");
                } else if (binding.password.getText().toString().length() == 0) {
                    binding.password.setError("Password diperlukan!");
                } else if (binding.konfPassword.getText().toString().length() == 0) {
                    binding.konfPassword.setError("Konfirmasi password diperlukan!");
//               }else if (binding.gender.getSelectedItem().toString().equals("")){
//                   Toast.makeText(DaftarActivity.this, "Pilih gender", Toast.LENGTH_SHORT).show();
                }else if(!binding.konfPassword.getText().toString().equals(binding.password.getText().toString())){
                    binding.konfPassword.setError("Password tidak sama!");
                }
                else if (!binding.menyetujui.isChecked()) {
                    Toast.makeText(DaftarActivity.this, "Tidak setuju", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(DaftarActivity.this, MainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }
            }
        });

    }

    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            new AlertDialog.Builder(DaftarActivity.this)
                    .setTitle("Perhatian")
                    .setIcon(R.drawable.pratis)
                    .setMessage("Apakah anda yakin ingin keluar?")
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent i = new Intent(DaftarActivity.this, MainActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            finish();
                        }
                    })
                    .setNegativeButton("Tidak", null)
                    .show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}