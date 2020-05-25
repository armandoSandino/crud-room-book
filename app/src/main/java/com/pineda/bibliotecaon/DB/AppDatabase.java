package com.pineda.bibliotecaon.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.pineda.bibliotecaon.DAO.HistorialContrasenaUsuarioDAO;
import com.pineda.bibliotecaon.DAO.LibroDAO;
import com.pineda.bibliotecaon.DAO.RolDAO;
import com.pineda.bibliotecaon.DAO.UsuarioDAO;
import com.pineda.bibliotecaon.Entity.HistorialContrasenaUsuario;
import com.pineda.bibliotecaon.Entity.Libro;
import com.pineda.bibliotecaon.Entity.Rol;
import com.pineda.bibliotecaon.Entity.Usuario;

@Database( entities = {Libro.class, Usuario.class, Rol.class, HistorialContrasenaUsuario.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public  abstract LibroDAO obtenerLibroDAO();
    public abstract UsuarioDAO obtenerUsuarioDAO();
    public abstract RolDAO obtenerRolDAO();
    public abstract HistorialContrasenaUsuarioDAO obtenerHistorialContrasenaUsuarioDAO();
}

