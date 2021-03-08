package com.nirvik.monsterchat.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.nirvik.monsterchat.R;
import com.nirvik.monsterchat.models.NameMessage;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter {
    int send=1;
    int receive=2;

    ArrayList<NameMessage> list;
    Context context;
    public MessageAdapter(ArrayList<NameMessage> list,Context context) {
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==send){
            Log.d("in send position","in send");

        View view= LayoutInflater.from(context).inflate(R.layout.item_sent,parent,false);
            return new Viewholder1(view);}
        else
        {
            Log.d("in receive position","in recieve");
            View view= LayoutInflater.from(context).inflate(R.layout.item_receive,parent,false);
            return new Viewholder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NameMessage nameMessage=list.get(position);
        if(holder.getClass()==Viewholder.class)
        {
            Log.d("in send position",nameMessage.getMessage());

            ((Viewholder) holder).message.setText(nameMessage.getMessage());
        }
        else
        {
            Log.d("in receive position","set message");
            ((Viewholder1) holder).message1.setText(nameMessage.getMessage());
        }


    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView message;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            message=itemView.findViewById(R.id.messageofsend);
        }
    }

    public class Viewholder1 extends RecyclerView.ViewHolder {
        TextView message1;
        public Viewholder1(@NonNull View itemView) {
            super(itemView);
            message1=itemView.findViewById(R.id.messageofreceive);
        }
    }

    @Override
    public int getItemViewType(int position) {

        NameMessage nameMessage=list.get(position);

        if(nameMessage.getUserid().equals(FirebaseAuth.getInstance().getUid()))
        {
            return send;
        }
        else
        {
            return receive;
        }
    }
}
