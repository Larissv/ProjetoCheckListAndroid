package com.cursoandroid.projetochecklistandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cursoandroid.projetochecklistandroid.R;
import com.cursoandroid.projetochecklistandroid.activity.listener.OnItemClickListener;
import com.cursoandroid.projetochecklistandroid.model.CheckList;

import java.util.List;

public class ListaCheckListsAdapter extends
        RecyclerView.Adapter<ListaCheckListsAdapter.CheckListViewHolder> {

    private final List<CheckList> checkLists;
    private final Context context;
    private OnItemClickListener onItemClickListener;

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
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_lista_checklist,
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

        private final TextView data;
        private final TextView hora;
        private final TextView placa;
        private final TextView motorista;
        private final TextView saidaRetorno;
        private CheckList checkList;

        public CheckListViewHolder(View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.item_check_list_data);
            hora = itemView.findViewById(R.id.item_check_list_hora);
            placa = itemView.findViewById(R.id.item_check_list_placa);
            motorista = itemView.findViewById(R.id.item_check_list_motorista);
            saidaRetorno = itemView.findViewById(R.id.item_check_list_saidaRetorno);

            itemView.setOnClickListener(view ->
                    onItemClickListener.onItemClick(checkList, getAbsoluteAdapterPosition()));
        }

        public void vincula(CheckList checkList) {
            this.checkList = checkList;
            preencheCampos(checkList);
        }

        private void preencheCampos(CheckList checkList) {
            data.setText(checkList.getData());
            hora.setText(checkList.getHora());
            placa.setText(checkList.getPlaca());
            motorista.setText(checkList.getMotorista());
            saidaRetorno.setText(checkList.getSaidaRetorno());
        }
    }
}
