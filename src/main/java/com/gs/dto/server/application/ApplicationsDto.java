package com.gs.dto.server.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;

import java.util.ArrayList;
import java.util.List;

public class ApplicationsDto extends ApiSuccessResponse {

    @JsonProperty("servidoresAplicativos")
    private List<ApplicationDetailDto> applications;

    public ApplicationsDto(){
        super(SystemMessage.OK);
        this.applications = new ArrayList<>();
    }

    public List<ApplicationDetailDto> getApplications(){
        return applications;
    }

    public void setApplications(List<ApplicationDetailDto> applications) {
        this.applications = applications;
    }

    public void addApplication(ApplicationDetailDto applicationDto){
        this.applications.add(applicationDto);
    }

}
