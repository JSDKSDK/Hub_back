package com.gs.dto.server.note;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;

public class ServerNoteDto extends ApiSuccessResponse {

    @JsonProperty("servidorNotas")
    private ServerNoteDetailDto serverNoteDetail;

    public ServerNoteDto() {
        super(SystemMessage.OK);
    }

    public ServerNoteDetailDto getServerNoteDetail() {
        return this.serverNoteDetail;
    }

    public void setServerNoteDetail(ServerNoteDetailDto serverNoteDetail) {
        this.serverNoteDetail = serverNoteDetail;
    }
}
