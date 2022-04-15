package com.gs.service.operatingSystem.impl;

import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.operatingSystem.OperatingSystemDetailDto;
import com.gs.dto.operatingSystem.OperatingSystemDto;
import com.gs.dto.operatingSystem.OperatingSystemsDto;
import com.gs.enums.StatusRecordDb;
import com.gs.exception.MasterException;
import com.gs.model.database.DbMessageModel;
import com.gs.model.operatingSystem.OperatingSystemModel;
import com.gs.repository.operatingSystem.IOperatingSystemRepository;
import com.gs.service.operatingSystem.IOperatingSystemService;
import com.gs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperatingSystemService implements IOperatingSystemService {

    @Autowired
    private IOperatingSystemRepository operatingSystemRepository;

    @Override
    public ApiSuccessResponse create(OperatingSystemDetailDto dto) throws MasterException {
        ApiSuccessResponse response = null;

        response = this.save(dto, false);

        return response;
    }

    @Override
    public ApiSuccessResponse update(OperatingSystemDetailDto dto) throws MasterException {
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

        OperatingSystemsDto operatingSystemListDto = null;
        DbMessageModel<OperatingSystemModel> resultTransaction = null;
        OperatingSystemDetailDto operatingSystemDto = null;
        OperatingSystemModel operatingSystemModel = null;

        try {
            resultTransaction = this.operatingSystemRepository.listAll();
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!resultTransaction.IsTransactionOk()) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, resultTransaction.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        operatingSystemListDto = new OperatingSystemsDto();
        operatingSystemListDto.setOperatingSystems(new ArrayList<>());

        if (resultTransaction.getAllData() != null) {
            for (int i = 0; i < resultTransaction.getAllData().size(); i++) {
                operatingSystemModel = resultTransaction.getAllData().get(i);

                operatingSystemDto = new OperatingSystemDetailDto();
                operatingSystemDto.setIdSOperativo(operatingSystemModel.getIdSOperativo());
                operatingSystemDto.setNombre(operatingSystemModel.getNombre());
                operatingSystemDto.setEstatus(operatingSystemModel.getEstatus());

                operatingSystemListDto.addOperatingSystem(operatingSystemDto);
            }
        }

        return operatingSystemListDto;
    }

    @Override
    public ApiSuccessResponse getById(int id) throws MasterException {

        DbMessageModel<OperatingSystemModel> resultTransaction;
        OperatingSystemDto operatingSystemDto = null;
        OperatingSystemDetailDto operatingSystemDetailDto = null;

        try {
            resultTransaction = this.operatingSystemRepository.getById(id);
        } catch (Exception exception) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resultTransaction.validateTransaction();

        operatingSystemDetailDto = new OperatingSystemDetailDto();
        operatingSystemDetailDto.setIdSOperativo(resultTransaction.getSingleData().getIdSOperativo());
        operatingSystemDetailDto.setNombre(resultTransaction.getSingleData().getNombre());
        operatingSystemDetailDto.setEstatus(resultTransaction.getSingleData().getEstatus());

        operatingSystemDto = new OperatingSystemDto();
        operatingSystemDto.setOperatingSystem(operatingSystemDetailDto);

        return operatingSystemDto;

    }
    private ApiSuccessResponse save(OperatingSystemDetailDto dto, boolean isUpdate) throws MasterException {

        DbMessageModel resulTransaction = null;
        ArrayList<String> errors = new ArrayList<>();
        OperatingSystemModel operatingSystemModel = null;
        ApiSuccessResponse response = null;

        if (!isValidDto(isUpdate, dto, errors)) {
            throw new MasterException(SystemMessage.INVALID_INFORMATION, errors, HttpStatus.BAD_REQUEST);
        }

        operatingSystemModel = OperatingSystemModel.dtoToModel(dto);

        try {
            if (isUpdate) {
                resulTransaction = this.operatingSystemRepository.update(operatingSystemModel);
            } else {
                resulTransaction = this.operatingSystemRepository.create(operatingSystemModel);
            }
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        response = new ApiSuccessResponse(SystemMessage.RECORD_SAVED_SUCCESSFULLY);

        return response;
    }

    private boolean isValidDto(boolean isUpdate, OperatingSystemDetailDto operatingSystemDto, List<String> errors) {

        boolean isOk = true;

        if (operatingSystemDto == null) {
            errors.add(SystemMessage.INVALID_DATA_NULL);
            isOk = false;
        }

        if (isOk) {
            if (isUpdate && operatingSystemDto.getIdSOperativo() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idSOperativo"));
            }

            if (Utils.isEmptyOrWhiteSpaceOrNUll(operatingSystemDto.getNombre())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("nombre"));
            }

            if (!StatusRecordDb.isValidStatus(operatingSystemDto.getEstatus())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("estatus"));
            }
        }

        return isOk;
    }
}
