package com.example.mbenkerroum.secured;

/**
 * Created by mbenkerroum on 15/02/2018.
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Password implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "passwordName")
    private String passwordName;

    @ColumnInfo(name = "passwordString")
    private String passwordString;

    @ColumnInfo(name = "passwordDesc")
    private String passwordDesc;
    @Ignore
    public Password(int uid, String passwordString) {
        this.uid = uid;
        this.passwordString = passwordString;
    }
    @Ignore
    public Password(String passwordName, String passwordString,String passwordDesc) {
        this.passwordName = passwordName;
        this.passwordString = passwordString;
        this.passwordDesc = passwordDesc;
    }

    public Password(String passwordString) {
        this.passwordString = passwordString;
    }



    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPasswordString() {
        return passwordString;
    }

    public void setPasswordString(String passwordString) {
        this.passwordString = passwordString;
    }

    public String getPasswordName() {
        return passwordName;
    }

    public void setPasswordName(String passwordName) {
        this.passwordName = passwordName;
    }

    public String getPasswordDesc() {
        return passwordDesc;
    }

    public void setPasswordDesc(String passwordDesc) {
        this.passwordDesc = passwordDesc;
    }
}
