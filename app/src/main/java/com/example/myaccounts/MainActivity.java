package com.example.myaccounts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myaccounts.database.Database;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

public class MainActivity extends AppCompatActivity {


 TextView WelcometextView;
 Intent intent;
 Database db;
 Dialog dialog;
 TextView IncometextView,CapitaltextView,AssettextView,LiabilitytextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WelcometextView = findViewById(R.id.WelcometextView);
        db = new Database(getApplicationContext());
        dialog = new Dialog(this);
        IncometextView = findViewById(R.id.IncometextView);
        CapitaltextView= findViewById(R.id.CapitaltextView);
        AssettextView= findViewById(R.id.AssettextView);
        LiabilitytextView = findViewById(R.id.LiabilitytextView);

        File f = new File("/data/data/com.example.myaccounts/shared_prefs/Company name.xml");
        if(f.exists()){
            SharedPreferences getShared = getSharedPreferences("Company name",MODE_PRIVATE);
            WelcometextView.setText(getShared.getString("name","Error Getting Name"));
        }
        else{
            Log.i("value","inside else");
            dialog.setContentView(R.layout.custom_dialogbox2);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);

            EditText editTextTextPersonName2 = dialog.findViewById(R.id.editTextTextPersonName2);
            Button save = dialog.findViewById(R.id.CompnayNameSavebutton);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(editTextTextPersonName2.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Enter Name First !! ", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        WelcometextView.setText(editTextTextPersonName2.getText());
                        SharedPreferences sharedPreferences = getSharedPreferences("Company name",MODE_PRIVATE);
                        SharedPreferences.Editor editor= sharedPreferences.edit();
                        editor.putString("name",editTextTextPersonName2.getText().toString());
                        editor.apply();
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Name Saved !", Toast.LENGTH_SHORT).show();}
                }
            });
            dialog.show();
        }
           valueSet();
    }

    public void changeName(View view){
       dialog.setContentView(R.layout.custom_dialogbox);
       dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       dialog.setCancelable(false);

        TextInputEditText currentName = dialog.findViewById(R.id.currentNameEditText);
        TextInputEditText NewName = dialog.findViewById(R.id.NewnameEdittext);
        Button save = dialog.findViewById(R.id.CompnayNameSavebutton);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(NewName.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter Name First !! ", Toast.LENGTH_SHORT).show();
                }
                else{
                WelcometextView.setText(NewName.getText());
                SharedPreferences sharedPreferences = getSharedPreferences("Company name",MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.putString("name",NewName.getText().toString());
                editor.apply();
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Name Changed !", Toast.LENGTH_SHORT).show();}
            }
        });
        Button cancel = dialog.findViewById(R.id.Cancelbutton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        SharedPreferences getShared = getSharedPreferences("Company name",MODE_PRIVATE);
        currentName.setText(getShared.getString("name","Error Getting Name"));
        dialog.show();
    }

    public void valueSet(){
        IncometextView.setText("$" + Float.toString(db.incomeValue()));
        float asset = db.assetValue();
        float capital = db.capitalValue();
        CapitaltextView.setText("$" + Float.toString(capital));
        AssettextView.setText("$" + Float.toString(asset));
        float liability = asset - capital;
        LiabilitytextView.setText("$" + Float.toString(liability));
    }

    public void transactions(View view){
        intent = new Intent(getApplicationContext(),Transactions.class);
        startActivity(intent);
    }

  public  void refresh(View view){
        valueSet();
      Toast.makeText(getApplicationContext(), "Values Refreshed !", Toast.LENGTH_SHORT).show();
  }


}