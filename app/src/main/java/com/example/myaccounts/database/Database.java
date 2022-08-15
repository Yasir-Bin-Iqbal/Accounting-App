package com.example.myaccounts.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myaccounts.MainActivity;
import com.example.myaccounts.model.FinancialStatements;
import com.example.myaccounts.model.JournalEnteryData;
import com.example.myaccounts.model.LedgerEntery;
import com.example.myaccounts.model.TrialBalance;
import com.example.myaccounts.params.Params;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    SQLiteDatabase db;

    public Database(@Nullable Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
        db = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("db", "crete called");

        String createJournal = "CREATE TABLE " + Params.Journal_ENT + " (" + Params.DATE + " INTEGER, " + Params.DEBIT + " TEXT, " + Params.DEBIT_TYPE + " TEXT, " +
                Params.CREDIT + " TEXT, " + Params.CREDIT_TYPE + " TEXT, " + Params.AMOUNT + " REAL, " + Params.Desp + " TEXT" + ")";
        db.execSQL(createJournal);

        String createLedger = "CREATE TABLE " + Params.LEDGER + " (" + Params.TRANSACTION + " TEXT, " + Params.TYPE + " TEXT, " + Params.L_AMOUNT + " REAL, " + Params.BALANCE + " TEXT" + ")";
        db.execSQL(createLedger);

        String createAdjEntery = "CREATE TABLE " + Params.ADJ_Journal_ENT + " (" + Params.DATE + " INTEGER, " + Params.DEBIT + " TEXT, " + Params.DEBIT_TYPE + " TEXT, " +
                Params.CREDIT + " TEXT, " + Params.CREDIT_TYPE + " TEXT, " + Params.AMOUNT + " REAL, " + Params.Desp + " TEXT" + ")";
        db.execSQL(createAdjEntery);

        String createAdjLedger = "CREATE TABLE " + Params.ADJ_LEDGER + " (" + Params.TRANSACTION + " TEXT, " + Params.TYPE + " TEXT, " + Params.L_AMOUNT + " REAL, " + Params.BALANCE + " TEXT" + ")";
        db.execSQL(createAdjLedger);

        String createAdjTB = "CREATE TABLE " + Params.ADJ_TRIAL_B + " (" + Params.TRANSACTION + " TEXT, " + Params.TYPE + " TEXT, " + Params.DEBIT + " REAL, " + Params.CREDIT + " REAL" + ")" ;
        db.execSQL(createAdjTB);

        String createIncSt = "CREATE TABLE " + Params.INC_ST + " (" + Params.TRANSACTION + " TEXT, "  + Params.AMOUNT + " REAL" + ")" ;
        db.execSQL(createIncSt);

        String createStOfEquity = "CREATE TABLE " + Params.OWNER_EQUITY + " (" + Params.TRANSACTION + " TEXT, "  + Params.AMOUNT + " REAL" + ")" ;
        db.execSQL(createStOfEquity);

        String createBS = "CREATE TABLE " + Params.BALANCE_SHEET + " (" + Params.TRANSACTION + " TEXT, "  + Params.AMOUNT + " REAL" + ")" ;
        db.execSQL(createBS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addJournalEntery(JournalEnteryData journalEnteryData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Params.DATE, journalEnteryData.getDate());
        contentValues.put(Params.DEBIT, journalEnteryData.getDebit());
        contentValues.put(Params.DEBIT_TYPE, journalEnteryData.getDebit_Type());
        contentValues.put(Params.CREDIT, journalEnteryData.getCredit());
        contentValues.put(Params.CREDIT_TYPE, journalEnteryData.getCredit_Type());
        contentValues.put(Params.AMOUNT, journalEnteryData.getAmount());
        contentValues.put(Params.Desp, journalEnteryData.getDescription());

        db.insert(Params.Journal_ENT, null, contentValues);
        db.close();
    }

    public void addAdjEntery(JournalEnteryData journalEnteryData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(Params.DATE, journalEnteryData.getDate());
        contentValues.put(Params.DEBIT, journalEnteryData.getDebit());
        contentValues.put(Params.DEBIT_TYPE, journalEnteryData.getDebit_Type());
        contentValues.put(Params.CREDIT, journalEnteryData.getCredit());
        contentValues.put(Params.CREDIT_TYPE, journalEnteryData.getCredit_Type());
        contentValues.put(Params.AMOUNT, journalEnteryData.getAmount());
        contentValues.put(Params.Desp, journalEnteryData.getDescription());

        db.insert(Params.ADJ_Journal_ENT, null, contentValues);
        db.close();
    }


    public void addLedger(LedgerEntery ledgerEntery) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.TRANSACTION, ledgerEntery.getTranasction());
        contentValues.put(Params.TYPE, ledgerEntery.getType());
        contentValues.put(Params.L_AMOUNT, ledgerEntery.getValue());
        contentValues.put(Params.BALANCE, ledgerEntery.getValueType());
        db.insert(Params.LEDGER, null, contentValues);
        db.close();
    }

    public void addAdjLedger(LedgerEntery ledgerEntery) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.TRANSACTION, ledgerEntery.getTranasction());
        contentValues.put(Params.TYPE, ledgerEntery.getType());
        contentValues.put(Params.L_AMOUNT, ledgerEntery.getValue());
        contentValues.put(Params.BALANCE, ledgerEntery.getValueType());
        db.insert(Params.ADJ_LEDGER, null, contentValues);
        db.close();
    }

    public void addAdjTrialBalance(TrialBalance trialBalance){
        Log.i("hello","called");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.TRANSACTION,trialBalance.getTransactionName());
        contentValues.put(Params.TYPE,trialBalance.getType());
        contentValues.put(Params.DEBIT,trialBalance.getDr());
        contentValues.put(Params.CREDIT,trialBalance.getCr());
        db.insert(Params.ADJ_TRIAL_B,null,contentValues);
        db.close();

    }

    public void addIncomeStatement(FinancialStatements financialStatements){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.TRANSACTION,financialStatements.getTransaction());
        contentValues.put(Params.AMOUNT,financialStatements.getAmount());
        db.insert(Params.INC_ST,null,contentValues);
        db.close();

    }

    public void addEquity(FinancialStatements financialStatements){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.TRANSACTION,financialStatements.getTransaction());
        contentValues.put(Params.AMOUNT,financialStatements.getAmount());
        db.insert(Params.OWNER_EQUITY,null,contentValues);
        db.close();

    }

    public void addBalanceSheet(FinancialStatements financialStatements){

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Params.TRANSACTION,financialStatements.getTransaction());
            contentValues.put(Params.AMOUNT,financialStatements.getAmount());
            db.insert(Params.BALANCE_SHEET,null,contentValues);
            db.close();
    }

    public List<JournalEnteryData> getJournalEntry() {
        List<JournalEnteryData> journalEnteryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + Params.Journal_ENT;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                JournalEnteryData journalEnteryData = new JournalEnteryData(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                journalEnteryList.add(journalEnteryData);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return journalEnteryList;
    }

    public List<JournalEnteryData> getAdjEntry() {
        List<JournalEnteryData> journalEnteryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + Params.ADJ_Journal_ENT;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                JournalEnteryData journalEnteryData = new JournalEnteryData(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                journalEnteryList.add(journalEnteryData);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return journalEnteryList;
    }


    public List<LedgerEntery> getLedger() {
        List<LedgerEntery> ledgerEnteryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + Params.LEDGER;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                LedgerEntery ledgerEntery = new LedgerEntery(cursor.getString(0), cursor.getString(1), cursor.getFloat(2), cursor.getString(3));
                ledgerEnteryList.add(ledgerEntery);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ledgerEnteryList;

    }


    public List<LedgerEntery> getAdjLedger() {
        List<LedgerEntery> ledgerEnteryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + Params.ADJ_LEDGER;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                LedgerEntery ledgerEntery = new LedgerEntery(cursor.getString(0), cursor.getString(1), cursor.getFloat(2), cursor.getString(3));
                ledgerEnteryList.add(ledgerEntery);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return ledgerEnteryList;
    }


    public List<TrialBalance> TrialBalance() {
        List<TrialBalance> trialBalanceList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + Params.LEDGER;

        float DebitBalance = 0;
        float CreditBalance = 0;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(3).equals("DR")) {
                    DebitBalance += cursor.getFloat(2);
                    TrialBalance trialBalance = new TrialBalance(cursor.getString(0) + "(", cursor.getString(1) + ")", cursor.getString(2), " ");
                    trialBalanceList.add(trialBalance);
                } else {
                    CreditBalance += cursor.getFloat(2);
                    TrialBalance trialBalance = new TrialBalance(cursor.getString(0) + "(", cursor.getString(1) + ")", " ", cursor.getString(2));
                    trialBalanceList.add(trialBalance);
                }

            } while (cursor.moveToNext());
        }
        TrialBalance trialBalance = new TrialBalance("Total", " ", Float.toString(DebitBalance), Float.toString(CreditBalance));
        trialBalanceList.add(trialBalance);
        cursor.close();
        db.close();
        return trialBalanceList;
    }


    public void AdjTrialBalance() {
        
        if(AdjTBisEmpty()) {
        
        List<LedgerEntery> ledgerEnteryList = getLedger();
        List<LedgerEntery> adjledgerEnterylist = getAdjLedger();
        List<LedgerEntery> remove = new ArrayList<>();

        for (LedgerEntery adjledger : adjledgerEnterylist) {
            for (LedgerEntery ledger : ledgerEnteryList) {
                 if(adjledger.getTranasction().equals(ledger.getTranasction())){
                     if(adjledger.getValueType().equals(ledger.getValueType())){
                         adjledger.setValue(adjledger.getValue() + ledger.getValue());

                     }
                     else{
                         adjledger.setValue(Math.abs(adjledger.getValue() - ledger.getValue()));
                         if(ledger.getValue() > adjledger.getValue()){
                             adjledger.setValueType(ledger.getValueType());
                         }
                     }
                     remove.add(ledger);
                 }
            }
        }

        for (LedgerEntery ledger: remove) {
            ledgerEnteryList.remove(ledger);
        }

        for (LedgerEntery ledger : ledgerEnteryList) {
            TrialBalance trialBalance;
            if(ledger.getValueType().equals("DR")){
             trialBalance = new TrialBalance(ledger.getTranasction(), ledger.getType(), Float.toString(ledger.getValue())," ");
            }
            else{
                trialBalance = new TrialBalance(ledger.getTranasction(), ledger.getType(), " ",Float.toString(ledger.getValue()));
            }
           addAdjTrialBalance(trialBalance);
        }

        for (LedgerEntery ledger : adjledgerEnterylist) {
            TrialBalance trialBalance;
            if(ledger.getValueType().equals("DR")){
                trialBalance = new TrialBalance(ledger.getTranasction(), ledger.getType(), Float.toString(ledger.getValue())," ");
            }
            else{
                trialBalance = new TrialBalance(ledger.getTranasction(), ledger.getType(), " ",Float.toString(ledger.getValue()));
            }
            addAdjTrialBalance(trialBalance);
        }
        incomeStatement();
     }
    }



    public List<TrialBalance> getAdjTB() {
        List<TrialBalance> trialBalanceList = new ArrayList<>();
        float debit= 0;
        float credit = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + Params.ADJ_TRIAL_B;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                if(cursor.getString(3).equals(" ")){
                 debit += cursor.getFloat(2) ;
                TrialBalance trialBalance = new TrialBalance(cursor.getString(0)+"(", cursor.getString(1)+")", cursor.getString(2), cursor.getString(3));
                trialBalanceList.add(trialBalance);
                }
                else{
                    credit += cursor.getFloat(3) ;
                    TrialBalance trialBalance = new TrialBalance(cursor.getString(0)+"(", cursor.getString(1)+")", cursor.getString(2), cursor.getString(3));
                    trialBalanceList.add(trialBalance);
                }
            } while (cursor.moveToNext());
            TrialBalance trialBalance =  new TrialBalance("Total"," ",Float.toString(debit),Float.toString(credit));
            trialBalanceList.add(trialBalance);
        }
        cursor.close();
        db.close();
        return trialBalanceList;
    }


    public List<FinancialStatements> getIncomeStatement(){
        List<FinancialStatements> array = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query ="SELECT * FROM " + Params.INC_ST;
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                FinancialStatements financialStatements = new FinancialStatements(cursor.getString(0), cursor.getString(1) );
                array.add(financialStatements);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return array;
    }


    public List<FinancialStatements> getEquity(){
        List<FinancialStatements> array = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query ="SELECT * FROM " + Params.OWNER_EQUITY;
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                FinancialStatements financialStatements = new FinancialStatements(cursor.getString(0), cursor.getString(1) );
                array.add(financialStatements);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return array;
    }

    public List<FinancialStatements> getBalanceSheet(){
        List<FinancialStatements> array = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query ="SELECT * FROM " + Params.BALANCE_SHEET;
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                FinancialStatements financialStatements = new FinancialStatements(cursor.getString(0), cursor.getString(1) );
                array.add(financialStatements);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return array;
    }


    public void incomeStatement() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<FinancialStatements> array = new ArrayList<>();

        FinancialStatements revenue = new FinancialStatements( "Revenue:","");
        FinancialStatements expense = new FinancialStatements("Expenses:","");

        float revenue_amount = 0;
        float expense_amount = 0;

        String profit_loss =" ";

        Cursor cursor;

        String query = "SELECT * FROM " + Params.ADJ_TRIAL_B + " WHERE " + Params.TYPE + "='REVENUE'";
        cursor = db.rawQuery(query, null);

        array.add(revenue);

        if (cursor.moveToFirst()) {
            do {
                revenue_amount += cursor.getFloat(3);
                FinancialStatements financialStatements = new FinancialStatements (cursor.getString(0), cursor.getString(3));
                array.add(financialStatements);
            } while (cursor.moveToNext());
        }

        String query2 = "SELECT * FROM " + Params.ADJ_TRIAL_B + " WHERE " + Params.TYPE + "='EXPENSE'";
        cursor = db.rawQuery(query2, null);

        array.add(expense);

        if (cursor.moveToFirst()) {
            do {
                expense_amount += cursor.getFloat(2);
                FinancialStatements financialStatements = new FinancialStatements (cursor.getString(0),cursor.getString(2));
                array.add(financialStatements);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        String amount = Float.toString(Math.abs(revenue_amount - expense_amount));
        if(revenue_amount > expense_amount){
            profit_loss="Net Income";
        }
        else{
            profit_loss="Net Loss";
        }
        FinancialStatements financialStatements = new FinancialStatements(profit_loss,amount);
        array.add(financialStatements);
        for (FinancialStatements fs: array) {
            addIncomeStatement(fs);
        }

        Equity(Float.parseFloat(amount),profit_loss);
    }

    public void Equity(float amount, String profit_loss) {

        SQLiteDatabase db = this.getReadableDatabase();
        List<FinancialStatements> array = new ArrayList<>();
        float Starting_Capital = 0;
        float drawings = 0;
        float Ending_Capital = 0;

        String query = "SELECT * FROM " + Params.ADJ_TRIAL_B + " WHERE " + Params.TYPE + "='CAPITAL'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Starting_Capital += cursor.getFloat(3);
                FinancialStatements financialStatements = new FinancialStatements("Beginning Capital",  cursor.getString(3));
                array.add(financialStatements);
            } while (cursor.moveToNext());
        }
        FinancialStatements net = new FinancialStatements (profit_loss,Float.toString(amount));
        array.add(net);

        String query2 = "SELECT * FROM " + Params.ADJ_TRIAL_B + " WHERE " + Params.TYPE + "='DRAWINGS'";
         cursor = db.rawQuery(query2, null);
        if (cursor.moveToFirst()) {
            do {
                drawings += cursor.getFloat(2);
                FinancialStatements financialStatements = new FinancialStatements("Drawings",  cursor.getString(2));
                array.add(financialStatements);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        if(profit_loss.equals("Net Income")){
         Ending_Capital = (Starting_Capital + amount) - drawings; }
        else{
            Ending_Capital = (Starting_Capital - amount) - drawings;
        }

        FinancialStatements end = new FinancialStatements("Ending Capital",Float.toString(Ending_Capital));
        array.add(end);

        for (FinancialStatements fs: array) {
            addEquity(fs);
        }

        BalanceSheet(Ending_Capital);
    }


    public void BalanceSheet(float EndingCapital){

        SQLiteDatabase db = this.getReadableDatabase();
        List<FinancialStatements> array = new ArrayList<>();
        float asset_amount= 0;
        float liability_amount = 0;

        FinancialStatements  asset = new FinancialStatements("Assets","");
        array.add(asset);

        String query = "SELECT * FROM " + Params.ADJ_TRIAL_B + " WHERE " + Params.TYPE + "='ASSET'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                asset_amount += cursor.getFloat(2);
                FinancialStatements financialStatements = new FinancialStatements(cursor.getString(0),cursor.getString(2));
                array.add(financialStatements);
            } while (cursor.moveToNext());
        }

        FinancialStatements Total_assets = new FinancialStatements ("Total: ",Float.toString(asset_amount));
        array.add(Total_assets);

        FinancialStatements liability = new FinancialStatements("Liabilities & Capital","");
        array.add(liability);

        String query2 = "SELECT * FROM " + Params.ADJ_TRIAL_B + " WHERE " + Params.TYPE + "='LIABILITY'";
        cursor = db.rawQuery(query2, null);
        if (cursor.moveToFirst()) {
            do {
                liability_amount += cursor.getFloat(3);
                FinancialStatements financialStatements = new FinancialStatements (cursor.getString(0),cursor.getString(3));
                array.add(financialStatements);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        FinancialStatements capital = new FinancialStatements("Capital",Float.toString(EndingCapital));
        array.add(capital);

        FinancialStatements Total_liabilty = new FinancialStatements ("Total: ",Float.toString(liability_amount+EndingCapital));
        array.add(Total_liabilty);

        for (FinancialStatements fs: array) {
            addBalanceSheet(fs);
        }


    }


    public void DeleteAll(){

        db.delete(Params.Journal_ENT,null,null);
        db.delete(Params.LEDGER,null,null);
        db.delete(Params.ADJ_Journal_ENT,null,null);
        db.delete(Params.ADJ_LEDGER,null,null);
        db.delete(Params.ADJ_TRIAL_B,null,null);
        db.delete(Params.INC_ST,null,null);
        db.delete(Params.OWNER_EQUITY,null,null);
        db.delete(Params.BALANCE_SHEET,null,null);
    }


    public boolean NotEmpty(){
        boolean b= true;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " SELECT COUNT(*) FROM " + Params.ADJ_LEDGER;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        if(cursor.getString(0).equals("0")){
            b= false;
        }
        cursor.close();
        return b;
    }


    public boolean LedgerisEmpty(){
        boolean b= false;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " SELECT COUNT(*) FROM " + Params.LEDGER;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        if(cursor.getString(0).equals("0")){
            b= true;
        }
        cursor.close();
        return b;
    }

    public float incomeValue(){
        float income= 0;
        SQLiteDatabase db = this.getReadableDatabase();

        String query ="SELECT "+ Params.AMOUNT + " FROM " + Params.INC_ST + " WHERE " + Params.TRANSACTION + "='Net Income'";
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
              income= cursor.getFloat(0);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return income;
    }

    public float capitalValue(){
        float capital= 0;
        SQLiteDatabase db = this.getReadableDatabase();

        String query ="SELECT "+ Params.AMOUNT + " FROM " + Params.OWNER_EQUITY + " WHERE " + Params.TRANSACTION + "='Ending Capital'";
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                capital= cursor.getFloat(0);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return capital;
    }

    public float assetValue(){
        float asset= 0;
        SQLiteDatabase db = this.getReadableDatabase();

        String query ="SELECT "+ Params.AMOUNT + " FROM " + Params.BALANCE_SHEET + " WHERE " + Params.TRANSACTION + "='Total: '";
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()) {
            do {
                asset = cursor.getFloat(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return asset;
    }
    
     public boolean AdjTBisEmpty(){
        boolean b= false;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = " SELECT COUNT(*) FROM " + Params.ADJ_TRIAL_B;
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();
        if(cursor.getString(0).equals("0")){
            b= true;
        }
        cursor.close();
        return b;
    }


}
