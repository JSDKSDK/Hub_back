package com.gs.dto.business;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;

public class BusinessDto extends ApiSuccessResponse {

    @JsonProperty("negocio")
    private BusinessDetailDto business;

    public BusinessDto() {
        super(SystemMessage.OK);
    }

    public BusinessDetailDto getBusiness() {
        return business;
    }

    public void setBusiness(BusinessDetailDto business) {
        this.business = business;
    }
}
