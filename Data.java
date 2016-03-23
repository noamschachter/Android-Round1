package com.il.tech.hqtask;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Noam Schachter on 3/22/2016.
 */
public class Data implements Serializable {
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("filePath")
    @Expose
    private String filePath;
    @SerializedName("namespace")
    @Expose
    private String namespace;
    @SerializedName("cache")
    @Expose
    private boolean cache;
    @SerializedName("params")
    @Expose
    private List<Object> params = new ArrayList<Object>();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }
}
