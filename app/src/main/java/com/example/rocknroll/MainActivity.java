package com.example.rocknroll;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    String[] diceType = { "4", "6", "8", "10", "12","20"};
    String diceSelected="";

    int sideUp=0;
    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,diceType);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),diceType[position] , Toast.LENGTH_LONG).show();

        diceSelected = diceType[position];

        i=  Integer.parseInt(diceSelected);

    }


    public void rollOnce(View view) {
        TextView tv =  findViewById(R.id.textView);
        tv.setText("Null");
        Die obj = new Die();


        switch (i) {
            case 4:
                sideUp =  obj.RollTheDie(4);

                tv.setText(String.valueOf(sideUp));
                break;
            case 6:
                sideUp = obj.RollTheDie(6);
                tv.setText(String.valueOf(sideUp));
                break;
            case 8:
                sideUp = obj.RollTheDie(8);
                tv.setText(String.valueOf(sideUp));
                break;

            case 10:
                sideUp = obj.RollTheDie(10);
                tv.setText(String.valueOf(sideUp));
                break;

            case 20:
                sideUp =  obj.RollTheDie(20);
                tv.setText(String.valueOf(sideUp));
                break;

        }
    }

    public void rollTwice(View view) {

              Die object = new Die();
        TextView tv =  findViewById(R.id.textView);
        String result ="";
        String secondTime = "";

        switch (i) {
                case 4:
                  sideUp =   object.RollTheDie(4);
                    result = String.valueOf(sideUp);
                    
                    result = result + ", " + String.valueOf(object.RollTheDie(4));
                    tv.setText(result);
                    break;


                case 6:
                    sideUp =   object.RollTheDie(6);
                    result = String.valueOf(sideUp);

                    result = result + ", " + String.valueOf(object.RollTheDie(6));
                    tv.setText(result);
                    break;
                case 8:
                    sideUp =   object.RollTheDie(8);
                    result = String.valueOf(sideUp);

                    result = result + ", " + String.valueOf(object.RollTheDie(8));
                    tv.setText(result);
                    break;
                case 10:
                    sideUp =   object.RollTheDie(10);
                    result = String.valueOf(sideUp);

                    result = result + ", " + String.valueOf(object.RollTheDie(10));
                    tv.setText(result);
                    break;
                case 20:
                    sideUp =   object.RollTheDie(20);
                    result = String.valueOf(sideUp);

                    result = result + ", " + String.valueOf(object.RollTheDie(20));
                    tv.setText(result);
                    break;
            }
    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}