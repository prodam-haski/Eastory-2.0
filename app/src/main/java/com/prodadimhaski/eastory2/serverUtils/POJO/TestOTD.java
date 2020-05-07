package com.prodadimhaski.eastory2.serverUtils.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestOTD {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("topicId")
    @Expose
    private Integer topicId;
    @SerializedName("questionId")
    @Expose
    private Integer questionId;

    public Integer getId() {
        return id;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
