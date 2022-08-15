package com.example.myaccounts.model;

public class LedgerEntery {
    private String Tranasction;
    private String Type;
    private float Value;
    private String ValueType;

    public String getTranasction() {
        return Tranasction;
    }

    public void setTranasction(String tranasction) {
        Tranasction = tranasction;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public float getValue() {
        return Value;
    }

    public void setValue(float value) {
        Value = value;
    }

    public String getValueType() {
        return ValueType;
    }

    public void setValueType(String valueType) {
        ValueType = valueType;
    }

    public LedgerEntery(String tranasction, String type, float value, String valueType) {
        Tranasction = tranasction;
        Type = type;
        Value = value;
        ValueType = valueType;
    }
}
