package com.example.myaccounts.params;

public class Params {
    public static final int DB_VERSION= 1;
    public static final String DB_NAME="Accounts";

    public static final String Journal_ENT="JournalEnteries";
    public static final String DATE= "Date";
    public static final String DEBIT="Debit";
    public static final String DEBIT_TYPE="DRType";
    public static final String CREDIT="Credit";
    public static final String CREDIT_TYPE="CRType";
    public static final String AMOUNT="Amount";
    public static final String Desp="Description";

    public static final String LEDGER="LedgerAccount";
    public static final String TRANSACTION="TransactionName";
    public static final String TYPE="Type";
    public static final String L_AMOUNT="Amount";
    public static final String BALANCE="Balance";

    public static final String ADJ_Journal_ENT="AdjustingJournalEnteries";
    public static final String ADJ_LEDGER="AdjustingLedgerAccount";


    public static final String ADJ_TRIAL_B="AdjustingTrialBalance";
    /* Transaction name
       Type
       DR
       Cr
       Already exist above
    */

    public static final String INC_ST="IncomeStatement";
     /* Transaction name
       DR
       Cr
       Already exist above
    */

    public static final String OWNER_EQUITY="StOfOwnerEquity";
     /* Transaction name
       Amount
       Already exist above
    */

    public static final String BALANCE_SHEET="BalanceSheet";
     /* Transaction name
       Amount
       Already exist above
    */
}
