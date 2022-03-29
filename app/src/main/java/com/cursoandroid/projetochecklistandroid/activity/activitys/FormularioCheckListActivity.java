package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_INSERE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_PAGINA_PRINCIPAL_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_VALIDACAO_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APPBAR_NOVO_CHECKLIST;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;
import com.cursoandroid.projetochecklistandroid.activity.service.CheckListService;
import com.cursoandroid.projetochecklistandroid.model.CheckList;
import com.cursoandroid.projetochecklistandroid.retrofit.config.RetrofitConfig;

import java.util.Calendar;
import java.util.Locale;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class FormularioCheckListActivity extends AppCompatActivity {

    private RadioGroup saidaRetorno;
    private EditText data;
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
    CompositeSubscription subscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_checklist);

        setTitle(TITULO_APPBAR_NOVO_CHECKLIST);
        inicializaCampos();
        configuraBotaoCancelar();
        configuraBotaoContinuar();
        configuraCalendario();
        configuraHora();
    }

    @Override
    protected void onDestroy(){
        subscription.unsubscribe();
        subscription = null;
        super.onDestroy();
    }

    private void configuraBotaoCancelar() {
        Button botaoCancelar = findViewById(
                R.id.botao_formulario_cancelar);
        botaoCancelar.setOnClickListener(view -> voltaParaPaginaPrincipal());
    }

    private void configuraBotaoContinuar() {
        Button botaoContinuar = findViewById(R.id.botao_formulario_continuar);
        botaoContinuar.setOnClickListener(view -> {
            vaiParaValidacao();
        });
    }

    private void voltaParaPaginaPrincipal() {
        Intent iniciaPaginaPrincipal =
                new Intent(FormularioCheckListActivity.this,
                        PaginaPrincipalActivity.class);
        startActivityIfNeeded(iniciaPaginaPrincipal, CODIGO_PAGINA_PRINCIPAL_CHECKLIST);
    }

    private void vaiParaValidacao() {
        Intent iniciaValidacao =
                new Intent(FormularioCheckListActivity.this,
                        ValidacaoActivity.class);
        startActivityIfNeeded(iniciaValidacao, CODIGO_VALIDACAO_CHECKLIST);
    }

    public void configuraHora() {
        hora.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    FormularioCheckListActivity.this,
                    (timePicker, selectedHour, selectedMinute) -> onTimeSelected(
                            selectedHour, selectedMinute), hour, minute,true);
            timePickerDialog.setTitle("Selecione a hora");
            timePickerDialog.show();
        });
    }

    // Essa abordagem corrige a forma de mostrar a hora como 10:01 ao invés de 10:1;
    private void onTimeSelected(int selectedHour, int selectedMinute) {
        hora.setText(String.format("%02d:%02d", selectedHour, selectedMinute));
    }

    public void configuraCalendario() {
        data.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    FormularioCheckListActivity.this,
                    (datePicker, year1, month1, day1) -> onDateSelected(calendar, year1, month1, day1),
                    year, month, day);
            datePickerDialog.show();
            datePickerDialog.getDatePicker();
        });
    }

    private void onDateSelected(Calendar calendar, int year1, int month1, int day1) {
        calendar.set(year1, month1, day1);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        data.setText(format.format(calendar.getTime()));
    }

    private void inicializaCampos() {
        saidaRetorno = findViewById(R.id.formulario_rgSaidaRetorno);
        data = findViewById(R.id.formulario_check_list_data);
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

    public void salvaCheckList() {
        CheckList checkListCriado = criaCheckList();
        Observable<CheckList> observable = RetrofitConfig.getRetrofit().create(
                CheckListService.class).cadastraNovoCheckList(checkListCriado);
        subscription.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckList>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(FormularioCheckListActivity.this,
                                "Opa! Ocorreu um erro." + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(CheckList checkList) {
                        finish();
                        vaiParaFinalizar();
                    }
                }));
    }

    private void vaiParaFinalizar() {
        Intent iniciaFinalizar =
                new Intent(FormularioCheckListActivity.this,
                        FinalizacaoActivity.class);
        startActivityIfNeeded(iniciaFinalizar, CODIGO_INSERE_CHECKLIST);
    }

    @NonNull
    private CheckList criaCheckList() {
        return new CheckList(
                getValorRadioSaidaRetorno(),
                data.getText().toString(),
                hora.getText().toString(),
                placa.getText().toString(),
                motorista.getText().toString(),
                Integer.valueOf(km.getText().toString()),
                getValorRadioTracao(),
                getValorRadioCalibragemPneu(),
                getValorRadioEstepe(),
                getValorRadioFreioDianteiro(),
                getValorRadioFreioTraseiro(),
                getValorRadioBalanceamento(),
                getValorRadioLimpezaRadiador(),
                getValorRadioOleoMotor(),
                getValorRadioFiltroOleo(),
                getValorRadioParaChoqueDianteiro(),
                getValorRadioParaChoqueTraseiro(),
                getValorRadioPlacasCaminhao(),
                getValorRadioCintoSeguranca(),
                getValorRadioPedais(),
                getValorRadioAberturaPortas());
    }

    public String getValorRadioSaidaRetorno() {
        String situacaoSaidaRetorno;
        final int idSelecionado = saidaRetorno.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoSaidaRetorno = String.valueOf(selecionado.getText());

        return situacaoSaidaRetorno;
    }

    public String getValorRadioTracao() {
        String situacaoTracao;
        final int idSelecionado = tracao.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoTracao = String.valueOf(selecionado.getText());

        return situacaoTracao;
    }

    public String getValorRadioCalibragemPneu() {
        String situacaoCalibragemPneu;
        final int idSelecionado = calibragemPneu.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoCalibragemPneu = String.valueOf(selecionado.getText());

        return situacaoCalibragemPneu;
    }

    public String getValorRadioEstepe() {
        String situacaoEstepe;
        final int idSelecionado = estepe.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoEstepe = String.valueOf(selecionado.getText());

        return situacaoEstepe;
    }

    public String getValorRadioFreioDianteiro() {
        String situacaoFreioDianteiro;
        final int idSelecionado = freioDianteiro.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoFreioDianteiro = String.valueOf(selecionado.getText());

        return situacaoFreioDianteiro;
    }

    public String getValorRadioFreioTraseiro() {
        String situacaoFreioTraseiro;
        final int idSelecionado = freioTraseiro.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoFreioTraseiro = String.valueOf(selecionado.getText());

        return situacaoFreioTraseiro;
    }

    public String getValorRadioBalanceamento() {
        String situacaoBalanceamento;
        final int idSelecionado = balanceamento.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoBalanceamento = String.valueOf(selecionado.getText());

        return situacaoBalanceamento;
    }

    public String getValorRadioLimpezaRadiador() {
        String situacaoLimpezaRadiador;
        final int idSelecionado = limpezaRadiador.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoLimpezaRadiador = String.valueOf(selecionado.getText());

        return situacaoLimpezaRadiador;
    }

    public String getValorRadioOleoMotor() {
        String situacaoOleoMotor;
        final int idSelecionado = oleoMotor.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoOleoMotor = String.valueOf(selecionado.getText());

        return situacaoOleoMotor;
    }

    public String getValorRadioFiltroOleo() {
        String situacaoFiltroOleo;
        final int idSelecionado = filtroOleo.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoFiltroOleo = String.valueOf(selecionado.getText());

        return situacaoFiltroOleo;
    }

    public String getValorRadioParaChoqueDianteiro() {
        String situacaoParaChoqueDianteiro;
        final int idSelecionado = paraChoqueDianteiro.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoParaChoqueDianteiro = String.valueOf(selecionado.getText());

        return situacaoParaChoqueDianteiro;
    }

    public String getValorRadioParaChoqueTraseiro() {
        String situacaoParaChoqueTraseiro;
        final int idSelecionado = paraChoqueTraseiro.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoParaChoqueTraseiro = String.valueOf(selecionado.getText());

        return situacaoParaChoqueTraseiro;
    }

    public String getValorRadioPlacasCaminhao() {
        String situacaoPlacasCaminhao;
        final int idSelecionado = placasCaminhao.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoPlacasCaminhao = String.valueOf(selecionado.getText());

        return situacaoPlacasCaminhao;
    }

    public String getValorRadioCintoSeguranca() {
        String situacaoCintoSeguranca;
        final int idSelecionado = cintoSeguranca.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoCintoSeguranca = String.valueOf(selecionado.getText());

        return situacaoCintoSeguranca;
    }

    public String getValorRadioPedais() {
        String situacaoPedais;
        final int idSelecionado = pedais.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoPedais = String.valueOf(selecionado.getText());

        return situacaoPedais;
    }

    public String getValorRadioAberturaPortas() {
        String situacaoAberturaPortas;
        final int idSelecionado = aberturaPortas.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoAberturaPortas = String.valueOf(selecionado.getText());

        return situacaoAberturaPortas;
    }
}
