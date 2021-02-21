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

    ArrayList<ProfileModel> list;
    Context context;
    RecyclerViewClickInterface recyclerViewClickInterface;

    public ProfileAdapter(ArrayList<ProfileModel> list, Context context, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.list = list;
        this.context = context;
        this.recyclerViewClickInterface=recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_recycleview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {

        ProfileModel profile=list.get(position);
        holder.image.setImageResource(R.drawable.student);
        holder.roll_number.setText(profile.getRoll_number());
        holder.name.setText(profile.getPerson_name());


    }

    @Override
    public int getItemCount() {
        return list.size();
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
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });

        }
    }
}
