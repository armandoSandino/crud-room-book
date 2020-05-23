package com.pineda.bibliotecaon.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.pineda.bibliotecaon.Entity.Contact;
import com.pineda.bibliotecaon.Entity.ContactRoom;

import java.util.List;

@Dao
public interface ContactDAO {
    @Insert
    public long addContact(ContactRoom contacto );

    @Update
    public void updateContact(ContactRoom contacto  );

    @Delete
    public void deleteContact(ContactRoom contactRoom );

    @Query("SELECT * FROM contacts")
    public List<ContactRoom> getContacts();

    @Query("SELECT * FROM contacts WHERE contact_id ==:idContacto")
    public ContactRoom getContact( long idContacto );
}
