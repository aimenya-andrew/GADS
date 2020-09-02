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

import com.paidapp.gadsproject.Adapter.LearnerAdapter;
import com.paidapp.gadsproject.Model.DataModel;
import com.paidapp.gadsproject.Model.LearnerModel;
import com.paidapp.gadsproject.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LearnerFragment extends Fragment {

    DataModel viewmodel ;
    private RecyclerView recyclerView;
    public LearnerFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_learners, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerlearners);
        viewmodel = new ViewModelProvider(this).get(DataModel.class);

        viewmodel.getTopLearners();

        final LearnerAdapter adapter = new LearnerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        viewmodel.learnermutabledata.observe((LifecycleOwner) getContext(), new Observer<List<LearnerModel>>() {
            @Override
            public void onChanged(List<LearnerModel> postModels) {
                adapter.setList(postModels);
            }
        });
    }
}
