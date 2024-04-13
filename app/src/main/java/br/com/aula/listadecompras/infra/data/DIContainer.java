package br.com.aula.listadecompras.infra.data;

import android.content.Context;

public class DIContainer {

    private static DIContainer instance;

    private Context context;
    private SqliteHelper sqliteHelper;
    private DIContainer(Context context) {
        this.context = context;
        this.sqliteHelper = new SqliteHelper(context);
    }

    public static DIContainer getInstance(Context context) {
        if (instance == null) {
            instance = new DIContainer(context);
        }
        return instance;
    }

    public ICategoriasRepository getCategoriasRepository() {
        return new CategoriasRepository(sqliteHelper.getWritableDatabase());
    }



}
