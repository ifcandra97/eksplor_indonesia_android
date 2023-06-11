package com.candra.eksplorindonesia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.candra.eksplorindonesia.API.APIRequestData;
import com.candra.eksplorindonesia.API.RetrofitServer;
import com.candra.eksplorindonesia.Model.ModelAllResponse;
import com.candra.eksplorindonesia.Utility.ControllerLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    private ImageView ivBackToMyProfile, ivDeleteProfile;
    private EditText etNamaLengkap, etEmail, etNomorTelepon, etPassword;
    private Button btnSimpanPerubahan;

    private ControllerLogin cLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        cLogin = new ControllerLogin(EditProfileActivity.this);

        ivBackToMyProfile = findViewById(R.id.iv_back_to_my_profile);
        ivBackToMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ivDeleteProfile = findViewById(R.id.iv_delete_profile);
        ivDeleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idUser = cLogin.getPreferences(EditProfileActivity.this, String.valueOf(cLogin.keySP_id));
                DeleteAccount(idUser);
            }
        });

        etNamaLengkap = findViewById(R.id.et_edit_nama_lengkap);
        etEmail = findViewById(R.id.et_edit_email);
        etNomorTelepon = findViewById(R.id.et_edit_nomor_telepon);
        etPassword = findViewById(R.id.et_edit_password);

        etNamaLengkap.setText(cLogin.getPreferences(EditProfileActivity.this, cLogin.keySP_fullname));
        etEmail.setText(cLogin.getPreferences(EditProfileActivity.this, cLogin.keySP_email));
        etNomorTelepon.setText(cLogin.getPreferences(EditProfileActivity.this, cLogin.keySP_phone));
        etPassword.setText(cLogin.getPreferences( EditProfileActivity.this, cLogin.keySP_password));
    }

    private void DeleteAccount(String idUser) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(EditProfileActivity.this);
        dialog.setTitle("Peringatan !");
        dialog.setMessage("Apakah anda yakin ingin menghapus Account ?");
        dialog.setCancelable(true);
        dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                APIRequestData ard = RetrofitServer.connectionRetrofit().create(APIRequestData.class);

                Call<ModelAllResponse> prosesDeleteAccount = ard.ardDeleteDataUser(idUser);

                prosesDeleteAccount.enqueue(new Callback<ModelAllResponse>() {
                    @Override
                    public void onResponse(Call<ModelAllResponse> call, Response<ModelAllResponse> response) {
                        if (response.isSuccessful()) {
                            String kode = response.body().getKode();
                            String pesan = response.body().getPesan();
                            Toast.makeText(EditProfileActivity.this, "Account berhasil dihapus !", Toast.LENGTH_SHORT).show();
                            cLogin.setPreferences(EditProfileActivity.this, cLogin.keySP_id, null);
                            cLogin.setPreferences(EditProfileActivity.this, cLogin.keySP_email, null);
                            cLogin.setPreferences(EditProfileActivity.this, cLogin.keySP_password, null);
                            cLogin.setPreferences(EditProfileActivity.this, cLogin.keySP_role, null);
                            cLogin.setPreferences(EditProfileActivity.this, cLogin.keySP_phone, null);
                            cLogin.setPreferences(EditProfileActivity.this, cLogin.keySP_fullname, null);
                            cLogin.setPreferences(EditProfileActivity.this, cLogin.keySP_foto, null);
                            startActivity(new Intent(EditProfileActivity.this, SplashScreen.class));
                        } else {
                            Toast.makeText(EditProfileActivity.this, "Gagal menghapus account!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelAllResponse> call, Throwable t) {
                        Toast.makeText(EditProfileActivity.this, "Account Gagal dihapus ! " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();
    }
}
