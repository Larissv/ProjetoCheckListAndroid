package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_PAGINA_PRINCIPAL_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APPBAR_FAQ;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;

public class FaqActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_checklist);

        setTitle(TITULO_APPBAR_FAQ);

        configuraBotaoVoltarPaginaInicial();
    }

    private void configuraBotaoVoltarPaginaInicial() {
        Button botaoVoltar = findViewById(R.id.botao_retornar_tela_inicial);
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retonaParaPaginaInicial();
            }
        });
    }

    private void retonaParaPaginaInicial() {
        Intent iniciaPaginaPrincipal =
                new Intent(FaqActivity.this,
                        PaginaPrincipalActivity.class);
        startActivityIfNeeded(iniciaPaginaPrincipal, CODIGO_PAGINA_PRINCIPAL_CHECKLIST);
    }

}
