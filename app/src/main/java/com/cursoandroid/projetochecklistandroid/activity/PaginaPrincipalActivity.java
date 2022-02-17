package com.cursoandroid.projetochecklistandroid.activity;

import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.CODIGO_INSERE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.CODIGO_MOSTRA_CHECKLIST;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;

public class PaginaPrincipalActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR_TELAINICIAL = "ProCheck";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal_check_list);

        setTitle(TITULO_APP_BAR_TELAINICIAL);

        configuraBotaoNovoCheckList();
        configuraBotaoMostraTodosCheckLists();
    }

      private void configuraBotaoNovoCheckList() {
        Button botaoNovoCheckList= findViewById(
                R.id.botao_telainicial_novo_checklist);
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
        Button botaoMostrarTodosCheckLists= findViewById(
                R.id.botao_telainicial_mostrar_todos);
        botaoMostrarTodosCheckLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaiParaListaCheckLists();
            }
        });
    }

    private void vaiParaListaCheckLists() {
        Intent iniciaListarTodosCheckLists =
                new Intent(PaginaPrincipalActivity.this,
                        ListaCheckListsActivity.class);
        startActivityIfNeeded(iniciaListarTodosCheckLists, CODIGO_MOSTRA_CHECKLIST);
    }
}
