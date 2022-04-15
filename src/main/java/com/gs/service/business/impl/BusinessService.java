package com.gs.service.business.impl;

import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.business.BusinessDetailDto;
import com.gs.dto.business.BusinessDto;
import com.gs.dto.business.BusinessesDto;
import com.gs.enums.StatusRecordDb;
import com.gs.exception.MasterException;
import com.gs.model.business.BusinessModel;
import com.gs.model.database.DbMessageModel;
import com.gs.repository.business.IBusinessRepository;
import com.gs.service.business.IBusinessService;
import com.gs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessService implements IBusinessService {

    @Autowired
    private IBusinessRepository businessRepository;

    @Override
    public ApiSuccessResponse create(BusinessDetailDto dto) throws MasterException {

        ApiSuccessResponse response = null;

        response = this.save(dto, false);

        return response;
    }

    @Override
    public ApiSuccessResponse update(BusinessDetailDto dto) throws MasterException {

        ApiSuccessResponse response = null;

        response = this.save(dto, true);

        return response;
    }

    @Override
    public ApiSuccessResponse changeStatus(Object body) {
        return null;
    }

    @Override
    public ApiSuccessResponse listAll() throws MasterException {

        BusinessesDto listbusinessDto = null;
        DbMessageModel<BusinessModel> resulTransaction = null;
        BusinessDetailDto businessDto = null;
        BusinessModel businessModel = null;

        try {
            resulTransaction = this.businessRepository.listAll();
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!resulTransaction.IsTransactionOk()) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, resulTransaction.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        listbusinessDto = new BusinessesDto();
        listbusinessDto.setBusinesses(new ArrayList<>());

        if (resulTransaction.getAllData() != null) {
            for (int i = 0; i < resulTransaction.getAllData().size(); i++) {
                businessModel = resulTransaction.getAllData().get(i);

                businessDto = new BusinessDetailDto();
                businessDto.setIdNegocio(businessModel.getIdNegocio());
                businessDto.setNombre(businessModel.getNombre());
                businessDto.setEstatus(businessModel.getEstatus());

                listbusinessDto.addBusiness(businessDto);
            }
        }

        return listbusinessDto;
    }

    @Override
    public ApiSuccessResponse getById(int id) throws MasterException {

        DbMessageModel<BusinessModel> resulTransaction;
        BusinessDto businessDto = null;
        BusinessDetailDto business = null;

        try {
            resulTransaction = this.businessRepository.getById(id);
        } catch (Exception exception) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        business = new BusinessDetailDto();
        business.setIdNegocio(resulTransaction.getSingleData().getIdNegocio());
        business.setNombre(resulTransaction.getSingleData().getNombre());
        business.setEstatus(resulTransaction.getSingleData().getEstatus());

        businessDto = new BusinessDto();
        businessDto.setBusiness(business);

        return businessDto;
    }

    private ApiSuccessResponse save(BusinessDetailDto dto, boolean isUpdate) throws MasterException {

        DbMessageModel resulTransaction = null;
        ArrayList<String> errors = new ArrayList<>();
        BusinessModel businessModel = null;
        ApiSuccessResponse response = null;

        if (!isValidDto(isUpdate, dto, errors)) {
            throw new MasterException(SystemMessage.INVALID_INFORMATION, errors, HttpStatus.BAD_REQUEST);
        }

        businessModel = BusinessModel.dtoToModel(dto);

        try {
            if (isUpdate) {
                resulTransaction = this.businessRepository.update(businessModel);
            } else {
                resulTransaction = this.businessRepository.create(businessModel);
            }
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        response = new ApiSuccessResponse(SystemMessage.RECORD_SAVED_SUCCESSFULLY);

        return response;
    }

    private boolean isValidDto(boolean isUpdate, BusinessDetailDto businessDto, List<String> errors) {

        boolean isOk = true;

        if (businessDto == null) {
            errors.add(SystemMessage.INVALID_DATA_NULL);
            isOk = false;
        }

        if (isOk) {
            if (isUpdate && businessDto.getIdNegocio() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idNegocio"));
            }

            if (Utils.isEmptyOrWhiteSpaceOrNUll(businessDto.getNombre())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("nombre"));
            }

            if (!StatusRecordDb.isValidStatus(businessDto.getEstatus())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("estatus"));
            }
        }

        return isOk;
    }
}
