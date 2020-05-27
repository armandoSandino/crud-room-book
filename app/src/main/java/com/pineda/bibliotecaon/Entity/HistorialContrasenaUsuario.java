package com.pineda.bibliotecaon.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity( tableName = "HistorialContrasenaUsuario")
public class HistorialContrasenaUsuario {
    @ColumnInfo(name = "IdHistorialContrasenaUsuario")
    @NonNull
    @PrimaryKey( autoGenerate = true)
    private long idHistorialContrasenaUsuario;
    @ColumnInfo(name = "IdUsuario")
    @NonNull
    private long idUsuario;
    @ColumnInfo(name = "Contrasena")
    @NonNull
    private String contrasena;
    @ColumnInfo( name = "FechaCreacion")
    @NonNull
    private String fechaCreacion;

    public HistorialContrasenaUsuario(long idHistorialContrasenaUsuario, long idUsuario, @NonNull String contrasena, @NonNull String fechaCreacion) {
        this.idHistorialContrasenaUsuario = idHistorialContrasenaUsuario;
        this.idUsuario = idUsuario;
        this.contrasena = contrasena;
        this.fechaCreacion = fechaCreacion;
    }

    @Ignore
    public HistorialContrasenaUsuario(){
    }

    public long getIdHistorialContrasenaUsuario() {
        return idHistorialContrasenaUsuario;
    }

    public void setIdHistorialContrasenaUsuario(long idHistorialContrasenaUsuario) {
        this.idHistorialContrasenaUsuario = idHistorialContrasenaUsuario;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @NonNull
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(@NonNull String contrasena) {
        this.contrasena = contrasena;
    }

    @NonNull
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(@NonNull String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
