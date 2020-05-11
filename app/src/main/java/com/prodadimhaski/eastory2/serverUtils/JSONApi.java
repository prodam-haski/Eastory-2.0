package com.prodadimhaski.eastory2.serverUtils;

import com.prodadimhaski.eastory2.serverUtils.POJO.ResultDTO;
import com.prodadimhaski.eastory2.serverUtils.POJO.TestOTD;
import com.prodadimhaski.eastory2.serverUtils.POJO.TopicOTD;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JSONApi {
    @GET("/test/{id}")
    public Call<List<TestOTD>> getTestByID(@Path("id") int id);

    @GET("/topic")
    public Call<List<TopicOTD>> getAllTopic();

    @GET("/topic/{id}")
    public Call<TopicOTD> getTopicById(@Path("id") int id);

    @POST("/test/check_results")
    public Call<ResponseBody> sendResult(@Body ResultDTO data);

    @POST("/topic")
    public Call<ResponseBody> sendTopic(@Body TopicOTD data);

    @POST("/test")
    public Call<ResponseBody> sendTest(@Body List<TestOTD> data);

    @GET("/test/find_all_results")
    public Call<List<ResultDTO>> getResult();



}
