package com.pineda.bibliotecaon.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.pineda.bibliotecaon.Entity.Usuario;

import java.util.List;

@Dao
public interface UsuarioDAO {
    @Insert
    public long agregarUsuario(Usuario item );

    @Update
    public int actualizarUsuario(Usuario item   );

    @Delete
    public void eliminarUsuario(Usuario item );

    @Query("SELECT * FROM Usuario")
    public List<Usuario> listaUsuario();

    @Query("SELECT * FROM Usuario WHERE IdUsuario ==:id")
    public Usuario obtenerUsuario( long id );
}
