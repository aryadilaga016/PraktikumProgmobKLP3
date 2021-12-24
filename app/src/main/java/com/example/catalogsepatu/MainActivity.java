package com.example.catalogsepatu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catalogsepatu.database.AppDatabase;
import com.example.catalogsepatu.database.entitas.Sepatu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnAbout;
    private RecyclerView rvIndex;
    private AppDatabase database;
    private com.example.catalogsepatu.CatalogAdapter CatalogAdapter;
    private List<Sepatu> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvIndex = findViewById(R.id.rv_sepatu);

        btnAdd = (Button) findViewById(R.id.btn_Tambah);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
            }
        });

        btnAbout = (Button) findViewById(R.id.btn_about);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, About.class);
                startActivity(intent);
            }
        });

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.sepatuDao().getAll());
        CatalogAdapter = new com.example.catalogsepatu.CatalogAdapter(getApplicationContext(),list);
        CatalogAdapter.setDialog(new com.example.catalogsepatu.CatalogAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit Produk", "Hapus Produk"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                                intent.putExtra("id_sepatu", list.get(position).id_sepatu);
                                startActivity(intent);
                                break;
                            case 1:
                                Sepatu Sepatu = list.get(position);
                                database.sepatuDao().delete(Sepatu);
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();

            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        rvIndex.setLayoutManager(layoutManager);
        rvIndex.setAdapter(CatalogAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(com.example.catalogsepatu.MainActivity.this,AddActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.sepatuDao().getAll());
        CatalogAdapter.notifyDataSetChanged();
    }
}