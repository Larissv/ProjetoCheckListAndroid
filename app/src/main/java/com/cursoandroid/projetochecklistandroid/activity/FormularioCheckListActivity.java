package com.cursoandroid.projetochecklistandroid.activity;

import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.CHAVE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.CHAVE_POSICAO;
import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.CODIGO_MOSTRA_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.POSICAO_INVALIDA;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;
import com.cursoandroid.projetochecklistandroid.model.CheckList;
import com.cursoandroid.projetochecklistandroid.retrofit.CheckListService;
import com.cursoandroid.projetochecklistandroid.retrofit.RetrofitConfig;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FormularioCheckListActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_NOVO_CHECKLIST = "Novo Check List";
    public static final String TITULO_APPBAR_MOSTRA_CHECKLIST = "Visualização Checklist";
    private int posicaoRecebida = POSICAO_INVALIDA;
    private RadioGroup saidaRetorno;
    private EditText dataC;
    private EditText hora;
    private EditText placa;
    private EditText motorista;
    private EditText km;
    private RadioGroup tracao;
    private RadioGroup calibragemPneu;
    private RadioGroup estepe;
    private RadioGroup freioDianteiro;
    private RadioGroup freioTraseiro;
    private RadioGroup balanceamento;
    private RadioGroup limpezaRadiador;
    private RadioGroup oleoMotor;
    private RadioGroup filtroOleo;
    private RadioGroup paraChoqueDianteiro;
    private RadioGroup paraChoqueTraseiro;
    private RadioGroup placasCaminhao;
    private RadioGroup cintoSeguranca;
    private RadioGroup pedais;
    private RadioGroup aberturaPortas;
    public CheckList updateCheckList = new CheckList();
    RetrofitConfig retrofitConfig = new RetrofitConfig();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_check_list);

        setTitle(TITULO_APPBAR_NOVO_CHECKLIST);
        inicializaCampos();

        Intent dadosRecebidos = getIntent();

        if(dadosRecebidos.hasExtra(CHAVE_CHECKLIST) &&
                dadosRecebidos.hasExtra(CHAVE_POSICAO)){
            setTitle(TITULO_APPBAR_MOSTRA_CHECKLIST);
            CheckList checkListRecebido = (CheckList) dadosRecebidos
                    .getSerializableExtra(CHAVE_CHECKLIST);
            posicaoRecebida = dadosRecebidos.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
            preencheCheckList(checkListRecebido);
        }
    }

    private void preencheCheckList(CheckList checkListPreenchido) {
        saidaRetorno.getCheckedRadioButtonId();
        dataC.setText(checkListPreenchido.getDataC());
        hora.setText(checkListPreenchido.getHora());
        placa.setText(checkListPreenchido.getPlaca());
        motorista.setText(checkListPreenchido.getMotorista());
        km.setText(checkListPreenchido.getKm());
        tracao.getCheckedRadioButtonId();
        calibragemPneu.getCheckedRadioButtonId();
        estepe.getCheckedRadioButtonId();
        freioDianteiro.getCheckedRadioButtonId();
        freioTraseiro.getCheckedRadioButtonId();
        balanceamento.getCheckedRadioButtonId();
        limpezaRadiador.getCheckedRadioButtonId();
        oleoMotor.getCheckedRadioButtonId();
        filtroOleo.getCheckedRadioButtonId();
        paraChoqueDianteiro.getCheckedRadioButtonId();
        paraChoqueTraseiro.getCheckedRadioButtonId();
        placasCaminhao.getCheckedRadioButtonId();
        cintoSeguranca.getCheckedRadioButtonId();
        pedais.getCheckedRadioButtonId();
        aberturaPortas.getCheckedRadioButtonId();

    }

    @SuppressLint("WrongViewCast")
    private void inicializaCampos() {
        saidaRetorno = findViewById(R.id.formulario_rgSaidaRetorno);
        dataC = findViewById(R.id.formulario_check_list_data);
        hora = findViewById(R.id.formulario_check_list_hora);
        placa = findViewById(R.id.formulario_check_list_placa);
        motorista = findViewById(R.id.formulario_check_list_motorista);
        km = findViewById(R.id.formulario_check_list_km);
        tracao = findViewById(R.id.formulario_rgtracao_pneu);
        calibragemPneu = findViewById(R.id.formulario_rgcalibragem_pneu);
        estepe = findViewById(R.id.formulario_rgestepe_pneu);
        freioDianteiro = findViewById(R.id.formulario_rgfreioDianteiro);
        freioTraseiro = findViewById(R.id.formulario_rgfreioTraseiro);
        balanceamento = findViewById(R.id.formulario_rgBalanceamento);
        limpezaRadiador = findViewById(R.id.formulario_rgLimpezaRadiador);
        oleoMotor = findViewById(R.id.formulario_rgOleoMotor);
        filtroOleo = findViewById(R.id.formulario_rgfiltroOleo);
        paraChoqueDianteiro = findViewById(R.id.formulario_rgPCDianteiro);
        paraChoqueTraseiro = findViewById(R.id.formulario_rgPCTraseiro);
        placasCaminhao = findViewById(R.id.formulario_rgPlacasCaminhao);
        cintoSeguranca = findViewById(R.id.formulario_rgcinto_seguranca);
        pedais = findViewById(R.id.formulario_rgpedais);
        aberturaPortas = findViewById(R.id.formulario_rgabertura_portas);

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_salva_check_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (updateCheckList.getPlaca() != null) {
            assert false;
            CheckList checkListMostrado = criaCheckList();
            retornaCheckList(checkListMostrado);
            Observable<List<CheckList>> observable = retrofitConfig.getRetrofit().create(
                    CheckListService.class).mostraTodosCheckLists();
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<CheckList>>() {
                        @Override
                        public void onCompleted() {
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(FormularioCheckListActivity.this,
                                    "Erro", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(List<CheckList> checkLists) {
                            Toast.makeText(FormularioCheckListActivity.this, "Sucesso!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
            return super.onOptionsItemSelected(item);
        }

        if (validaMenuSalvar(item)) {
            CheckList checkListCriado = criaCheckList();
            Observable<CheckList> observable = retrofitConfig.getRetrofit().create(
                    CheckListService.class).cadastraNovoCheckList(checkListCriado);
            observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CheckList>() {
                        @Override
                        public void onCompleted() {
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(FormularioCheckListActivity.this,
                                    "Erro ao salvar", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onNext(CheckList checkList) {
                            Toast.makeText(FormularioCheckListActivity.this,
                                    "Check List salvo com sucesso!", Toast.LENGTH_SHORT).show();

                        }
                    });
        }
        return super.onOptionsItemSelected(item);
    }

    private void retornaCheckList(CheckList checkList) {
        Intent resultado = new Intent();
        resultado.putExtra(CHAVE_CHECKLIST, checkList);
        resultado.putExtra(CHAVE_POSICAO, posicaoRecebida);
        setResult(CODIGO_MOSTRA_CHECKLIST, resultado);
    }

    private CheckList criaCheckList() {
        return new CheckList(saidaRetorno.getCheckedRadioButtonId(),
                dataC.getText().toString(),
                hora.getText(),toString(),
                placa.getText().toString(),
                motorista.getText().toString(),
                km.getText().toString(),
                tracao.getCheckedRadioButtonId(),
                calibragemPneu.getCheckedRadioButtonId(),
                estepe.getCheckedRadioButtonId(),
                freioDianteiro.getCheckedRadioButtonId(),
                freioTraseiro.getCheckedRadioButtonId(),
                balanceamento.getCheckedRadioButtonId(),
                limpezaRadiador.getCheckedRadioButtonId(),
                oleoMotor.getCheckedRadioButtonId(),
                filtroOleo.getCheckedRadioButtonId(),
                paraChoqueDianteiro.getCheckedRadioButtonId(),
                paraChoqueTraseiro.getCheckedRadioButtonId(),
                placasCaminhao.getCheckedRadioButtonId(),
                pedais.getCheckedRadioButtonId(),
                aberturaPortas.getCheckedRadioButtonId());
    }

    public boolean validaMenuSalvar(@NonNull MenuItem item) {
        return item.getItemId() == R.id.menu_formulario_salva_check_list;
    }
}
