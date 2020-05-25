package com.pineda.bibliotecaon.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.pineda.bibliotecaon.Entity.Rol;

import java.util.List;

@Dao
public interface RolDAO {
    @Insert
    public long agregarRol(Rol item );

    @Update
    public int actualizarRol(Rol item   );

    @Delete
    public void eliminarRol(Rol item );

    @Query("SELECT * FROM Rol")
    public List< Rol > listaRol();

    @Query("SELECT * FROM Rol WHERE IdRol ==:id")
    public Rol verificarRol(int id );
}
