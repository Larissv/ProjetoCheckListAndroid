package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_PAGINA_PRINCIPAL_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_VALIDACAO_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.POSICAO_INVALIDA;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APPBAR_NOVO_CHECKLIST;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;
import com.cursoandroid.projetochecklistandroid.model.CheckList;
import com.cursoandroid.projetochecklistandroid.retrofit.service.CheckListService;
import com.cursoandroid.projetochecklistandroid.retrofit.RetrofitConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FormularioCheckListActivity extends AppCompatActivity {

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
    private int idSelecionado;
    RadioButton selecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_check_list);

        setTitle(TITULO_APPBAR_NOVO_CHECKLIST);
        inicializaCampos();
        configuraBotaoCancelar();
        configuraBotaoSalvar();
        verificaRadioButtonsSelecionado();
        configuraCalendario();
        configuraEscolhaHora();

    }

    private void configuraBotaoCancelar() {
        Button botaoCancelar = findViewById(
                R.id.botao_formulario_cancelar);
        botaoCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltaParaPaginaPrincipal();
            }
        });
    }

    private void voltaParaPaginaPrincipal() {
        Intent iniciaPaginaPrincipal =
                new Intent(FormularioCheckListActivity.this,
                        PaginaPrincipalActivity.class);
        startActivityIfNeeded(iniciaPaginaPrincipal, CODIGO_PAGINA_PRINCIPAL_CHECKLIST);
    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.botao_formulario_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // salvaCheckList();
                vaiParaValidacao();
            }
        });
    }

    private void vaiParaValidacao() {
        Intent iniciaValidacao =
                new Intent(FormularioCheckListActivity.this,
                        ValidacaoActivity.class);
        startActivityIfNeeded(iniciaValidacao, CODIGO_VALIDACAO_CHECKLIST);
    }

    public void salvaCheckList() {
        CheckList checkListCriado = criaCheckList();
        verificaRadioButtonsSelecionado();
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
                                "Erro!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(CheckList checkList) {
                        Toast.makeText(FormularioCheckListActivity.this,
                                "Check List salvo!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public EditText configuraEscolhaHora() {
        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        FormularioCheckListActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        hora.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                timePickerDialog.setTitle("Selecione a hora");
                timePickerDialog.show();
            }
        });
        return null;
    }

    public EditText configuraCalendario() {
        dataC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        FormularioCheckListActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                calendar.set(year, month, day);
                                String format = "dd/MM/yyyy";
                                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
                                Date date;

                                try {
                                    date = sdf.parse(sdf.format(calendar.getTime()));
                                    String dayS = new SimpleDateFormat(
                                            "dd", Locale.ENGLISH).format(date);
                                    String monthS = new SimpleDateFormat(
                                            "MM", Locale.ENGLISH).format(date);
                                    String yearS = new SimpleDateFormat(
                                            "yyyy", Locale.ENGLISH).format(date);

                                    dataC.setText((dayS + "/" + monthS + "/" + yearS));
                                } catch (ParseException ignored) {
                                }
                            }
                        }, year, month, day);
                datePickerDialog.show();
                datePickerDialog.getDatePicker();
            }
        });
        return null;
    }

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

    @NonNull
    private CheckList criaCheckList() {
        return new CheckList(
                pegaValorRadioSaidaRetorno(),
                dataC.getText().toString(),
                hora.getText().toString(),
                placa.getText().toString(),
                motorista.getText().toString(),
                km.getText().toString(),
                pegaValorRadioTracao(),
                pegaValorRadioCalibragemPneu(),
                pegaValorRadioEstepe(),
                pegaValorRadioFreioDianteiro(),
                pegaValorRadioFreioTraseiro(),
                pegaValorRadioBalanceamento(),
                pegaValorRadioLimpezaRadiador(),
                pegaValorRadioOleoMotor(),
                pegaValorRadioFiltroOleo(),
                pegaValorRadioParaChoqueDianteiro(),
                pegaValorRadioParaChoqueTraseiro(),
                pegaValorRadioPlacasCaminhao(),
                pegaValorRadioCintoSeguranca(),
                pegaValorRadioCintoSPedais(),
                pegaValorRadioAberturaPortas());
    }

    public void verificaRadioButtonsSelecionado() {
        verificaRadioButtonSelecionadoSaidaRetorno();
        verificaRadioButtonSelecionadoTracao();
        verificaRadioButtonSelecionadoCalibragemPneu();
        verificaRadioButtonSelecionadoEstepe();
        verificaRadioButtonSelecionadoFreioDianteiro();
        verificaRadioButtonSelecionadoFreioTraseiro();
        verificaRadioButtonSelecionadoBalanceamento();
        verificaRadioButtonSelecionadoLimpezaRadiador();
        verificaRadioButtonSelecionadoOleoMotor();
        verificaRadioButtonSelecionadoFiltroOleo();
        verificaRadioButtonSelecionadoParaChoqueDianteiro();
        verificaRadioButtonSelecionadoParaChoqueTraseiro();
        verificaRadioButtonSelecionadoPlacasCaminhao();
        verificaRadioButtonSelecionadoCintoSeguranca();
        verificaRadioButtonSelecionadoPedais();
        verificaRadioButtonSelecionadoAberturaPortas();
    }

    public String pegaValorRadioSaidaRetorno() {
        String situacaoSaidaRetorno;
        idSelecionado = saidaRetorno.getCheckedRadioButtonId();
        selecionado = findViewById(idSelecionado);
        situacaoSaidaRetorno = String.valueOf(selecionado.getText());

        return situacaoSaidaRetorno;
    }

    public void verificaRadioButtonSelecionadoSaidaRetorno() {
        saidaRetorno.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.formulario_rbSaida) {
                    saidaRetorno.check(R.id.formulario_rbSaida);
                    Toast.makeText(getApplicationContext(),
                            "Saída", Toast.LENGTH_SHORT).show();
                } else {
                    saidaRetorno.check(R.id.formulario_rbRetorno);
                    Toast.makeText(getApplicationContext(),
                            "Retorno", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String pegaValorRadioTracao() {
        String situacaoTracao;
        idSelecionado = tracao.getCheckedRadioButtonId();
        selecionado = findViewById(idSelecionado);
        situacaoTracao = String.valueOf(selecionado.getText());

        return situacaoTracao;
    }

    public void verificaRadioButtonSelecionadoTracao() {
        tracao.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.formulario_rbtracao_ok) {
                    Toast.makeText(getApplicationContext(), "OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar tração - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String pegaValorRadioCalibragemPneu() {
        String situacaoCalibragemPneu;
        idSelecionado = tracao.getCheckedRadioButtonId();
        selecionado = findViewById(idSelecionado);
        situacaoCalibragemPneu = String.valueOf(selecionado.getText());

        return situacaoCalibragemPneu;
    }

    public void verificaRadioButtonSelecionadoCalibragemPneu() {
        calibragemPneu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.formulario_rbcalibragem_ok) {
                    Toast.makeText(getApplicationContext(), "OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar calibragem do pneu - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String pegaValorRadioEstepe() {
        String situacaoEstepe;
        idSelecionado = tracao.getCheckedRadioButtonId();
        selecionado = findViewById(idSelecionado);
        situacaoEstepe = String.valueOf(selecionado.getText());

        return situacaoEstepe;
    }

    public void verificaRadioButtonSelecionadoEstepe() {
        estepe.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.formulario_rbestepe_ok) {
                    Toast.makeText(getApplicationContext(), "OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar estepe - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String pegaValorRadioFreioDianteiro() {
        String situacaoFreioDianteiro;
        idSelecionado = tracao.getCheckedRadioButtonId();
        selecionado = findViewById(idSelecionado);
        situacaoFreioDianteiro = String.valueOf(selecionado.getText());

        return situacaoFreioDianteiro;
    }

    public void verificaRadioButtonSelecionadoFreioDianteiro() {
        freioDianteiro.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.formulario_rbfreioDianteiro_ok) {
                    Toast.makeText(getApplicationContext(), "OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar freio dianteiro - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String pegaValorRadioFreioTraseiro() {
        String situacaoFreioTraseiro;
        idSelecionado = freioTraseiro.getCheckedRadioButtonId();
        selecionado = findViewById(idSelecionado);
        situacaoFreioTraseiro = String.valueOf(selecionado.getText());

        return situacaoFreioTraseiro;
    }

    public void verificaRadioButtonSelecionadoFreioTraseiro() {
        freioTraseiro.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.formulario_rbfreioTraseiro_ok) {
                    Toast.makeText(getApplicationContext(), "OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar freio traseiro - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String pegaValorRadioBalanceamento() {
        String situacaoBalanceamento;
        idSelecionado = freioTraseiro.getCheckedRadioButtonId();
        selecionado = findViewById(idSelecionado);
        situacaoBalanceamento = String.valueOf(selecionado.getText());

        return situacaoBalanceamento;
    }

    public void verificaRadioButtonSelecionadoBalanceamento() {
        balanceamento.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.formulario_rbBalanceamento_ok) {
                    Toast.makeText(getApplicationContext(), "OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar balanceamento - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String pegaValorRadioLimpezaRadiador() {
        String situacaoLimpezaRadiador;
        idSelecionado = freioTraseiro.getCheckedRadioButtonId();
        selecionado = findViewById(idSelecionado);
        situacaoLimpezaRadiador = String.valueOf(selecionado.getText());

        return situacaoLimpezaRadiador;
    }

    public void verificaRadioButtonSelecionadoLimpezaRadiador() {
        limpezaRadiador.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.formulario_rbLimpezaRadiador_ok) {
                    Toast.makeText(getApplicationContext(), "OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar a limpeza do radiador - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String pegaValorRadioOleoMotor() {
        String situacaoOleoMotor;
        idSelecionado = freioTraseiro.getCheckedRadioButtonId();
        selecionado = findViewById(idSelecionado);
        situacaoOleoMotor = String.valueOf(selecionado.getText());

        return situacaoOleoMotor;
    }

    public void verificaRadioButtonSelecionadoOleoMotor() {
        oleoMotor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.formulario_rbOleoMotor_ok) {
                    Toast.makeText(getApplicationContext(), "OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar o óleo do motor - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String pegaValorRadioFiltroOleo() {
        String situacaoFiltroOleo;
        idSelecionado = freioTraseiro.getCheckedRadioButtonId();
        selecionado = findViewById(idSelecionado);
        situacaoFiltroOleo = String.valueOf(selecionado.getText());

        return situacaoFiltroOleo;
    }

    public void verificaRadioButtonSelecionadoFiltroOleo() {
        filtroOleo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.formulario_rbFiltroOleo_ok) {
                    Toast.makeText(getApplicationContext(), "OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar o filtro de óleo - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String pegaValorRadioParaChoqueDianteiro() {
        String situacaoParaChoqueDianteiro;
        idSelecionado = freioTraseiro.getCheckedRadioButtonId();
        selecionado = findViewById(idSelecionado);
        situacaoParaChoqueDianteiro = String.valueOf(selecionado.getText());

        return situacaoParaChoqueDianteiro;
    }

    public void verificaRadioButtonSelecionadoParaChoqueDianteiro() {
        paraChoqueDianteiro.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.formulario_rbPcDianteiro_ok) {
                    Toast.makeText(getApplicationContext(), "OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar o para-choque dianteiro - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String pegaValorRadioParaChoqueTraseiro() {
        String situacaoParaChoqueTraseiro;
        idSelecionado = freioTraseiro.getCheckedRadioButtonId();
        selecionado = findViewById(idSelecionado);
        situacaoParaChoqueTraseiro = String.valueOf(selecionado.getText());

        return situacaoParaChoqueTraseiro;
    }

    public void verificaRadioButtonSelecionadoParaChoqueTraseiro() {
        paraChoqueTraseiro.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.formulario_rbPcTraseiro_ok) {
                    Toast.makeText(getApplicationContext(), "OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar o para-choque traseiro - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String pegaValorRadioPlacasCaminhao() {
        String situacaoPlacasCaminhao;
        idSelecionado = freioTraseiro.getCheckedRadioButtonId();
        selecionado = findViewById(idSelecionado);
        situacaoPlacasCaminhao = String.valueOf(selecionado.getText());

        return situacaoPlacasCaminhao;
    }

    public void verificaRadioButtonSelecionadoPlacasCaminhao() {
        placasCaminhao.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.formulario_rbPlacasCaminhao_ok) {
                    Toast.makeText(getApplicationContext(), "OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar as placas - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String pegaValorRadioCintoSeguranca() {
        String situacaoCintoSeguranca;
        idSelecionado = freioTraseiro.getCheckedRadioButtonId();
        selecionado = findViewById(idSelecionado);
        situacaoCintoSeguranca = String.valueOf(selecionado.getText());

        return situacaoCintoSeguranca;
    }

    public void verificaRadioButtonSelecionadoCintoSeguranca() {
        cintoSeguranca.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.formulario_rbcinto_seguranca_ok) {
                    Toast.makeText(getApplicationContext(), "OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar cintos de segurança - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String pegaValorRadioCintoSPedais() {
        String situacaoPedais;
        idSelecionado = freioTraseiro.getCheckedRadioButtonId();
        selecionado = findViewById(idSelecionado);
        situacaoPedais = String.valueOf(selecionado.getText());

        return situacaoPedais;
    }

    public void verificaRadioButtonSelecionadoPedais() {
        pedais.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.formulario_rbpedais_ok) {
                    Toast.makeText(getApplicationContext(), "OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar os pedais - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String pegaValorRadioAberturaPortas() {
        String situacaoAberturaPortas;
        idSelecionado = freioTraseiro.getCheckedRadioButtonId();
        selecionado = findViewById(idSelecionado);
        situacaoAberturaPortas = String.valueOf(selecionado.getText());

        return situacaoAberturaPortas;
    }

    public void verificaRadioButtonSelecionadoAberturaPortas() {
        aberturaPortas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if (id == R.id.formulario_rbabertura_portas_ok) {
                    Toast.makeText(getApplicationContext(), "OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar abertura de portas - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
