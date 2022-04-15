package com.gs.service.server.note;

import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.server.note.ServerNoteEntryDto;
import com.gs.exception.MasterException;
import com.gs.service.IService;

public interface IServerNoteService extends IService<ServerNoteEntryDto> {

    ApiSuccessResponse listByServerId(int idServidor) throws MasterException;
}
