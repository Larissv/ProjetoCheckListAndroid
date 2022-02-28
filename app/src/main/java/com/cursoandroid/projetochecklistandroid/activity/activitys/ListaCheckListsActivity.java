package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_POSICAO;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_RESUMO_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.POSICAO_INVALIDA;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APPBAR_LISTA;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.projetochecklistandroid.activity.listener.OnItemClickListener;
import com.cursoandroid.projetochecklistandroid.retrofit.adapter.ListaCheckListsAdapter;
import com.cursoandroid.projetochecklistandroid.R;
import com.cursoandroid.projetochecklistandroid.model.CheckList;
import com.cursoandroid.projetochecklistandroid.retrofit.service.CheckListService;
import com.cursoandroid.projetochecklistandroid.retrofit.RetrofitConfig;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ListaCheckListsActivity extends AppCompatActivity {

    private TextView data;
    private TextView hora;
    private TextView saidaRetorno;
    private TextView motorista;
    private TextView placa;
    private TextView km;
    private TextView tracao;
    private TextView calibracao;
    private TextView estepe;
    private TextView freioDianteiro;
    private TextView freioTraseiro;
    private TextView balanceamento;
    private TextView limpezaRadiador;
    private TextView oleoMotor;
    private TextView filtroOleo;
    private TextView paraChoqueDianteiro;
    private TextView paraChoqueTraseiro;
    private TextView placas;
    private TextView cintoSeguranca;
    private TextView pedais;
    private TextView aberturaPortas;

    private ListaCheckListsAdapter listaCheckListsAdapter;
    RetrofitConfig retrofitConfig = new RetrofitConfig();
    public CheckList updateCheckList = new CheckList();
    private int posicaoRecebida = POSICAO_INVALIDA;


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
                                "Erro ao mostrar todos: " + e.getMessage(),
                                Toast.LENGTH_SHORT).show();
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
                mostraCheckListId();
                vaiParaMostraCheckListPreenchido(checkList, posicao);
            }
        });
    }

    private void vaiParaMostraCheckListPreenchido(CheckList checkList, int posicao) {
        Intent abreCheckListPreenchido = new Intent(ListaCheckListsActivity.this,
                ResumoCheckListActivity.class);
        abreCheckListPreenchido.putExtra(CHAVE_CHECKLIST, checkList);
        abreCheckListPreenchido.putExtra(CHAVE_POSICAO, posicao);
        startActivityIfNeeded(abreCheckListPreenchido, CODIGO_RESUMO_CHECKLIST);
    }

    public void mostraCheckListId() {
        if (updateCheckList.getMotorista() != null) {
            CheckList checkListSelecionado = criaCheckList();
            retornaCheckList(checkListSelecionado);
            Observable<CheckList> observable = (Observable<CheckList>) retrofitConfig.getRetrofit()
                    .create(CheckListService.class).verificaCheckList(updateCheckList.getId(),
                            checkListSelecionado);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CheckList>() {
                        @Override
                        public void onCompleted() {
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(ListaCheckListsActivity.this,
                                    "Erro!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(CheckList checkList) {
                            Toast.makeText(ListaCheckListsActivity.this,
                                    "Sucesso!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @NonNull
    private CheckList criaCheckList() {
        return new CheckList(
                data.getText().toString(),
                hora.getText().toString(),
                saidaRetorno.getText().toString(),
                placa.getText().toString(),
                motorista.getText().toString(),
                km.getText().toString(),
                tracao.getText().toString(),
                calibracao.getText().toString(),
                estepe.getText().toString(),
                freioDianteiro.getText().toString(),
                freioTraseiro.getText().toString(),
                balanceamento.getText().toString(),
                limpezaRadiador.getText().toString(),
                oleoMotor.getText().toString(),
                filtroOleo.getText().toString(),
                paraChoqueDianteiro.getText().toString(),
                paraChoqueTraseiro.getText().toString(),
                placas.getText().toString(),
                cintoSeguranca.getText().toString(),
                pedais.getText().toString(),
                aberturaPortas.getText().toString());
    }

    private void retornaCheckList(CheckList checkList) {
        Intent resultado = new Intent();
        resultado.putExtra(CHAVE_CHECKLIST, checkList);
        resultado.putExtra(CHAVE_POSICAO, posicaoRecebida);
        setResult(CODIGO_RESUMO_CHECKLIST, resultado);
    }
}

