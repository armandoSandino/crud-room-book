package com.pineda.bibliotecaon.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.pineda.bibliotecaon.Entity.Usuario;

import java.util.List;

@Dao
public interface UsuarioDAO {
    @Insert
    public long agregarUsuario(Usuario item );

    @Update( onConflict = OnConflictStrategy.REPLACE )
    public void updateUsuario( Usuario user);
    @Delete
    public void eliminarUsuario(Usuario item );

    @Query("SELECT * FROM Usuario")
    public List<Usuario> listaUsuario();

    @Query("SELECT * FROM Usuario WHERE IdUsuario ==:id")
    public Usuario obtenerUsuario( long id );

    @Query("SELECT * FROM Usuario WHERE Correo ==:correo")
    public Usuario verificarCuenta(String correo );

    @Query("SELECT * FROM Usuario WHERE Correo==:correo AND Contrasena==:contrasena")
    public Usuario iniciarSesion(String correo, String contrasena );

    @Query("SELECT * FROM Usuario WHERE Activo==:estado")
    public Usuario obtenerSesion( int estado );
}
