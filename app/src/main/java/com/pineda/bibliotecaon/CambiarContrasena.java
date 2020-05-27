package com.pineda.bibliotecaon;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pineda.bibliotecaon.DB.AppDatabase;
import com.pineda.bibliotecaon.ETC.Util;
import com.pineda.bibliotecaon.Entity.HistorialContrasenaUsuario;
import com.pineda.bibliotecaon.Entity.Libro;
import com.pineda.bibliotecaon.Entity.Usuario;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class CambiarContrasena extends Fragment {

    @BindView(R.id.entradaRepetirContraseña)
    TextInputLayout entradaRepetirContraseña;

    @BindView(R.id.ctRepetirContraseña)
    TextInputEditText ctRepetirContraseña;

    @BindView(R.id.entradaContraseña)
    TextInputLayout entradaContraseña;

    @BindView(R.id.ctContraseña)
    TextInputEditText ctContraseña;

    @BindView(R.id.btcambiarContraseñaForm)
    Button btcambiarContraseñaForm;

    AppDatabase db;

    public CambiarContrasena() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_cambiar_contrasena, container, false);
        ButterKnife.bind( this, vista );
        db = AppDatabase.obtenerBaseDeDato( getContext() );
        btcambiarContraseñaForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarContraseña( v );
            }
        });
        bindListenerCajas( );

        return vista;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private void actualizarContraseña( View v ) {
        Snackbar.make( v , "Esta seguro?", Snackbar.LENGTH_LONG).setAction("SI", new View.OnClickListener() {
            @Override
            public void onClick(View vis) {
                btcambiarContraseñaForm.setEnabled( false );
                if( validarDato() ){
                    Usuario user = db.obtenerUsuarioDAO().obtenerSesion( 1 );
                    if (  user != null) {

                        user.setContrasena( ctRepetirContraseña.getText().toString().trim() );
                        db.obtenerUsuarioDAO().updateUsuario( user );

                        Date c = (Date) Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                        String  formattedDate = df.format(c);
                        HistorialContrasenaUsuario  his = new
                                HistorialContrasenaUsuario( 0 , user.getIdUsuario(),
                                user.getContrasena(),
                                formattedDate
                        );
                        long respuesta = db.obtenerHistorialContrasenaUsuarioDAO().
                                agregarHistorialContrasenaUsuario( his );
                        if ( respuesta > 0 ) {
                            limpiarCajas();
                            onCreateDialogMensaje(420, "Contraseña actualizada..").
                                    show();
                        } else {
                            onCreateDialogMensaje(420, "Intentelo nuevamente.\n").
                                    show();
                        }

                    }
                }else{
                    onCreateDialogMensaje( 420 ,"\n" +
                            "Vuelva a intentarlo más tarde\n.").show();
                    Snackbar.make(vis ,"Verifique la información proporcionada ", Snackbar.LENGTH_LONG).show();
                }
                btcambiarContraseñaForm.setEnabled(true);
            }
        }).show();
    }

    //validamos la informacion
    private boolean validarDato () {
        if( ctContraseña.getText().toString().trim().isEmpty() ){
            Toast.makeText(getContext(), "Introduzca la contraseña", Toast.LENGTH_SHORT).show();
            return  false;
        }
        if( ctRepetirContraseña.getText().toString().trim().isEmpty() ) {
            Toast.makeText( getContext(), "Repita nuevamente la contraseña", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( !Util.compararContraseña(ctContraseña.getText().toString().trim().toLowerCase(),
                ctRepetirContraseña.getText().toString().trim().toLowerCase()
                ) ) {
            Toast.makeText( getContext(), "Las contraseña no coinciden", Toast.LENGTH_SHORT).show();
            return  false;
        }
        return true;
    }
    //controlamos lo que el usuario escribe en los TextInputEditeText
    private void bindListenerCajas() {

        ctContraseña.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String val=null;
                if (ctContraseña.getText().toString().trim().isEmpty()== true  ) {
                    setMessageTextInputLayoutError( entradaContraseña ,"Ingresa la contraseña");
                } else {
                    setMessageTextInputLayoutError( entradaContraseña ,val );
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ctRepetirContraseña.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String val=null;
                if (ctRepetirContraseña.getText().toString().trim().isEmpty() == true  ) {
                    setMessageTextInputLayoutError( entradaRepetirContraseña ,"Repita la contraseña");
                } else {
                    setMessageTextInputLayoutError( entradaRepetirContraseña ,val );
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                String val=null;
                if ( !Util.compararContraseña(
                        ctContraseña.getText().toString().trim().toLowerCase(),
                        ctRepetirContraseña.getText().toString().trim().toLowerCase()
                )) {
                    setMessageTextInputLayoutError( entradaRepetirContraseña ,"Las contraseñas no coinciden");
                } else {
                    setMessageTextInputLayoutError( entradaRepetirContraseña ,val );
                }
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
        AlertDialog.Builder builder = new AlertDialog.Builder( getContext()  );
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
    //limpiar cajas
    private void limpiarCajas() {
        ctContraseña.setText("");
        ctRepetirContraseña.setText("");
        setMessageTextInputLayoutError( entradaContraseña, null );
        setMessageTextInputLayoutError( entradaRepetirContraseña, null );
    }
}
