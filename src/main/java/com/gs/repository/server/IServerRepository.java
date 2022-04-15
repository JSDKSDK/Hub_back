package com.gs.repository.server;

import com.gs.model.database.DbMessageModel;
import com.gs.model.server.ServerModel;
import com.gs.model.server.application.ApplicationModel;
import com.gs.repository.IRepository;
import org.springframework.transaction.annotation.Transactional;

public interface IServerRepository extends IRepository<ServerModel, ServerModel> {

}
