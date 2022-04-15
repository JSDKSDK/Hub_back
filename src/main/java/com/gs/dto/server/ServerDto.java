package com.gs.dto.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;

public class ServerDto extends ApiSuccessResponse {

    @JsonProperty("servidor")
    private ServerDetailDto server;

    public ServerDto(){super(SystemMessage.OK);}

    public ServerDetailDto getServer(){return server;}

    public void setServer(ServerDetailDto server){this.server = server;}
}
