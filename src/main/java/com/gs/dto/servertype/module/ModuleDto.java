package com.gs.dto.servertype.module;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;

public class ModuleDto extends ApiSuccessResponse {

    @JsonProperty("modulo")
    private ModuleDetailDto modulo;

    public ModuleDto(){super(SystemMessage.OK);}

    public ModuleDetailDto getModulo() {
        return modulo;
    }

    public void setModule(ModuleDetailDto modulo) {
        this.modulo = modulo;
    }
}
