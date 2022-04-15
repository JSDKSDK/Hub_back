package com.gs.service.passwordType.impl;

import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.passwordType.PasswordTypeDetailDto;
import com.gs.dto.passwordType.PasswordTypeDto;
import com.gs.dto.passwordType.PasswordTypesDto;
import com.gs.enums.StatusRecordDb;
import com.gs.exception.MasterException;
import com.gs.model.database.DbMessageModel;
import com.gs.model.passwordType.PasswordTypeModel;
import com.gs.repository.passwordType.IPasswordTypeRepository;
import com.gs.service.passwordType.IPasswordTypeService;
import com.gs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordTypeService implements IPasswordTypeService {

    @Autowired
    private IPasswordTypeRepository passwordTypeRepository;



    @Override
    public ApiSuccessResponse create(PasswordTypeDetailDto dto) throws MasterException {

        ApiSuccessResponse response = null;

        response = this.save(dto, false);

        return response;
    }

    @Override
    public ApiSuccessResponse update(PasswordTypeDetailDto dto) throws MasterException {

        ApiSuccessResponse response = null;

        response = this.save(dto, true);

        return response;
    }

    @Override
    public ApiSuccessResponse changeStatus(Object dto) {
        return null;
    }

    @Override
    public ApiSuccessResponse listAll() throws MasterException {

        PasswordTypesDto passwordTypeListDto = null;
        DbMessageModel<PasswordTypeModel> resultTransaction = null;
        PasswordTypeDetailDto passwordTypeDto = null;
        PasswordTypeModel passwordTypeModel = null;

        try {
            resultTransaction = this.passwordTypeRepository.listAll();
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!resultTransaction.IsTransactionOk()) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, resultTransaction.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        passwordTypeListDto = new PasswordTypesDto();
        passwordTypeListDto.setPasswordTypes(new ArrayList<>());

        if (resultTransaction.getAllData() != null) {
            for (int i = 0; i < resultTransaction.getAllData().size(); i++) {
                passwordTypeModel = resultTransaction.getAllData().get(i);

                passwordTypeDto = new PasswordTypeDetailDto();
                passwordTypeDto.setIdPasswordType(passwordTypeModel.getIdPasswordType());
                passwordTypeDto.setPasswordType(passwordTypeModel.getPasswordType());
                passwordTypeDto.setEstatus(passwordTypeModel.getEstatus());

                passwordTypeListDto.addPasswordType(passwordTypeDto);
            }
        }

        return passwordTypeListDto;
    }

    @Override
    public ApiSuccessResponse getById(int id) throws MasterException {

        DbMessageModel<PasswordTypeModel> resultTransaction;
        PasswordTypeDto passwordTypeDto = null;
        PasswordTypeDetailDto passwordTypeDetailDto = null;

        try {
            resultTransaction = this.passwordTypeRepository.getById(id);
        } catch (Exception exception) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resultTransaction.validateTransaction();

        passwordTypeDetailDto = new PasswordTypeDetailDto();
        passwordTypeDetailDto.setIdPasswordType(resultTransaction.getSingleData().getIdPasswordType());
        passwordTypeDetailDto.setPasswordType(resultTransaction.getSingleData().getPasswordType());
        passwordTypeDetailDto.setEstatus(resultTransaction.getSingleData().getEstatus());

        passwordTypeDto = new PasswordTypeDto();
        passwordTypeDto.setPasswordType(passwordTypeDetailDto);

        return passwordTypeDto;
    }

    private ApiSuccessResponse save(PasswordTypeDetailDto dto, boolean isUpdate) throws MasterException {

        DbMessageModel resulTransaction = null;
        ArrayList<String> errors = new ArrayList<>();
        PasswordTypeModel passwordTypeModel = null;
        ApiSuccessResponse response = null;

        if (!isValidDto(isUpdate, dto, errors)) {
            throw new MasterException(SystemMessage.INVALID_INFORMATION, errors, HttpStatus.BAD_REQUEST);
        }

        passwordTypeModel = passwordTypeModel.dtoToModel(dto);

        try {
            if (isUpdate) {
                resulTransaction = this.passwordTypeRepository.update(passwordTypeModel);
            } else {
                resulTransaction = this.passwordTypeRepository.create(passwordTypeModel);
            }
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        response = new ApiSuccessResponse(SystemMessage.RECORD_SAVED_SUCCESSFULLY);

        return response;
    }

    private boolean isValidDto(boolean isUpdate, PasswordTypeDetailDto passwordTypeDto, List<String> errors) {

        boolean isOk = true;

        if (passwordTypeDto == null) {
            errors.add(SystemMessage.INVALID_DATA_NULL);
            isOk = false;
        }

        if (isOk) {
            if (isUpdate && passwordTypeDto.getIdPasswordType() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idPasswordType"));
            }

            if (Utils.isEmptyOrWhiteSpaceOrNUll(passwordTypeDto.getPasswordType())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("passwordType"));
            }

            if (!StatusRecordDb.isValidStatus(passwordTypeDto.getEstatus())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("estatus"));
            }
        }

        return isOk;
    }
}
