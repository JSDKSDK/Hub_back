package com.gs.repository.server.application;

import com.gs.model.database.DbMessageModel;
import com.gs.model.server.application.ApplicationModel;
import com.gs.repository.IRepository;
import org.springframework.transaction.annotation.Transactional;

public interface IApplicationRepository extends IRepository<ApplicationModel, ApplicationModel> {

    DbMessageModel<ApplicationModel> listAllByServerId(int idServidor);

}
