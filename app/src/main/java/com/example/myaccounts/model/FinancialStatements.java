package com.example.myaccounts.model;

public class FinancialStatements {
    String Transaction;
    String amount;

    public String getTransaction() {
        return Transaction;
    }

    public void setTransaction(String transaction) {
        Transaction = transaction;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public FinancialStatements(String transaction, String amount) {
        Transaction = transaction;
        this.amount = amount;
    }
}
