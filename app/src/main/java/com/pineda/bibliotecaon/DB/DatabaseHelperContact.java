package com.pineda.bibliotecaon.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.pineda.bibliotecaon.Entity.Contact;

import java.util.ArrayList;

public class DatabaseHelperContact extends SQLiteOpenHelper {
    private static  final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contact_db";
    public DatabaseHelperContact(@Nullable Context context ) {
        super(context, DATABASE_NAME ,  null , DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contact.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Contact.TABLE_NAME );
        onCreate( db );
    }
    public long insertContact( String name , String email ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(Contact.COLUMN_NAME , name );
        valores.put(Contact.COLUMN_EMAIL , email );
        long res = db.insert(Contact.TABLE_NAME , null, valores );
        db.close();
        return res;
    }
    public Contact getContact( long id ) {
        Contact contacto = null;
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(Contact.TABLE_NAME ,
                    new String[]{Contact.COLUMN_ID, Contact.COLUMN_NAME,Contact.COLUMN_EMAIL },
                    Contact.COLUMN_ID+"=?",
                    new String[]{ String.valueOf(id)}, null,null,null,null
                    );
            if ( cursor != null ) {
                cursor.moveToFirst();
            }
            contacto = new Contact(
                    cursor.getString( cursor.getColumnIndex(Contact.COLUMN_NAME ) ),
                    cursor.getString( cursor.getColumnIndex(Contact.COLUMN_EMAIL)),
                    cursor.getInt( cursor.getColumnIndex(Contact.COLUMN_ID ))
            );
            cursor.close();
            db.close();
        }catch ( Exception ex ){
            System.err.println(" error " + ex.getMessage() + ex.getStackTrace() );
            ex.printStackTrace();
        } finally {
        }
        return contacto;
    }
    public ArrayList<Contact> getAllContact() {
        ArrayList<Contact> lista = new ArrayList<Contact>();
        try {
            String sql = "SELECT * FROM "+ Contact.TABLE_NAME+ " ORDER BY "+ Contact.COLUMN_ID +" DESC ";
            SQLiteDatabase db  = this.getWritableDatabase();
            Cursor cursor = db.rawQuery( sql , null);
            if( cursor.moveToFirst() ) {
                do {
                Contact item = new Contact();
                item.setId( cursor.getInt( cursor.getColumnIndex(Contact.COLUMN_ID) ));
                item.setName( cursor.getString( cursor.getColumnIndex(Contact.COLUMN_NAME) ));
                item.setEmail( cursor.getString( cursor.getColumnIndex(Contact.COLUMN_EMAIL) ));
                lista.add( item );
                } while( cursor.moveToNext() );
            }
            cursor.close();
            db.close();
        }catch ( Exception ex ){
            System.err.println(" error " + ex.getMessage() + ex.getStackTrace() );
            ex.printStackTrace();
        }finally {
        }
        return lista;
    }
    public int updateContact (Contact contacto ) {
        int res = -1;
        try {
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues valores = new ContentValues();
         valores.put( Contact.COLUMN_NAME, contacto.getName() );
         valores.put( Contact.COLUMN_EMAIL, contacto.getEmail() );
         res = db.update(Contact.TABLE_NAME, valores,Contact.COLUMN_ID+" = ?",
                 new String[]{String.valueOf( contacto.getId() )} ) ;
         db.close();
        }catch ( Exception ex ){
            System.err.println(" error " + ex.getMessage() + ex.getStackTrace() );
            ex.printStackTrace();
        }finally {
        }
        return res;
    }
    public int deleteContact(Contact contacto ) {
        int res = -1;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            res = db.delete(Contact.TABLE_NAME, Contact.COLUMN_ID+" =?",
                    new String[]{String.valueOf(contacto.getId()) });
            db.close();
        }catch ( Exception ex ){
            System.err.println(" error " + ex.getMessage() + ex.getStackTrace() );
            ex.printStackTrace();
        }finally {
            return  res;
        }
    }
}
