package com.example.mbenkerroum.secured;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

/**
 * Created by mbenkerroum on 15/02/2018.
 */

@Database(entities = {Password.class}, version = 3)
public abstract class MyDatabase extends RoomDatabase {
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE password "
                    + " ADD COLUMN passwordName TEXT");

            App.get().setForceUpdate(true);
        }
    };

    public static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE password "
                    + " ADD COLUMN passwordDesc TEXT");

            App.get().setForceUpdate(true);
        }
    };

    public abstract PasswordDAO passwordDao();
}