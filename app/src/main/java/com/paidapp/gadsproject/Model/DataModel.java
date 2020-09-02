package com.paidapp.gadsproject.Model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.paidapp.gadsproject.Retrofit.PostClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataModel extends ViewModel {

    public  MutableLiveData<List<SkillModel>> skillmutabledata = new MutableLiveData<>();
    public MutableLiveData<List<LearnerModel>> learnermutabledata = new MutableLiveData<>();


    public void getTopSkill(){

        PostClient.getINSTANCE().getTopSkill().enqueue(new Callback<List<SkillModel>>() {
            @Override
            public void onResponse(Call<List<SkillModel>> call, Response<List<SkillModel>> response) {
                skillmutabledata.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<SkillModel>> call, Throwable t) {

            }
        });
    }
    public void getTopLearners(){

        PostClient.getINSTANCE().getTopLearners().enqueue(new Callback<List<LearnerModel>>() {
            @Override
            public void onResponse(Call<List<LearnerModel>> call, Response<List<LearnerModel>> response) {
                learnermutabledata.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<LearnerModel>> call, Throwable t) {

            }
        });
    }
}

