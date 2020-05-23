package com.pineda.bibliotecaon.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.pineda.bibliotecaon.Entity.Libro;

import java.util.List;

@Dao
public interface LibroDAO {
    @Insert
    public long agregarLibro(Libro contacto );

    @Update
    public void actualizarLibro(Libro contacto  );

    @Delete
    public void eliminarLibro(Libro contactRoom );

    @Query("SELECT * FROM Libro")
    public List<Libro> listaLibro();

    @Query("SELECT * FROM Libro WHERE IdLibro ==:idLibro")
    public Libro obtenerLibro( long idLibro );
}
