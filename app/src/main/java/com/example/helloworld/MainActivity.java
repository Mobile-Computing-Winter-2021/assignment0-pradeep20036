package com.example.helloworld;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gettingInputData(View view) {
//    getting the input data and sending it to the send activity for display

        EditText et_name=findViewById(R.id.et_name);

        EditText et_ans1=findViewById(R.id.et_ans_1);
        EditText et_ans2=findViewById(R.id.et_ans_2);
        EditText et_ans3=findViewById(R.id.et_ans_3);
        EditText et_ans4=findViewById(R.id.et_ans_4);

        String name=et_name.getText().toString();

        String ans1=et_ans1.getText().toString().toUpperCase();
        String ans2=et_ans2.getText().toString().toUpperCase();
        String ans3=et_ans3.getText().toString().toUpperCase();
        String ans4=et_ans4.getText().toString().toUpperCase();

        Intent intent =new Intent(getApplicationContext(),SecondActivity.class);
//        putting data into the intent to pass to the second activity
        intent.putExtra("name",name);
        intent.putExtra("ans1",ans1);
        intent.putExtra("ans2",ans2);
        intent.putExtra("ans3",ans3);
        intent.putExtra("ans4",ans4);

        startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        when the second activity will result the result we need to catch the result and perform certain operations
//        in first activity







    }
}