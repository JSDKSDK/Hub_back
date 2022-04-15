package com.gs.dto.direction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;

import java.util.ArrayList;
import java.util.List;

public class DirectionsDto extends ApiSuccessResponse {

    @JsonProperty("direcciones")
    private List<DirectionDetailDto> directionDetails;

    public DirectionsDto() {
        super(SystemMessage.OK);
        this.directionDetails = new ArrayList<>();
    }

    public List<DirectionDetailDto> getDirectionDetails() {
        return directionDetails;
    }

    public void setDirectionDetails(List<DirectionDetailDto> directionDetails) {
        this.directionDetails = directionDetails;
    }

    public void addDirectionDetail(DirectionDetailDto directionDetail) {

        if(this.directionDetails ==null){
            this.directionDetails = new ArrayList<>();
        }

        this.directionDetails.add(directionDetail);
    }
}
