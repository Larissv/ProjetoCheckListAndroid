package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_INSERE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APP_BAR_VALIDACAO;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;

public class ValidacaoActivity extends AppCompatActivity {

    private EditText senhaValidacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist_validacao);

        setTitle(TITULO_APP_BAR_VALIDACAO);

        senhaValidacao = findViewById(R.id.codigo_validacao);

        configuraBotaoValidar();
    }

    private void configuraBotaoValidar() {
        Button botaoValidar = findViewById(
                R.id.botao_validacao);
        botaoValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (senhaValidacao != null){
                    vaiParaFinalizar();
                }
            }
        });
    }

    private void vaiParaFinalizar() {
        Intent iniciaFinalizar =
                new Intent(ValidacaoActivity.this,
                        FinalizacaoActivity.class);
        startActivityIfNeeded(iniciaFinalizar, CODIGO_INSERE_CHECKLIST);
    }
}
