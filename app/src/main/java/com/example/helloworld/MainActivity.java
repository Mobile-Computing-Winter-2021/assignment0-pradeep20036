package com.example.helloworld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

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
        Log.i

        setContentView(R.layout.activity_main);
        et_name=findViewById(R.id.et_name);
        checkBox1=findViewById(R.id.checkBox1);
        checkBox2=findViewById(R.id.checkBox2);
        checkBox3=findViewById(R.id.checkBox3);
        checkBox4=findViewById(R.id.checkBox4);
        checkBox5=findViewById(R.id.checkBox5);

    }

    public void gettingInputData(View view) {
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
//        when the second activity will result the result we need to catch the result and perform certain operations
//        in first activity
        if(requestCode==2)
        {
            tv_result=findViewById(R.id.tv_result_display);
            int value=data.getIntExtra("color",0);
            if(value==1)
            {
                tv_result.setText("YOU ARE SAFE");
                tv_result.setBackgroundColor(Color.GREEN);
            }
            else
            {
                tv_result.setText("YOU ARE UNSAFE");
                tv_result.setBackgroundColor(Color.RED);
            }

        }

    }

    public void clearData(View view) {
        et_name.setText("");
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        checkBox4.setChecked(false);
        checkBox5.setChecked(false);
        tv_result.setText("");
        tv_result.setBackgroundColor(Color.WHITE);

    }
}