package com.candra.eksplorindonesia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AddWisataActivity extends AppCompatActivity {

    private ImageView ivBackToWisata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wisata);

        ivBackToWisata = findViewById(R.id.iv_back_to_wisata2);
        ivBackToWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}