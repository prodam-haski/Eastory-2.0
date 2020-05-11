package com.prodadimhaski.eastory2.serverUtils.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopicOTD {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("topicText")
    @Expose
    private String topicText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopicText() {
        return topicText;
    }

    public void setTopicText(String topicText) {
        this.topicText = topicText;
    }

    public TopicOTD(Integer id, String topicText) {
        this.id = id;
        this.topicText = topicText;
    }
}
