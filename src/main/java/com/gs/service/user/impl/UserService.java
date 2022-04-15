package com.gs.service.user.impl;

import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.user.UserDetailDto;
import com.gs.dto.user.UserEntryDto;
import com.gs.exception.MasterException;
import com.gs.model.database.DbMessageModel;
import com.gs.model.user.UserModel;
import com.gs.repository.user.IUserRepository;
import com.gs.service.user.IUserService;
import com.gs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    IUserRepository iUserRepository;

    @Autowired
    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public ApiSuccessResponse create(UserEntryDto dto) throws MasterException {
        return null;
    }

    @Override
    public ApiSuccessResponse update(UserEntryDto dto) throws MasterException {
        return null;
    }

    @Override
    public ApiSuccessResponse changeStatus(Object dto) {
        return null;
    }

    @Override
    public ApiSuccessResponse listAll() throws MasterException {
        return null;
    }

    @Override
    public ApiSuccessResponse getById(int id) throws MasterException {
        return null;
    }

    @Override
    public UserDetailDto getByEmployeeNumber(String employeeNumber) throws MasterException {

        UserDetailDto userDetailDto = null;
        DbMessageModel<UserModel> resulTransacion;

        if (Utils.isEmptyOrWhiteSpaceOrNUll(employeeNumber)) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, SystemMessage.getMessageParamValueInvalid("número de empleado"), HttpStatus.BAD_REQUEST);
        }

        resulTransacion = this.iUserRepository.getByEmployeeNumber(employeeNumber);

        resulTransacion.validateTransaction();
        userDetailDto = UserDetailDto.userModelToDto(resulTransacion.getSingleData());

        return userDetailDto;
    }
}
