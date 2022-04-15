package com.gs.dto.area;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;

import java.util.ArrayList;
import java.util.List;

public class AreasDto extends ApiSuccessResponse {

    @JsonProperty("areas")
    private List<AreaDetailDto> areaDetails;

    public AreasDto() {
        super(SystemMessage.OK);
        this.areaDetails = new ArrayList<>();
    }

    public List<AreaDetailDto> getAreaDetails() {
        return areaDetails;
    }

    public void setAreaDetails(List<AreaDetailDto> areaDetails) {
        this.areaDetails = areaDetails;
    }

    public void addAreaDetail(AreaDetailDto areaDetailDto) {

        if (this.areaDetails == null) {
            this.areaDetails = new ArrayList<>();
        }

        this.areaDetails.add(areaDetailDto);
    }
}
