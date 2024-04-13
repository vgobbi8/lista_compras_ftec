package br.com.aula.listadecompras;

import android.app.Application;

import br.com.aula.listadecompras.infra.data.DIContainer;


public class MyApplication extends Application {
    public DIContainer diContainer = DIContainer.getInstance(this);
}
