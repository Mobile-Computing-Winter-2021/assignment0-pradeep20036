package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private int checkedCount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.i("INFO","State of activity SecondActivity is onCreate");
        Toast.makeText(this, "State of activity SecondActivity is onCreate", Toast.LENGTH_SHORT).show();
    
        

//        getting the values of the intent passed
           displayingIntentResults();

        Log.i("INFO","State of activity SecondActivity  changed from onCreate to onStart");
        Toast toast=Toast.makeText(this, "State of activity SecondActivity  changed from onCreate to onStart", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("INFO","State of activity SecondActivity  changed from onStart to onResume");
        Toast toast=Toast.makeText(this, "State of activity SecondActivity  changed from onStart to onResume", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("INFO","State of activity SecondActivity  changed from onResume to onPause");
        Toast.makeText(this, "State of activity SecondActivity  changed from onResume to onPause", Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("INFO","State of activity SecondActivity  changed from onPause to onStop");
        Toast.makeText(this, "State of activity SecondActivity  changed from onPause to onStop", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("INFO","State of activity SecondActivity  changed from onStop to onDestroy");
        Toast.makeText(this, "State of activity SecondActivity  changed from onStop to onDestroy", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("INFO","State of activity SecondActivity  changed from onStop to onRestart");
        Log.i("INFO","State of activity SecondActivity  changed from onRestart to onStart");
        Toast.makeText(this, "State of activity SecondActivity  changed from onStop to onRestart", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "State of activity SecondActivity  changed from onRestart to onStart", Toast.LENGTH_SHORT).show();


    }


    protected void displayingIntentResults()
    {
        String name=getIntent().getStringExtra("name");

        Boolean ans1=getIntent().getBooleanExtra("ans1",false);
        Boolean ans2=getIntent().getBooleanExtra("ans2",false);
        Boolean ans3=getIntent().getBooleanExtra("ans3",false);
        Boolean ans4=getIntent().getBooleanExtra("ans4",false);
        Boolean ans5=getIntent().getBooleanExtra("ans5",false);


//        getting the number of checks to get the risk status
        if(ans1)
            checkedCount++;
        if(ans2)
            checkedCount++;
        if(ans3)
            checkedCount++;
        if(ans4)
            checkedCount++;
        if(ans5)
            checkedCount++;




//        getting the elements of layout to display the results in them
        EditText et_name=findViewById(R.id.et_name);
        CheckBox checkBox1=findViewById(R.id.checkBox1);
        CheckBox checkBox2=findViewById(R.id.checkBox2);
        CheckBox checkBox3=findViewById(R.id.checkBox3);
        CheckBox checkBox4=findViewById(R.id.checkBox4);
        CheckBox checkBox5=findViewById(R.id.checkBox5);

        et_name.setText("Dear, "+name);
        checkBox1.setChecked(ans1);
        checkBox2.setChecked(ans2);
        checkBox3.setChecked(ans3);
        checkBox4.setChecked(ans4);
        checkBox5.setChecked(ans5);

        checkBox1.setClickable(false);
        checkBox2.setClickable(false);
        checkBox3.setClickable(false);
        checkBox4.setClickable(false);
        checkBox5.setClickable(false);

        et_name.setClickable(false);
        et_name.setFocusable(false);

    }

    public void sendRiskStatus(View view) {

//        color codes 1 for green safe
//        color codes 2 for red for unsafe
        Intent intent=new Intent();
        if(checkedCount==5)
        {
            intent.putExtra("color",1);
        }
        else {
            intent.putExtra("color",2);
        }

        setResult(2,intent);
        finish();

    }
}