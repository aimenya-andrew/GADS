package com.paidapp.gadsproject.Retrofit;

import com.paidapp.gadsproject.Model.LearnerModel;
import com.paidapp.gadsproject.Model.SkillModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostInterface {
    @GET("api/skilliq")
    public Call<List<SkillModel>> getTopSkill();

    @GET("api/hours")
    public Call<List<LearnerModel>>   getTopLearners();
}
