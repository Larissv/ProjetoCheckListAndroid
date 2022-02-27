package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_PAGINA_PRINCIPAL_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APP_BAR_FINALIZACAO;

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

        setTitle(TITULO_APP_BAR_FINALIZACAO);

        configuraBotaoVoltarInicio();
    }

    private void configuraBotaoVoltarInicio() {
        Button botaoVoltarInicio = findViewById(
                R.id.botao_voltar_inicio_finalizacao);
        botaoVoltarInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    voltaParaPaginaInicial();
            }
        });
    }

    private void voltaParaPaginaInicial() {
        Intent voltaPaginaInicial =
                new Intent(FinalizacaoActivity.this,
                        PaginaPrincipalActivity.class);
        startActivityIfNeeded(voltaPaginaInicial, CODIGO_PAGINA_PRINCIPAL_CHECKLIST);
    }
}
