package com.gs.service.server.application.impl;

import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.server.application.ApplicationDetailDto;
import com.gs.dto.server.application.ApplicationDto;
import com.gs.dto.server.application.ApplicationsDto;
import com.gs.enums.StatusRecordDb;
import com.gs.exception.MasterException;
import com.gs.model.server.application.ApplicationModel;
import com.gs.model.database.DbMessageModel;
import com.gs.repository.server.application.IApplicationRepository;
import com.gs.service.server.application.IApplicationService;
import com.gs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationService implements IApplicationService {

    @Autowired
    private IApplicationRepository applicationRepository;

    @Override
    public ApiSuccessResponse create(ApplicationDetailDto dto) throws MasterException {

        ApiSuccessResponse response = null;

        response = this.save(dto, false);

        return response;
    }

    @Override
    public ApiSuccessResponse update(ApplicationDetailDto dto) throws MasterException {

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

        int idServer = -1;

        return this.listAllByServerId(idServer);
    }

    @Override
    public ApiSuccessResponse listAllByServerId(int idServidor) throws MasterException {

        ApplicationsDto listapplicationDto = null;
        DbMessageModel<ApplicationModel> resulTransaction = null;
        ApplicationDetailDto applicationDto = null;
        ApplicationModel applicationModel = null;

        try {
            if(idServidor > 0){
                resulTransaction = this.applicationRepository.listAllByServerId(idServidor);
            }else{
                resulTransaction = this.applicationRepository.listAll();
            }
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!resulTransaction.IsTransactionOk()) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, resulTransaction.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        listapplicationDto = new ApplicationsDto();
        listapplicationDto.setApplications(new ArrayList<>());

        if (resulTransaction.getAllData() != null) {
            for (int i = 0; i < resulTransaction.getAllData().size(); i++) {
                applicationModel = resulTransaction.getAllData().get(i);

                applicationDto = new ApplicationDetailDto();
                applicationDto.setIdSAplicativo(applicationModel.getIdSAplicativo());
                applicationDto.setComentarios(applicationModel.getComentarios());
                applicationDto.setUrl(applicationModel.getUrl());
                applicationDto.setEstatus(applicationModel.getEstatus());
                applicationDto.setIdServidor(applicationModel.getIdServidor());
                applicationDto.setDescripcion(applicationModel.getDescripcion());

                listapplicationDto.addApplication(applicationDto);
            }
        }
        return listapplicationDto;
    }

    @Override
    public ApiSuccessResponse getById(int id) throws MasterException {

        DbMessageModel<ApplicationModel> resulTransaction;
        ApplicationDto applicationDto = null;
        ApplicationDetailDto aplication = null;

        try {
            resulTransaction = this.applicationRepository.getById(id);
        } catch (Exception exception) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        aplication = new ApplicationDetailDto();
        aplication.setIdSAplicativo(resulTransaction.getSingleData().getIdSAplicativo());
        aplication.setComentarios(resulTransaction.getSingleData().getComentarios());
        aplication.setUrl(resulTransaction.getSingleData().getUrl());
        aplication.setEstatus(resulTransaction.getSingleData().getEstatus());
        aplication.setIdServidor(resulTransaction.getSingleData().getIdServidor());
        aplication.setDescripcion(resulTransaction.getSingleData().getDescripcion());


        applicationDto = new ApplicationDto();
        applicationDto.setApplication(aplication);

        return applicationDto;
    }


    private ApiSuccessResponse save(ApplicationDetailDto dto, boolean isUpdate) throws MasterException {

        DbMessageModel resulTransaction = null;
        ArrayList<String> errors = new ArrayList<>();
        ApplicationModel applicationModel = null;
        ApiSuccessResponse response = null;

        if (!isValidDto(isUpdate, dto, errors)) {
            throw new MasterException(SystemMessage.INVALID_INFORMATION, errors, HttpStatus.BAD_REQUEST);
        }

        applicationModel = ApplicationModel.dtoToModel(dto);

        try {
            if (isUpdate) {
                resulTransaction = this.applicationRepository.update(applicationModel);
            } else {
                resulTransaction = this.applicationRepository.create(applicationModel);
            }
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        response = new ApiSuccessResponse(SystemMessage.RECORD_SAVED_SUCCESSFULLY);

        return response;
    }

    private boolean isValidDto(boolean isUpdate, ApplicationDetailDto applicationDto, List<String> errors) {

        boolean isOk = true;

        if (applicationDto == null) {
            errors.add(SystemMessage.INVALID_DATA_NULL);
            isOk = false;
        }

        if (isOk) {
            if (isUpdate && applicationDto.getIdSAplicativo() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idSAplicativo"));
            }
            if (applicationDto.getIdServidor() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idServidor"));
            }

            if (Utils.isEmptyOrWhiteSpaceOrNUll(applicationDto.getUrl())){
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("url"));
            }

            if (Utils.isEmptyOrWhiteSpaceOrNUll(applicationDto.getComentarios())){
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("comentarios"));
            }

            if (!StatusRecordDb.isValidStatus(applicationDto.getEstatus())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("estatus"));
            }
        }

        return isOk;
    }
}
