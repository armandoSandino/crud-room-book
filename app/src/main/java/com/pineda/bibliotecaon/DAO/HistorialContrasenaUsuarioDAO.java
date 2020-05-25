package com.pineda.bibliotecaon.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.pineda.bibliotecaon.Entity.HistorialContrasenaUsuario;

import java.util.List;

@Dao
public interface HistorialContrasenaUsuarioDAO {
    @Insert
    public long agregarHistorialContrasenaUsuario(HistorialContrasenaUsuario item );

    @Update
    public int actualizarHistorialContrasenaUsuario(HistorialContrasenaUsuario item   );

    @Delete
    public void eliminarHistorialContrasenaUsuario(HistorialContrasenaUsuario item );

    @Query("SELECT * FROM HistorialContrasenaUsuario")
    public List<HistorialContrasenaUsuario> listaHistorialContrasenaUsuario();

    @Query("SELECT * FROM HistorialContrasenaUsuario WHERE idHistorialContrasenaUsuario ==:id")
    public HistorialContrasenaUsuario verificarHistorialContrasenaUsuario(int id );
}
