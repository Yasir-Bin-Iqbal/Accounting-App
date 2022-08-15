package com.example.myaccounts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaccounts.R;
import com.example.myaccounts.model.TrialBalance;

import java.util.List;

public class TrialBalanceRecyclerViewAdapter extends RecyclerView.Adapter<TrialBalanceRecyclerViewAdapter.ViewHolder> {
    private Context context;
    List<TrialBalance> trialBalanceList;

    public TrialBalanceRecyclerViewAdapter(Context context, List<TrialBalance> trialBalanceList) {
        this.context = context;
        this.trialBalanceList = trialBalanceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trial_balance, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrialBalance trialBalance = trialBalanceList.get(position);
        holder.nameandtype.setText(trialBalance.getTransactionName() + trialBalance.getType());
        holder.Dr.setText(trialBalance.getDr());
        holder.Cr.setText(trialBalance.getCr());

    }

    @Override
    public int getItemCount() {
        return trialBalanceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameandtype, Dr, Cr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nameandtype = itemView.findViewById(R.id.nameAndtype);
            Dr = itemView.findViewById(R.id.Dr);
            Cr = itemView.findViewById(R.id.Cr);
        }

        @Override
        public void onClick(View view) {

        }
    }
}

