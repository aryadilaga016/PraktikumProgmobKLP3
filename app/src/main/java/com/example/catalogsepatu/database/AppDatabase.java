package com.example.catalogsepatu.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.catalogsepatu.database.dao.SepatuDao;
import com.example.catalogsepatu.database.entitas.Sepatu;

@Database(entities = {Sepatu.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static com.example.catalogsepatu.database.AppDatabase sInstance;
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
    @VisibleForTesting
    public static final String DATABASE_NAME= "db_catalogsepatu";

    public abstract SepatuDao sepatuDao();

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    private void updateDatabaseCreated(final Context context){
        if(context.getDatabasePath(DATABASE_NAME).exists()){
            setDatabaseCreated();
        }
    }

    public static com.example.catalogsepatu.database.AppDatabase buildDatabase(final Context context){
        return Room.databaseBuilder(context, com.example.catalogsepatu.database.AppDatabase.class,DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        com.example.catalogsepatu.database.AppDatabase database = com.example.catalogsepatu.database.AppDatabase.getInstance(context);
                        database.setDatabaseCreated();
                    }
                }).allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    public static com.example.catalogsepatu.database.AppDatabase getInstance(Context context) {
        if(sInstance == null){
            synchronized (com.example.catalogsepatu.database.AppDatabase.class){
                if(sInstance == null){
                    sInstance = buildDatabase(context);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }
}
