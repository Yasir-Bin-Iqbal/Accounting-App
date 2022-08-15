package com.example.myaccounts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaccounts.R;
import com.example.myaccounts.model.LedgerEntery;

import java.util.List;

public class LedgerRecyclerViewAdapter extends RecyclerView.Adapter<LedgerRecyclerViewAdapter.ViewHolder> {

    private Context context;
    List<LedgerEntery> ledgerEnteryList;

    public LedgerRecyclerViewAdapter(Context context, List<LedgerEntery> ledgerEnteryList) {
        this.context = context;
        this.ledgerEnteryList = ledgerEnteryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ledger,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LedgerEntery ledgerEntery = ledgerEnteryList.get(position);
        holder.TranasctionTextView.setText(ledgerEntery.getTranasction());
        holder.TypetextView.setText(ledgerEntery.getType());
        holder.AmountTextView.setText(Float.toString(ledgerEntery.getValue()) + " " + ledgerEntery.getValueType());

    }

    @Override
    public int getItemCount() {
        return ledgerEnteryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
      TextView TypetextView , TranasctionTextView , AmountTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            TranasctionTextView = itemView.findViewById(R.id.TransactiontextView);
            TypetextView = itemView.findViewById(R.id.TypetextView);
            AmountTextView = itemView.findViewById(R.id.AmountTextView);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
