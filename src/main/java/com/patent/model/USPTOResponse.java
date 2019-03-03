package com.patent.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class USPTOResponse {

    @SerializedName("numFound")
    @Expose
    private Long numFound;
    @SerializedName("start")
    @Expose
    private Long start;
    @SerializedName("docs")
    @Expose
    private List<Patent> docs = null;

    public Long getNumFound() {
        return numFound;
    }

    public void setNumFound(Long numFound) {
        this.numFound = numFound;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public List<Patent> getDocs() {
        return docs;
    }

    public void setDocs(List<Patent> docs) {
        this.docs = docs;
    }
}
