package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    private int count_yes=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


//        getting the values of the intent passed
           displayingIntentResults();


    }

    protected void displayingIntentResults()
    {
        String name=getIntent().getStringExtra("name");
        String ans1=getIntent().getStringExtra("ans1");
        String ans2=getIntent().getStringExtra("ans2");
        String ans3=getIntent().getStringExtra("ans3");
        String ans4=getIntent().getStringExtra("ans4");




//        getting the elements of layout to display the results in them
        EditText et_name=findViewById(R.id.et_name);
        EditText et_ans1=findViewById(R.id.et_ans_1);
        EditText et_ans2=findViewById(R.id.et_ans_2);
        EditText et_ans3=findViewById(R.id.et_ans_3);
        EditText et_ans4=findViewById(R.id.et_ans_4);

        et_name.setText(name);
        et_ans1.setText(ans1);
        et_ans2.setText(ans2);
        et_ans3.setText(ans3);
        et_ans4.setText(ans4);

    }


    public void checkRiskStatus(View view) {





    }
}