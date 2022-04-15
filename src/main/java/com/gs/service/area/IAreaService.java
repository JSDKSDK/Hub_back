package com.gs.service.area;

import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.area.AreaEntryDto;
import com.gs.dto.direction.DirectionEntryDto;
import com.gs.exception.MasterException;
import com.gs.service.IService;

public interface IAreaService extends IService<AreaEntryDto> {

    ApiSuccessResponse listAllByDirectionId(int directionId) throws MasterException;
}
