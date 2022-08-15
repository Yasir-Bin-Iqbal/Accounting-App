package com.example.myaccounts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myaccounts.database.Database;

public class Transactions extends AppCompatActivity {

    Intent intent;
    Database db;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        db = new Database(getApplicationContext());
        dialog = new Dialog(this);

    }

        public void createEntery (View view){
            intent = new Intent(getApplicationContext(), EnteriesActivity.class);
            intent.putExtra("tag", "journal");
            startActivity(intent);

        }


        public void EnteryView(View view){
            intent= new Intent(getApplicationContext(),RecordsView.class);
            intent.putExtra("tag","Journal");
            startActivity(intent);

        }

        public void LedgerView(View view){
            intent = new Intent(getApplicationContext(),RecordsView.class);
            intent.putExtra("tag","Ledger");
            startActivity(intent);
        }

        public void TBView(View view){
            intent = new Intent(getApplicationContext(),RecordsView.class);
            intent.putExtra("tag","TrialBalance");
            startActivity(intent);
        }

        public void createAdjEntery(View view){
           if(db.LedgerisEmpty()){
               Toast.makeText(getApplicationContext(), "Create Journal Entries First !!", Toast.LENGTH_SHORT).show();
           }
           else{
            intent = new Intent(getApplicationContext(),EnteriesActivity.class);
            intent.putExtra("tag","AdjEntery");
            startActivity(intent);
           }
        }

        public void AdjEnteryView(View view){
            intent = new Intent(getApplicationContext(),RecordsView.class);
            intent.putExtra("tag","AdjEntery");
            startActivity(intent);
        }

        public void AdjLedgerView(View view){
            intent = new Intent(getApplicationContext(),RecordsView.class);
            intent.putExtra("tag","AdjLedger");
            startActivity(intent);
        }

        public void AdjTBView(View view){
            intent = new Intent(getApplicationContext(),RecordsView.class);
            intent.putExtra("tag","AdjustingTrialBalance");
            startActivity(intent);
        }

        public void FinancialStatementsView(View view){
            intent = new Intent(getApplicationContext(),FinancialStatementsView.class);
            startActivity(intent);
        }

        public void Clear(View view){

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Delete Records ?");
            alertDialog.setIcon(R.drawable.delete);
            alertDialog.setMessage("Are you sure you want to delete all records ? ");
            alertDialog.setCancelable(false);
            alertDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    db.DeleteAll();
                    Toast.makeText(getApplicationContext(), "All records deleted", Toast.LENGTH_SHORT).show();
                }
            });
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog alertDialog1 = alertDialog.create();
            alertDialog1.show();

        }



}