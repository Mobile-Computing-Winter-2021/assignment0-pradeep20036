package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.MainActivity;
import com.example.assignment3.R;

import java.util.ArrayList;

import Model.ProfileModel;

public class DetailsFragment extends Fragment {


    Button btn_edit;
    Button btn_back;
    ArrayList<ProfileModel> profiles;
    int position;
    public DetailsFragment(ArrayList<ProfileModel> list,int position) {
        profiles=list;
        this.position=position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_details, container, false);
        EditText tv_name;
        EditText tv_rollno;
        EditText tv_email;
        EditText tv_branch;
        ImageView profile_pic;
        tv_name=view.findViewById(R.id.tv_name_display);
        tv_rollno=view.findViewById(R.id.tv_rollnumber_display);
        tv_email=view.findViewById(R.id.tv_email_display);
        tv_branch=view.findViewById(R.id.tv_branch_display);
        profile_pic=view.findViewById(R.id.iv_imageProfile);


        ProfileModel person_profile=profiles.get(position);
        tv_name.setText(person_profile.getPerson_name());
        tv_rollno.setText(person_profile.getRoll_number());
        tv_email.setText(person_profile.getEmail_id());
        tv_branch.setText(person_profile.getPerson_branch());
        profile_pic.setImageResource(person_profile.getPic());

        btn_back=view.findViewById(R.id.bt_back);
        btn_edit=view.findViewById(R.id.bt_edit);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(),"Edit Text Enabled",Toast.LENGTH_SHORT).show();

                ProfileModel person_profile=profiles.get(position);

                System.out.println("Arraylist: Details Fragment"+profiles);

                person_profile.setPerson_name(tv_name.getText().toString());
                person_profile.setRoll_number(tv_rollno.getText().toString());
                person_profile.setEmail_id(tv_email.getText().toString());
                person_profile.setPerson_branch(tv_branch.getText().toString());

            }
        });



        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MainActivity.class));
            }
        });



        return view;
    }
}


