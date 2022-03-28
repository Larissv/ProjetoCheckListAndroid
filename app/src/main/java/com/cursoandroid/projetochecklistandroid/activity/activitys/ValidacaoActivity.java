package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APPBAR_VALIDACAO;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.cursoandroid.projetochecklistandroid.R;

public class ValidacaoActivity extends AppCompatActivity {

    private EditText senhaValidacao;
    private FormularioCheckListActivity formularioCheckListActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao_checklist);

        setTitle(TITULO_APPBAR_VALIDACAO);

        senhaValidacao = findViewById(R.id.codigo_validacao);

        configuraBotaoValidar();
    }

    private void configuraBotaoValidar() {
        Button botaoValidar = findViewById(
                R.id.botao_validacao);
        botaoValidar.setOnClickListener(view -> {
            if (senhaValidacao.getText().toString().equals("")) {
                Toast.makeText(ValidacaoActivity.this, "Código de verificação inválido",
                        Toast.LENGTH_SHORT).show();
            } else {
                formularioCheckListActivity.salvaCheckList();
            }
        });
    }
}
