package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_PAGINA_PRINCIPAL_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_VALIDACAO_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APPBAR_NOVO_CHECKLIST;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;

import java.util.Calendar;
import java.util.Locale;

import rx.subscriptions.CompositeSubscription;

public class FormularioCheckListActivity extends AppCompatActivity {

    private RadioGroup saida_retorno;
    private EditText data;
    private EditText hora;
    private EditText placa;
    private EditText motorista;
    private EditText km;
    private RadioGroup tracao;
    private RadioGroup calibragem_pneu;
    private RadioGroup estepe;
    private RadioGroup freio_dianteiro;
    private RadioGroup freioTraseiro;
    private RadioGroup balanceamento;
    private RadioGroup limpeza_radiador;
    private RadioGroup oleo_motor;
    private RadioGroup filtro_oleo;
    private RadioGroup paraChoque_dianteiro;
    private RadioGroup paraChoque_traseiro;
    private RadioGroup placas_caminhao;
    private RadioGroup cinto_seguranca;
    private RadioGroup pedais;
    private RadioGroup abertura_portas;
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
    protected void onDestroy() {
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
        botaoContinuar.setOnClickListener(view ->
                vaiParaValidacao());
    }

    private void voltaParaPaginaPrincipal() {
        Intent iniciaPaginaPrincipal =
                new Intent(FormularioCheckListActivity.this,
                        PaginaPrincipalActivity.class);
        startActivityIfNeeded(iniciaPaginaPrincipal, CODIGO_PAGINA_PRINCIPAL_CHECKLIST);
    }

    private void vaiParaValidacao() {
        Intent abreDadosCheckList = new Intent(FormularioCheckListActivity.this,
                ValidacaoActivity.class);
        abreDadosCheckList.getExtras();
        startActivityIfNeeded(abreDadosCheckList, CODIGO_VALIDACAO_CHECKLIST);
    }

