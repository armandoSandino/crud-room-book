package com.pineda.bibliotecaon.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//Cada @Entityclase representa una tabla SQLite.
// Anote su declaración de clase para indicar que es una entidad.
// Puede especificar el nombre de la tabla si desea que sea diferente del nombre de la clase.
@Entity(
        tableName="Usuario"
)
public class Usuario {
    //Cada entidad necesita una clave primaria. Para mantener las cosas simples, cada palabra actúa como su propia clave principal.
    @PrimaryKey( autoGenerate = true )
    @ColumnInfo(name ="IdUsuario")//Especifique el nombre de la columna en la tabla si desea que sea diferente del nombre de la variable miembro.
    private long idUsuario;

    @ColumnInfo( name= "IdRol")
    @NonNull
    private int idRol;

    @NonNull //Indica que un valor de retorno de parámetro, campo o método nunca puede ser nulo.
    @ColumnInfo(name="Nombre")
    private String nombre;

    @ColumnInfo(name = "Apellido")
    @NonNull
    private String apellido;

    @ColumnInfo(name = "Correo")
    @NonNull
    private  String correo;

    @ColumnInfo(name = "Contrasena")
    @NonNull
    private String contrasena;

    @ColumnInfo(name = "Activo")
    @NonNull
    private int activo;

    @Ignore
    public Usuario (){
    }
    public Usuario(int idUsuario, int idRol,  String nombre,  String apellido
                  ,  String correo,  String contraseña, int activo) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo  = correo;
        this.contrasena = contraseña;
        this.activo  = activo;
    }

    public Usuario( String nombre) {
        this.nombre = nombre;
    }
    //Cada campo que se almacena en la base de datos debe ser público o tener un método "getter".
    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario( long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }
    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre( String nombre) {
        this.nombre = nombre;
    }

    @NonNull
    public String getApellido() {
        return this.apellido;
    }

    public void setApellido( String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo( String correo) {
         this.correo = correo;
    }

    @NonNull
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena( String contrasena) {
        this.contrasena = contrasena;
    }

    public int getActivo() {
        return this.activo;
    }

    public void setActivo(int activo) {
         this.activo = activo;
    }
}
