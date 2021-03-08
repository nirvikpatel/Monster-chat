package com.nirvik.monsterchat.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.nirvik.monsterchat.MessageActivity;
import com.nirvik.monsterchat.R;
import com.nirvik.monsterchat.models.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsersAdapter extends  RecyclerView.Adapter<UsersAdapter.ViewHolder>{
    DatabaseReference reference;
    @NonNull
    ArrayList<User> list;
    Context context;

    public UsersAdapter(@NonNull ArrayList<User> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sample_show_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user=list.get(position);
        Picasso.get().load(user.getProfile()).placeholder(R.drawable.avatar).into(holder.image);
        holder.username.setText(user.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MessageActivity.class);
                intent.putExtra("name",user.getName());
                intent.putExtra("userid",user.getId());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView image;
        TextView username,lastmessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.profile);
            username=itemView.findViewById(R.id.username);
            lastmessage=itemView.findViewById(R.id.lastMsg);


        }
    }
}
