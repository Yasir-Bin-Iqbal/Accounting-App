package com.example.myaccounts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaccounts.R;
import com.example.myaccounts.model.JournalEnteryData;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<JournalEnteryData> journalEnteryDataList;

    public RecyclerViewAdapter(Context context, List<JournalEnteryData> journalEnteryDataList) {
        this.context = context;
        this.journalEnteryDataList = journalEnteryDataList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.enteries,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
      JournalEnteryData journalEnteryData = journalEnteryDataList.get(position);
      holder.DateTextView.setText(journalEnteryData.getDate());
      holder.DebitTextView.setText(journalEnteryData.getDebit());
      holder.CreditTextView.setText(journalEnteryData.getCredit());
      holder.DRTextView.setText(journalEnteryData.getAmount());
      holder.CRTextView.setText(journalEnteryData.getAmount());
      holder.DescriptionTextView.setText(journalEnteryData.getDescription());
    }

    @Override
    public int getItemCount() {
        return journalEnteryDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView DateTextView, DebitTextView, CreditTextView, DRTextView, CRTextView, DescriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            DateTextView = itemView.findViewById(R.id.DateTextView);
            DebitTextView = itemView.findViewById(R.id.DebitTextView);
            CreditTextView = itemView.findViewById(R.id.CreditTextView);
            DRTextView = itemView.findViewById(R.id.DRtextView);
            CRTextView = itemView.findViewById(R.id.CRtextView);
            DescriptionTextView = itemView.findViewById(R.id.DescriptionTextView);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
