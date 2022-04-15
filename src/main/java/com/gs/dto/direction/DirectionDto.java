package com.gs.dto.direction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;

public class DirectionDto extends ApiSuccessResponse {

    @JsonProperty("direccion")
    private DirectionDetailDto directionDetail;

    public DirectionDto() {

        super(SystemMessage.OK);
    }

    public DirectionDetailDto getDirectionDetail() {
        return this.directionDetail;
    }

    public void setDirectionDetail(DirectionDetailDto directionDetail) {
        this.directionDetail = directionDetail;
    }
}
