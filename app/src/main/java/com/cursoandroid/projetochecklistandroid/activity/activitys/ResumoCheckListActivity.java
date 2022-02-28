package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_POSICAO;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_PAGINA_PRINCIPAL_CHECKLIST;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;
import com.cursoandroid.projetochecklistandroid.model.CheckList;
import com.cursoandroid.projetochecklistandroid.retrofit.adapter.ListaCheckListsAdapter;
import com.cursoandroid.projetochecklistandroid.retrofit.config.RetrofitConfig;

import java.util.List;

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
    private ListaCheckListsAdapter adapter;
    private List<CheckList> checkLists;
    private int posicaoRecebida = POSICAO_INVALIDA;
    public CheckList updateCheckList = new CheckList();

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
            checkLists.remove(itemId);
            adapter.removeCheckList(itemId, posicaoRecebida);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraBotaoConcluir() {
        Button botaoConcluir = findViewById(
                R.id.botao_resumo_concluir);
        botaoConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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


