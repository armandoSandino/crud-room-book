package com.pineda.bibliotecaon.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity( tableName = "Rol")
public class Rol {
    @ColumnInfo(name = "IdRol")
    @NonNull
    @PrimaryKey( autoGenerate = true)
    private int idRol;

    @ColumnInfo(name = "Nombre")
    @NonNull
    private String nombre;

    @ColumnInfo(name = "Descripcion")
    @NonNull
    private String descripcion;

    @Ignore
    public Rol() {}
    public Rol(int idRol, String nombre, String descripcion) {
        this.idRol = idRol;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
