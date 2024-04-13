package br.com.aula.listadecompras.infra.data;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class BaseRepository {

    protected SQLiteDatabase database;

    public BaseRepository(SQLiteDatabase database) {
        this.database = database;
    }

}


