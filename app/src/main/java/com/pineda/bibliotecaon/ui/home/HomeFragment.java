package com.pineda.bibliotecaon.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import com.pineda.bibliotecaon.ADAPTER.LibroRecyclerViewAdapter;
import com.pineda.bibliotecaon.DB.AppDatabase;

import com.pineda.bibliotecaon.ETC.Util;
import com.pineda.bibliotecaon.Entity.Libro;

import com.pineda.bibliotecaon.R;
import com.pineda.bibliotecaon.VIEW.EditarLibro;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private LibroRecyclerViewAdapter adaptador;
    private ArrayList<Libro> lista = new ArrayList<>();
    RecyclerView.LayoutManager mLayoutManager;

    @BindView(R.id.recyListaLibroHomeFrag)
     RecyclerView recyclerView;
    @BindView(R.id.lbMessageListaLibroHomeFrag)
    TextView lbMessageListaLibroHomeFrag;

    private AppDatabase db;
    private static final String TAG="HomeFragmento";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View vista  = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        ButterKnife.bind( this, vista );
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        db = Room.
                databaseBuilder(getContext(), AppDatabase.class, Util.nombreDb).
                allowMainThreadQueries().
                build();
        //obtiene y establece una lista de contactos
        lista.addAll( db.obtenerLibroDAO().listaLibro() );
        agregarDatoReclicador();
        if ( lista.size() <= 0 )
            lbMessageListaLibroHomeFrag.setVisibility(View.VISIBLE);
        else
            lbMessageListaLibroHomeFrag.setVisibility(View.GONE);

        return vista;
    }

    public void onItemClick( boolean b, Libro libro,  int position) {
                            Intent intent = new Intent(  getContext() , EditarLibro.class);
                            intent.putExtra("idLibro",String.valueOf(libro.getId() ) );
                            intent.putExtra("idUsuario", 1 );
                            //intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity( intent );
                            lista.removeAll( lista );
                            lista.clear();

                            lista.addAll( db.obtenerLibroDAO().listaLibro() );
                            agregarDatoReclicador();
    }
    private  void agregarDatoReclicador() {
        adaptador = new LibroRecyclerViewAdapter(  getContext() , lista, HomeFragment.this  );
        mLayoutManager = new LinearLayoutManager( getContext() );
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter( adaptador );
        //nototificamos los cambios al reciclador
        adaptador.notifyDataSetChanged();
    }
}
