package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_PAGINA_PRINCIPAL_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APPBAR_HELP;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;

public class HelpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_checklist);

        setTitle(TITULO_APPBAR_HELP);

        configuraBotaoVoltarPaginaInicial();
    }

    private void configuraBotaoVoltarPaginaInicial() {
        Button botaoVoltar = findViewById(R.id.botao_retornar_tela_inicial);
        botaoVoltar.setOnClickListener(view -> retonaParaPaginaInicial());
    }

    private void retonaParaPaginaInicial() {
        Intent iniciaPaginaPrincipal =
                new Intent(HelpActivity.this,
                        PaginaPrincipalActivity.class);
        startActivityIfNeeded(iniciaPaginaPrincipal, CODIGO_PAGINA_PRINCIPAL_CHECKLIST);
    }

}
