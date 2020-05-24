package com.pineda.bibliotecaon.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegistrarLibroViewModel extends ViewModel {
    private MutableLiveData<String> lbSubtituloRegistrarLibro;
    private  MutableLiveData<String>lbTituloRegistrarLibro;

    public RegistrarLibroViewModel() {
        lbSubtituloRegistrarLibro = new MutableLiveData<>();
        lbSubtituloRegistrarLibro.setValue("Información del libro");
        lbTituloRegistrarLibro = new MutableLiveData<>();
        lbTituloRegistrarLibro.setValue("Registrar libro");
    }
    public LiveData<String> getlbSubtituloRegistrarLibro() {
        return lbSubtituloRegistrarLibro;
    }
    public LiveData<String> getlbTituloRegistrarLibro() {
        return lbTituloRegistrarLibro;
    }
}