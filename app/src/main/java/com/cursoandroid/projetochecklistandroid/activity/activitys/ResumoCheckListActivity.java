package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_POSICAO;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_LISTA_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APPBAR_RESUMO_CHECKLIST;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.projetochecklistandroid.R;
import com.cursoandroid.projetochecklistandroid.activity.service.CheckListService;
import com.cursoandroid.projetochecklistandroid.adapter.ListaCheckListsAdapter;
import com.cursoandroid.projetochecklistandroid.model.CheckList;
import com.cursoandroid.projetochecklistandroid.retrofit.config.RetrofitConfig;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ResumoCheckListActivity extends AppCompatActivity {

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
    private CheckList checkListMostrado;
    CompositeSubscription subscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_checklist_preenchido);
        setTitle(TITULO_APPBAR_RESUMO_CHECKLIST);

        inicializaCampos();
        configuraBotaoConcluir();

        Intent dadosRecebidos = getIntent();

        if (dadosRecebidos.hasExtra(CHAVE_CHECKLIST) &&
                dadosRecebidos.hasExtra(CHAVE_POSICAO)) {
            checkListMostrado = (CheckList) dadosRecebidos.getSerializableExtra(CHAVE_CHECKLIST);
            preencheCheckList();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu){
        getMenuInflater().inflate(R.menu.menu_lista_remover, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.menu_lista_remover, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        confirmaRemocao();
        return super.onOptionsItemSelected(item);
    }

    private void confirmaRemocao() {
        new AlertDialog.Builder(this).setMessage("Ops, quer mesmo remover o checklist?")
                .setPositiveButton("Sim",
                        (dialogInterface, i) -> deletaChecklist())
                .setNegativeButton("Nao", null).show();
    }

    private void deletaChecklist() {
        Observable<List<CheckList>> observable = RetrofitConfig.getRetrofit().create(
                CheckListService.class).removeCheckList(checkListMostrado.getId());
        subscription.remove(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<CheckList>>() {
                    @Override
                    public void onCompleted() {
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Opa, algo deu errado", e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(List<CheckList> checkLists) {

                    }
                }));
    }

    private void configuraBotaoConcluir() {
        Button botaoConcluir = findViewById(R.id.botao_resumo_concluir);
        botaoConcluir.setOnClickListener(view -> retonaParaListaChecklists());
    }

    private void retonaParaListaChecklists() {
        Intent iniciaListaChecklists =
                new Intent(ResumoCheckListActivity.this, ListaCheckListsActivity.class);
        startActivityIfNeeded(iniciaListaChecklists, CODIGO_LISTA_CHECKLIST);
    }

    private void inicializaCampos() {
        data = findViewById(R.id.resumo_data);
        hora = findViewById(R.id.resumo_hora);
        saidaRetorno = findViewById(R.id.resumo_motivo);
        motorista = findViewById(R.id.resumo_motorista);
        placa = findViewById(R.id.resumo_placa);
        km = findViewById(R.id.resumo_km);
        tracao = findViewById(R.id.resumo_tracao);
        calibracao = findViewById(R.id.resumo_calibragem);
        estepe = findViewById(R.id.resumo_estepe);
        freioDianteiro = findViewById(R.id.resumo_freio_dianteiro);
        freioTraseiro = findViewById(R.id.resumo_freio_traseiro);
        balanceamento = findViewById(R.id.resumo_balanceamento);
        limpezaRadiador = findViewById(R.id.resumo_limpeza_radiador);
        oleoMotor = findViewById(R.id.resumo_oleo_motor);
        filtroOleo = findViewById(R.id.resumo_filtro_oleo);
        paraChoqueDianteiro = findViewById(R.id.resumo_pc_dianteiro);
        paraChoqueTraseiro = findViewById(R.id.resumo_pc_traseiro);
        placas = findViewById(R.id.resumo_placas);
        cintoSeguranca = findViewById(R.id.resumo_cinto_seguranca);
        pedais = findViewById(R.id.resumo_pedais);
        aberturaPortas = findViewById(R.id.resumo_abertura_portas);
    }

    private void preencheCheckList() {
        data.setText(checkListMostrado.getData());
        hora.setText(checkListMostrado.getHora());
        saidaRetorno.setText(checkListMostrado.getSaidaRetorno());
        placa.setText(checkListMostrado.getPlaca());
        motorista.setText(checkListMostrado.getMotorista());
        km.setText(checkListMostrado.getKm().toString());
        tracao.setText(checkListMostrado.getTracao());
        calibracao.setText(checkListMostrado.getCalibragemPneu());
        estepe.setText(checkListMostrado.getEstepe());
        freioDianteiro.setText(checkListMostrado.getFreioDianteiro());
        freioTraseiro.setText(checkListMostrado.getFreioTraseiro());
        balanceamento.setText(checkListMostrado.getBalanceamento());
        limpezaRadiador.setText(checkListMostrado.getLimpezaRadiador());
        oleoMotor.setText(checkListMostrado.getOleoMotor());
        filtroOleo.setText(checkListMostrado.getFiltroOleo());
        paraChoqueDianteiro.setText(checkListMostrado.getParaChoqueDianteiro());
        paraChoqueTraseiro.setText(checkListMostrado.getParaChoqueTraseiro());
        placas.setText(checkListMostrado.getPlacasCaminhao());
        cintoSeguranca.setText(checkListMostrado.getCintoSeguranca());
        pedais.setText(checkListMostrado.getPedais());
        aberturaPortas.setText(checkListMostrado.getAberturaPortas());
    }
}


