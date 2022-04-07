package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_POSICAO;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_PAGINA_PRINCIPAL_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_RESUMO_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_VALIDACAO_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APPBAR_NOVO_CHECKLIST;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;
import com.cursoandroid.projetochecklistandroid.model.CheckList;

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
    private RadioGroup freio_traseiro;
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
        checkedTodosRadioButtonsSelecionados();
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
        botaoContinuar.setOnClickListener(this::vaiParaValidacao);
    }

    private void voltaParaPaginaPrincipal() {
        Intent iniciaPaginaPrincipal =
                new Intent(FormularioCheckListActivity.this,
                        PaginaPrincipalActivity.class);
        startActivityIfNeeded(iniciaPaginaPrincipal, CODIGO_PAGINA_PRINCIPAL_CHECKLIST);
    }

    private void vaiParaValidacao(View view) {
        Intent enviaDadosPreenchidos = new Intent(FormularioCheckListActivity.this,
                ValidacaoActivity.class);
        enviaDadosPreenchidos.putExtra(CHAVE_CHECKLIST, "checkList");
        enviaDadosPreenchidos.putExtra(CHAVE_POSICAO, "posicao");
        startActivity(enviaDadosPreenchidos);
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
        freio_traseiro = findViewById(R.id.formulario_rgfreioTraseiro);
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
        final int idSelecionado = saida_retorno.getCheckedRadioButtonId();
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
        final int idSelecionado = calibragem_pneu.getCheckedRadioButtonId();
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
        final int idSelecionado = freio_dianteiro.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoFreioDianteiro = String.valueOf(selecionado.getText());

        return situacaoFreioDianteiro;
    }

    public String getValorRadioFreioTraseiro() {
        String situacaoFreioTraseiro;
        final int idSelecionado = freio_traseiro.getCheckedRadioButtonId();
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
        final int idSelecionado = limpeza_radiador.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoLimpezaRadiador = String.valueOf(selecionado.getText());

        return situacaoLimpezaRadiador;
    }

    public String getValorRadioOleoMotor() {
        String situacaoOleoMotor;
        final int idSelecionado = oleo_motor.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoOleoMotor = String.valueOf(selecionado.getText());

        return situacaoOleoMotor;
    }

    public String getValorRadioFiltroOleo() {
        String situacaoFiltroOleo;
        final int idSelecionado = filtro_oleo.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoFiltroOleo = String.valueOf(selecionado.getText());

        return situacaoFiltroOleo;
    }

    public String getValorRadioParaChoqueDianteiro() {
        String situacaoParaChoqueDianteiro;
        final int idSelecionado = paraChoque_dianteiro.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoParaChoqueDianteiro = String.valueOf(selecionado.getText());

        return situacaoParaChoqueDianteiro;
    }

    public String getValorRadioParaChoqueTraseiro() {
        String situacaoParaChoqueTraseiro;
        final int idSelecionado = paraChoque_traseiro.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoParaChoqueTraseiro = String.valueOf(selecionado.getText());

        return situacaoParaChoqueTraseiro;
    }

    public String getValorRadioPlacasCaminhao() {
        String situacaoPlacasCaminhao;
        final int idSelecionado = placas_caminhao.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoPlacasCaminhao = String.valueOf(selecionado.getText());

        return situacaoPlacasCaminhao;
    }

    public String getValorRadioCintoSeguranca() {
        String situacaoCintoSeguranca;
        final int idSelecionado = cinto_seguranca.getCheckedRadioButtonId();
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
        final int idSelecionado = abertura_portas.getCheckedRadioButtonId();
        final RadioButton selecionado = findViewById(idSelecionado);
        situacaoAberturaPortas = String.valueOf(selecionado.getText());

        return situacaoAberturaPortas;
    }

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
            } else {
                saida_retorno.check(R.id.formulario_rbRetorno);
            }
        });
    }

    public void checkRadioButtonSelecionadoTracao() {
        tracao.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbtracao_ok) {
                tracao.check(R.id.formulario_rbtracao_ok);
            } else {
               tracao.check(R.id.formulario_rbtracao_nok);
            }
        });
    }

    public void checkRadioButtonSelecionadoCalibragemPneu() {
        calibragem_pneu.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbcalibragem_ok) {
                calibragem_pneu.check(R.id.formulario_rbcalibragem_ok);
            } else {
                calibragem_pneu.check(R.id.formulario_rbcalibragem_nok);
            }
        });
    }

    public void checkRadioButtonSelecionadoEstepe() {
        estepe.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbestepe_ok) {
               estepe.check(R.id.formulario_rbestepe_ok);
            } else {
                estepe.check(R.id.formulario_rbestepe_nok);
            }
        });
    }

    public void checkRadioButtonSelecionadoFreioDianteiro() {
        freio_dianteiro.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbfreioDianteiro_ok) {
                freio_dianteiro.check(R.id.formulario_rbfreioDianteiro_ok);
            } else {
                freio_dianteiro.check(R.id.formulario_rbfreioDianteiro_nok);
            }
        });
    }

    public void checkRadioButtonSelecionadoFreioTraseiro() {
        freio_traseiro.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbfreioTraseiro_ok) {
                freio_traseiro.check(R.id.formulario_rbfreioTraseiro_ok);
            } else {
                freio_traseiro.check(R.id.formulario_rbfreioTraseiro_nok);
            }
        });
    }

    public void checkRadioButtonSelecionadoBalanceamento() {
        balanceamento.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbBalanceamento_ok) {
                balanceamento.check(R.id.formulario_rbBalanceamento_ok);
            } else {
                balanceamento.check(R.id.formulario_rbBalanceamento_nok);
            }
        });
    }

    public void checkRadioButtonSelecionadoLimpezaRadiador() {
        limpeza_radiador.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbLimpezaRadiador_ok) {
                limpeza_radiador.check(R.id.formulario_rbLimpezaRadiador_ok);
            } else {
                limpeza_radiador.check(R.id.formulario_rbLimpezaRadiador_nok);
            }
        });
    }

    public void checkRadioButtonSelecionadoOleoMotor() {
        oleo_motor.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbOleoMotor_ok) {
                oleo_motor.check(R.id.formulario_rbOleoMotor_ok);
            } else {
                oleo_motor.check(R.id.formulario_rbOleoMotor_nok);
            }
        });
    }

    public void checkRadioButtonSelecionadoFiltroOleo() {
        filtro_oleo.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbFiltroOleo_ok) {
                filtro_oleo.check(R.id.formulario_rbFiltroOleo_ok);
            } else {
                filtro_oleo.check(R.id.formulario_rbFiltroOleo_nok);
            }
        });
    }

    public void checkRadioButtonSelecionadoParaChoqueDianteiro() {
        paraChoque_dianteiro.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbPcDianteiro_ok) {
                paraChoque_dianteiro.check(R.id.formulario_rbPcDianteiro_ok);
            } else {
                paraChoque_dianteiro.check(R.id.formulario_rbPCDianteiro_nok);
            }
        });
    }

    public void checkRadioButtonSelecionadoParaChoqueTraseiro() {
        paraChoque_traseiro.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbPcTraseiro_ok) {
                paraChoque_traseiro.check(R.id.formulario_rbPcTraseiro_ok);
            } else {
                paraChoque_traseiro.check(R.id.formulario_rbPCTraseiro_nok);
            }
        });
    }

    public void checkRadioButtonSelecionadoPlacasCaminhao() {
        placas_caminhao.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbPlacasCaminhao_ok) {
                placas_caminhao.check(R.id.formulario_rbPlacasCaminhao_ok);
            } else {
                placas_caminhao.check(R.id.formulario_rbPlacasCaminhao_nok);
            }
        });
    }

    public void checkRadioButtonSelecionadoCintoSeguranca() {
        cinto_seguranca.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbcinto_seguranca_ok) {
                cinto_seguranca.check(R.id.formulario_rbcinto_seguranca_ok);
            } else {
                cinto_seguranca.check(R.id.formulario_rbcinto_seguranca_nok);
            }
        });
    }

    public void checkRadioButtonSelecionadoPedais() {
        pedais.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbpedais_ok) {
                pedais.check(R.id.formulario_rbpedais_ok);
            } else {
                pedais.check(R.id.formulario_rbpedais_nok);
            }
        });
    }

    public void checkRadioButtonSelecionadoAberturaPortas() {
        abertura_portas.setOnCheckedChangeListener((radioGroup, id) -> {
            if (id == R.id.formulario_rbabertura_portas_ok) {
                abertura_portas.check(R.id.formulario_rbabertura_portas_ok);
            } else {
                abertura_portas.check(R.id.formulario_rbabertura_portas_nok);
            }
        });
    }
}
