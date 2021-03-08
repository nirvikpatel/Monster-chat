package com.nirvik.monsterchat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nirvik.monsterchat.adapters.MessageAdapter;
import com.nirvik.monsterchat.models.NameMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.internal.cache.DiskLruCache;

public class MessageActivity extends AppCompatActivity {
        TextView name;
        RecyclerView recyclerView;
        FirebaseAuth auth;
        FirebaseDatabase database;
        ArrayList<NameMessage> list;
        NameMessage nameMessage;
        ImageView send;
        TextView messsagebox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        name=findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("name"));
        String nametobeprint=getIntent().getStringExtra("name");
        String userid=getIntent().getStringExtra("userid");
        auth=FirebaseAuth.getInstance();
        send=findViewById(R.id.sendBtn);
        list=new ArrayList<>();
        database=FirebaseDatabase.getInstance();
        messsagebox=findViewById(R.id.messageBox);

        recyclerView=findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseUser user=auth.getCurrentUser();
        String myid=user.getUid();


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=messsagebox.getText().toString();
                NameMessage nameMessage=new NameMessage(myid,message);
                
                database.getReference().child("chats").child(userid+myid).push().setValue(nameMessage);

            }
        });
        MessageAdapter messageAdapter = new MessageAdapter(list, MessageActivity.this);
        recyclerView.setAdapter(messageAdapter);
        database.getReference().child("chats").child(myid+userid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                if(snapshot.getChildrenCount()!=0) {

                    for (DataSnapshot data : snapshot.getChildren()) {
                        list.add(new NameMessage(nametobeprint, (String) data.child("message").getValue(),(String) data.child("name").getValue()));

                    }
                    messageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








    }
}