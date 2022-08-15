package com.example.myaccounts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myaccounts.database.Database;
import com.example.myaccounts.model.JournalEnteryData;
import com.example.myaccounts.model.LedgerEntery;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class EnteriesActivity extends AppCompatActivity {
    TextInputEditText date,debit,credit,amount,description;
    AutoCompleteTextView debitType,creditType;
    TextView textView;
  ArrayList TransactionName,Type,Value,ValueType;
 Database db;
 String tag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nteries);
        Bundle bundle = getIntent().getExtras();
         tag= bundle.getString("tag");

         db = new Database(this);

         String [] type = {"Asset","Liability","Expense","Drawings","Capital","Revenue"};
         ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,type);

        date= findViewById(R.id.editTextDate);
        debit= findViewById(R.id.editTextDebit);
        debitType= findViewById(R.id.editTextDebitType);
        credit= findViewById(R.id.editTextCredit);
        creditType= findViewById(R.id.editTextCreditType);
        amount= findViewById(R.id.editTextAmount);
        description= findViewById(R.id.editTextDescription);
        textView = findViewById(R.id.textView);

        debitType.setAdapter(arrayAdapter);
        creditType.setAdapter(arrayAdapter);

       TransactionName= new ArrayList();
        Type= new ArrayList();
        Value= new ArrayList();
        ValueType= new ArrayList();

        if(tag.equals("journal")){
            textView.setText("JOURNAL ENTRIES");
        }
        else{
            textView.setText("ADJUSTING ENTRIES");
        }


    }

    public void Addbutton(View view){
        if(date.getText().toString().isEmpty() || debit.getText().toString().isEmpty() || debitType.getText().toString().isEmpty() || credit.getText().toString().isEmpty() || creditType.getText().toString().isEmpty() ||
                amount.getText().toString().isEmpty() || description.getText().toString().isEmpty()   ){
            Toast.makeText(this, "Please fill all data !!", Toast.LENGTH_SHORT).show();
        }
        else {
       JournalEnteryData journalEnteryData = new JournalEnteryData(date.getText().toString(),debit.getText().toString(),debitType.getText().toString(),credit.getText().toString(),creditType.getText().toString(),amount.getText().toString(),description.getText().toString());
       LedgerValues(debit.getText().toString(),credit.getText().toString(),Float.parseFloat(amount.getText().toString()),debitType.getText().toString(),creditType.getText().toString());
       if(tag.equals("journal")){
           db.addJournalEntery(journalEnteryData);
       }
       else{
           db.addAdjEntery(journalEnteryData);
       }
        Toast.makeText(getApplicationContext(), "Entry Added !", Toast.LENGTH_SHORT).show();
        }

    }
    public void LedgerValues(String debit,String credit,float amount,String debitType,String creditType){

        if (TransactionName.isEmpty()){

            TransactionName.add(debit.toUpperCase());
            Type.add(debitType.toUpperCase());
            Value.add(amount);
            ValueType.add("DR");

            TransactionName.add(credit.toUpperCase());
            Type.add(creditType.toUpperCase());
            Value.add(amount);
            ValueType.add("CR");

        }
        else{
            if(TransactionName.contains(debit.toUpperCase())){
                int position = TransactionName.indexOf(debit.toUpperCase());
                float newAmount;
                if(ValueType.get(position).equals("DR")){
                    newAmount = amount + Float.parseFloat(Value.get(position).toString());
                }
                else{
                     newAmount= Math.abs(Float.parseFloat(Value.get(position).toString()) - amount);
                     if( amount > Float.parseFloat(Value.get(position).toString())){
                         ValueType.set(position,"DR");
                     }
                }
                Value.set(position,newAmount);
            }
            if (!TransactionName.contains(debit.toUpperCase())){
                TransactionName.add(debit.toUpperCase());
                Type.add(debitType.toUpperCase());
                Value.add(amount);
                ValueType.add("DR");
            }

            if(TransactionName.contains(credit.toUpperCase())){
                int position = TransactionName.indexOf(credit.toUpperCase());
                float newAmount;
                if(ValueType.get(position).equals("CR")){
                    newAmount = amount + Float.parseFloat(Value.get(position).toString());
                }
                else{
                    newAmount= Math.abs(Float.parseFloat(Value.get(position).toString()) - amount);
                    if( amount > Float.parseFloat(Value.get(position).toString())){
                        ValueType.set(position,"CR");
                    }
                }
                Value.set(position,newAmount);
            }
            if(!TransactionName.contains(credit.toUpperCase())){
                TransactionName.add(credit.toUpperCase());
                Type.add(creditType.toUpperCase());
                Value.add(amount);
                ValueType.add("CR");
            }
        }
        for (int i=0 ; i< TransactionName.size();i++){
            Log.i("Ledger","Name: " + TransactionName.get(i) + "Type: " + Type.get(i) + "Amount: " + Value.get(i) + "Vtype: "+ ValueType.get(i));
        }
    }

    public void Finish(View view){

        for (int i=0 ; i< TransactionName.size();i++){
            LedgerEntery ledgerEntery = new LedgerEntery(TransactionName.get(i).toString(),Type.get(i).toString(),Float.parseFloat(Value.get(i).toString()),ValueType.get(i).toString());
           Log.i("Leger",": "+ ledgerEntery.getTranasction() + ledgerEntery.getType() + ledgerEntery.getValue() + ledgerEntery.getValueType());
           if(tag.equals("journal")){
               db.addLedger(ledgerEntery);
           }
           else{
               db.addAdjLedger(ledgerEntery);
           }
        }
        Toast.makeText(getApplicationContext(), "Ledger Created !", Toast.LENGTH_SHORT).show();
        if(db.NotEmpty()){
            db.AdjTrialBalance();

        }
         Intent intent = new Intent(this,Transactions.class);
        startActivity(intent);

    }

}