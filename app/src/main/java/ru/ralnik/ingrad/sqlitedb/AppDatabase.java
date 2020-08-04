package ru.ralnik.ingrad.sqlitedb;

import androidx.room.Database;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.RoomDatabase;
import androidx.room.Room;
import android.content.Context;
import androidx.annotation.NonNull;

import ru.ralnik.ingrad.model.Flat;

@Database(entities = {Flat.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static Context context;

    public abstract FlatDao flatDao();
    private static volatile AppDatabase INSTANCE;


    public static AppDatabase getInstance(Context arg)  {
        context = arg;
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "ingrad.db")
                            //.allowMainThreadQueries()
                            //.addMigrations(MIGRATION_1_2)
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            //new PopulateDbAsync(INSTANCE).execute();
        }
    };

//    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
//        @Override
//        public void migrate(SupportSQLiteDatabase database) {
//            String sql = "ALTER TABLE FLATS \n" +
//                    "ADD COLUMN BuildingGroup TEXT";
//            database.beginTransaction();
//            database.execSQL(sql);
//            database.endTransaction();
//        }
//    };
}
