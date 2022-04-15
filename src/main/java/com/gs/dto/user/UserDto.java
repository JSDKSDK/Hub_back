package com.gs.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;

public class UserDto extends ApiSuccessResponse {

    @JsonProperty("usuario")
    private UserDetailDto userDetailDto;

    public UserDto() {
        super(SystemMessage.OK);
    }

    public UserDetailDto getUserDetailDto() {
        return userDetailDto;
    }

    public void setUserDetailDto(UserDetailDto userDetailDto) {
        this.userDetailDto = userDetailDto;
    }
}
