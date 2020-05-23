package com.pineda.bibliotecaon.VIEW;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.pineda.bibliotecaon.R;

public class EditarLibro extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_libro);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case 1: break;
            default: break;
        }
    }
}
