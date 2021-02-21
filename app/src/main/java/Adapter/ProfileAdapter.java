package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment3.R;
import com.example.assignment3.RecyclerViewClickInterface;

import java.util.ArrayList;

import Fragments.RecyclerFragment;
import Model.ProfileModel;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    static ArrayList<ProfileModel> profiles;
    Context context;
    RecyclerViewClickInterface recyclerViewClickInterface;
    static boolean flag=false;
    public ProfileAdapter(Context context, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.context = context;
        this.recyclerViewClickInterface=recyclerViewClickInterface;

        if(!flag) {
            profiles = new ArrayList<>();
            System.out.println("Creating New ArrayList");
            profiles.add(new ProfileModel("pradeep Kumar", "pradeep20036@gmail.com", "MT20036", "CSE"));
            profiles.add(new ProfileModel("Nitika Bansal", "Nitika20032@gmail.com", "MT20036", "CSE"));
            profiles.add(new ProfileModel("Akanksha Pandey", "pradeep20031@gmail.com", "MT20036", "CSE"));
            profiles.add(new ProfileModel("Shivank Agrahari", "pradsddeep20030@gmail.com", "MT20036", "CSE"));
            profiles.add(new ProfileModel("Deepankar Kansal", "deep20029@gmail.com", "MT20036", "CSE"));
            profiles.add(new ProfileModel("Divya Pandey", "Divya20128@gmail.com", "MT20128", "CSE"));
            profiles.add(new ProfileModel("Shreya Garg", "shreya20133@gmail.com", "MT20133", "CSE"));
            profiles.add(new ProfileModel("Rahul Gupta", "rahul20311@gmail.com", "MT20311", "CSE"));
            profiles.add(new ProfileModel("Mansi Bansal", "mansi20132@gmail.com", "MT20132", "CSE"));
            profiles.add(new ProfileModel("Deepankar Sarkar", "deepankar20307@gmail.com", "MT20307", "CSE"));
            profiles.add(new ProfileModel("Suverna Bisht", "suverna20125@gmail.com", "MT20125", "CSE"));
            profiles.add(new ProfileModel("Pragya Pal", "Pragya Pal@gmail.com", "MT20309", "CSE"));
            profiles.add(new ProfileModel("Akanksha Pandey", "pradeep20031@gmail.com", "MT20036", "CSE"));
            profiles.add(new ProfileModel("Shivank Agrahari", "pradsddeep20030@gmail.com", "MT20036", "CSE"));
            profiles.add(new ProfileModel("Deepankar Kansal", "deep20029@gmail.com", "MT20036", "CSE"));
//
//
//            MT20131	Kajal Rawat
//            MT20121	Shivam Sharma
//            MT20303	Gaurav Sharma
//            MT20310	Pushpraj Anand
//            MT20309	Pragya Pal
//            MT20130	shivam joshi
//            MT20127	Abhinav Saurabh
//            MT20124	Sriza Sen
//            MT20305	Vishal Kumar
//            MT20301	Aishwariya Sethi


            flag=true;
        }


    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_recycleview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {

        ProfileModel profile=profiles.get(position);
        holder.image.setImageResource(R.drawable.student);
        holder.roll_number.setText(profile.getRoll_number());
        holder.name.setText(profile.getPerson_name());

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        TextView roll_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.iv_image);
            name=itemView.findViewById(R.id.tv_name);
            roll_number=itemView.findViewById(R.id.tv_rollnumber);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerViewClickInterface.onItemClick(getAdapterPosition(),profiles);
                }
            });

        }
    }
}
