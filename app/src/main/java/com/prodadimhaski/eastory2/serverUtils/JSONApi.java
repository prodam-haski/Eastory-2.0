package com.prodadimhaski.eastory2.serverUtils;

import com.prodadimhaski.eastory2.serverUtils.POJO.ResultDTO;
import com.prodadimhaski.eastory2.serverUtils.POJO.TestOTD;
import com.prodadimhaski.eastory2.serverUtils.POJO.TopicOTD;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JSONApi {
    @GET("/test/{id}")
    public Call<List<TestOTD>> getTestByID(@Path("id") int id);


    @GET("/topic")
    public Call<List<TopicOTD>> getAllTopic();

    @POST("/test/check_result")
    public Call<ResultDTO> sendResult(@Body ResultDTO data);

}
