package com.gs.dto.server.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;

public class ApplicationDto extends ApiSuccessResponse {

    @JsonProperty("servidorAplicativo")
    private ApplicationDetailDto application;

    public ApplicationDto() {
        super(SystemMessage.OK);
    }

    public ApplicationDetailDto getApplication() {
        return application;
    }

    public void setApplication(ApplicationDetailDto application) {
        this.application = application;
    }
}
