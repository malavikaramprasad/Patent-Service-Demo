package com.patent.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class USPTOOutput {
	@SerializedName("response")
    @Expose
    private USPTOResponse response;

    public USPTOResponse getResponse() {
        return response;
    }

    public void setResponse(USPTOResponse response) {
        this.response = response;
    }
}
