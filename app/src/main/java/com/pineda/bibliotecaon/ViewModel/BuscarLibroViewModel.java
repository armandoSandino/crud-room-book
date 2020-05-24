
package com.pineda.bibliotecaon.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BuscarLibroViewModel extends ViewModel {
    private MutableLiveData<String> lbTituloBuscarLibro;
    private  MutableLiveData<String> lbSubTituloBuscarLibro;

    public BuscarLibroViewModel() {
        lbTituloBuscarLibro = new MutableLiveData<>();
        lbTituloBuscarLibro.setValue("Buscar libro");

        lbSubTituloBuscarLibro = new MutableLiveData<>();
        lbSubTituloBuscarLibro.setValue("Puedes especificar el nombre o ISBN de un libro para poder buscarlo");
    }
    public LiveData<String> getlbTituloBuscarLibro() {
        return lbTituloBuscarLibro;
    }

    public LiveData<String> getlbSubTituloBuscarLibro() {
        return lbSubTituloBuscarLibro;
    }
}