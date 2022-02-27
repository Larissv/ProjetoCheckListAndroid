package com.cursoandroid.projetochecklistandroid.activity;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_INSERE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APP_BAR_TELAINICIAL;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PaginaPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal_check_list);

        setTitle(TITULO_APP_BAR_TELAINICIAL);

        configuraBotaoNovoCheckList();
        configuraBotaoMostraTodosCheckLists();
    }

    private void configuraBotaoNovoCheckList() {
        FloatingActionButton botaoNovoCheckList = findViewById(
                R.id.botao_fab_pagina_inicial_novo_checklist);
        botaoNovoCheckList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaiParaFormularioCheckList();
            }
        });
    }

    private void vaiParaFormularioCheckList() {
        Intent iniciaFormularioCheckList =
                new Intent(PaginaPrincipalActivity.this,
                        FormularioCheckListActivity.class);
        startActivityIfNeeded(iniciaFormularioCheckList, CODIGO_INSERE_CHECKLIST);
    }

    private void configuraBotaoMostraTodosCheckLists() {
        FloatingActionButton botaoMostrarTodosCheckLists = findViewById(
                R.id.botao_fab_pagina_inicial_lista_checklist);
        botaoMostrarTodosCheckLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaiParaListaCheckLists();
            }
        });
    }

    private void vaiParaListaCheckLists() {
        startActivity(new Intent(PaginaPrincipalActivity.this,
                ListaCheckListsActivity.class));
    }
}
