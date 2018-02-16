package com.example.mbenkerroum.secured;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

/**
 * Created by mbenkerroum on 15/02/2018.
 */

@Database(entities = {Password.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE product "
                    + " ADD COLUMN price INTEGER");

            // enable flag to force update products
            App.get().setForceUpdate(true);
        }
    };

    public abstract PasswordDAO productDao();
}