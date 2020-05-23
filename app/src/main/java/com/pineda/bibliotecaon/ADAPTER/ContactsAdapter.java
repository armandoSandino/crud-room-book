package com.pineda.bibliotecaon.ADAPTER;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;


import com.pineda.bibliotecaon.Entity.ContactRoom;
import com.pineda.bibliotecaon.MainActivity;
import com.pineda.bibliotecaon.R;

import java.util.ArrayList;


public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<ContactRoom> contactssList;
    private MainActivity mainActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView emil;


        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            emil = view.findViewById(R.id.email);

        }
    }


    public ContactsAdapter(Context context, ArrayList<ContactRoom> contacts, MainActivity mainActivity) {
        this.context = context;
        this.contactssList = contacts;
        this.mainActivity = mainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final ContactRoom contact = contactssList.get(position);

        holder.name.setText(contact.getName());
        holder.emil.setText(contact.getEmail());

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

               // mainActivity.addAndEditContacts(true, contact, position);
            }
        });*/

    }

    @Override
    public int getItemCount() {

        return contactssList.size();
    }


}
