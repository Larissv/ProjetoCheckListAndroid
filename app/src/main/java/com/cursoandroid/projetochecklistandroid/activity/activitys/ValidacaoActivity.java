package com.cursoandroid.projetochecklistandroid.activity.activitys;

import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CHAVE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_FINALIZACAO_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.CODIGO_INSERE_CHECKLIST;
import static com.cursoandroid.projetochecklistandroid.activity.constantes.CheckListConstantesActivity.TITULO_APPBAR_VALIDACAO;

import static java.lang.String.valueOf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cursoandroid.projetochecklistandroid.R;
import com.cursoandroid.projetochecklistandroid.activity.service.CheckListService;
import com.cursoandroid.projetochecklistandroid.model.CheckList;
import com.cursoandroid.projetochecklistandroid.retrofit.config.RetrofitConfig;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class ValidacaoActivity extends AppCompatActivity {

    private EditText senhaValidacao;
    public String checkListMostrado = valueOf(new CheckList());
    CompositeSubscription subscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao_checklist);

        setTitle(TITULO_APPBAR_VALIDACAO);

        senhaValidacao = findViewById(R.id.codigo_validacao);

        configuraBotaoValidar();

        Intent intent = getIntent();
        if (intent != null){
            checkListMostrado = (String) intent.getSerializableExtra(CHAVE_CHECKLIST);
        }

    }

    private void configuraBotaoValidar() {
        Button botaoValidar = findViewById(R.id.botao_validacao);
        botaoValidar.setOnClickListener(view -> {
            if (senhaValidacao.getText().toString().equals("")) {
                Toast.makeText(ValidacaoActivity.this, "Código de verificação inválido",
                        Toast.LENGTH_SHORT).show();
            } else {
                salvaCheckList();
            }
        });
    }

    public void salvaCheckList() {
        Observable<String> observable = RetrofitConfig.getRetrofit().create(
                CheckListService.class).cadastraNovoCheckList(checkListMostrado);
        subscription.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ValidacaoActivity.this,
                                "Opa! Algo deu errado." + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(String checkList) {
                        vaiParaFinalizar();
                    }
                }));
    }

    private void vaiParaFinalizar() {
        Intent iniciaFinalizar =
                new Intent(ValidacaoActivity.this,
                        FinalizacaoActivity.class);
        startActivityIfNeeded(iniciaFinalizar, CODIGO_FINALIZACAO_CHECKLIST);
    }
}
