package com.gs.dto.server.credential;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;

public class ServerCredentialDto extends ApiSuccessResponse {

    @JsonProperty("servidorCredencial")
    private ServerCredentialDetailDto serverCredentialDetail;

    public ServerCredentialDto() {
        super(SystemMessage.OK);
    }

    public ServerCredentialDetailDto getServerCredentialDetail() {
        return this.serverCredentialDetail;
    }

    public void setServerCredentialDetail(ServerCredentialDetailDto serverCredentialDetail) {
        this.serverCredentialDetail = serverCredentialDetail;
    }
}
