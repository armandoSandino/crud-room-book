package com.pineda.bibliotecaon;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pineda.bibliotecaon.Entity.Libro;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoBuscar extends Fragment {

    private ProgressDialog progressDialog;
    private List<Libro> listaPublicacion;
    private RecyclerView recyclerViewPublicacion;
    private RecyclerView.Adapter adapterPublicacion;
    private RecyclerView.LayoutManager layoutManagerPublicacion;
    TextView lb_message_listarpublicFrag;
    public FragmentoBuscar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmento_buscar, container, false);
    }
}
