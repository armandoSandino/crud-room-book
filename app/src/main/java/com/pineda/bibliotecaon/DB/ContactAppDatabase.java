package com.pineda.bibliotecaon.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.pineda.bibliotecaon.DAO.ContactDAO;
import com.pineda.bibliotecaon.Entity.ContactRoom;

@Database( entities = {ContactRoom.class }, version = 1)
public abstract class ContactAppDatabase extends RoomDatabase {
    public  abstract ContactDAO getContactDAO();
}
