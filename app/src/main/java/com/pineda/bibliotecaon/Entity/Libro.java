package com.pineda.bibliotecaon.Entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import java.util.Date;

import javax.xml.namespace.QName;

@Entity (
        tableName ="Libro"
)
public class Libro {
    @ColumnInfo(name = "Nombre")
    @NonNull
    private String nombre;

    @ColumnInfo(name ="Isbn")
    @NonNull
    private String isbn;

    @ColumnInfo(name = "Descripcion")
    @NonNull
    private String descripcion;

    @ColumnInfo(name = "FechaRegistrado")
    @NonNull
    private String fecha;

    @ColumnInfo( name="IdLibro")
    @NonNull
    @PrimaryKey( autoGenerate = true)
    private long id;

    @Ignore
    public  Libro() { }
    public Libro( long id, String nombre, String isbn, String descripcion, String fecha ) {
        this.nombre = nombre;
        this.isbn =  isbn;
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @NonNull
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(@NonNull String descripcion) {
        this.descripcion = descripcion;
    }
    @NonNull
    public String getFecha() {
        return fecha;
    }

    public void setFecha(@NonNull String fecha) {
        this.fecha = fecha;
    }
}
