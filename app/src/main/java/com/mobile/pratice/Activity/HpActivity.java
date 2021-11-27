package com.mobile.pratice.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mobile.pratice.R;
import com.mobile.pratice.databinding.ActivityHpBinding;

import java.util.concurrent.TimeUnit;

import dmax.dialog.SpotsDialog;

public class HpActivity extends AppCompatActivity {
    private ActivityHpBinding binding;
    String hp;
    boolean changing = false;
    android.app.AlertDialog alertDialog;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        alertDialog = new SpotsDialog.Builder().setContext(HpActivity.this).setMessage("Sedang Memproses ....").setCancelable(false).build();

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HpActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

        binding.periksa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.nohp.getText().toString().isEmpty()) {
                    binding.nohp.setError("No HP diperlukan!");
                } else {
                    alertDialog.show();
                    sendVerificationCode(binding.nohp.getText().toString());
                }
            }
        });

        binding.nohp.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (!changing && binding.nohp.getText().toString().startsWith("0")) {
                    changing = true; // <-- public boolean
                    binding.nohp.setText(binding.nohp.getText().toString().replace("0", ""));
                }
                changing = false;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

        });
    }

    private void sendVerificationCode(String nohp) {
        hp = nohp;
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+62" + nohp,
                60,
                TimeUnit.SECONDS,
                this,
                mCall
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCall = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            alertDialog.cancel();
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            alertDialog.cancel();
            //cek no hp
            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                showSnackbarMessage(getString(R.string.hp_salah));
            } else if (e instanceof FirebaseTooManyRequestsException) {
                showSnackbarMessage(getString(R.string.terlalu_banyak));
            }
        }

        private void showSnackbarMessage(String message) {
            alertDialog.cancel();
            Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
            String mVerificationId = verificationId;
            Log.d("MainActivity", "Verification id : " + verificationId);
            Intent intent = new Intent(HpActivity.this, OtpActivity.class);
            intent.putExtra("verificationId", mVerificationId);
            intent.putExtra("nohp", hp);
            startActivity(intent);
            Log.d("tag", "NOHP : " + hp);
            alertDialog.cancel();
            finish();
        }
    };

}