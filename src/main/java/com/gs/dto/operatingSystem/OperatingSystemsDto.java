package com.gs.dto.operatingSystem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.business.BusinessDetailDto;

import java.util.ArrayList;
import java.util.List;

public class OperatingSystemsDto extends ApiSuccessResponse {
    @JsonProperty("sistemasOperativos")
    private List<OperatingSystemDetailDto> operatingSystems;

    public OperatingSystemsDto() {
        super(SystemMessage.OK);
        this.operatingSystems = new ArrayList<>();
    }

    public List<OperatingSystemDetailDto> getOperatingSystems() {
        return operatingSystems;
    }

    public void setOperatingSystems(List<OperatingSystemDetailDto> operatingSystemDtos) {
        this.operatingSystems = operatingSystemDtos;
    }

    public void addOperatingSystem(OperatingSystemDetailDto operatingSystemDto) {
        this.operatingSystems.add(operatingSystemDto);
    }
}
