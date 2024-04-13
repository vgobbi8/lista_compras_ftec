package br.com.aula.listadecompras.presentation.fragments.categoria_cadastro;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;
import static androidx.lifecycle.SavedStateHandleSupport.createSavedStateHandle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import br.com.aula.listadecompras.MyApplication;
import br.com.aula.listadecompras.domain.models.CategoriaModel;
import br.com.aula.listadecompras.infra.data.CategoriasRepository;
import br.com.aula.listadecompras.infra.data.ICategoriasRepository;

public class CategoriaCadastroViewModel extends ViewModel {

    public int id = 0;
    public MutableLiveData<String> nome = new MutableLiveData<>("");

    private ICategoriasRepository categoriasRepository;

    public CategoriaCadastroViewModel() {
        //Get DIContainer
        MyApplication app = new MyApplication();
        categoriasRepository = app.diContainer.getCategoriasRepository();

    }

    public void salvarCategoria() {
        categoriasRepository.inserir(new CategoriaModel(id, nome.getValue()));
    }

    public void alterarCategoria() {
        categoriasRepository.alterar(new CategoriaModel(id, nome.getValue()));
    }


}