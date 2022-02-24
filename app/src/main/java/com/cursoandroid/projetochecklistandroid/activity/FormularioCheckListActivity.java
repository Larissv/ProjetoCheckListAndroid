package com.cursoandroid.projetochecklistandroid.activity;

import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.CHAVE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.CHAVE_POSICAO;
import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.CODIGO_INSERE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.CODIGO_MOSTRA_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.CODIGO_PAGINA_PRINCIPAL_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.POSICAO_INVALIDA;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;
import com.cursoandroid.projetochecklistandroid.model.CheckList;
import com.cursoandroid.projetochecklistandroid.retrofit.CheckListService;
import com.cursoandroid.projetochecklistandroid.retrofit.RetrofitConfig;

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
        configuraBotaoCancelar();
        configuraBotaoSalvar();
        verificaRadioButtonsSelecionado();

        Intent dadosRecebidos = getIntent();

        if(dadosRecebidos.hasExtra(CHAVE_CHECKLIST) &&
                dadosRecebidos.hasExtra(CHAVE_POSICAO)){
            setTitle(TITULO_APPBAR_MOSTRA_CHECKLIST);
            CheckList checkListRecebido = (CheckList) dadosRecebidos
                    .getSerializableExtra(CHAVE_CHECKLIST);
            updateCheckList = checkListRecebido;
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

    private void inicializaCampos() {
        saidaRetorno = (RadioGroup) findViewById(R.id.formulario_rgSaidaRetorno);
        dataC = (EditText) findViewById(R.id.formulario_check_list_data);
        hora = (EditText) findViewById(R.id.formulario_check_list_hora);
        placa = (EditText) findViewById(R.id.formulario_check_list_placa);
        motorista = (EditText) findViewById(R.id.formulario_check_list_motorista);
        km = (EditText) findViewById(R.id.formulario_check_list_km);
        tracao = (RadioGroup) findViewById(R.id.formulario_rgtracao_pneu);
        calibragemPneu = (RadioGroup) findViewById(R.id.formulario_rgcalibragem_pneu);
        estepe = (RadioGroup) findViewById(R.id.formulario_rgestepe_pneu);
        freioDianteiro = (RadioGroup) findViewById(R.id.formulario_rgfreioDianteiro);
        freioTraseiro = (RadioGroup) findViewById(R.id.formulario_rgfreioTraseiro);
        balanceamento = (RadioGroup) findViewById(R.id.formulario_rgBalanceamento);
        limpezaRadiador = (RadioGroup) findViewById(R.id.formulario_rgLimpezaRadiador);
        oleoMotor = (RadioGroup) findViewById(R.id.formulario_rgOleoMotor);
        filtroOleo = (RadioGroup) findViewById(R.id.formulario_rgfiltroOleo);
        paraChoqueDianteiro = (RadioGroup) findViewById(R.id.formulario_rgPCDianteiro);
        paraChoqueTraseiro = (RadioGroup) findViewById(R.id.formulario_rgPCTraseiro);
        placasCaminhao = (RadioGroup) findViewById(R.id.formulario_rgPlacasCaminhao);
        cintoSeguranca = (RadioGroup) findViewById(R.id.formulario_rgcinto_seguranca);
        pedais = (RadioGroup) findViewById(R.id.formulario_rgpedais);
        aberturaPortas = (RadioGroup) findViewById(R.id.formulario_rgabertura_portas);

    }

    private void configuraBotaoSalvar() {
        Button botaoSalvar = findViewById(R.id.botao_formulario_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                                        "Erro!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(CheckList checkList) {
                                Toast.makeText(FormularioCheckListActivity.this,
                                        "Check List salvo com sucesso!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void vaiParaListaCheckLists() {
        Intent iniciaListaAtualizadaCheckList =
                new Intent(FormularioCheckListActivity.this,
                        ListaCheckListsActivity.class);
        startActivityIfNeeded(iniciaListaAtualizadaCheckList, CODIGO_MOSTRA_CHECKLIST);
    }

    private void retornaCheckList(CheckList checkList) {
        Intent resultado = new Intent();
        resultado.putExtra(CHAVE_CHECKLIST, checkList);
        resultado.putExtra(CHAVE_POSICAO, posicaoRecebida);
        setResult(CODIGO_MOSTRA_CHECKLIST, resultado);
    }

    @NonNull
    private CheckList criaCheckList() {
        return new CheckList(
                saidaRetorno.getCheckedRadioButtonId(),
                dataC.getText().toString(),
                hora.getText().toString(),
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
                cintoSeguranca.getCheckedRadioButtonId(),
                pedais.getCheckedRadioButtonId(),
                aberturaPortas.getCheckedRadioButtonId());
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

    public void verificaRadioButtonSelecionadoSaidaRetorno(){
         saidaRetorno.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.formulario_rbSaida) {
                    Toast.makeText(getApplicationContext(),
                            "Saída", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Retorno", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.formulario_rbSaida:
                if (checked)
                    break;
            case R.id.formulario_rbRetorno:
                if (checked)
                    break;
        }
    }

    public void verificaRadioButtonSelecionadoTracao(){
        tracao.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.formulario_rbtracao_ok) {
                    Toast.makeText(getApplicationContext(), "Tração - OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar tração - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verificaRadioButtonSelecionadoCalibragemPneu(){
        calibragemPneu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.formulario_rbcalibragem_ok) {
                    Toast.makeText(getApplicationContext(), "Calibragem do pneu - OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar calibragem do pneu - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verificaRadioButtonSelecionadoEstepe(){
        estepe.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.formulario_rbestepe_ok) {
                    Toast.makeText(getApplicationContext(), "Estepe - OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar estepe - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verificaRadioButtonSelecionadoFreioDianteiro(){
        freioDianteiro.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.formulario_rbfreioDianteiro_ok) {
                    Toast.makeText(getApplicationContext(), "Freio dianteiro - OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar freio dianteiro - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verificaRadioButtonSelecionadoFreioTraseiro(){
        freioTraseiro.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.formulario_rbfreioTraseiro_ok) {
                    Toast.makeText(getApplicationContext(), "Freio traseiro - OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar freio traseiro - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verificaRadioButtonSelecionadoBalanceamento(){
        balanceamento.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.formulario_rbBalanceamento_ok) {
                    Toast.makeText(getApplicationContext(), "Balanceamento - OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar balanceamento - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verificaRadioButtonSelecionadoLimpezaRadiador(){
        limpezaRadiador.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.formulario_rbLimpezaRadiador_ok) {
                    Toast.makeText(getApplicationContext(), "Limpeza do Radiador - OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar limpeza do Radiador - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verificaRadioButtonSelecionadoOleoMotor(){
        oleoMotor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.formulario_rbOleoMotor_ok) {
                    Toast.makeText(getApplicationContext(), "Óleo do motor - OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar Óleo do motor - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verificaRadioButtonSelecionadoFiltroOleo(){
        filtroOleo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.formulario_rbFiltroOleo_ok) {
                    Toast.makeText(getApplicationContext(), "Filtro de óleo - OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar filtro de óleo - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verificaRadioButtonSelecionadoParaChoqueDianteiro(){
        paraChoqueDianteiro.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.formulario_rbPcDianteiro_ok) {
                    Toast.makeText(getApplicationContext(), "Para-choque dianteiro - OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar para-choque dianteiro - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verificaRadioButtonSelecionadoParaChoqueTraseiro(){
        paraChoqueTraseiro.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.formulario_rbPcTraseiro_ok) {
                    Toast.makeText(getApplicationContext(), "Para-choque traseiro - OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar para-choque traseiro - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verificaRadioButtonSelecionadoPlacasCaminhao(){
        placasCaminhao.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.formulario_rbPlacasCaminhao_ok) {
                    Toast.makeText(getApplicationContext(), "Placas - OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar placas - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verificaRadioButtonSelecionadoCintoSeguranca(){
        cintoSeguranca.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.formulario_rbcinto_seguranca_ok) {
                    Toast.makeText(getApplicationContext(), "Cintos de segurança - OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar cintos de segurança - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verificaRadioButtonSelecionadoPedais(){
        pedais.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.formulario_rbpedais_ok) {
                    Toast.makeText(getApplicationContext(), "Pedais - OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar pedais - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verificaRadioButtonSelecionadoAberturaPortas(){
        aberturaPortas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.formulario_rbabertura_portas_ok) {
                    Toast.makeText(getApplicationContext(), "Abertura de portas - OK",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "ATENÇÃO! Verificar abertura de portas - NOK",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verificaRadioButtonsSelecionado(){
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



}
