package com.gs.service.area.impl;

import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.area.AreaDetailDto;
import com.gs.dto.area.AreaDto;
import com.gs.dto.area.AreaEntryDto;
import com.gs.dto.area.AreasDto;
import com.gs.enums.StatusRecordDb;
import com.gs.exception.MasterException;
import com.gs.model.area.AreaDetailModel;
import com.gs.model.area.AreaModel;
import com.gs.model.database.DbMessageModel;
import com.gs.repository.area.IAreaRepository;
import com.gs.service.area.IAreaService;
import com.gs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaService implements IAreaService {

    @Autowired
    private IAreaRepository areaRepository;

    @Override
    public ApiSuccessResponse create(AreaEntryDto areaEntryDto) throws MasterException {

        ApiSuccessResponse response = null;

        response = this.save(areaEntryDto, false);

        return response;
    }

    @Override
    public ApiSuccessResponse update(AreaEntryDto areaEntryDto) throws MasterException {

        ApiSuccessResponse response = null;

        response = this.save(areaEntryDto, true);

        return response;
    }

    @Override
    public ApiSuccessResponse changeStatus(Object body) {
        return null;
    }

    @Override
    public ApiSuccessResponse listAll() throws MasterException {

        int directionId = -1;

        return this.listAllByDirectionId(directionId);
    }

    @Override
    public ApiSuccessResponse listAllByDirectionId(int directionId) throws MasterException {

        AreaDetailDto areaDetailDto = null;
        DbMessageModel<AreaDetailModel> resulTransaction = null;
        AreasDto areasDto = null;

        try {
            if (directionId > 0) {
                resulTransaction = this.areaRepository.listAllByDirectionId(directionId);
            } else {
                resulTransaction = this.areaRepository.listAll();
            }
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!resulTransaction.IsTransactionOk()) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, resulTransaction.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        areasDto = new AreasDto();

        if (resulTransaction.getAllData() != null) {
            for (int i = 0; i < resulTransaction.getAllData().size(); i++) {
                areaDetailDto = AreaDetailDto.areaDetailModelToDto(resulTransaction.getAllData().get(i));
                areasDto.addAreaDetail(areaDetailDto);
            }
        }

        return areasDto;
    }

    @Override
    public ApiSuccessResponse getById(int id) throws MasterException {

        DbMessageModel<AreaDetailModel> resulTransaction;
        AreaDto areaDto = null;
        AreaDetailDto areaDetailDto = null;

        try {
            resulTransaction = this.areaRepository.getById(id);
        } catch (Exception exception) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        areaDto = new AreaDto();
        areaDetailDto = AreaDetailDto.areaDetailModelToDto(resulTransaction.getSingleData());
        areaDto.setAreaDetailDto(areaDetailDto);

        return areaDto;
    }

    private ApiSuccessResponse save(AreaEntryDto dto, boolean isUpdate) throws MasterException {

        DbMessageModel resulTransaction = null;
        ArrayList<String> errors = new ArrayList<>();
        AreaModel areaModel = null;
        ApiSuccessResponse response = null;

        if (!isValidDto(isUpdate, dto, errors)) {
            throw new MasterException(SystemMessage.INVALID_INFORMATION, errors, HttpStatus.BAD_REQUEST);
        }

        areaModel = AreaModel.dtoToModel(dto);

        try {
            if (isUpdate) {
                resulTransaction = this.areaRepository.update(areaModel);
            } else {
                resulTransaction = this.areaRepository.create(areaModel);
            }
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        response = new ApiSuccessResponse(SystemMessage.RECORD_SAVED_SUCCESSFULLY);

        return response;
    }

    private boolean isValidDto(boolean isUpdate, AreaEntryDto dto, List<String> errors) {

        boolean isOk = true;

        if (dto == null) {
            errors.add(SystemMessage.INVALID_DATA_NULL);
            isOk = false;
        }

        if (isOk) {
            if (isUpdate && dto.getIdArea() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idArea"));
            }

            if (dto.getIdDireccion() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idDireccion"));
            }

            if (Utils.isEmptyOrWhiteSpaceOrNUll(dto.getNombre())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("nombre"));
            }

            if (!StatusRecordDb.isValidStatus(dto.getEstatus())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("estatus"));
            }
        }

        return isOk;
    }
}
