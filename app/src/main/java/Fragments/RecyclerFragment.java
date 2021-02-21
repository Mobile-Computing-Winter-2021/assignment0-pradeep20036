package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment3.R;
import com.example.assignment3.RecyclerViewClickInterface;

import java.util.ArrayList;

import Adapter.ProfileAdapter;
import Model.ProfileModel;

public class RecyclerFragment extends Fragment {
    RecyclerViewClickInterface recyclerViewClickInterface;
    public RecyclerFragment( RecyclerViewClickInterface recyclerViewClickInterface) {
        this.recyclerViewClickInterface=recyclerViewClickInterface;
    }

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_recycler, container, false);
        recyclerView=view.findViewById(R.id.recyclerview);

//        passing data to the list
        ProfileAdapter adapter=new ProfileAdapter(getContext(),recyclerViewClickInterface);
//setting the adapter
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linear_layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linear_layoutManager);

        return view;
    }
}