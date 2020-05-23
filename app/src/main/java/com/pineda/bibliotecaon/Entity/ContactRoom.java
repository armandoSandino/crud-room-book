package com.pineda.bibliotecaon.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import javax.xml.namespace.QName;

@Entity (
        tableName ="contacts"
)
public class ContactRoom {
    @ColumnInfo(name = "contact_name")
    @NonNull
    private String name;
    @ColumnInfo(name ="contact_email")
    @NonNull
    private String email;
    @ColumnInfo( name="contact_id")
    @NonNull
    @PrimaryKey( autoGenerate = true)
    private long id;
    @Ignore
    public  ContactRoom() { }
    public ContactRoom( long id, String name, String email ) {
        this.name = name;
        this.email =  email;
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
