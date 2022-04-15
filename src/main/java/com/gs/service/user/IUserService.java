package com.gs.service.user;


import com.gs.dto.user.UserDetailDto;
import com.gs.dto.user.UserEntryDto;
import com.gs.exception.MasterException;
import com.gs.service.IService;

public interface IUserService extends IService<UserEntryDto> {

    UserDetailDto getByEmployeeNumber(String employeeNumber) throws MasterException;
}
