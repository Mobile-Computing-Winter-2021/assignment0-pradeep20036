package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_name;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    CheckBox checkBox5;
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Log.i("INFO","State of activity MainActivity is onCreate");
        Toast toast1=Toast.makeText(this, "State of activity MainActivity is onCreate", Toast.LENGTH_SHORT);
        toast1.setGravity(Gravity.CENTER,0,0);
        toast1.show();

//        getting ID's of different views
        et_name=findViewById(R.id.et_name);
        checkBox1=findViewById(R.id.checkBox1);
        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);
        checkBox5=findViewById(R.id.checkBox5);
        tv_result=findViewById(R.id.tv_result_display);

        if(savedInstanceState!=null){
                if(!TextUtils.isEmpty(savedInstanceState.getString(("message")))){
                mText=savedInstanceState.getString("message");
                mColor=savedInstanceState.getString("color");
                tv_result.setText(mText);
                tv_result.setBackgroundColor(Color.parseColor(mColor));
            }
        }


        Log.i("INFO","State of activity MainActivity  changed from onCreate to onStart");
        Toast toast=Toast.makeText(this, "State of activity MainActivity  changed from onCreate to onStart", Toast.LENGTH_SHORT);
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
        Log.i("INFO","State of activity MainActivity  changed from onStart to onResume");
        Toast toast=Toast.makeText(this, "State of activity MainActivity  changed from onStart to onResume", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("INFO","State of activity MainActivity  changed from onResume to onPause");
        Toast toast=Toast.makeText(this, "State of activity MainActivity  changed from onResume to onPause", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();


    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("INFO","State of activity MainActivity  changed from onPause to onStop");
        Toast toast=Toast.makeText(this, "State of activity MainActivity  changed from onPause to onStop", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("INFO","State of activity MainActivity  changed from onStop to onDestroy");
        Toast toast=Toast.makeText(this, "State of activity MainActivity  changed from onStop to onDestroy", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("INFO","State of activity MainActivity  changed from onStop to onRestart");
        Log.i("INFO","State of activity MainActivity  changed from onRestart to onStart");
        Toast.makeText(this, "State of activity MainActivity  changed from onStop to onRestart", Toast.LENGTH_SHORT).show();
        Toast toast=Toast.makeText(this, "State of activity MainActivity  changed from onRestart to onStart", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();

    }


    private String mText,mColor;


    public void gettingInputData(View view) {
//        when the submit button is pressed this function is called.
//    getting the input data and sending it to the send activity for display
        String name=et_name.getText().toString();
//        getting the states of checkboxes and passing it to the next Activity
        boolean checkbox1_state=checkBox1.isChecked();
        boolean checkbox2_state=checkBox2.isChecked();
        boolean checkbox3_state=checkBox3.isChecked();
        boolean checkbox4_state=checkBox4.isChecked();
        boolean checkbox5_state=checkBox5.isChecked();

        Intent intent =new Intent(getApplicationContext(),SecondActivity.class);
//        putting data into the intent to pass to the second activity
        intent.putExtra("name",name);
        intent.putExtra("ans1",checkbox1_state);
        intent.putExtra("ans2",checkbox2_state);
        intent.putExtra("ans3",checkbox3_state);
        intent.putExtra("ans4",checkbox4_state);
        intent.putExtra("ans5",checkbox5_state);

        startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        when the second activity will return the results we need to catch the result and perform certain operations
//        in first activity
        if(requestCode==2)
        {
            String text;
            String color;
            int value=data.getIntExtra("color",0);
            if(value==1)
            {
               text="YOU ARE SAFE";
                color="#34eb40";
            }
            else
            {

                text="YOU ARE UNSAFE";
                color="#FF0000";
            }

            mText=text;
            mColor=color;
            tv_result.setText(mText);
            tv_result.setBackgroundColor(Color.parseColor(mColor));

        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message",mText);
        outState.putString("color",mColor);
    }


    public void clearData(View view) {
        et_name.setText("");
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
        checkBox5.setChecked(false);
        tv_result.setText("");
        tv_result.setBackgroundColor(Color.parseColor("#080277BD"));

    }
}