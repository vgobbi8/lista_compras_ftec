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

    public ArrayList<SqlTableColumn> getTableSchema(String tableName) {
        String sql = "PRAGMA table_info(" + tableName + ")";
        Cursor cursor = database.rawQuery(sql, null);
        ArrayList<SqlTableColumn> columns = new ArrayList<>();
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String type = cursor.getString(cursor.getColumnIndex("type"));
            @SuppressLint("Range") boolean isPrimaryKey = cursor.getInt(cursor.getColumnIndex("pk")) == 1;
            @SuppressLint("Range") boolean isAutoIncrement = cursor.getInt(cursor.getColumnIndex("pk")) == 1;
            columns.add(new SqlTableColumn(name, type, isPrimaryKey, isAutoIncrement));

        }
        cursor.close();
        return columns;
    }

    protected String getInsertFields(ArrayList<SqlTableColumn> columns) {
        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            sql.append(columns.get(i).getName());
            if (i < columns.size() - 1) {
                sql.append(",");
            }
        }
        return sql.toString();
    }

    protected String getInsertParameters(ArrayList<SqlTableColumn> columns) {
        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < columns.size(); i++) {
            sql.append("?");
            if (i < columns.size() - 1) {
                sql.append(",");
            }
        }
        return sql.toString();
    }
}


