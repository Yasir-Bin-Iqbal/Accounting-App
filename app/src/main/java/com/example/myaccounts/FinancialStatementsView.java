package com.example.myaccounts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myaccounts.adapter.FinancialStatementsRecyclerViewAdapter;
import com.example.myaccounts.database.Database;
import com.example.myaccounts.model.FinancialStatements;

import java.util.ArrayList;
import java.util.List;

public class FinancialStatementsView extends AppCompatActivity {

 RecyclerView IncomeStatementRecyclerView,OwnerEquityStatementRecyclerView,BalanceSheetRecyclerView;
 FinancialStatementsRecyclerViewAdapter financialStatementsRecyclerViewAdapter;
 ArrayList<FinancialStatements> IncomeStatementsArrayList;
 ArrayList<FinancialStatements> OwnerEquityArrayList;
 ArrayList<FinancialStatements> BalanceSheetArrayList;
 Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_statements);

        IncomeStatementRecyclerView = findViewById(R.id.IncomeStatementRecyclerView);
        IncomeStatementRecyclerView.setHasFixedSize(true);
        IncomeStatementRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        OwnerEquityStatementRecyclerView = findViewById(R.id.OwnerEquityStatementRecyclerView);
        OwnerEquityStatementRecyclerView.setHasFixedSize(true);
        OwnerEquityStatementRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        BalanceSheetRecyclerView= findViewById(R.id.BalanceSheetRecyclerView);
        BalanceSheetRecyclerView.setHasFixedSize(true);
        BalanceSheetRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        db=  new Database(this);

        IncomeStatementsArrayList = new ArrayList<>();
        OwnerEquityArrayList = new ArrayList<>();
        BalanceSheetArrayList = new ArrayList<>();

        List<FinancialStatements> IncomeStatementsList;
        List<FinancialStatements> OwnerEquityList;
        List<FinancialStatements> BalanceSheetList;


         IncomeStatementsList = db.getIncomeStatement();
        for (FinancialStatements fs: IncomeStatementsList) {
            IncomeStatementsArrayList.add(fs);
        }
        financialStatementsRecyclerViewAdapter = new FinancialStatementsRecyclerViewAdapter(getApplicationContext(),IncomeStatementsArrayList);
        IncomeStatementRecyclerView.setAdapter(financialStatementsRecyclerViewAdapter);


         OwnerEquityList = db.getEquity();
        for (FinancialStatements fs: OwnerEquityList) {
            OwnerEquityArrayList.add(fs);
        }
        financialStatementsRecyclerViewAdapter = new FinancialStatementsRecyclerViewAdapter(getApplicationContext(),OwnerEquityArrayList);
        OwnerEquityStatementRecyclerView.setAdapter(financialStatementsRecyclerViewAdapter);


        BalanceSheetList = db.getBalanceSheet();
        for (FinancialStatements fs: BalanceSheetList) {
            BalanceSheetArrayList.add(fs);
            Log.i("fs","bs: "+ fs);
        }
        financialStatementsRecyclerViewAdapter = new FinancialStatementsRecyclerViewAdapter(getApplicationContext(),BalanceSheetArrayList);
        BalanceSheetRecyclerView.setAdapter(financialStatementsRecyclerViewAdapter);

        Log.i("fs","end");
    }
}