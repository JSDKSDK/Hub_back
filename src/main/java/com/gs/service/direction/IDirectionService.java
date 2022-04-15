package com.gs.service.direction;

import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.business.BusinessDetailDto;
import com.gs.dto.direction.DirectionEntryDto;
import com.gs.exception.MasterException;
import com.gs.service.IService;

public interface IDirectionService extends IService<DirectionEntryDto> {
    
    ApiSuccessResponse listByBusinessId(int businessId) throws MasterException;
}
