package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_POSICAO;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_INSERE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APPBAR_VALIDACAO;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

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
    private TextView saida_retorno;
    private TextView data;
    private TextView hora;
    private TextView placa;
    private TextView motorista;
    private TextView km;
    private TextView tracao;
    private TextView calibragem_pneu;
    private TextView estepe;
    private TextView freio_dianteiro;
    private TextView freio_traseiro;
    private TextView balanceamento;
    private TextView limpeza_radiador;
    private TextView oleo_motor;
    private TextView filtro_oleo;
    private TextView paraChoque_dianteiro;
    private TextView paraChoque_traseiro;
    private TextView placas_caminhao;
    private TextView cinto_seguranca;
    private TextView pedais;
    private TextView abertura_portas;
    private CheckList checkListMostrado = new CheckList();
    CompositeSubscription subscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao_checklist);

        setTitle(TITULO_APPBAR_VALIDACAO);

        senhaValidacao = findViewById(R.id.codigo_validacao);
        //inicializaCampos();

        configuraBotaoValidar();

        Intent dadosRecebidos = getIntent();
        if (dadosRecebidos.hasExtra(CHAVE_CHECKLIST) &&
                dadosRecebidos.hasExtra(CHAVE_POSICAO)) {
            checkListMostrado = (CheckList) dadosRecebidos.getSerializableExtra(CHAVE_CHECKLIST);
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

    private CheckList criaCheck() {
        return new CheckList(
                saida_retorno.getText().toString(),
                data.getText().toString(),
                hora.getText().toString(),
                placa.getText().toString(),
                motorista.getText().toString(),
                Integer.valueOf(km.getText().toString()),
                tracao.getText().toString(),
                calibragem_pneu.getText().toString(),
                estepe.getText().toString(),
                freio_dianteiro.getText().toString(),
                freio_traseiro.getText().toString(),
                balanceamento.getText().toString(),
                limpeza_radiador.getText().toString(),
                oleo_motor.getText().toString(),
                filtro_oleo.getText().toString(),
                paraChoque_dianteiro.getText().toString(),
                paraChoque_traseiro.getText().toString(),
                placas_caminhao.getText().toString(),
                cinto_seguranca.getText().toString(),
                pedais.getText().toString(),
                abertura_portas.getText().toString());
    }

    private void vaiParaFinalizar() {
        Intent iniciaFinalizar =
                new Intent(ValidacaoActivity.this,
                        FinalizacaoActivity.class);
        startActivityIfNeeded(iniciaFinalizar, CODIGO_INSERE_CHECKLIST);
    }

    private void inicializaCampos() {
        data = findViewById(R.id.resumo_data);
        hora = findViewById(R.id.resumo_hora);
        saida_retorno = findViewById(R.id.resumo_motivo);
        motorista = findViewById(R.id.resumo_motorista);
        placa = findViewById(R.id.resumo_placa);
        km = findViewById(R.id.resumo_km);
        tracao = findViewById(R.id.resumo_tracao);
        calibragem_pneu = findViewById(R.id.resumo_calibragem);
        estepe = findViewById(R.id.resumo_estepe);
        freio_dianteiro = findViewById(R.id.resumo_freio_dianteiro);
        freio_traseiro = findViewById(R.id.resumo_freio_traseiro);
        balanceamento = findViewById(R.id.resumo_balanceamento);
        limpeza_radiador = findViewById(R.id.resumo_limpeza_radiador);
        oleo_motor = findViewById(R.id.resumo_oleo_motor);
        filtro_oleo = findViewById(R.id.resumo_filtro_oleo);
        paraChoque_dianteiro = findViewById(R.id.resumo_pc_dianteiro);
        paraChoque_traseiro = findViewById(R.id.resumo_pc_traseiro);
        placas_caminhao = findViewById(R.id.resumo_placas);
        cinto_seguranca = findViewById(R.id.resumo_cinto_seguranca);
        pedais = findViewById(R.id.resumo_pedais);
        abertura_portas = findViewById(R.id.resumo_abertura_portas);
    }

    private void preencheCheckList() {
        data.setText(checkListMostrado.getData());
        hora.setText(checkListMostrado.getHora());
        saida_retorno.setText(checkListMostrado.getSaidaRetorno());
        placa.setText(checkListMostrado.getPlaca());
        motorista.setText(checkListMostrado.getMotorista());
        km.setText(checkListMostrado.getKm().toString());
        tracao.setText(checkListMostrado.getTracao());
        calibragem_pneu.setText(checkListMostrado.getCalibragemPneu());
        estepe.setText(checkListMostrado.getEstepe());
        freio_dianteiro.setText(checkListMostrado.getFreioDianteiro());
        freio_traseiro.setText(checkListMostrado.getFreioTraseiro());
        balanceamento.setText(checkListMostrado.getBalanceamento());
        limpeza_radiador.setText(checkListMostrado.getLimpezaRadiador());
        oleo_motor.setText(checkListMostrado.getOleoMotor());
        filtro_oleo.setText(checkListMostrado.getFiltroOleo());
        paraChoque_dianteiro.setText(checkListMostrado.getParaChoqueDianteiro());
        paraChoque_traseiro.setText(checkListMostrado.getParaChoqueTraseiro());
        placas_caminhao.setText(checkListMostrado.getPlacasCaminhao());
        cinto_seguranca.setText(checkListMostrado.getCintoSeguranca());
        pedais.setText(checkListMostrado.getPedais());
        abertura_portas.setText(checkListMostrado.getAberturaPortas());
    }
}
