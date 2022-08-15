package com.example.myaccounts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myaccounts.adapter.LedgerRecyclerViewAdapter;
import com.example.myaccounts.adapter.RecyclerViewAdapter;
import com.example.myaccounts.adapter.TrialBalanceRecyclerViewAdapter;
import com.example.myaccounts.database.Database;
import com.example.myaccounts.model.JournalEnteryData;
import com.example.myaccounts.model.LedgerEntery;
import com.example.myaccounts.model.TrialBalance;

import java.util.ArrayList;
import java.util.List;

public class RecordsView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private LedgerRecyclerViewAdapter ledgerRecyclerViewAdapter;
    private TrialBalanceRecyclerViewAdapter trialBalanceRecyclerViewAdapter;
    private ArrayList<JournalEnteryData> journalEnteryDataArrayList;
    private ArrayList<TrialBalance> trialBalanceArrayList;
    private ArrayList<LedgerEntery> ledgerEnteryArrayList;
    TextView NameTextView;
    private ArrayAdapter<String> arrayAdapter;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records_view);

        Bundle bundle = getIntent().getExtras();
        String tag = bundle.getString("tag");
        NameTextView = findViewById(R.id.NameTextView);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db= new Database(this);

        journalEnteryDataArrayList= new ArrayList<>();
        ledgerEnteryArrayList = new ArrayList<>();
        trialBalanceArrayList = new ArrayList<>();

       if(tag.equals("Journal")){

        NameTextView.setText("JOURNAL ENTRIES");
        List<JournalEnteryData> journalEnteryDataList = db.getJournalEntry();
        for (JournalEnteryData entery: journalEnteryDataList) {
            journalEnteryDataArrayList.add(entery);
        }
        recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(),journalEnteryDataArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
       }

       else if(tag.equals("Ledger")){

           NameTextView.setText("LEDGER ACCOUNTS");
           List<LedgerEntery> ledgerEnteryList = db.getLedger();
           for (LedgerEntery entery : ledgerEnteryList) {
               ledgerEnteryArrayList.add(entery);
           }
           ledgerRecyclerViewAdapter = new LedgerRecyclerViewAdapter(getApplicationContext(),ledgerEnteryArrayList);
           recyclerView.setAdapter(ledgerRecyclerViewAdapter);
       }

     else if(tag.equals("TrialBalance")){

           NameTextView.setText("TRIAL BALANCE");
         List<TrialBalance> trialBalanceList = db.TrialBalance();

           for (TrialBalance trial : trialBalanceList) {
               trialBalanceArrayList.add(trial);
           }
           trialBalanceRecyclerViewAdapter = new TrialBalanceRecyclerViewAdapter(getApplicationContext(),trialBalanceArrayList);
           recyclerView.setAdapter(trialBalanceRecyclerViewAdapter);
       }

       else if(tag.equals("AdjustingTrialBalance")){

           NameTextView.setText("ADJUSTED TRIAL BALANCE");
           List<TrialBalance> trialBalanceList = db.getAdjTB();

           for (TrialBalance trial : trialBalanceList) {
               trialBalanceArrayList.add(trial);
           }
           trialBalanceRecyclerViewAdapter = new TrialBalanceRecyclerViewAdapter(getApplicationContext(),trialBalanceArrayList);
           recyclerView.setAdapter(trialBalanceRecyclerViewAdapter);
       }


       else if (tag.equals("AdjEntery")){

           NameTextView.setText("ADJUSTING ENTRIES");
           List<JournalEnteryData> journalEnteryDataList = db.getAdjEntry();
           for (JournalEnteryData entery: journalEnteryDataList) {
               journalEnteryDataArrayList.add(entery);
           }
           recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(),journalEnteryDataArrayList);
           recyclerView.setAdapter(recyclerViewAdapter);
       }

       else if(tag.equals("AdjLedger")){

           NameTextView.setText("ADJUSTED LEDGER ACCOUNTS");
           List<LedgerEntery> ledgerEnteryList = db.getAdjLedger();
           for (LedgerEntery entery : ledgerEnteryList) {
               ledgerEnteryArrayList.add(entery);
           }
           ledgerRecyclerViewAdapter = new LedgerRecyclerViewAdapter(getApplicationContext(),ledgerEnteryArrayList);
           recyclerView.setAdapter(ledgerRecyclerViewAdapter);
       }
    }
}