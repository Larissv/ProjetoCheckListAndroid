package com.cursoandroid.projetochecklistandroid.activity;

import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.CHAVE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.CHAVE_POSICAO;
import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.CODIGO_MOSTRA_CHECKLIST;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.projetochecklistandroid.R;
import com.cursoandroid.projetochecklistandroid.adapter.ListaCheckListsAdapter;
import com.cursoandroid.projetochecklistandroid.model.CheckList;
import com.cursoandroid.projetochecklistandroid.retrofit.CheckListService;
import com.cursoandroid.projetochecklistandroid.retrofit.RetrofitConfig;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ListaCheckListsActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_LISTA = "Check Lists";
    private ListaCheckListsAdapter listaCheckListsAdapter;
    RetrofitConfig retrofitConfig = new RetrofitConfig();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_check_lists);

        setTitle(TITULO_APPBAR_LISTA);
        mostraTodosCheckLists();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_lista_check_lists);
        mostraTodosCheckLists();
    }

    public void mostraTodosCheckLists() {
        Observable<List<CheckList>> observable =
                retrofitConfig.getRetrofit().create(CheckListService.class)
                        .mostraTodosCheckLists();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CheckList>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ListaCheckListsActivity.this,
                                "Erro ao mostrar todos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(List<CheckList> checkLists) {
                        Toast.makeText(ListaCheckListsActivity.this,
                                "Mostrando todos!", Toast.LENGTH_SHORT).show();
                        configuraRecyclerView(checkLists);
                    }
                });
    }

    private void configuraRecyclerView(List<CheckList> todosCheckLists) {
        RecyclerView listaCheckLists = findViewById(R.id.lista_checklists_recyclerview);
        configuraAdapter(todosCheckLists, listaCheckLists);
    }


    private void configuraAdapter(List<CheckList> todosCheckLists, RecyclerView listaCheckLists) {
        listaCheckListsAdapter = new ListaCheckListsAdapter(todosCheckLists, this);
        listaCheckLists.setAdapter(listaCheckListsAdapter);
        listaCheckListsAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(CheckList checkList, int posicao) {
                vaiParaMostraCheckListPreenchido(checkList, posicao);
            }
        });
    }

    private void vaiParaMostraCheckListPreenchido(CheckList checkList, int posicao) {
        Intent abreCheckListPreenchido = new Intent(ListaCheckListsActivity.this,
                FormularioCheckListActivity.class);
        abreCheckListPreenchido.putExtra(CHAVE_CHECKLIST, checkList);
        abreCheckListPreenchido.putExtra(CHAVE_POSICAO, posicao);
        startActivityIfNeeded(abreCheckListPreenchido, CODIGO_MOSTRA_CHECKLIST);
    }
}

