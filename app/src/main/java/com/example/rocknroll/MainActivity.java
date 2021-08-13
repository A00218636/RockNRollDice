package com.example.rocknroll;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    //Default list which stores the dice types in spinner.
    List<String> diceTypeList = new ArrayList<>(Arrays.asList("4", "6", "8", "10", "12", "20"));


    String diceSelected="";
    String[] customDiceTypeArray = new String[5];

    int sideUp=0;
    int i=0;

    Button btnSave, btnAddCustomDie, btnClrCustomDice;
    TextView resultView, customDieView;
    SharedPreferences sp;
    String resultsValue, customDieValue;
    int lastDicePosition=0;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        btnSave =  findViewById(R.id.btnSave);
        resultView = findViewById(R.id.resultView);
        btnAddCustomDie = findViewById(R.id.btnAddCustomDie);
        customDieView = findViewById(R.id.customDie);
        btnClrCustomDice = findViewById(R.id.btnClearCustomDice);




        //Instantiate shared preferences to save the values of the user

        sp = getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();


        //Get list data from sp
        //Retrieve the values from shared preferences. Shared preferences use Set collection for storing the
        //list values. Order will be different as Set doesn't keep the values in order like list.
        Set<String> defaultSet = new HashSet<>();
        Set<String> keyValSet = sp.getStringSet("keyValues", defaultSet);

        if(!keyValSet.isEmpty()) {
            diceTypeList.clear();
            diceTypeList.addAll(keyValSet);
        }


        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,diceTypeList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);


          //Set controls value from shared pref on load of the main activity
          resultView.setText(sp.getString("result", ""));
          String lastDieString = sp.getString("lastDicePos", "");
          if(lastDieString !="") {
              int posStr = parseInt(lastDieString);
              spin.setSelection(posStr);
          }

          String custDieStr = sp.getString("customDie","");

          if(custDieStr !="") {
              customDieView.setText(custDieStr);
          }

          //On click of SAVE button, app saves the current state
       btnSave.setOnClickListener(v -> {

           resultsValue = resultView.getText().toString();

          //Save results of the dice throw
           editor.putString("result", resultsValue);


           editor.putString("lastDicePos", String.valueOf(lastDicePosition));

           //Save custom Die value
           customDieValue = customDieView.getText().toString();
           editor.putString("customDie", customDieValue);

           //Set the values of list
           Set<String> set = new HashSet<String>(diceTypeList);
           editor.putStringSet("keyValues", set);

           editor.apply();
           Toast.makeText(MainActivity.this, "Data saved", Toast.LENGTH_LONG).show();

       });

          //When user clicks on Add button to add custom type
          btnAddCustomDie.setOnClickListener(v -> {

              customDieValue = customDieView.getText().toString();
              diceTypeList.add(customDieValue);


          });


          //To clear the shared preferences
           btnClrCustomDice.setOnClickListener(v -> {

//               if(diceTypeList.size()>6) {
//                   for (int j = diceTypeList.size(); j > 6; j--) {
//
//                       diceTypeList.remove(j - 1);
//
//
//                   }
//               }

//               SharedPreferences.Editor editor = sp.edit();
               editor.clear();
               editor.apply();
           });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), diceTypeList.get(position), Toast.LENGTH_LONG).show();

        diceSelected = diceTypeList.get(position);
        lastDicePosition = position;

        if(diceSelected!="") {
            i = parseInt(diceSelected);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void rollOnce(View view) {
        TextView tv =  findViewById(R.id.resultView);
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



            default:
                sideUp = obj.RollTheDie(i);
                tv.setText(String.valueOf(sideUp));
                break;
        }


    }

    //Triggers when rolled twice
    public void rollTwice(View view) {

              Die object = new Die();
        TextView tv =  findViewById(R.id.resultView);
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

            default:
                sideUp =   object.RollTheDie(i);
                result = String.valueOf(sideUp);
                result = result + ", " + String.valueOf(object.RollTheDie(i));

                tv.setText(result);
                break;
            }
    }

}