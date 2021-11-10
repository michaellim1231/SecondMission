package com.example.mission2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mission2.R;
import com.example.mission2.model.User;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ArrayList<User> userArrayList;

    public MainAdapter(ArrayList<User> data) {
        this.userArrayList = data;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
        holder.uName.setText(userArrayList.get(position).getName());
        holder.uAge.setText(userArrayList.get(position).getAge());
        holder.uAddress.setText(userArrayList.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView uName;
        public TextView uAge;
        public TextView uAddress;


        public ViewHolder(View itemView) {
            super(itemView);
            uName = (TextView) itemView.findViewById(R.id.name_txt);
            uAge = (TextView) itemView.findViewById(R.id.age_txt);
            uAddress = (TextView) itemView.findViewById(R.id.address_txt);

        }
    }
}
