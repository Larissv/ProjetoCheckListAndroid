package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_POSICAO;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_INSERE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_VALIDACAO_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.POSICAO_INVALIDA;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APPBAR_VALIDACAO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

public class ValidacaoActivity extends AppCompatActivity {

    private EditText senhaValidacao;
    private TextView saidaRetorno;
    private TextView data;
    private TextView hora;
    private TextView placa;
    private TextView motorista;
    private TextView km;
    private TextView tracao;
    private TextView calibragemPneu;
    private TextView estepe;
    private TextView freioDianteiro;
    private TextView freioTraseiro;
    private TextView balanceamento;
    private TextView limpezaRadiador;
    private TextView oleoMotor;
    private TextView filtroOleo;
    private TextView paraChoqueDianteiro;
    private TextView paraChoqueTraseiro;
    private TextView placasCaminhao;
    private TextView cintoSeguranca;
    private TextView pedais;
    private TextView aberturaPortas;
    private CheckList checkListMostrado;
    CompositeSubscription subscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao_checklist);

        setTitle(TITULO_APPBAR_VALIDACAO);

        senhaValidacao = findViewById(R.id.codigo_validacao);
        inicializaCampos();

        configuraBotaoValidar();

        Intent dadosCheckList = getIntent();
        if (dadosCheckList.hasExtra(CHAVE_CHECKLIST) &&
                dadosCheckList.hasExtra(CHAVE_POSICAO)) {
            checkListMostrado = (CheckList) dadosCheckList.getSerializableExtra(CHAVE_CHECKLIST);
            preencheCheckList();
        }
    }

    private void configuraBotaoValidar() {
        Button botaoValidar = findViewById(
                R.id.botao_validacao);
        botaoValidar.setOnClickListener(view -> {
            if (senhaValidacao.getText().toString().equals("")) {
                Toast.makeText(ValidacaoActivity.this, "Código de verificação inválido",
                        Toast.LENGTH_SHORT).show();
            } else {
                salvaCheckList();
            }
        });
    }

    @NonNull
    public void salvaCheckList() {
        Observable<CheckList> observable = RetrofitConfig.getRetrofit().create(
                CheckListService.class).cadastraNovoCheckList(checkListMostrado);
        subscription.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckList>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ValidacaoActivity.this,
                                "Opa! Algo deu errado." + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(CheckList checkList) {
                        finish();
                    }
                }));
    }

    private void vaiParaFinalizar() {
        Intent iniciaFinalizar =
                new Intent(ValidacaoActivity.this,
                        FinalizacaoActivity.class);
        startActivityIfNeeded(iniciaFinalizar, CODIGO_INSERE_CHECKLIST);
    }

    private void retornaCheck(CheckList checkList){
        Intent resultado = new Intent();
        resultado.putExtra(CHAVE_CHECKLIST, checkList);
        resultado.putExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
        setResult(CODIGO_VALIDACAO_CHECKLIST, resultado);
    }

    private void inicializaCampos() {
        data = findViewById(R.id.resumo_data);
        hora = findViewById(R.id.resumo_hora);
        saidaRetorno = findViewById(R.id.resumo_motivo);
        motorista = findViewById(R.id.resumo_motorista);
        placa = findViewById(R.id.resumo_placa);
        km = findViewById(R.id.resumo_km);
        tracao = findViewById(R.id.resumo_tracao);
        calibragemPneu = findViewById(R.id.resumo_calibragem);
        estepe = findViewById(R.id.resumo_estepe);
        freioDianteiro = findViewById(R.id.resumo_freio_dianteiro);
        freioTraseiro = findViewById(R.id.resumo_freio_traseiro);
        balanceamento = findViewById(R.id.resumo_balanceamento);
        limpezaRadiador = findViewById(R.id.resumo_limpeza_radiador);
        oleoMotor = findViewById(R.id.resumo_oleo_motor);
        filtroOleo = findViewById(R.id.resumo_filtro_oleo);
        paraChoqueDianteiro = findViewById(R.id.resumo_pc_dianteiro);
        paraChoqueTraseiro = findViewById(R.id.resumo_pc_traseiro);
        placasCaminhao = findViewById(R.id.resumo_placas);
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
        calibragemPneu.setText(checkListMostrado.getCalibragemPneu());
        estepe.setText(checkListMostrado.getEstepe());
        freioDianteiro.setText(checkListMostrado.getFreioDianteiro());
        freioTraseiro.setText(checkListMostrado.getFreioTraseiro());
        balanceamento.setText(checkListMostrado.getBalanceamento());
        limpezaRadiador.setText(checkListMostrado.getLimpezaRadiador());
        oleoMotor.setText(checkListMostrado.getOleoMotor());
        filtroOleo.setText(checkListMostrado.getFiltroOleo());
        paraChoqueDianteiro.setText(checkListMostrado.getParaChoqueDianteiro());
        paraChoqueTraseiro.setText(checkListMostrado.getParaChoqueTraseiro());
        placasCaminhao.setText(checkListMostrado.getPlacasCaminhao());
        cintoSeguranca.setText(checkListMostrado.getCintoSeguranca());
        pedais.setText(checkListMostrado.getPedais());
        aberturaPortas.setText(checkListMostrado.getAberturaPortas());
    }
}
