package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_POSICAO;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_RESUMO_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APPBAR_LISTA;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.projetochecklistandroid.activity.adapter.ListaCheckListsAdapter;
import com.cursoandroid.projetochecklistandroid.R;
import com.cursoandroid.projetochecklistandroid.model.CheckList;
import com.cursoandroid.projetochecklistandroid.retrofit.service.CheckListService;
import com.cursoandroid.projetochecklistandroid.retrofit.config.RetrofitConfig;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ListaCheckListsActivity extends AppCompatActivity {

    CompositeSubscription subscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_checklists);
        setTitle(TITULO_APPBAR_LISTA);

        mostraTodosCheckLists();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_lista_checklists);
        mostraTodosCheckLists();
    }

    @Override
    protected void onDestroy() {
        subscription.unsubscribe();
        subscription = null;
        super.onDestroy();
    }

    public void mostraTodosCheckLists() {
        Observable<List<CheckList>> observable =
                RetrofitConfig.getRetrofit().create(CheckListService.class).mostraTodosCheckLists();
        subscription.add(
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<List<CheckList>>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(ListaCheckListsActivity.this,
                                        "Erro ao mostrar checklists: " + e.getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(List<CheckList> checkLists) {
                                configuraRecyclerView(checkLists);
                            }
                        }));
    }

    private void configuraRecyclerView(List<CheckList> todosCheckLists) {
        RecyclerView listaCheckLists = findViewById(R.id.lista_checklists_recyclerview);
        configuraAdapter(todosCheckLists, listaCheckLists);
    }

    private void configuraAdapter(List<CheckList> todosCheckLists, RecyclerView listaCheckLists) {
        ListaCheckListsAdapter listaCheckListsAdapter = new ListaCheckListsAdapter(todosCheckLists, this);
        listaCheckLists.setAdapter(listaCheckListsAdapter);
        listaCheckListsAdapter.setOnItemClickListener(this::vaiParaMostraCheckListPreenchido);
    }

    private void vaiParaMostraCheckListPreenchido(CheckList checkList, int posicao) {
        Intent abreCheckListPreenchido = new Intent(ListaCheckListsActivity.this,
                ResumoCheckListActivity.class);
        abreCheckListPreenchido.putExtra(CHAVE_CHECKLIST, checkList);
        abreCheckListPreenchido.putExtra(CHAVE_POSICAO, posicao);
        startActivityIfNeeded(abreCheckListPreenchido, CODIGO_RESUMO_CHECKLIST);
    }
}

