package com.pineda.bibliotecaon.VIEW;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pineda.bibliotecaon.DB.AppDatabase;
import com.pineda.bibliotecaon.ETC.Util;
import com.pineda.bibliotecaon.Entity.Libro;
import com.pineda.bibliotecaon.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditarLibro extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.btEditarLibroForm)
    Button btEditarLibroForm;

    @BindView(R.id.entradaDescripcionEditarLibro)
    TextInputLayout entradaDescripcionEditarLibro;
    @BindView(R.id.ctDescripcionEditarLibro)
    TextInputEditText ctDescripcionEditarLibro;

    @BindView(R.id.entradaIsbnEditarLibro)
    TextInputLayout entradaIsbnEditarLibro;
    @BindView(R.id.ctIsbnEditarLibro)
    TextInputEditText ctIsbnEditarLibro;

    @BindView(R.id.entradaNombreEditarLibro)
    TextInputLayout entradaNombreEditarLibro;
    @BindView(R.id.ctNombreEditarLibro)
    TextInputEditText ctNombreEditarLibro;

    @BindView(R.id.lbSubtituloEditarLibro)
    TextView lbSubtituloEditarLibro;
    @BindView(R.id.lbTituloEditarLibro)
    TextView lbTituloEditarLibro;
    private int idLibro;
    private int idUsuario;
    private AppDatabase db;
    Libro dato = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_libro);
        ButterKnife.bind( this );
        Intent in  = getIntent();
        idLibro = Integer.valueOf( in.getStringExtra("idLibro") );
        idUsuario = in.getIntExtra("idUsuario",0);
        db = Room.databaseBuilder( this , AppDatabase.class, Util.nombreDb).
                allowMainThreadQueries()
                .build();
        obtenerInformacion( idLibro );
        bindListenerCajas();
        btEditarLibroForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make( v , "La información es correcta?", Snackbar.LENGTH_LONG).setAction("SI", new View.OnClickListener() {
                    @Override
                    public void onClick(View vis) {
                        btEditarLibroForm.setEnabled( false );
                        if( validarDato() ){
                            Libro libro = new Libro( dato.getId(),
                                    ctNombreEditarLibro.getText().toString().trim(),
                                    ctIsbnEditarLibro.getText().toString().trim(),
                                    ctDescripcionEditarLibro.getText().toString().trim(),
                                    dato.getFecha() );
                            long res = db.obtenerLibroDAO().actualizarLibro( libro );
                            if ( res > 0 ){
                                limpiarCajas();
                                onCreateDialogMensaje(420, "Libro actualizado correctamente.").
                                        show();
                            } else {
                                onCreateDialogMensaje(420 , "Algo no salió bien inténtelo nuevamente.")
                                        .show();
                            }
                        }else{
                            Snackbar.make(vis ,"Verifique la información proporcionada ", Snackbar.LENGTH_LONG).show();
                        }
                        btEditarLibroForm.setEnabled(true);
                    }
                }).show();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case 1: break;
            default: break;
        }
    }
    private final  void obtenerInformacion( int id ) {
       dato = db.obtenerLibroDAO().obtenerLibro( id );
       if ( dato == null ){
           btEditarLibroForm.setEnabled( false );
           return;
       }
       ctNombreEditarLibro.setText( dato.getNombre() );
       ctIsbnEditarLibro.setText( dato.getIsbn() );
       ctDescripcionEditarLibro.setText( dato.getDescripcion() );
    }
    //validamos la informacion
    private boolean validarDato () {
        if( ctNombreEditarLibro.getText().toString().trim().isEmpty() ){
            Toast.makeText( getApplicationContext() , "Introduzca el nombre del libro", Toast.LENGTH_SHORT).show();
            return  false;
        }
        if( ctIsbnEditarLibro.getText().toString().trim().isEmpty() ) {
            Toast.makeText( getApplicationContext(), "Introduce el ISBN del libro", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( ctDescripcionEditarLibro.getText().toString().trim().isEmpty() ) {
            Toast.makeText( getApplicationContext() , "Ingrese la descripción", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    //controlamos lo que el usuario escribe en los TextInputEditeText
    private void bindListenerCajas() {

        ctNombreEditarLibro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String val=null;
                if (ctNombreEditarLibro.getText().toString().trim().isEmpty()== true  ) {
                    setMessageTextInputLayoutError( entradaNombreEditarLibro ,"Introduce el nombre del libro");
                } else {
                    setMessageTextInputLayoutError( entradaNombreEditarLibro ,val );
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                //Toast.makeText(RegistrarVehiculo.this, " se termino de escribir ", Toast.LENGTH_SHORT).show();
            }
        });
        ctIsbnEditarLibro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String val=null;
                if (ctIsbnEditarLibro.getText().toString().trim().isEmpty() == true  ) {
                    setMessageTextInputLayoutError( entradaIsbnEditarLibro ,"Introduce el ISBN del libro");
                } else {
                    setMessageTextInputLayoutError( entradaIsbnEditarLibro  ,val );
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        ctDescripcionEditarLibro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String val=null;
                if (ctDescripcionEditarLibro.getText().toString().trim().isEmpty() == true  ) {
                    setMessageTextInputLayoutError( entradaDescripcionEditarLibro ,"Ingrese la descripción");
                } else {
                    setMessageTextInputLayoutError( entradaDescripcionEditarLibro ,val );
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

    //limpiar cajas
    private void limpiarCajas() {
        setMessageTextInputLayoutError( entradaNombreEditarLibro, null );
        setMessageTextInputLayoutError( entradaIsbnEditarLibro, null );
        setMessageTextInputLayoutError( entradaDescripcionEditarLibro, null );
        ctDescripcionEditarLibro.setText("");
        ctIsbnEditarLibro.setText("");
        ctNombreEditarLibro.setText("");
    }
    // llamar con :  onCreateDialog(4).show();
    protected Dialog onCreateDialogMensaje(int code, String contenido ) {
        Dialog dialogBuilder = null;
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        //builder = builder.setTitle("Biblioteca");
        builder = builder.setIcon(R.drawable.libro520px);
        builder.setCancelable( false );

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
                finish();
            }
        });
        dialogBuilder = builder.create();
        return dialogBuilder;
    }
}
