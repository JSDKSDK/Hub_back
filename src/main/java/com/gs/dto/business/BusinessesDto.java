package com.gs.dto.business;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;

import java.util.ArrayList;
import java.util.List;

public class BusinessesDto extends ApiSuccessResponse {

    @JsonProperty("negocios")
    private List<BusinessDetailDto> businesses;

    public BusinessesDto() {
        super(SystemMessage.OK);
        this.businesses = new ArrayList<>();
    }

    public List<BusinessDetailDto> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<BusinessDetailDto> businessDtos) {
        this.businesses = businessDtos;
    }

    public void addBusiness(BusinessDetailDto businessDto) {
        this.businesses.add(businessDto);
    }
}
