package com.pineda.bibliotecaon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pineda.bibliotecaon.DB.AppDatabase;
import com.pineda.bibliotecaon.ETC.Util;
import com.pineda.bibliotecaon.Entity.Rol;
import com.pineda.bibliotecaon.Entity.Usuario;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btAccederLogin)
    Button btAccederLogin;
    @BindView(R.id.entradaUsuarioLogin)
    TextInputLayout entradaUsuarioLogin;
    @BindView(R.id.ctUsuarioLogin)
    TextInputEditText ctUsuarioLogin;
    @BindView(R.id.entradaPasswordLogin)
    TextInputLayout entradaPasswordLogin;
    @BindView(R.id.ctPasswordLogin)
    TextInputEditText ctPasswordLogin;


    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind( this );
        db = Room.databaseBuilder( getApplicationContext() ,AppDatabase.class, Util.nombreDb).
                allowMainThreadQueries()
                .build();
        agregarRol();
        agregarCuentaAdministrador();

        btAccederLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity( new Intent( Login.this, Principal.class ) );
                btAccederLogin.setEnabled( false );
                if ( validarDato() ) {
                    iniciarSesion();
                }
                btAccederLogin.setEnabled( true );
            }
        });
        bindListenerCajas();
    }

    @Override
    public void onClick(View v) {

    }
    private void iniciarSesion() {
        Usuario dato = db.obtenerUsuarioDAO().iniciarSesion(
                ctUsuarioLogin.getText().toString().trim().toLowerCase(),
                ctPasswordLogin.getText().toString().trim()
        );
        if ( dato != null ) {
            dato.setActivo(1);
            int res = db.obtenerUsuarioDAO().actualizarUsuario( dato );
            if ( res > 0 ) {
                Intent intent = new Intent(  getApplicationContext() , Principal.class);
                intent.putExtra("idUsuario", dato.getIdUsuario() );
                intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity( intent );
            }
        } else {
            onCreateDialogMensaje(345,"Credenciales incorrectas").show();
        }
    }
    private void agregarCuentaAdministrador() {
        Usuario user = db.obtenerUsuarioDAO().verificarCuenta("admin@biblioteca.com");
        if (  user == null ) {
            user = new  Usuario(0, 1 ,"administrador","pineda","admin@biblioteca.com","123456",
                    0);
            long res = db.obtenerUsuarioDAO().agregarUsuario( user );
        } else
            Toast.makeText(this, "el usuario ya existe", Toast.LENGTH_SHORT).show();
    }
    //inserta todos los roles disponibles en la app
    private void agregarRol() {
        List<Rol> lista = db.obtenerRolDAO().listaRol();
        if ( lista == null ){
            int con = 0;
            while( con >= 2 ) {
                Rol rol =  null ;
                if( con == 0)
                    rol = new Rol( (con+1) , "Administrador", "Administrador de la app.");
                if ( con == 1 )
                    rol = new Rol( (con+1), "Invitado", "Invitado de la app.");
                db.obtenerRolDAO().agregarRol( rol );
                con++;
            }
        }
    }
    //validamos la informacion
    private boolean validarDato () {
        if( ctUsuarioLogin.getText().toString().trim().isEmpty() ){
            Toast.makeText(getApplicationContext(), "Introduzca el correo", Toast.LENGTH_SHORT).show();
            return  false;
        }
        if( ctPasswordLogin.getText().toString().trim().isEmpty() ){
            Toast.makeText(getApplicationContext(), "Introduzca la contraseña", Toast.LENGTH_SHORT).show();
            return  false;
        }
        return true;
    }
    //controlamos lo que el usuario escribe en los TextInputEditeText
    private void bindListenerCajas() {
        ctUsuarioLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String val=null;
                if (ctUsuarioLogin.getText().toString().trim().isEmpty()== true  ) {
                    setMessageTextInputLayoutError( entradaUsuarioLogin ,"Introduzca el correo");
                } else {
                    setMessageTextInputLayoutError( entradaUsuarioLogin ,val );
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        ctPasswordLogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String val=null;
                if (ctUsuarioLogin.getText().toString().trim().isEmpty()== true  ) {
                    setMessageTextInputLayoutError( entradaUsuarioLogin ,"Introduzca la contraseña");
                } else {
                    setMessageTextInputLayoutError( entradaUsuarioLogin ,val );
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    //mostramos y ocultamos los mensajes de error en los TextInputLayout
    private  void setMessageTextInputLayoutError(@NonNull TextInputLayout textInputLayout, String msg) {
        textInputLayout.setError(msg);
        if (msg == null) {
            textInputLayout.setErrorEnabled(false);
        } else {
            textInputLayout.setErrorEnabled(true);
        }
    }
    // llamar con :  onCreateDialog(4).show();
    protected Dialog onCreateDialogMensaje(int code, String contenido ) {
        Dialog dialogBuilder = null;
        AlertDialog.Builder builder = new AlertDialog.Builder( getApplicationContext()  );
        //builder = builder.setTitle("Biblioteca");
        builder = builder.setIcon(R.drawable.libro520px);
        //error de conexion
        if(code == 0){
            builder = builder.setTitle("No tienes conexion a internet");
            builder = builder.setMessage(contenido);
        }else if(code == -1 ){// mensaje de error
            builder = builder.setMessage(contenido);
        }else{ // mensaje de notificacion
            builder = builder.setTitle("Notificación ");
            builder = builder.setMessage(contenido);
        }
        builder.setPositiveButton("ENTIENDO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogBuilder = builder.create();
        return dialogBuilder;
    }

    //ciclo de vida
    //cuando de clic en regresar
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

}
