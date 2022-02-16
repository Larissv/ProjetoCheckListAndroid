package com.cursoandroid.projetochecklistandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;
import com.cursoandroid.projetochecklistandroid.model.CheckList;
import com.cursoandroid.projetochecklistandroid.retrofit.RetrofitConfig;

public class FormularioCheckListActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_NOVO_CHECKLIST = "Novo Checklist";
    public static final String TITULO_APPBAR_MOSTRA_CHECKLIST = "Visualização Checklist";
    private int posicaoRecebida = -1;
    private TextView dataC;
    private TextView hora;
    private TextView placa;
    private TextView motorista;
    public CheckList checkListPreenchido = new CheckList();
    RetrofitConfig retrofitConfig = new RetrofitConfig();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_check_list);

        setTitle(TITULO_APPBAR_NOVO_CHECKLIST);
        inicializaCampos();

        Intent dadosRecebidos = getIntent();

        if(dadosRecebidos.hasExtra("funcionario") &&
                dadosRecebidos.hasExtra("posicao")){
            setTitle(TITULO_APPBAR_MOSTRA_CHECKLIST);
            CheckList checkListRecebido = (CheckList) dadosRecebidos
                    .getSerializableExtra("funcionario");
            checkListPreenchido = checkListRecebido;
            posicaoRecebida = dadosRecebidos.getIntExtra("posicao", -1);
            preencheCheckList(checkListRecebido);
        }
    }

    private void preencheCheckList(CheckList checkListPreenchido) {
        dataC.setText(checkListPreenchido.getDataC());
        hora.setText(checkListPreenchido.getHora());
        placa.setText(checkListPreenchido.getPlaca());
        motorista.setText(checkListPreenchido.getMotorista());

    }

    private void inicializaCampos() {
        dataC = findViewById(R.id.item_check_list_data);

    }
}
