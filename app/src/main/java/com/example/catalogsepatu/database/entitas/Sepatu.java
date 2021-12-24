package com.example.catalogsepatu.database.entitas;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sepatu {
    @PrimaryKey(autoGenerate = true)
    public int id_sepatu;

    public String Nama;
    public String Merk;
    public String Harga;
    public String Ukuran;
    public String Jenis;
    public String Stock;

    public int getId_sepatu() {
        return id_sepatu;
    }

    public void getId_sepatu(int id_sepatu) {
        this.id_sepatu = id_sepatu;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        this.Nama = nama;
    }

    public String getUkuran() {
        return Ukuran;
    }

    public void setukuran(String Ukuran) { this.Ukuran = Ukuran; }

    public String getHarga() {
        return Harga;
    }

    public void setHarga(String harga) {
        this.Harga = harga;
    }

    public String getMerk() {
        return Merk;
    }

    public void setMerk(String merk) {
        this.Merk = merk;
    }

    public String getJenis() {
        return Jenis;
    }

    public void setJenis(String jenis) { this.Jenis = jenis; }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        this.Stock = stock;
    }
}
