package com.derrick.park.assignment3_contacts.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insert(ContactEntity contact);

    @Query("SELECT * FROM contact_table ORDER BY first_name")
    List<ContactEntity> getAll();
}
