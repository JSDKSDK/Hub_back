package com.gs.dto.passwordType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.operatingSystem.OperatingSystemDetailDto;

import java.util.ArrayList;
import java.util.List;

public class PasswordTypesDto extends ApiSuccessResponse {

    @JsonProperty("tiposContrasena")
    private List<PasswordTypeDetailDto> passwordTypes;

    public PasswordTypesDto() {
        super(SystemMessage.OK);
        this.passwordTypes = new ArrayList<>();
    }

    public List<PasswordTypeDetailDto> getPasswordTypes() {
        return passwordTypes;
    }

    public void setPasswordTypes(List<PasswordTypeDetailDto> passwordTypesDtos) {
        this.passwordTypes = passwordTypesDtos;
    }

    public void addPasswordType(PasswordTypeDetailDto passwordTypeDto) {
        this.passwordTypes.add(passwordTypeDto);
    }
}
