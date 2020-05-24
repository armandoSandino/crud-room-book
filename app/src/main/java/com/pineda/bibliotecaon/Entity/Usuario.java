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
    @NonNull
    @ColumnInfo(name ="IdUsuario")//Especifique el nombre de la columna en la tabla si desea que sea diferente del nombre de la variable miembro.
    private int idUsuario;
    @NonNull //Indica que un valor de retorno de parámetro, campo o método nunca puede ser nulo.
    @ColumnInfo(name="Nombre")
    private String nombre;

    @ColumnInfo(name = "Apellido")
    @NonNull
    private String Apellido;

    @ColumnInfo(name = "Sexo")
    @NonNull
    private String Sexo;

    @ColumnInfo(name="DNI")
    @NonNull
    private String DNI;

    @ColumnInfo(name = "FechaNacimiento")
    @NonNull
    private String FechaNacimiento;
    @ColumnInfo(name = "Telefono ")
    @NonNull
    private String Telefono;
    @ColumnInfo(name = "Correo")
    @NonNull
    private  String Correo;

    @ColumnInfo(name  ="Direccion")
    @NonNull
    private String Direccion;
    @ColumnInfo(name = "Usuario")
    @NonNull
    private  String Usuario;

    @ColumnInfo(name = "Contraseña")
    @NonNull
    private String Contraseña;
    @ColumnInfo(name = "Activo")
    @NonNull
    private int Activo;

    @Ignore
    public Usuario (){
    }
    public Usuario(int idUsuario, @NonNull String nombre, @NonNull String apellido,
                   @NonNull String sexo, @NonNull String DNI, @NonNull String fechaNacimiento,
                   @NonNull String telefono, @NonNull String correo, @NonNull String direccion,
                   @NonNull String usuario, @NonNull String contraseña, int activo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        Apellido = apellido;
        Sexo = sexo;
        this.DNI = DNI;
        FechaNacimiento = fechaNacimiento;
        Telefono = telefono;
        Correo = correo;
        Direccion = direccion;
        Usuario = usuario;
        Contraseña = contraseña;
        Activo = activo;
    }

    public Usuario( String nombre) {
        this.nombre = nombre;
    }
    //Cada campo que se almacena en la base de datos debe ser público o tener un método "getter".
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }
    @NonNull
    public String getApellido() {
        return Apellido;
    }

    public void setApellido(@NonNull String apellido) {
        Apellido = apellido;
    }

    @NonNull
    public String getSexo() {
        return Sexo;
    }

    public void setSexo(@NonNull String sexo) {
        Sexo = sexo;
    }

    @NonNull
    public String getDNI() {
        return DNI;
    }

    public void setDNI(@NonNull String DNI) {
        this.DNI = DNI;
    }

    @NonNull
    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(@NonNull String fechaNacimiento) {
        FechaNacimiento = fechaNacimiento;
    }

    @NonNull
    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(@NonNull String telefono) {
        Telefono = telefono;
    }

    @NonNull
    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(@NonNull String correo) {
        Correo = correo;
    }

    @NonNull
    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(@NonNull String direccion) {
        Direccion = direccion;
    }

    @NonNull
    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(@NonNull String usuario) {
        Usuario = usuario;
    }

    @NonNull
    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(@NonNull String contraseña) {
        Contraseña = contraseña;
    }

    public int getActivo() {
        return Activo;
    }

    public void setActivo(int activo) {
        Activo = activo;
    }
}
