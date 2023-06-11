package com.candra.eksplorindonesia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.candra.eksplorindonesia.API.APIRequestData;
import com.candra.eksplorindonesia.API.RetrofitServer;
import com.candra.eksplorindonesia.Model.ModelAllResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity
{
    private EditText etNamaLengkap, etEmail, etNomorTelepon, etPassword, etKonfirmasiPassword;

    private ImageView ivFoto;

    private Button btnLogin;

    private TextView tvLogin;

    String namaLengkap, email, notelp, password, konfirmasiPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNamaLengkap = findViewById(R.id.et_nama_lengkap_register);
        etEmail = findViewById(R.id.et_email_register);
        etNomorTelepon = findViewById(R.id.et_nomor_telepon_register);
        etPassword = findViewById(R.id.et_password_register);
        etKonfirmasiPassword = findViewById(R.id.et_konfirmasi_password_register);
        ivFoto = findViewById(R.id.iv_foto_register);

        tvLogin = findViewById(R.id.tv_redirect_login);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namaLengkap = etNamaLengkap.getText().toString();
                email = etEmail.getText().toString();
                notelp = etNomorTelepon.getText().toString();
                password = etPassword.getText().toString();
                konfirmasiPassword = etKonfirmasiPassword.getText().toString();

                if(namaLengkap.trim().equals(""))
                {
                    etNamaLengkap.setError("Nama lengkap tidak boleh kosong !");
                    etNamaLengkap.requestFocus();
                }
                else if(email.trim().equals(""))
                {
                    etEmail.setError("Email tidak boleh kosong !");
                    etEmail.requestFocus();
                }
                else if(notelp.trim().equals(""))
                {
                    etNomorTelepon.setError("Nomor tekepon tidak boleh kosong !");
                    etNomorTelepon.requestFocus();
                }
                else if(password.trim().equals(""))
                {
                    etPassword.setError("Password tidak boleh kosong !");
                    etPassword.requestFocus();
                }
                else if(konfirmasiPassword.trim().equals(""))
                {
                    etKonfirmasiPassword.setError("Konfirmasi password tidak boleh kosong !");
                    etKonfirmasiPassword.requestFocus();
                }
                else
                {
                    Register();
                }
            }
        });

    }

    private void Register()
    {
        if(password.equals(konfirmasiPassword))
        {
            APIRequestData ard = RetrofitServer.connectionRetrofit().create(APIRequestData.class);
            Call<ModelAllResponse> prosesRegister = ard.ardCreateDataUser(namaLengkap, email,"user", notelp, "foto", password);

            prosesRegister.enqueue(new Callback<ModelAllResponse>() {
                @Override
                public void onResponse(Call<ModelAllResponse> call, Response<ModelAllResponse> response) {
                    String kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(RegisterActivity.this, "Proses Register berhasil !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this
                    , LoginActivity.class));
                    finish();
                }

                @Override
                public void onFailure(Call<ModelAllResponse> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Proses registrasi gagal !" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            etKonfirmasiPassword.setError("Konfirmasi password tidak sama dengan password !");
            etKonfirmasiPassword.requestFocus();
            etKonfirmasiPassword.setText("");
        }

    }
}