package com.gs.dto.server.note;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import java.util.ArrayList;
import java.util.List;

public class ServerNotesDto extends ApiSuccessResponse {

    @JsonProperty("servidorNotas")
    private List<ServerNoteDetailDto> serverNoteDetails;

    public ServerNotesDto() {
        super(SystemMessage.OK);
        this.serverNoteDetails = new ArrayList<>();
    }

    public List<ServerNoteDetailDto> getServerNoteDetails() {
        return serverNoteDetails;
    }

    public void setServerNoteDetails(List<ServerNoteDetailDto> serverNoteDetails) {
        this.serverNoteDetails = serverNoteDetails;
    }

    public void addServerNoteDetails(ServerNoteDetailDto serverNoteDetail) {

        if(this.serverNoteDetails == null){
            this.serverNoteDetails = new ArrayList<>();
        }

        this.serverNoteDetails.add(serverNoteDetail);
    }
}
