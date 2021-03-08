package com.nirvik.monsterchat.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nirvik.monsterchat.R;
import com.nirvik.monsterchat.adapters.UsersAdapter;
import com.nirvik.monsterchat.models.User;

import java.util.ArrayList;


public class ChatFragment extends Fragment {



    public ChatFragment() {

    }
    ArrayList<User> list=new ArrayList<>();
    FirebaseDatabase database;
    RecyclerView recyclerView;
    UsersAdapter usersAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        recyclerView=view.findViewById(R.id.recylerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        database=FirebaseDatabase.getInstance();
        list.clear();
        database.getReference().child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {

                    User user=dataSnapshot.getValue(User.class);
                    if(user!=null && !user.getId().equals(FirebaseAuth.getInstance().getUid())){
                    list.add(user);
                    }
                }

                usersAdapter=new UsersAdapter(list,getContext());
                recyclerView.setAdapter(usersAdapter);
                usersAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



        return view;

    }

}