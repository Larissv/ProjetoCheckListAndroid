package com.cursoandroid.projetochecklistandroid.activity;

import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.CHAVE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.CHAVE_POSICAO;
import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.CODIGO_MOSTRA_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.CheckListConstantesActivity.POSICAO_INVALIDA;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;
import com.cursoandroid.projetochecklistandroid.model.CheckList;
import com.cursoandroid.projetochecklistandroid.retrofit.CheckListService;
import com.cursoandroid.projetochecklistandroid.retrofit.RetrofitConfig;

import java.io.Serializable;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FormularioCheckListActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_NOVO_CHECKLIST = "Novo Check List";
    public static final String TITULO_APPBAR_MOSTRA_CHECKLIST = "Visualização Checklist";
    private int posicaoRecebida = POSICAO_INVALIDA;
    private TextView dataC;
    private TextView hora;
    private TextView placa;
    private TextView motorista;
    public CheckList updateCheckList = new CheckList();
    RetrofitConfig retrofitConfig = new RetrofitConfig();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_check_list);

        setTitle(TITULO_APPBAR_NOVO_CHECKLIST);
        inicializaCampos();

        Intent dadosRecebidos = getIntent();

//        if(dadosRecebidos.hasExtra(CHAVE_CHECKLIST) &&
//                dadosRecebidos.hasExtra(CHAVE_POSICAO)){
//            setTitle(TITULO_APPBAR_MOSTRA_CHECKLIST);
//            CheckList checkListRecebido = (CheckList) dadosRecebidos
//                    .getSerializableExtra(CHAVE_CHECKLIST);
//            posicaoRecebida = dadosRecebidos.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
//            preencheCheckList(checkListRecebido);
//        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_formulario_check_list);

    }

    private void preencheCheckList(CheckList checkListPreenchido) {
        dataC.setText(checkListPreenchido.getDataC());
        hora.setText(checkListPreenchido.getHora());
        placa.setText(checkListPreenchido.getPlaca());
        motorista.setText(checkListPreenchido.getMotorista());

    }

    private void inicializaCampos() {
        dataC = findViewById(R.id.item_check_list_data);
        hora = findViewById(R.id.item_check_list_hora);
        placa = findViewById(R.id.item_check_list_placa);
        motorista = findViewById(R.id.item_check_list_motorista);

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_salva_check_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (updateCheckList.getPlaca() != null) {
            assert false;
            CheckList checkListMostrado = criaCheckList();
            retornaCheckList(checkListMostrado);
            Observable<List<CheckList>> observable = retrofitConfig.getRetrofit().create(
                    CheckListService.class).mostraTodosCheckLists();
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<CheckList>>() {
                        @Override
                        public void onCompleted() {
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(FormularioCheckListActivity.this,
                                    "Erro", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNext(List<CheckList> checkLists) {
                            Toast.makeText(FormularioCheckListActivity.this, "Sucesso!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
            return super.onOptionsItemSelected(item);
        }

        if (validaMenuSalvar(item)) {
            CheckList checkListCriado = criaCheckList();
            Observable<CheckList> observable = retrofitConfig.getRetrofit().create(
                    CheckListService.class).cadastraNovoCheckList(checkListCriado);
            observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<CheckList>() {
                        @Override
                        public void onCompleted() {
                            finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(FormularioCheckListActivity.this,
                                    "Erro ao salvar", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onNext(CheckList checkList) {
                            Toast.makeText(FormularioCheckListActivity.this,
                                    "Check List salvo com sucesso!", Toast.LENGTH_SHORT).show();

                        }
                    });
        }
        return super.onOptionsItemSelected(item);
    }

    private void retornaCheckList(CheckList checkList) {
        Intent resultado = new Intent();
        resultado.putExtra(CHAVE_CHECKLIST, checkList);
        resultado.putExtra(CHAVE_POSICAO, posicaoRecebida);
        setResult(CODIGO_MOSTRA_CHECKLIST, resultado);
    }

    private CheckList criaCheckList() {
        return new CheckList(dataC.getText().toString(),
                hora.getText(),toString(),
                placa.getText().toString(),
                motorista.getText().toString());
    }

    public boolean validaMenuSalvar(@NonNull MenuItem item) {
        return item.getItemId() == R.id.menu_formulario_salva_check_list;
    }
}
