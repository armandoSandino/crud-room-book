package com.pineda.bibliotecaon.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.pineda.bibliotecaon.DAO.HistorialContrasenaUsuarioDAO;
import com.pineda.bibliotecaon.DAO.LibroDAO;
import com.pineda.bibliotecaon.DAO.RolDAO;
import com.pineda.bibliotecaon.DAO.UsuarioDAO;
import com.pineda.bibliotecaon.ETC.Util;
import com.pineda.bibliotecaon.Entity.HistorialContrasenaUsuario;
import com.pineda.bibliotecaon.Entity.Libro;
import com.pineda.bibliotecaon.Entity.Rol;
import com.pineda.bibliotecaon.Entity.Usuario;

@Database( entities = {Libro.class, Usuario.class, Rol.class, HistorialContrasenaUsuario.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE =  null;

    public  abstract LibroDAO obtenerLibroDAO();
    public abstract UsuarioDAO obtenerUsuarioDAO();
    public abstract RolDAO obtenerRolDAO();
    public abstract HistorialContrasenaUsuarioDAO obtenerHistorialContrasenaUsuarioDAO();
    /*static AppDatabase obtenerBaseDeDato( Context contexto ) {
        if( INSTANCE == null ) {
            synchronized (AppDatabase.class){
                if( INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder( contexto.getApplicationContext(),AppDatabase.class, Util.nombreDb )
                    .build();
                }
            }
        }
        return INSTANCE;
    }*/
    public static AppDatabase obtenerBaseDeDato(Context contexto ) {
        if( INSTANCE == null) {
            INSTANCE = Room.databaseBuilder( contexto , AppDatabase.class, Util.nombreDb )
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}

