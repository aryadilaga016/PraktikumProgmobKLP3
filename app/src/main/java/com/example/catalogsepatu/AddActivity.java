package com.example.catalogsepatu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.catalogsepatu.database.entitas.Sepatu;
import com.example.catalogsepatu.database.AppDatabase;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editNama, editMerk, editHarga;
    SeekBar seekBarStock;
    TextView stockSeekbar;
    RadioButton radio1, radio2, radio3, rb;
    RadioGroup rgJenis;
    Button btnTampil;
    CheckBox cb1, cb2, cb3, cb4, cb5, cb6;
    String textNama, textMerk, textHarga, textStock;

    private AppDatabase database;
    private boolean isEdit = false;
    private int id_sepatu = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        database = AppDatabase.getInstance(getApplicationContext());

        editNama = (EditText) findViewById(R.id.editNama);
        editMerk = (EditText) findViewById(R.id.editMerk);
        editHarga = (EditText) findViewById(R.id.editHarga);

        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio2 = (RadioButton) findViewById(R.id.radio2);
        radio3 = (RadioButton) findViewById(R.id.radio3);
        rgJenis = (RadioGroup) findViewById(R.id.rGroup_jenis);

        cb1 = (CheckBox) findViewById(R.id.ukuran1);
        cb2 = (CheckBox) findViewById(R.id.ukuran2);
        cb3 = (CheckBox) findViewById(R.id.ukuran3);
        cb4 = (CheckBox) findViewById(R.id.ukuran4);
        cb5 = (CheckBox) findViewById(R.id.ukuran5);
        cb6 = (CheckBox) findViewById(R.id.ukuran6);


        btnTampil = (Button) findViewById(R.id.btnTampilkan);
        btnTampil.setOnClickListener(this);

        seekBarStock = (SeekBar) findViewById(R.id.seekbarStock); // ini untuk tombol
        stockSeekbar = (TextView) findViewById(R.id.stockSeekbar); // ini untuk text

        Intent intent = getIntent();
        id_sepatu = intent.getIntExtra("id_sepatu", 0);
        if (id_sepatu>0) {
            isEdit = true;
            Sepatu Sepatu = database.sepatuDao().get(id_sepatu);
            editNama.setText(Sepatu.Nama);
            editMerk.setText(Sepatu.Merk);
            editHarga.setText(Sepatu.Harga);
            stockSeekbar.setText(Sepatu.Stock);

            if (Sepatu.Jenis.toString().equals("Olahraga")) {
                radio1.setChecked(true);
            } if (Sepatu.Jenis.toString().equals("Kantor")) {
                radio2.setChecked(true);
            } else if (Sepatu.Jenis.toString().equals("Fashion")) {
                radio3.setChecked(true);
            }

        }else{
            isEdit = false;
        }

        seekBarStock.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                stockSeekbar.setText(String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public void onClick(View v) {

        int radio = rgJenis.getCheckedRadioButtonId();
        rb = findViewById(radio);

        String Nama = editNama.getText().toString();
        String Merk = editMerk.getText().toString();
        String Harga = editHarga.getText().toString();
        String Ukuran = "";
        String stock = stockSeekbar.getText().toString();
        String jenis = rb.getText().toString();


        //Check Box
        if (cb1.isChecked()) {
            Ukuran += "38";
        }
        if (cb2.isChecked()) {
            Ukuran += "39";
        }
        if (cb3.isChecked()) {
            Ukuran += "40";
        }
        if (cb4.isChecked()) {
            Ukuran += "41";
        }
        if (cb5.isChecked()) {
            Ukuran += "42";
        }
        if (cb6.isChecked()) {
            Ukuran += "43";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Toast.makeText(this, "Mohon Cek Kembali", Toast.LENGTH_SHORT).show();
        builder.setTitle("Pengecekan Ulang");
        String finalUkuran = Ukuran;
        builder.setMessage(
                "Barang : " + String.valueOf(Nama) + "\n" +
                        "Harga : " + "Rp." + String.valueOf(Harga) + "\n" +
                        "Merk : " + String.valueOf(Merk) + "\n" +
                        "Jenis : " + String.valueOf(jenis) + "\n" +
                        "Ukuran : " + String.valueOf(Ukuran) + "\n" +
                        "Stock : " + String.valueOf(stock) + "." + "\n" + "\n" + "Apakah anda yakin ingin menyimpan produk ini ?")
                .setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), com.example.catalogsepatu.TampilActivity.class);

                        if (isEdit){
                            database.sepatuDao().update(id_sepatu, Nama, Merk, Harga, finalUkuran, jenis, stock);
                            intent.putExtra("nama", Nama);
                            intent.putExtra("merk", Merk.toString());
                            intent.putExtra("harga", Harga);
                            intent.putExtra("ukuran", finalUkuran);
                            intent.putExtra("jenis", jenis);
                            intent.putExtra("stock", stock);
                        }else{
                            database.sepatuDao().insertSepatu(Nama, Merk, Harga, finalUkuran, jenis, stock);
                            intent.putExtra("nama", Nama);
                            intent.putExtra("merk", Merk.toString());
                            intent.putExtra("harga", Harga);
                            intent.putExtra("ukuran", finalUkuran);
                            intent.putExtra("jenis", jenis);
                            intent.putExtra("stock", stock);
                        }

                        startActivity(intent);
                        finish();
                    }

                });
        builder.setNegativeButton(
                "Batalkan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(com.example.catalogsepatu.AddActivity.this, "Mohon input dengan benar", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog dialoghasil = builder.create();
        dialoghasil.show();
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, com.example.catalogsepatu.MainActivity.class);
        startActivity(intent);
    };


    //Untuk menampilkan hasil input data diatas di Activity Tampil
    public void openActivityTampil() {
        Intent intent = new Intent(this, TampilActivity.class);

        String Nama = editNama.getText().toString();
        String Merk = editMerk.getText().toString();
        String Harga = editHarga.getText().toString();
        String Ukuran = "";
        String stock = stockSeekbar.getText().toString();
        String jenis = rb.getText().toString();


        //Radio Button Size
        if (radio1.isChecked()) {
            Ukuran += "Olahraga";
        }
        if (radio2.isChecked()) {
            Ukuran += "Kantor";
        }
        if (radio3.isChecked()) {
            Ukuran += "Fashion";
        }


        ///Check Box
        if (cb1.isChecked()) {
            Ukuran += "38";
        }
        if (cb2.isChecked()) {
            Ukuran += "39";
        }
        if (cb3.isChecked()) {
            Ukuran += "40";
        }
        if (cb4.isChecked()) {
            Ukuran += "41";
        }
        if (cb5.isChecked()) {
            Ukuran += "42";
        }
        if (cb6.isChecked()) {
            Ukuran += "43";
        }

        intent.putExtra("nama", Nama);
        intent.putExtra("merk", Merk);
        intent.putExtra("harga", Harga);
        intent.putExtra("ukuran", Ukuran);
        intent.putExtra("jenis", jenis);
        intent.putExtra("stock", stock);

        startActivity(intent);

    }

    //Lifecycle
    @Override //saat pencet tombol tambah
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Silahkan Masukan Data Produk", Toast.LENGTH_SHORT).show();
    }

    @Override //saat input data
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Input Data Sedang Berjalan",Toast.LENGTH_SHORT).show();
    }

    @Override //loading
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Mohon Menunggu",Toast.LENGTH_SHORT).show();
    }

    @Override //selesai menginput data
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Produk Berhasil Ditambahkan",Toast.LENGTH_SHORT).show();
    }

    @Override //keluar aplikasi
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Aplikasi ditutup, Selamat Tinggal",Toast.LENGTH_SHORT).show();
    }

}
