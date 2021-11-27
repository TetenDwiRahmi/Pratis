package com.mobile.pratice.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mobile.pratice.R;
import com.mobile.pratice.databinding.ActivityOtpBinding;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import dmax.dialog.SpotsDialog;

public class OtpActivity extends AppCompatActivity {
    private ActivityOtpBinding binding;
    FirebaseAuth mAuth;
    String verificationId, nohp;
    private long exitTime = 0;
    android.app.AlertDialog alertDialog;

    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        alertDialog = new SpotsDialog.Builder().setContext(OtpActivity.this).setMessage("Sedang Memproses ....").setCancelable(false).build();

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(OtpActivity.this)
                        .setTitle("Perhatian")
                        .setIcon(R.drawable.pratis)
                        .setMessage("Apakah anda yakin ingin keluar?")
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(OtpActivity.this, HpActivity.class)
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

        mAuth = FirebaseAuth.getInstance();

        Intent i = new Intent(getIntent());
        verificationId = i.getStringExtra("verificationId");
        nohp = i.getStringExtra("nohp");
        Log.d("OTPActivity" , "Verification id : " + verificationId);

        binding.terkirim.setText("Terkirim ke (+62" + nohp + ")");

        binding.otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(binding.otp1.getText().toString().length() == 0){
                    binding.otp2.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

        }});

        binding.otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(binding.otp2.getText().toString().length() == 0){
                    binding.otp3.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }});

        binding.otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(binding.otp3.getText().toString().length() == 0){
                    binding.otp4.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }});
        binding.otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(binding.otp4.getText().toString().length() == 0){
                    binding.otp5.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }});

        binding.otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(binding.otp5.getText().toString().length() == 0){
                    binding.otp6.requestFocus();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }});

        binding.otp6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(binding.otp6.getText().toString().length() == 0){
                    binding.konfirmasi.callOnClick();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }});

        binding.konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = binding.otp1.getText().toString() + binding.otp2.getText().toString() + binding.otp3.getText().toString() +
                        binding.otp4.getText().toString() + binding.otp5.getText().toString() + binding.otp6.getText().toString();
                if(!TextUtils.isEmpty(code)){
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

        binding.kirimUlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerificationCode(nohp);
            }
        });
    }

    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            new AlertDialog.Builder(OtpActivity.this)
                    .setTitle("Perhatian")
                    .setIcon(R.drawable.pratis)
                    .setMessage("Apakah anda yakin ingin keluar?")
                    .setCancelable(false)
                    .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent i = new Intent(OtpActivity.this, HpActivity.class)
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

    private void sendVerificationCode(String nohp) {
        alertDialog.show();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+62"+nohp,
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
        public void onCodeSent(String verificationIds, PhoneAuthProvider.ForceResendingToken token) {
            verificationId = verificationIds;
            Log.d("MainActivity" , "Verification id : " + verificationId);
            alertDialog.cancel();
            binding.kirimUlang.setEnabled(false);
            new CountDownTimer(180* 1000+1000, 1000) {

                @SuppressLint({"DefaultLocale", "SetTextI18n"})
                public void onTick(long millisUntilFinished) {
                    binding.waktu.setVisibility(View.VISIBLE);
                    int seconds = (int) (millisUntilFinished / 1000);
                    int minutes = seconds / 60;
                    seconds = seconds % 60;
                    binding.waktu.setText(String.format("%02d", minutes)
                            + ":" + String.format("%02d", seconds));
                }

                public void onFinish() {
                    binding.kirimUlang.setEnabled(true);
                    binding.waktu.setVisibility(View.GONE);
                }
            }.start();
        }
    };



    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(OtpActivity.this, DaftarActivity.class);
                            i.putExtra("nohp", nohp);
                            startActivity(i);
                            finish();
                        }
//                        else {
//                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
////                                notif2("Kode Salah!");
//                                Log.d("tag", "kode salah");
//                            } else if (task.getException() instanceof FirebaseTooManyRequestsException) {
////                                notif2("Terlalu Banyak Permintaan, silakan coba dengan nomor telepon lain!");
////                                notif("Terlalu Banyak Permintaan, silakan coba dengan nomor telepon lain!");
//                                Log.d("tag", "kode salah2");
//                            }
//                        }
                    }
                });
    }
}