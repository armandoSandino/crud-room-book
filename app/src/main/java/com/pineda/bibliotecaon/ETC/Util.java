package com.pineda.bibliotecaon.ETC;

import android.content.Context;

import androidx.room.Room;

import com.pineda.bibliotecaon.DB.AppDatabase;

public class Util {
    public  static final String nombreDb = "DB";
    public Util(){}
    public static boolean compararContraseña(String uno, String dos ) {
        if( uno.equals(dos) ) {
            return true;
        }
        return false;
    }
}
