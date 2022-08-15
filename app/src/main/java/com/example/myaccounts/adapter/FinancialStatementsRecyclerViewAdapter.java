package com.example.myaccounts.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaccounts.R;
import com.example.myaccounts.model.FinancialStatements;

import java.util.List;

public class FinancialStatementsRecyclerViewAdapter extends RecyclerView.Adapter<FinancialStatementsRecyclerViewAdapter.ViewHolder>{
    Context context;
    List<FinancialStatements> financialStatementsList;

    public FinancialStatementsRecyclerViewAdapter(Context context, List<FinancialStatements> financialStatementsList) {
        this.context = context;
        this.financialStatementsList = financialStatementsList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.financialstatements,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FinancialStatements financialStatements = financialStatementsList.get(position);
        holder.FsTransactionTextView.setText(financialStatements.getTransaction());
        holder.FsAmountTextView.setText(financialStatements.getAmount());

    }

    @Override
    public int getItemCount() {
        return financialStatementsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView FsTransactionTextView, FsAmountTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
           FsTransactionTextView = itemView.findViewById(R.id.FsTransactiontextView);
           FsAmountTextView = itemView.findViewById(R.id.FsAmounttextView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
