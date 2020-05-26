package com.pineda.bibliotecaon.ETC;

public class Util {
    public  static final String nombreDb = "libroDB";
    public Util(){}
    public static boolean compararContraseña(String uno, String dos ) {
        if( uno.equals(dos) ) {
            return true;
        }
        return false;
    }
}
