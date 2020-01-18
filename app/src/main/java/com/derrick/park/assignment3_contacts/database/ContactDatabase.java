package com.derrick.park.assignment3_contacts.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = ContactEntity.class, version = 1, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase {
    private static ContactDatabase INSTANCE;
    public abstract ContactDao contactDao();

    public static ContactDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContactDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.inMemoryDatabaseBuilder(context,
                            ContactDatabase.class)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
