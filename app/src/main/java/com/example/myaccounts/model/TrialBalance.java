package com.example.myaccounts.model;

public class TrialBalance {
    String TransactionName ;
    String Type;
    String Dr;
    String Cr;

    public String getTransactionName() {
        return TransactionName;
    }

    public void setTransactionName(String transactionName) {
        TransactionName = transactionName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDr() {
        return Dr;
    }

    public void setDr(String dr) {
        Dr = dr;
    }

    public String getCr() {
        return Cr;
    }

    public void setCr(String cr) {
        Cr = cr;
    }

    public TrialBalance(String transactionName, String type, String dr, String cr) {

        TransactionName = transactionName;
        Type = type;
        Dr = dr;
        Cr = cr;
    }
}
