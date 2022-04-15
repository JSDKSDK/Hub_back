package com.gs.dto.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;

import java.util.ArrayList;
import java.util.List;

public class ServersDto extends ApiSuccessResponse {

    @JsonProperty("Servidores")
    private List<ServerDetailDto> servers;

    public ServersDto(){
        super(SystemMessage.OK);
        this.servers = new ArrayList<>();
    }

    public List<ServerDetailDto> getServers(){return servers;}

    public void setServers(List<ServerDetailDto> serversDtos){this.servers = serversDtos;}

    public void addServer(ServerDetailDto serverDto){
        this.servers.add(serverDto);
    }


}
