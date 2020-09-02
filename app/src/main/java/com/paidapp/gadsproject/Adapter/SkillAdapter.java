package com.paidapp.gadsproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paidapp.gadsproject.Model.SkillModel;
import com.paidapp.gadsproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.PostViewHolder> {
    private List<SkillModel> skillList = new ArrayList<>();
    private static final String TAG = "SkillAdapter";


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {


        holder.skillName.setText(skillList.get(position).getName());
        holder.skillScore.setText(String.valueOf(skillList.get(position).getScore())+" Skill IQ Score");
        holder.skillCountry.setText(skillList.get(position).getCountry());
        Picasso.get().load(skillList.get(position).getBadgeUrl()).fit().centerInside().into(holder.skill);
    }

    @Override
    public int getItemCount() {
        return skillList.size();
    }

    public void setList(List<SkillModel> list) {
        this.skillList = list;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView skillName, skillScore, skillCountry;
        ImageView skill ;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            skill= itemView.findViewById(R.id.skill);
            skillName = itemView.findViewById(R.id.skillname);
            skillScore = itemView.findViewById(R.id.skillscore);
            skillCountry = itemView.findViewById(R.id.skillcountry);
        }
    }

}
