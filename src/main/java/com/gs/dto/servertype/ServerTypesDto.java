package com.gs.dto.servertype;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.business.BusinessDetailDto;

import java.util.ArrayList;
import java.util.List;

public class ServerTypesDto extends ApiSuccessResponse {

    @JsonProperty("tipoServidores")
    private List<ServerTypeDetailDto> serverTypes;

    public ServerTypesDto(){
        super(SystemMessage.OK);
        this.serverTypes = new ArrayList<>();
    }

    public List<ServerTypeDetailDto> getServerTypes() {
        return serverTypes;
    }

    public void setServerTypes(List<ServerTypeDetailDto> serverTypesDtos) {
        this.serverTypes = serverTypesDtos;
    }

    public void addServerTypes(ServerTypeDetailDto serverTypesDtos) {
        this.serverTypes.add(serverTypesDtos);
    }

}
