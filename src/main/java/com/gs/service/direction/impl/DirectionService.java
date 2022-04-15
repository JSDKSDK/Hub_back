package com.gs.service.direction.impl;

import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.direction.DirectionDetailDto;
import com.gs.dto.direction.DirectionDto;
import com.gs.dto.direction.DirectionEntryDto;
import com.gs.dto.direction.DirectionsDto;
import com.gs.enums.StatusRecordDb;
import com.gs.exception.MasterException;
import com.gs.model.database.DbMessageModel;
import com.gs.model.direction.DirectionDetailModel;
import com.gs.model.direction.DirectionModel;
import com.gs.repository.direction.IDirectionRepository;
import com.gs.service.direction.IDirectionService;
import com.gs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DirectionService implements IDirectionService {

    @Autowired
    private IDirectionRepository directionRepository;

    @Override
    public ApiSuccessResponse create(DirectionEntryDto directionDto) throws MasterException {

        ApiSuccessResponse response = null;

        response = this.save(directionDto, false);

        return response;
    }

    @Override
    public ApiSuccessResponse update(DirectionEntryDto directionDto) throws MasterException {

        ApiSuccessResponse response = null;

        response = this.save(directionDto, true);

        return response;
    }

    @Override
    public ApiSuccessResponse changeStatus(Object body) {
        return null;
    }

    @Override
    public ApiSuccessResponse listAll() throws MasterException {

        int businessId=-1;

        return this.list(businessId);
    }

    @Override
    public ApiSuccessResponse listByBusinessId(int businessId) throws MasterException {

        return this.list(businessId);
    }

    @Override
    public ApiSuccessResponse getById(int id) throws MasterException {

        DbMessageModel<DirectionDetailModel> resulTransaction;
        DirectionDto directionDto = null;
        DirectionDetailDto directionDetailDto = null;
        try {
            resulTransaction = this.directionRepository.getById(id);
        } catch (Exception exception) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        directionDto = new DirectionDto();
        directionDetailDto = DirectionDetailDto.directioDetailModelToDto(resulTransaction.getSingleData());
        directionDto.setDirectionDetail(directionDetailDto);

        return directionDto;
    }

    private ApiSuccessResponse list(int businessId) throws MasterException {

        DirectionDetailDto directionDetailDto = null;
        DbMessageModel<DirectionDetailModel> resulTransaction = null;
        DirectionsDto directionsDto = null;

        try {
            if(businessId>0){
                resulTransaction = this.directionRepository.listByBusinessId(businessId);
            }
            else {
                resulTransaction = this.directionRepository.listAll();
            }
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!resulTransaction.IsTransactionOk()) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, resulTransaction.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        directionsDto = new DirectionsDto();

        if (resulTransaction.getAllData() != null) {
            for (int i = 0; i < resulTransaction.getAllData().size(); i++) {
                directionDetailDto = DirectionDetailDto.directioDetailModelToDto(resulTransaction.getAllData().get(i));
                directionsDto.addDirectionDetail(directionDetailDto);
            }
        }

        return directionsDto;
    }

    private ApiSuccessResponse save(DirectionEntryDto directionDto, boolean isUpdate) throws MasterException {

        DbMessageModel resulTransaction = null;
        ArrayList<String> errors = new ArrayList<>();
        DirectionModel directionModel = null;
        ApiSuccessResponse response = null;

        if (!isValidDto(isUpdate, directionDto, errors)) {
            throw new MasterException(SystemMessage.INVALID_INFORMATION, errors, HttpStatus.BAD_REQUEST);
        }

        directionModel = DirectionModel.dtoToModel(directionDto);

        try {
            if (isUpdate) {
                resulTransaction = this.directionRepository.update(directionModel);
            } else {
                resulTransaction = this.directionRepository.create(directionModel);
            }
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        response = new ApiSuccessResponse(SystemMessage.RECORD_SAVED_SUCCESSFULLY);

        return response;
    }

    private boolean isValidDto(boolean isUpdate, DirectionEntryDto directionDto, List<String> errors) {

        boolean isOk = true;

        if (directionDto == null) {
            errors.add(SystemMessage.INVALID_DATA_NULL);
            isOk = false;
        }

        if (isOk) {
            if (isUpdate && directionDto.getIdDireccion() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idDireccion"));
            }

            if (directionDto.getIdNegocio() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idNegocio"));
            }

            if (Utils.isEmptyOrWhiteSpaceOrNUll(directionDto.getNombre())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("nombre"));
            }

            if (!StatusRecordDb.isValidStatus(directionDto.getEstatus())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("estatus"));
            }
        }

        return isOk;
    }
}
