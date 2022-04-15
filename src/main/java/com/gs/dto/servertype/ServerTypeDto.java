package com.gs.dto.servertype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;

public class ServerTypeDto extends ApiSuccessResponse {

    @JsonProperty("tipoServidor")
    private ServerTypeDetailDto serverType;

    public ServerTypeDto(){
        super(SystemMessage.OK);
    }

    public ServerTypeDetailDto getServerType(){
        return serverType;
    }

    public void setServerType(ServerTypeDetailDto serverType){
        this.serverType = serverType;
    }
}
