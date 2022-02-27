package com.cursoandroid.projetochecklistandroid.activity;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_POSICAO;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_PAGINA_PRINCIPAL_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_RESUMO_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.POSICAO_INVALIDA;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APPBAR_MOSTRA_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APPBAR_RESUMO_CHECKLIST;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;
import com.cursoandroid.projetochecklistandroid.model.CheckList;
import com.cursoandroid.projetochecklistandroid.retrofit.adapter.ListaCheckListsAdapter;
import com.cursoandroid.projetochecklistandroid.retrofit.service.CheckListService;
import com.cursoandroid.projetochecklistandroid.retrofit.RetrofitConfig;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
    private ListaCheckListsAdapter listaCheckListsAdapter;
    private int posicaoRecebida = POSICAO_INVALIDA;
    public CheckList updateCheckList = new CheckList();
    RetrofitConfig retrofitConfig = new RetrofitConfig();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_checklist_preenchido);
        setTitle(TITULO_APPBAR_MOSTRA_CHECKLIST);

        inicializaCampos();
        configuraBotaoConcluir();

        Intent dadosRecebidos = getIntent();

        if (dadosRecebidos.hasExtra(CHAVE_CHECKLIST) &&
                dadosRecebidos.hasExtra(CHAVE_POSICAO)) {
            setTitle(TITULO_APPBAR_RESUMO_CHECKLIST);
            CheckList checkListRecebido = (CheckList) dadosRecebidos
                    .getSerializableExtra(CHAVE_CHECKLIST);
            updateCheckList = checkListRecebido;
            posicaoRecebida = dadosRecebidos.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
            preencheCheckList(checkListRecebido);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
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
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.item_menu_lista_remover) {
            listaCheckListsAdapter.removeCheckList(itemId);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraBotaoConcluir() {
        Button botaoConcluir = findViewById(
                R.id.botao_formulario_cancelar);
        botaoConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (updateCheckList.getMotorista() != null) {
                    CheckList checkListSelecionado = criaCheckList();
                    retornaCheckList(checkListSelecionado);
                    Observable<CheckList> observable = (Observable<CheckList>) retrofitConfig.getRetrofit()
                            .create(CheckListService.class).atualizaCheckList(updateCheckList.getId(),
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
                                    Toast.makeText(ResumoCheckListActivity.this,
                                            "Erro!", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onNext(CheckList checkList) {
                                    Toast.makeText(ResumoCheckListActivity.this,
                                            "Sucesso!", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                retonaParaPaginaInicial();
            }
        });
    }

    private void retonaParaPaginaInicial() {
        Intent iniciaPaginaPrincipal =
                new Intent(ResumoCheckListActivity.this,
                        PaginaPrincipalActivity.class);
        startActivityIfNeeded(iniciaPaginaPrincipal, CODIGO_PAGINA_PRINCIPAL_CHECKLIST);
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
        data.setText(checkListPreenchido.getDataC());
        hora.setText(checkListPreenchido.getHora());
        saidaRetorno.setText(checkListPreenchido.getSaidaRetorno());
        placa.setText(checkListPreenchido.getPlaca());
        motorista.setText(checkListPreenchido.getMotorista());
        km.setText(checkListPreenchido.getKm());
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


