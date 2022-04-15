package com.gs.dto.area;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;

public class AreaDto extends ApiSuccessResponse {

    @JsonProperty("area")
    private AreaDetailDto areaDetailDto;

    public AreaDto() {
        super(SystemMessage.OK);
    }

    public AreaDetailDto getAreaDetailDto() {
        return this.areaDetailDto;
    }

    public void setAreaDetailDto(AreaDetailDto directionDetail) {
        this.areaDetailDto = directionDetail;
    }
}
