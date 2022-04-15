package com.gs.dto.server.credential;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;

import java.util.ArrayList;
import java.util.List;

public class ServerCredentialsDto extends ApiSuccessResponse {

    @JsonProperty("servidorCredenciales")
    private List<ServerCredentialDetailDto> serverCredentialDetails;

    public ServerCredentialsDto() {
        super(SystemMessage.OK);
        this.serverCredentialDetails = new ArrayList<>();
    }

    public List<ServerCredentialDetailDto> getServerCredentialDetails() {
        return serverCredentialDetails;
    }

    public void setServerCredentialDetails(List<ServerCredentialDetailDto> serverCredentialDetails) {
        this.serverCredentialDetails = serverCredentialDetails;
    }

    public void addServerCredentialDetails(ServerCredentialDetailDto serverCredentialDetail) {

        if(this.serverCredentialDetails == null){
            this.serverCredentialDetails = new ArrayList<>();
        }

        this.serverCredentialDetails.add(serverCredentialDetail);
    }
}
