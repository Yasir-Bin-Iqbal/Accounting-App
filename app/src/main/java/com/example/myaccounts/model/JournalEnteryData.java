package com.example.myaccounts.model;

public class JournalEnteryData {

    private String date;
    private String debit;
    private String debit_Type;
    private String credit;
    private String credit_Type;
    private String amount;
    private String description;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getDebit_Type() {
        return debit_Type;
    }

    public void setDebit_Type(String debit_Type) {
        this.debit_Type = debit_Type;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCredit_Type() {
        return credit_Type;
    }

    public void setCredit_Type(String credit_Type) {
        this.credit_Type = credit_Type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JournalEnteryData(String date, String debit, String debit_Type, String credit, String credit_Type, String amount, String description) {
        this.date = date;
        this.debit = debit;
        this.debit_Type = debit_Type;
        this.credit = credit;
        this.credit_Type = credit_Type;
        this.amount = amount;
        this.description = description;
    }
}

