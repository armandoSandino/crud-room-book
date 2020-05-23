package com.pineda.bibliotecaon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pineda.bibliotecaon.ADAPTER.ContactsAdapter;
import com.pineda.bibliotecaon.DB.ContactAppDatabase;
import com.pineda.bibliotecaon.Entity.ContactRoom;

import java.util.ArrayList;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private ContactAppDatabase contactAppDatabase;
    private ContactsAdapter contactsAdapter;
    private ArrayList<ContactRoom> lista = new ArrayList<ContactRoom>();
    private RecyclerView recyclerView;
    private static final String TAG="MainActivityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" Contacts Manager ");

        recyclerView = findViewById(R.id.recycler_view_contacts);
        contactAppDatabase = Room.
                databaseBuilder(getApplicationContext(), ContactAppDatabase.class,"ContactDB").
                allowMainThreadQueries().
                build();
        //obtiene y establece una lista de contactos
        lista.addAll( contactAppDatabase.getContactDAO().getContacts() );



        //new GetAllContactsAsyncTask().execute();

        contactsAdapter = new ContactsAdapter(this, lista, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactsAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addAndEditContacts(false, null, -1);
            }


        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_configuracion) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
