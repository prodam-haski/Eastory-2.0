package com.prodadimhaski.eastory2.serverUtils.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultDTO {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("topicId")
    @Expose
    private Integer topicId;
    @SerializedName("rightAnswers")
    @Expose
    private Integer rightAnswers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(Integer rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    public ResultDTO(String name, Integer topicId, Integer rightAnswers) {
        this.name = name;
        this.topicId = topicId;
        this.rightAnswers = rightAnswers;
    }
}
