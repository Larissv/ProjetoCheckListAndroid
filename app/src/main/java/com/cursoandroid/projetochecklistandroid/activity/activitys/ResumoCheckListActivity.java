package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_POSICAO;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_LISTA_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_RESUMO_CHECKLIST;
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

import com.cursoandroid.projetochecklistandroid.R;
import com.cursoandroid.projetochecklistandroid.activity.service.CheckListService;
import com.cursoandroid.projetochecklistandroid.model.CheckList;
import com.cursoandroid.projetochecklistandroid.retrofit.config.RetrofitConfig;

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
    public CheckList checkListMostrado = new CheckList();
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
            preencheCheckList(checkListMostrado);
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
                        (dialogInterface, i) -> deletaChecklist(checkListMostrado.getId()))
                .setNegativeButton("Nao", null).show();
    }

    private void deletaChecklist(Integer id) {
        Observable<Void> observable = RetrofitConfig.getRetrofit().create(
                CheckListService.class).removeCheckList(id);
        subscription.remove(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Void>() {
                    @Override
                    public void onCompleted() {
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Opa, algo deu errado", e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(Void unused) {

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

    private void preencheCheckList(CheckList checkListPreenchido) {
        data.setText(checkListPreenchido.getData());
        hora.setText(checkListPreenchido.getHora());
        saidaRetorno.setText(checkListPreenchido.getSaidaRetorno());
        placa.setText(checkListPreenchido.getPlaca());
        motorista.setText(checkListPreenchido.getMotorista());
        km.setText(checkListPreenchido.getKm().toString());
        tracao.setText(checkListPreenchido.getTracao());
        calibracao.setText(checkListPreenchido.getCalibragemPneu());
        estepe.setText(checkListPreenchido.getEstepe());
        freioDianteiro.setText(checkListPreenchido.getFreioDianteiro());
        freioTraseiro.setText(checkListPreenchido.getFreioTraseiro());
        balanceamento.setText(checkListPreenchido.getBalanceamento());
        limpezaRadiador.setText(checkListPreenchido.getLimpezaRadiador());
        oleoMotor.setText(checkListPreenchido.getOleoMotor());
        filtroOleo.setText(checkListPreenchido.getFiltroOleo());
        paraChoqueDianteiro.setText(checkListPreenchido.getParaChoqueDianteiro());
        paraChoqueTraseiro.setText(checkListPreenchido.getParaChoqueTraseiro());
        placas.setText(checkListPreenchido.getPlacasCaminhao());
        cintoSeguranca.setText(checkListPreenchido.getCintoSeguranca());
        pedais.setText(checkListPreenchido.getPedais());
        aberturaPortas.setText(checkListPreenchido.getAberturaPortas());
    }
}