    public void configuraHora() {
        hora.setOnClickListener(view -> {
            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    FormularioCheckListActivity.this,
                    (timePicker, selectedHour, selectedMinute) -> onTimeSelected(
                            selectedHour, selectedMinute), hour, minute, true);
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
        saida_retorno = findViewById(R.id.formulario_rgSaidaRetorno);
        data = findViewById(R.id.formulario_check_list_data);
        hora = findViewById(R.id.formulario_check_list_hora);
        placa = findViewById(R.id.formulario_check_list_placa);
        motorista = findViewById(R.id.formulario_check_list_motorista);
        km = findViewById(R.id.formulario_check_list_km);
        tracao = findViewById(R.id.formulario_rgtracao_pneu);
        calibragem_pneu = findViewById(R.id.formulario_rgcalibragem_pneu);
        estepe = findViewById(R.id.formulario_rgestepe_pneu);
        freio_dianteiro = findViewById(R.id.formulario_rgfreioDianteiro);
        freioTraseiro = findViewById(R.id.formulario_rgfreioTraseiro);
        balanceamento = findViewById(R.id.formulario_rgBalanceamento);
        limpeza_radiador = findViewById(R.id.formulario_rgLimpezaRadiador);
        oleo_motor = findViewById(R.id.formulario_rgOleoMotor);
        filtro_oleo = findViewById(R.id.formulario_rgfiltroOleo);
        paraChoque_dianteiro = findViewById(R.id.formulario_rgPCDianteiro);
        paraChoque_traseiro = findViewById(R.id.formulario_rgPCTraseiro);
        placas_caminhao = findViewById(R.id.formulario_rgPlacasCaminhao);
        cinto_seguranca = findViewById(R.id.formulario_rgcinto_seguranca);
        pedais = findViewById(R.id.formulario_rgpedais);
        abertura_portas = findViewById(R.id.formulario_rgabertura_portas);
    }

//    public void salvaCheckList() {
//        CheckList checkListCriado = criaCheckList();
//        Observable<CheckList> observable = RetrofitConfig.getRetrofit().create(
//                CheckListService.class).cadastraNovoCheckList(checkListCriado);
//        subscription.add(observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<CheckList>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Toast.makeText(FormularioCheckListActivity.this,
//                                "Opa! Ocorreu um erro." + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onNext(CheckList checkList) {
//                        finish();
//                        vaiParaFinalizar();
//                    }
//                }));
//    }
//
//    private void vaiParaFinalizar() {
//        Intent iniciaFinalizar =
//                new Intent(FormularioCheckListActivity.this,
//                        FinalizacaoActivity.class);
//        startActivityIfNeeded(iniciaFinalizar, CODIGO_INSERE_CHECKLIST);
//    }
//
//    @NonNull
//    private CheckList criaCheckList() {
//        return new CheckList(
//                getValorRadioSaidaRetorno(),
//                data.getText().toString(),
//                hora.getText().toString(),
//                placa.getText().toString(),
//                motorista.getText().toString(),
//                Integer.valueOf(km.getText().toString()),
//                getValorRadioTracao(),
//                getValorRadioCalibragemPneu(),
//                getValorRadioEstepe(),
//                getValorRadioFreioDianteiro(),
//                getValorRadioFreioTraseiro(),
//                getValorRadioBalanceamento(),
//                getValorRadioLimpezaRadiador(),
//                getValorRadioOleoMotor(),
//                getValorRadioFiltroOleo(),
//                getValorRadioParaChoqueDianteiro(),
//                getValorRadioParaChoqueTraseiro(),
//                getValorRadioPlacasCaminhao(),
//                getValorRadioCintoSeguranca(),
//                getValorRadioPedais(),
//                getValorRadioAberturaPortas());
//    }
//
//    public String getValorRadioSaidaRetorno() {
//        String situacaoSaidaRetorno;
//        final int idSelecionado = saidaRetorno.getCheckedRadioButtonId();
//        final RadioButton selecionado = findViewById(idSelecionado);
//        situacaoSaidaRetorno = String.valueOf(selecionado.getText());
//
//        return situacaoSaidaRetorno;
//    }
//
//    public String getValorRadioTracao() {
//        String situacaoTracao;
//        final int idSelecionado = tracao.getCheckedRadioButtonId();
//        final RadioButton selecionado = findViewById(idSelecionado);
//        situacaoTracao = String.valueOf(selecionado.getText());
//
//        return situacaoTracao;
//    }
//
//    public String getValorRadioCalibragemPneu() {
//        String situacaoCalibragemPneu;
//        final int idSelecionado = calibragemPneu.getCheckedRadioButtonId();
//        final RadioButton selecionado = findViewById(idSelecionado);
//        situacaoCalibragemPneu = String.valueOf(selecionado.getText());
//
//        return situacaoCalibragemPneu;
//    }
//
//    public String getValorRadioEstepe() {
//        String situacaoEstepe;
//        final int idSelecionado = estepe.getCheckedRadioButtonId();
//        final RadioButton selecionado = findViewById(idSelecionado);
//        situacaoEstepe = String.valueOf(selecionado.getText());
//
//        return situacaoEstepe;
//    }
//
//    public String getValorRadioFreioDianteiro() {
//        String situacaoFreioDianteiro;
//        final int idSelecionado = freioDianteiro.getCheckedRadioButtonId();
//        final RadioButton selecionado = findViewById(idSelecionado);
//        situacaoFreioDianteiro = String.valueOf(selecionado.getText());
//
//        return situacaoFreioDianteiro;
//    }
//
//    public String getValorRadioFreioTraseiro() {
//        String situacaoFreioTraseiro;
//        final int idSelecionado = freioTraseiro.getCheckedRadioButtonId();
//        final RadioButton selecionado = findViewById(idSelecionado);
//        situacaoFreioTraseiro = String.valueOf(selecionado.getText());
//
//        return situacaoFreioTraseiro;
//    }
//
//    public String getValorRadioBalanceamento() {
//        String situacaoBalanceamento;
//        final int idSelecionado = balanceamento.getCheckedRadioButtonId();
//        final RadioButton selecionado = findViewById(idSelecionado);
//        situacaoBalanceamento = String.valueOf(selecionado.getText());
//
//        return situacaoBalanceamento;
//    }
//
//    public String getValorRadioLimpezaRadiador() {
//        String situacaoLimpezaRadiador;
//        final int idSelecionado = limpezaRadiador.getCheckedRadioButtonId();
//        final RadioButton selecionado = findViewById(idSelecionado);
//        situacaoLimpezaRadiador = String.valueOf(selecionado.getText());
//
//        return situacaoLimpezaRadiador;
//    }
//
//    public String getValorRadioOleoMotor() {
//        String situacaoOleoMotor;
//        final int idSelecionado = oleoMotor.getCheckedRadioButtonId();
//        final RadioButton selecionado = findViewById(idSelecionado);
//        situacaoOleoMotor = String.valueOf(selecionado.getText());
//
//        return situacaoOleoMotor;
//    }
//
//    public String getValorRadioFiltroOleo() {
//        String situacaoFiltroOleo;
//        final int idSelecionado = filtroOleo.getCheckedRadioButtonId();
//        final RadioButton selecionado = findViewById(idSelecionado);
//        situacaoFiltroOleo = String.valueOf(selecionado.getText());
//
//        return situacaoFiltroOleo;
//    }
//
//    public String getValorRadioParaChoqueDianteiro() {
//        String situacaoParaChoqueDianteiro;
//        final int idSelecionado = paraChoqueDianteiro.getCheckedRadioButtonId();
//        final RadioButton selecionado = findViewById(idSelecionado);
//        situacaoParaChoqueDianteiro = String.valueOf(selecionado.getText());
//
//        return situacaoParaChoqueDianteiro;
//    }
//
//    public String getValorRadioParaChoqueTraseiro() {
//        String situacaoParaChoqueTraseiro;
//        final int idSelecionado = paraChoqueTraseiro.getCheckedRadioButtonId();
//        final RadioButton selecionado = findViewById(idSelecionado);
//        situacaoParaChoqueTraseiro = String.valueOf(selecionado.getText());
//
//        return situacaoParaChoqueTraseiro;
//    }
//
//    public String getValorRadioPlacasCaminhao() {
//        String situacaoPlacasCaminhao;
//        final int idSelecionado = placasCaminhao.getCheckedRadioButtonId();
//        final RadioButton selecionado = findViewById(idSelecionado);
//        situacaoPlacasCaminhao = String.valueOf(selecionado.getText());
//
//        return situacaoPlacasCaminhao;
//    }
//
//    public String getValorRadioCintoSeguranca() {
//        String situacaoCintoSeguranca;
//        final int idSelecionado = cintoSeguranca.getCheckedRadioButtonId();
//        final RadioButton selecionado = findViewById(idSelecionado);
//        situacaoCintoSeguranca = String.valueOf(selecionado.getText());
//
//        return situacaoCintoSeguranca;
//    }
//
//    public String getValorRadioPedais() {
//        String situacaoPedais;
//        final int idSelecionado = pedais.getCheckedRadioButtonId();
//        final RadioButton selecionado = findViewById(idSelecionado);
//        situacaoPedais = String.valueOf(selecionado.getText());
//
//        return situacaoPedais;
//    }
//
//    public String getValorRadioAberturaPortas() {
//        String situacaoAberturaPortas;
//        final int idSelecionado = aberturaPortas.getCheckedRadioButtonId();
//        final RadioButton selecionado = findViewById(idSelecionado);
//        situacaoAberturaPortas = String.valueOf(selecionado.getText());
//
//        return situacaoAberturaPortas;
//    }

    public void checkedTodosRadioButtonsSelecionados() {
        checkRadioButtonSelecionadoSaidaRetorno();
        checkRadioButtonSelecionadoTracao();
        checkRadioButtonSelecionadoCalibragemPneu();
        checkRadioButtonSelecionadoEstepe();
        checkRadioButtonSelecionadoFreioDianteiro();
        checkRadioButtonSelecionadoFreioTraseiro();
        checkRadioButtonSelecionadoBalanceamento();
        checkRadioButtonSelecionadoLimpezaRadiador();
        checkRadioButtonSelecionadoOleoMotor();
        checkRadioButtonSelecionadoFiltroOleo();
        checkRadioButtonSelecionadoParaChoqueDianteiro();
        checkRadioButtonSelecionadoParaChoqueTraseiro();
        checkRadioButtonSelecionadoPlacasCaminhao();
        checkRadioButtonSelecionadoCintoSeguranca();
        checkRadioButtonSelecionadoPedais();
        checkRadioButtonSelecionadoAberturaPortas();
    }

    public void checkRadioButtonSelecionadoSaidaRetorno() {
        saida_retorno.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbSaida) {
                saida_retorno.check(R.id.formulario_rbSaida);
                Toast.makeText(getApplicationContext(),
                        "Saída", Toast.LENGTH_SHORT).show();
            } else {
                saida_retorno.check(R.id.formulario_rbRetorno);
                Toast.makeText(getApplicationContext(),
                        "Retorno", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkRadioButtonSelecionadoTracao() {
        tracao.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbtracao_ok) {
                Toast.makeText(getApplicationContext(), "OK",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "ATENÇÃO! Verificar tração - NOK",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkRadioButtonSelecionadoCalibragemPneu() {
        calibragem_pneu.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbcalibragem_ok) {
                Toast.makeText(getApplicationContext(), "OK",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "ATENÇÃO! Verificar calibragem do pneu - NOK",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkRadioButtonSelecionadoEstepe() {
        estepe.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbestepe_ok) {
                Toast.makeText(getApplicationContext(), "OK",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "ATENÇÃO! Verificar estepe - NOK",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkRadioButtonSelecionadoFreioDianteiro() {
        freio_dianteiro.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbfreioDianteiro_ok) {
                Toast.makeText(getApplicationContext(), "OK",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "ATENÇÃO! Verificar freio dianteiro - NOK",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkRadioButtonSelecionadoFreioTraseiro() {
        freioTraseiro.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbfreioTraseiro_ok) {
                Toast.makeText(getApplicationContext(), "OK",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "ATENÇÃO! Verificar freio traseiro - NOK",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkRadioButtonSelecionadoBalanceamento() {
        balanceamento.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbBalanceamento_ok) {
                Toast.makeText(getApplicationContext(), "OK",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "ATENÇÃO! Verificar balanceamento - NOK",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkRadioButtonSelecionadoLimpezaRadiador() {
        limpeza_radiador.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbLimpezaRadiador_ok) {
                Toast.makeText(getApplicationContext(), "OK",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "ATENÇÃO! Verificar a limpeza do radiador - NOK",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkRadioButtonSelecionadoOleoMotor() {
        oleo_motor.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbOleoMotor_ok) {
                Toast.makeText(getApplicationContext(), "OK",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "ATENÇÃO! Verificar o óleo do motor - NOK",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkRadioButtonSelecionadoFiltroOleo() {
        filtro_oleo.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbFiltroOleo_ok) {
                Toast.makeText(getApplicationContext(), "OK",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "ATENÇÃO! Verificar o filtro de óleo - NOK",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkRadioButtonSelecionadoParaChoqueDianteiro() {
        paraChoque_dianteiro.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbPcDianteiro_ok) {
                Toast.makeText(getApplicationContext(), "OK",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "ATENÇÃO! Verificar o para-choque dianteiro - NOK",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkRadioButtonSelecionadoParaChoqueTraseiro() {
        paraChoque_traseiro.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbPcTraseiro_ok) {
                Toast.makeText(getApplicationContext(), "OK",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "ATENÇÃO! Verificar o para-choque traseiro - NOK",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkRadioButtonSelecionadoPlacasCaminhao() {
        placas_caminhao.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbPlacasCaminhao_ok) {
                Toast.makeText(getApplicationContext(), "OK",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "ATENÇÃO! Verificar as placas - NOK",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkRadioButtonSelecionadoCintoSeguranca() {
        cinto_seguranca.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbcinto_seguranca_ok) {
                Toast.makeText(getApplicationContext(), "OK",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "ATENÇÃO! Verificar cintos de segurança - NOK",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkRadioButtonSelecionadoPedais() {
        pedais.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbpedais_ok) {
                Toast.makeText(getApplicationContext(), "OK",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "ATENÇÃO! Verificar os pedais - NOK",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkRadioButtonSelecionadoAberturaPortas() {
        abertura_portas.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbabertura_portas_ok) {
                Toast.makeText(getApplicationContext(), "OK",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "ATENÇÃO! Verificar abertura de portas - NOK",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
