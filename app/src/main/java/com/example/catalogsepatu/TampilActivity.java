package com.example.catalogsepatu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TampilActivity extends AppCompatActivity {
    TextView tampilNama, tampilMerk, tampilHarga, tampilUkuran, tampilJenis, tampilStock;
    String nama, merk, harga, ukuran, jenis, stock;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        tampilNama = (TextView) findViewById(R.id.tampilNama);
        tampilMerk = (TextView) findViewById(R.id.tampilMerk);
        tampilHarga = (TextView) findViewById(R.id.tampilHarga);
        tampilUkuran = (TextView) findViewById(R.id.tampilUkuran);
        tampilJenis = (TextView) findViewById(R.id.tampilJenis);
        tampilStock = (TextView) findViewById(R.id.tampilStock);

        if (getIntent().getStringExtra("nama") != "") {
            nama = getIntent().getStringExtra("nama");
            tampilNama.setText(nama);
        }
        if (getIntent().getStringExtra("merk") != "") {
            merk = getIntent().getStringExtra("merk");
            tampilMerk.setText(merk);
        }
        if (getIntent().getStringExtra("harga") != "") {
            harga = getIntent().getStringExtra("harga");
            tampilHarga.setText(harga);
        }
        if (getIntent().getStringExtra("ukuran") != "") {
            ukuran = getIntent().getStringExtra("ukuran");
            tampilUkuran.setText(ukuran);
        }
        if (getIntent().getStringExtra("jenis") != "") {
            jenis = getIntent().getStringExtra("jenis");
            tampilJenis.setText(jenis);
        }
        if (getIntent().getStringExtra("stock") != "") {
            stock = getIntent().getStringExtra("stock");
            tampilStock.setText(stock);
        }
    }
    public void  submit (View view) {
        Intent intent = new Intent(com.example.catalogsepatu.TampilActivity.this, MainActivity.class);
        startActivity(intent);
    }
}


