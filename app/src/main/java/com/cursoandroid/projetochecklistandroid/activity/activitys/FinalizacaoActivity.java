package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_MOSTRA_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_PAGINA_PRINCIPAL_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APPBAR_FINALIZACAO;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;

public class FinalizacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizacao_checklist);

        setTitle(TITULO_APPBAR_FINALIZACAO);

        configuraBotaoVoltarInicio();
        configuraBotaoIrParaListaCheckLists();
    }

    private void configuraBotaoIrParaListaCheckLists() {
        Button botaoIrParaListaCheckLists = findViewById(R.id.botao_mostrar_checklists_finalizacao);
        botaoIrParaListaCheckLists.setOnClickListener(view -> vaiParaListaCheckLists());
    }

    private void vaiParaListaCheckLists() {
        Intent voltaPaginaInicial =
                new Intent(FinalizacaoActivity.this,
                        ListaCheckListsActivity.class);
        startActivityIfNeeded(voltaPaginaInicial, CODIGO_MOSTRA_CHECKLIST);
    }

    private void configuraBotaoVoltarInicio() {
        Button botaoVoltarInicio = findViewById(R.id.botao_voltar_inicio_finalizacao);
        botaoVoltarInicio.setOnClickListener(view -> voltaParaPaginaInicial());
    }

    private void voltaParaPaginaInicial() {
        Intent voltaPaginaInicial =
                new Intent(FinalizacaoActivity.this,
                        PaginaPrincipalActivity.class);
        startActivityIfNeeded(voltaPaginaInicial, CODIGO_PAGINA_PRINCIPAL_CHECKLIST);
    }
}
