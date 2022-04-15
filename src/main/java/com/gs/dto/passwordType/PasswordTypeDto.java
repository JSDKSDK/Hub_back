package com.gs.dto.passwordType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.operatingSystem.OperatingSystemDetailDto;

public class PasswordTypeDto extends ApiSuccessResponse {

    @JsonProperty("tiposContrasena")
    private PasswordTypeDetailDto passwordType;

    public PasswordTypeDto() {
        super(SystemMessage.OK);
    }

    public PasswordTypeDetailDto getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(PasswordTypeDetailDto passwordType) {
        this.passwordType = passwordType;
    }
}
