package com.cursoandroid.projetochecklistandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.icu.number.NumberRangeFormatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.projetochecklistandroid.activity.OnItemClickListener;
import com.cursoandroid.projetochecklistandroid.model.CheckList;
import com.cursoandroid.projetochecklistandroid.retrofit.CheckListService;
import com.cursoandroid.projetochecklistandroid.retrofit.RetrofitConfig;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ListaCheckListsAdapter extends RecyclerView.Adapter<ListaCheckListsAdapter
        .CheckListViewHolder> {

    private final List<CheckList> checkLists;
    private final Context context;
    private OnItemClickListener onItemClickListener;
    private ListaCheckListsAdapter adapter;
    RetrofitConfig retrofitConfig = new RetrofitConfig();

    public ListaCheckListsAdapter(List<CheckList> checkLists, Context context) {
        this.checkLists = checkLists;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ListaCheckListsAdapter.CheckListViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_check_list_lista,
                parent, false);
        return new CheckListViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaCheckListsAdapter.CheckListViewHolder holder,
                                 int position) {
        CheckList checkList = checkLists.get(position);
        holder.vincula(checkList);
    }

    @Override
    public int getItemCount() {
        return checkLists.size();
    }

    public void removeCheckList(int posicao) {
        removeCheckList(checkLists.get(posicao).getId(), posicao);
        notifyDataSetChanged();

    }

    public void removeCheckList(int id, int posicao){
        List<CheckList> checkLists = null;
        Observable<CheckList> observable = (Observable<CheckList>) retrofitConfig.getRetrofit()
                .create(CheckListService.class).removeCheckList(id);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CheckList>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Erro!", e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(CheckList checkList) {
                        Log.e("Check list removido!", "Removido!");
                        checkLists.remove(id);
                    }
                });
    }


    public class CheckListViewHolder extends RecyclerView.ViewHolder {

        private final RadioGroup saidaRetorno;
        private final EditText dataC;
        private final EditText hora;
        private final EditText placa;
        private final EditText motorista;
        private CheckList checkList;

        public CheckListViewHolder(View itemView) {
            super(itemView);
            saidaRetorno = (RadioGroup) itemView.findViewById(R.id.item_rgSaidaRetorno);
           dataC = itemView.findViewById(R.id.item_check_list_data);
           hora = itemView.findViewById(R.id.item_check_list_hora);
           placa = itemView.findViewById(R.id.item_check_list_placa);
           motorista = itemView.findViewById(R.id.item_check_list_motorista);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   onItemClickListener.onItemClick(checkList, getAbsoluteAdapterPosition());
               }
           });
        }

        public void vincula(CheckList checkList) {
            this.checkList = checkList;
            preencheCampo(checkList);
        }

        private void preencheCampo(CheckList checkList) {
            saidaRetorno.getCheckedRadioButtonId();
            dataC.setText(checkList.getDataC());
            hora.setText(checkList.getHora());
            placa.setText(checkList.getPlaca());
            motorista.setText(checkList.getMotorista());
        }
    }
}
