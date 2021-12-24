package com.example.catalogsepatu.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.catalogsepatu.database.entitas.Sepatu;

import java.util.List;

@Dao
public interface SepatuDao {
    @Query("SELECT * FROM Sepatu")
    List<Sepatu> getAll();

    @Query("INSERT INTO Sepatu (nama, merk, harga, ukuran, jenis, stock) VALUES(:Nama,:Merk,:Harga,:Ukuran,:Jenis,:Stock)")
    void insertSepatu(String Nama, String Merk, String Harga, String Ukuran, String Jenis, String Stock);

    @Query("UPDATE Sepatu SET nama=:Nama , merk=:Merk, harga=:Harga , ukuran=:Ukuran , jenis=:Jenis , stock=:Stock WHERE id_sepatu=:id_sepatu")
    void update(int id_sepatu, String Nama, String Merk, String Harga, String Ukuran, String Jenis, String Stock);

    @Query("SELECT * FROM Sepatu WHERE id_sepatu=:id_sepatu")
    Sepatu get(int id_sepatu);

    @Delete
    void delete(Sepatu Sepatu);

}


