package com.pineda.bibliotecaon.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Cada @Entityclase representa una tabla SQLite.
// Anote su declaración de clase para indicar que es una entidad.
// Puede especificar el nombre de la tabla si desea que sea diferente del nombre de la clase.
@Entity(
        tableName="Usuarios"
)
public class Usuario {
    //Cada entidad necesita una clave primaria. Para mantener las cosas simples, cada palabra actúa como su propia clave principal.
    @PrimaryKey( autoGenerate = true )
    @NonNull
    @ColumnInfo(name ="IdUsuario")//Especifique el nombre de la columna en la tabla si desea que sea diferente del nombre de la variable miembro.
    private int idUsuario;
    @NonNull //Indica que un valor de retorno de parámetro, campo o método nunca puede ser nulo.
    @ColumnInfo(name="Nombre")
    private String nombre;

    public Usuario( String nombre) {
        this.nombre = nombre;
    }
    //Cada campo que se almacena en la base de datos debe ser público o tener un método "getter".
    public int getIdUsuario() { return this.idUsuario; }
    public String getNombre(){ return this.nombre; }
}
