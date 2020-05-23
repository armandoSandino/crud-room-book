package com.pineda.bibliotecaon.ADAPTER;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.pineda.bibliotecaon.Entity.Libro;
import com.pineda.bibliotecaon.R;
import com.pineda.bibliotecaon.ui.home.HomeFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class LibroRecyclerViewAdapter extends RecyclerView.Adapter<LibroRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Libro> lista;
    private HomeFragment mainActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imvAvatarItemlistarLibroFrag;
        public TextView lbDescripcionItemListarLibroFrag ,
                lbIsbnItemListarLibroFrag ,lbNombreItemListarLibroFrag, lbFechaItemListarLibroFrag;


        public MyViewHolder(View view) {
            super(view);
            imvAvatarItemlistarLibroFrag = view.findViewById(R.id.imvAvatarItemlistarLibroFrag);
            lbNombreItemListarLibroFrag = view.findViewById(R.id.lbNombreItemListarLibroFrag);
            lbDescripcionItemListarLibroFrag = view.findViewById(R.id.lbDescripcionItemListarLibroFrag);
            lbIsbnItemListarLibroFrag = view.findViewById(R.id.lbIsbnItemListarLibroFrag);
            lbFechaItemListarLibroFrag = view.findViewById(R.id.lbFechaItemListarLibroFrag);
        }
    }


    public LibroRecyclerViewAdapter(Context context, ArrayList<Libro> contacts, HomeFragment mainActivity) {
        this.context = context;
        this.lista = contacts;
        this.mainActivity = mainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.libro_lista_item, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final Libro libro = lista.get(position);

        holder.lbDescripcionItemListarLibroFrag.setText(libro.getDescripcion());
        holder.lbNombreItemListarLibroFrag.setText(libro.getNombre() );
        holder.lbIsbnItemListarLibroFrag.setText("ISBN: "+libro.getIsbn() );
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        //String fecha = df.format( libro.getFecha() );
        holder.lbFechaItemListarLibroFrag.setText( libro.getFecha() );
        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mainActivity.onItemClick(true, libro, position);
            }
        });

    }

    @Override
    public int getItemCount() {

        return lista.size();
    }


}