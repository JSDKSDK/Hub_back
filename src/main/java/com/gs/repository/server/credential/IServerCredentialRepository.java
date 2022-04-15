package com.gs.repository.server.credential;

import com.gs.model.database.DbMessageModel;
import com.gs.model.server.credential.ServerCredentialDetailModel;
import com.gs.model.server.credential.ServerCredentialModel;
import com.gs.repository.IRepository;

public interface IServerCredentialRepository extends IRepository<ServerCredentialModel, ServerCredentialDetailModel> {
    DbMessageModel<ServerCredentialDetailModel> listAllByServerId(int idServidor);
}
