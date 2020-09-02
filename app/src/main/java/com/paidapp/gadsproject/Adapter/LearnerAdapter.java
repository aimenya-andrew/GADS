package com.paidapp.gadsproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paidapp.gadsproject.Model.LearnerModel;
import com.paidapp.gadsproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LearnerAdapter extends RecyclerView.Adapter<LearnerAdapter.learnerViewHolder> {
    private List<LearnerModel> learnerList = new ArrayList<>();

    @NonNull
    @Override
    public learnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new learnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.learner_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull learnerViewHolder holder, int position) {


        holder.learnersName.setText(learnerList.get(position).getName());
        holder.learnersHours.setText(String.valueOf(learnerList.get(position).getHours())+" Learning hours");
        holder.learnersCountry.setText(learnerList.get(position).getCountry());
        Picasso.get().load(learnerList.get(position).getBadgeUrl()).fit().centerInside().into(holder.learner);
    }

    @Override
    public int getItemCount() {
        return learnerList.size();
    }

    public void setList(List<LearnerModel> list) {
        this.learnerList = list;
        notifyDataSetChanged();
    }

    public class learnerViewHolder extends RecyclerView.ViewHolder {
        TextView learnersName, learnersHours, learnersCountry;
        ImageView learner ;
        public learnerViewHolder(@NonNull View itemView) {
            super(itemView);
            learner= itemView.findViewById(R.id.learners);
            learnersName = itemView.findViewById(R.id.learnersname);
            learnersHours = itemView.findViewById(R.id.learnershours);
            learnersCountry = itemView.findViewById(R.id.learnerscountry);
        }
    }
}