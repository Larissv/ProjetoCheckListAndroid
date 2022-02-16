package com.cursoandroid.projetochecklistandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.projetochecklistandroid.R;
import com.cursoandroid.projetochecklistandroid.activity.OnItemClickListener;
import com.cursoandroid.projetochecklistandroid.model.CheckList;
import com.cursoandroid.projetochecklistandroid.retrofit.RetrofitConfig;

import java.util.List;

public class ListaCheckListsAdapter extends RecyclerView.Adapter<ListaCheckListsAdapter
        .CheckListViewHolder> {

    private final List<CheckList> checkLists;
    private final Context context;
    private OnItemClickListener onItemClickListener;
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

    public class CheckListViewHolder extends RecyclerView.ViewHolder {

//        private final TextView saida;
//        private final TextView retorno;
        private final TextView dataC;
        private final TextView hora;
        private final TextView placa;
        private final TextView motorista;
        private CheckList checkList;

        public CheckListViewHolder(View itemView) {
            super(itemView);
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
            dataC.setText(checkList.getDataC());
            hora.setText(checkList.getHora());
            placa.setText(checkList.getPlaca());
            motorista.setText(checkList.getMotorista());

        }
    }
}
