package com.gs.service.server.application;

import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.server.application.ApplicationDetailDto;
import com.gs.exception.MasterException;
import com.gs.service.IService;

public interface IApplicationService extends IService<ApplicationDetailDto> {
    ApiSuccessResponse listAllByServerId(int idServidor) throws MasterException;
}
