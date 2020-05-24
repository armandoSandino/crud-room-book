package com.pineda.bibliotecaon;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pineda.bibliotecaon.ADAPTER.LibroRecyclerViewAdapter;
import com.pineda.bibliotecaon.DB.AppDatabase;
import com.pineda.bibliotecaon.ETC.Util;
import com.pineda.bibliotecaon.Entity.Libro;
import com.pineda.bibliotecaon.ViewModel.BuscarLibroViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoBuscar extends Fragment {

    private BuscarLibroViewModel buscarViewModel;

    private RecyclerView.LayoutManager layoutManager;

    private LibroRecyclerViewAdapter adaptador;
    private ArrayList<Libro> lista = new ArrayList<>();
    private AppDatabase db;

    @BindView(R.id.lbTituloBuscarLibro)
        TextView lbTituloBuscarLibro;
    @BindView(R.id.lbSubTituloBuscarLibro)
        TextView lbSubTituloBuscarLibro;
    @BindView(R.id.recyListaLibroBuscarFrag)
        RecyclerView recyListaLibroBuscarFrag;

    @BindView(R.id.entradaTerminoBuscarLibro)
    TextInputLayout entradaTerminoBuscarLibro;
    @BindView(R.id.ctTerminoBuscarLibro)
    TextInputEditText ctTerminoBuscarLibro;

    @BindView(R.id.imbBuscarFrag)
    android.widget.ImageButton imageButton;

    public FragmentoBuscar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        buscarViewModel = ViewModelProviders.of( this).get(BuscarLibroViewModel.class);
        // Inflate the layout for this fragment
       View vista = inflater.inflate(R.layout.fragment_fragmento_buscar, container, false);
        ButterKnife.bind( this, vista);
        buscarViewModel.getlbTituloBuscarLibro().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                lbTituloBuscarLibro.setText( s);
            }
        });
        buscarViewModel.getlbSubTituloBuscarLibro().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                lbSubTituloBuscarLibro.setText( s );
            }
        });
        db = Room.
                databaseBuilder(getContext(), AppDatabase.class, Util.nombreDb ).
                allowMainThreadQueries().
                build();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageButton.setEnabled( false );
                if( validarDato() ){
                    Libro libro  = db.obtenerLibroDAO().buscarLibro(
                            ctTerminoBuscarLibro.getText().toString().trim(),
                            ctTerminoBuscarLibro.getText().toString().trim()
                    );
                    if ( libro == null )
                        onCreateDialogMensaje(123, "libro no encontrado ").show();
                    else {
                        onCreateDialogMensaje(1234, "libro encontrado").show();
                        lista = new ArrayList<>();
                        lista.add( libro );
                        agregarDatoReclicador( lista );
                    }

                }else{
                    onCreateDialogMensaje( 420 ,"\n" +
                            "Vuelva a intentarlo más tarde\n.").show();
                    Snackbar.make( v ,"Verifique la información proporcionada ", Snackbar.LENGTH_LONG).show();
                }
                imageButton.setEnabled(true);
            }
        });
        bindListenerCajas();
       return vista;
    }
    public void onItemClick( boolean b, Libro libro,  int position) {
        /*Intent intent = new Intent(  getContext() , EditarLibro.class);
        intent.putExtra("idLibro",String.valueOf(libro.getId() ) );
        intent.putExtra("idUsuario", 1 );
        //intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity( intent );
        lista.removeAll( lista );
        lista.clear();

        lista.addAll( db.obtenerLibroDAO().listaLibro() );
        agregarDatoReclicador();*/
    }
    private  void agregarDatoReclicador( ArrayList<Libro> listaResultado ) {
        adaptador = new LibroRecyclerViewAdapter(  getContext() , listaResultado ,
                FragmentoBuscar.this  );

        layoutManager = new LinearLayoutManager( getContext() );
        recyListaLibroBuscarFrag.setLayoutManager( layoutManager );
        recyListaLibroBuscarFrag.setItemAnimator(new DefaultItemAnimator());
        recyListaLibroBuscarFrag.setAdapter( adaptador );
        //nototificamos los cambios al reciclador
        adaptador.notifyDataSetChanged();
    }
    //validamos la informacion
    private boolean validarDato () {
        if( ctTerminoBuscarLibro.getText().toString().trim().isEmpty() ){
            Toast.makeText(getContext(), "Introduzca el término de búsqueda", Toast.LENGTH_SHORT).show();
            return  false;
        }
        return true;
    }
    //controlamos lo que el usuario escribe en los TextInputEditeText
    private void bindListenerCajas() {
        ctTerminoBuscarLibro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String val=null;
                if (ctTerminoBuscarLibro.getText().toString().trim().isEmpty()== true  ) {
                    setMessageTextInputLayoutError( entradaTerminoBuscarLibro ,"Introduzca el término de búsqueda");
                } else {
                    setMessageTextInputLayoutError( entradaTerminoBuscarLibro ,val );
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                //Toast.makeText(RegistrarVehiculo.this, " se termino de escribir ", Toast.LENGTH_SHORT).show();
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

}
