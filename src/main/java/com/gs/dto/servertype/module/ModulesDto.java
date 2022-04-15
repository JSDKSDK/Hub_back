package com.gs.dto.servertype.module;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;

import java.util.ArrayList;
import java.util.List;

public class ModulesDto extends ApiSuccessResponse {

    @JsonProperty("modulos")
    private List<ModuleDetailDto> modules;

    public ModulesDto() {
        super(SystemMessage.OK);
        this.modules = new ArrayList<>();
    }

    public List<ModuleDetailDto> getModules() {
        return modules;
    }

    public void setModules(List<ModuleDetailDto> modulesDtos) {
        this.modules = modulesDtos;
    }

    public void addModules(ModuleDetailDto modulesDtos) {
        this.modules.add(modulesDtos);
    }

}


