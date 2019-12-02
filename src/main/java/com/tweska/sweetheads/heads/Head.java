package com.tweska.sweetheads.heads;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.UUID;

public class Head {
    @SerializedName("name")
    private String name;

    @SerializedName("value")
    private String value;

    @SerializedName("uuid")
    private String uuidString;

    @SerializedName("category")
    private String category;

    @SerializedName("tags")
    private List<String> tags;

    @SerializedName("source")
    private String source;

    @SerializedName("url")
    private String sourceURL;


    public Head(String name, String value, String uuid, String category, List<String> tags, String source, String sourceURL) {
        this.name = name;
        this.value = value;
        this.uuidString = uuid;
        this.category = category;
        this.tags = tags;
        this.source = source;
        this.sourceURL = sourceURL;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public UUID getUUID() {
        return UUID.fromString(uuidString);
    }

    public String getCategory() {
        return category;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getSource() {
        return source;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public CustomHead getItem() {
        return new CustomHead(name, uuidString, value);
    }
}
