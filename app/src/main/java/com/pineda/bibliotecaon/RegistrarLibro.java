package com.pineda.bibliotecaon;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pineda.bibliotecaon.DB.AppDatabase;
import com.pineda.bibliotecaon.DB.ContactAppDatabase;
import com.pineda.bibliotecaon.ETC.Util;
import com.pineda.bibliotecaon.Entity.Libro;
import com.pineda.bibliotecaon.ViewModel.RegistrarLibroViewModel;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrarLibro extends Fragment implements  View.OnClickListener{
    private RegistrarLibroViewModel registrarLibroViewModel;
    @BindView(R.id.bt_registrarLibroForm)
    Button bt_registrarLibroForm;

    @BindView(R.id.entradaNombreRegistrarLibro)
    TextInputLayout entradaNombreRegistrarLibro;
    @BindView(R.id.ctNombreRegistrarLibro)
    TextInputEditText ctNombreRegistrarLibro;

    @BindView(R.id.entradaIsbnRegistrarLibro)
    TextInputLayout entradaIsbnRegistrarLibro;
    @BindView(R.id.ctIsbnRegistrarLibro)
    TextInputEditText ctIsbnRegistrarLibro;

    @BindView(R.id.entradaDescripcionRegistrarLibro)
            TextInputLayout entradaDescripcionRegistrarLibro;
    @BindView(R.id.ctDescripcionRegistrarLibro)
            TextInputEditText ctDescripcionRegistrarLibro;
    @BindView(R.id.lbSubtituloRegistrarLibro)
            TextView lbSubtituloRegistrarLibro;
    @BindView(R.id.lbTituloRegistrarLibro)
            TextView lbTituloRegistrarLibro;

    AppDatabase db;

    public RegistrarLibro() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View view  = inflater.inflate(R.layout.fragment_registrar_libro, container, false);

        registrarLibroViewModel =
                ViewModelProviders.of(this).get(RegistrarLibroViewModel.class);
        View vista = inflater.inflate(R.layout.fragment_registrar_libro, container, false);
        ButterKnife.bind(this, vista );
        registrarLibroViewModel.getlbSubtituloRegistrarLibro().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                lbSubtituloRegistrarLibro.setText( s );
            }
        });
        registrarLibroViewModel.getlbTituloRegistrarLibro().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                lbTituloRegistrarLibro.setText( s );
            }
        });
        db = Room.
                databaseBuilder(getContext(), AppDatabase.class, Util.nombreDb ).
                allowMainThreadQueries().
                build();

        bt_registrarLibroForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make( v , "La información es correcta?", Snackbar.LENGTH_LONG).setAction("SI", new View.OnClickListener() {
                    @Override
                    public void onClick(View vis) {
                        bt_registrarLibroForm.setEnabled( false );
                        if( validarDato() ){
                            Date c = (Date) Calendar.getInstance().getTime();
                            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                            String  formattedDate = df.format(c);
                            Libro libro = new Libro( 0,
                                    ctNombreRegistrarLibro.getText().toString().trim(),
                                    ctIsbnRegistrarLibro.getText().toString().trim(),
                                    ctDescripcionRegistrarLibro.getText().toString().trim(),
                                    formattedDate );
                            long res = db.obtenerLibroDAO().agregarLibro( libro );
                            if ( res > 0 ){
                                limpiarCajas();
                                onCreateDialogMensaje(420, "Libro agregado correctamente.").
                                        show();
                                Navigation.findNavController( v ).navigate(R.id.nav_home);
                            } else {
                                onCreateDialogMensaje(420 , "Algo no salió bien inténtelo nuevamente.")
                                        .show();
                            }
                        }else{
                            onCreateDialogMensaje( 420 ,"\n" +
                                    "Vuelva a intentarlo más tarde\n.").show();
                            Snackbar.make(vis ,"Verifique la información proporcionada ", Snackbar.LENGTH_LONG).show();
                        }
                        bt_registrarLibroForm.setEnabled(true);
                    }
                }).show();
            }
        });

        bindListenerCajas();

        return vista;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onClick(View v) {

    }
    //validamos la informacion
    private boolean validarDato () {
        if( ctNombreRegistrarLibro.getText().toString().trim().isEmpty() ){
            Toast.makeText(getContext(), "Introduzca el nombre del libro", Toast.LENGTH_SHORT).show();
            return  false;
        }
        if( ctIsbnRegistrarLibro.getText().toString().trim().isEmpty() ) {
            Toast.makeText( getContext(), "Introduce el ISBN del libro", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( ctDescripcionRegistrarLibro.getText().toString().trim().isEmpty() ) {
            Toast.makeText( getContext() , "Ingrese la descripción", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    //controlamos lo que el usuario escribe en los TextInputEditeText
    private void bindListenerCajas() {

        ctNombreRegistrarLibro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String val=null;
                if (ctNombreRegistrarLibro.getText().toString().trim().isEmpty()== true  ) {
                    setMessageTextInputLayoutError( entradaNombreRegistrarLibro ,"Introduce el nombre del libro");
                } else {
                    setMessageTextInputLayoutError( entradaNombreRegistrarLibro ,val );
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                //Toast.makeText(RegistrarVehiculo.this, " se termino de escribir ", Toast.LENGTH_SHORT).show();
            }
        });
        ctIsbnRegistrarLibro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String val=null;
                if (ctIsbnRegistrarLibro.getText().toString().trim().isEmpty() == true  ) {
                    setMessageTextInputLayoutError( entradaIsbnRegistrarLibro ,"Introduce el ISBN del libro");
                } else {
                    setMessageTextInputLayoutError( entradaIsbnRegistrarLibro ,val );
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        ctDescripcionRegistrarLibro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String val=null;
                if (ctDescripcionRegistrarLibro.getText().toString().trim().isEmpty() == true  ) {
                    setMessageTextInputLayoutError( entradaDescripcionRegistrarLibro ,"Ingrese la descripción");
                } else {
                    setMessageTextInputLayoutError( entradaDescripcionRegistrarLibro ,val );
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
        ctNombreRegistrarLibro.setText("");
        ctIsbnRegistrarLibro.setText("");
        ctDescripcionRegistrarLibro.setText("");
        setMessageTextInputLayoutError( entradaNombreRegistrarLibro, null );
        setMessageTextInputLayoutError( entradaIsbnRegistrarLibro, null );
        setMessageTextInputLayoutError( entradaDescripcionRegistrarLibro, null );
    }
}
