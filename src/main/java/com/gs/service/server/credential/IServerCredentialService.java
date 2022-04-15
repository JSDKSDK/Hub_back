package com.gs.service.server.credential;

import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.server.credential.ServerCredentialEntryDto;
import com.gs.exception.MasterException;
import com.gs.model.database.DbMessageModel;
import com.gs.model.server.credential.ServerCredentialModel;
import com.gs.service.IService;

public interface IServerCredentialService extends IService<ServerCredentialEntryDto> {
    ApiSuccessResponse create(ServerCredentialEntryDto dto) throws MasterException;

    ApiSuccessResponse update(ServerCredentialEntryDto dto) throws MasterException;

    ApiSuccessResponse changeStatus(Object dto);

    ApiSuccessResponse listAll() throws MasterException;

    ApiSuccessResponse getById(int id) throws MasterException;

    ApiSuccessResponse listAllCredentialsByServerId(int idServidor) throws MasterException;
}
