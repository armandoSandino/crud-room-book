package com.pineda.bibliotecaon.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.pineda.bibliotecaon.DAO.LibroDAO;
import com.pineda.bibliotecaon.Entity.Libro;

@Database( entities = {Libro.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public  abstract LibroDAO obtenerLibroDAO();
}
