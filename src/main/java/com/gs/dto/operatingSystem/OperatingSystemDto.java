package com.gs.dto.operatingSystem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.business.BusinessDetailDto;

public class OperatingSystemDto extends ApiSuccessResponse {
    @JsonProperty("sistemasOperativos")
    private OperatingSystemDetailDto operatingSystem;

    public OperatingSystemDto() {
        super(SystemMessage.OK);
    }

    public OperatingSystemDetailDto getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(OperatingSystemDetailDto operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
}
