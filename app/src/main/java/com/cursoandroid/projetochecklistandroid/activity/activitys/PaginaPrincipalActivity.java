package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_INSERE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APPBAR_TELAINICIAL;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PaginaPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal_checklist);

        setTitle(TITULO_APPBAR_TELAINICIAL);

        configuraBotaoNovoCheckList();
        configuraBotaoMostraTodosCheckLists();
        configuraBotaoChat();
        configuraBotaoHelp();
    }

    private void configuraBotaoNovoCheckList() {
        FloatingActionButton botaoNovoCheckList = findViewById(
                R.id.botao_fab_pagina_inicial_novo_checklist);
        botaoNovoCheckList.setOnClickListener(view -> vaiParaFormularioCheckList());
    }

    private void configuraBotaoMostraTodosCheckLists() {
        FloatingActionButton botaoMostrarTodosCheckLists = findViewById(
                R.id.botao_fab_pagina_inicial_lista_checklist);
        botaoMostrarTodosCheckLists.setOnClickListener(view -> vaiParaListaCheckLists());
    }

    private void configuraBotaoChat() {
        FloatingActionButton botaoChat = findViewById(R.id.botao_fab_pagina_inicial_chat);
        botaoChat.setOnClickListener(view -> vaiParaChat());
    }

    private void configuraBotaoHelp() {
        FloatingActionButton botaoHelp = findViewById(R.id.botao_fab_pagina_inicial_help);
        botaoHelp.setOnClickListener(view -> vaiParaHelp());
    }

    private void vaiParaFormularioCheckList() {
        Intent iniciaFormularioCheckList =
                new Intent(PaginaPrincipalActivity.this,
                        FormularioCheckListActivity.class);
        startActivityIfNeeded(iniciaFormularioCheckList, CODIGO_INSERE_CHECKLIST);
    }

    private void vaiParaListaCheckLists() {
        startActivity(new Intent(PaginaPrincipalActivity.this,
                ListaCheckListsActivity.class));
    }

    private void vaiParaChat() {
        startActivity(new Intent(PaginaPrincipalActivity.this, ChatActivity.class));
    }

    private void vaiParaHelp() {
        startActivity(new Intent(PaginaPrincipalActivity.this, HelpActivity.class));
    }
}
