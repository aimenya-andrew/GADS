package com.paidapp.gadsproject.TabFragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.paidapp.gadsproject.Adapter.SkillAdapter;
import com.paidapp.gadsproject.Model.DataModel;
import com.paidapp.gadsproject.Model.SkillModel;
import com.paidapp.gadsproject.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SkillFragment extends Fragment {
    DataModel viewmodel ;
    private RecyclerView recyclerView;

    public SkillFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerskill);
        viewmodel = new ViewModelProvider(this).get(DataModel.class);

        viewmodel.getTopSkill();

        final SkillAdapter adapter = new SkillAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        viewmodel.skillmutabledata.observe((LifecycleOwner) getContext(), new Observer<List<SkillModel>>() {
            @Override
            public void onChanged(List<SkillModel> postModels) {
                adapter.setList(postModels);
            }
        });
    }
}
