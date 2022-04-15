package com.gs.service.servertype.impl;

import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.servertype.ServerTypeDetailDto;
import com.gs.dto.servertype.ServerTypeDto;
import com.gs.dto.servertype.ServerTypesDto;
import com.gs.enums.StatusRecordDb;
import com.gs.exception.MasterException;
import com.gs.model.database.DbMessageModel;
import com.gs.model.servertype.ServerTypeModel;
import com.gs.repository.servertype.IServerTypeRepository;
import com.gs.service.servertype.IServerTypeService;
import com.gs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServerTypeService implements IServerTypeService {

    @Autowired
    private IServerTypeRepository serverTypeRepository;

    @Override
    public ApiSuccessResponse create(ServerTypeDetailDto dto) throws MasterException{

        ApiSuccessResponse response = null;

        response = this.save(dto, false);

        return response;
    }

    @Override
    public ApiSuccessResponse update(ServerTypeDetailDto dto) throws MasterException{

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

        ServerTypesDto listservertypeDto = null;
        DbMessageModel<ServerTypeModel> resulTransaction = null;
        ServerTypeDetailDto serverTypeDto = null;
        ServerTypeModel serverTypeModel = null;

        try {
            resulTransaction = this.serverTypeRepository.listAll();
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!resulTransaction.IsTransactionOk()) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, resulTransaction.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        listservertypeDto = new ServerTypesDto();
        listservertypeDto.setServerTypes(new ArrayList<>());

        if (resulTransaction.getAllData() != null) {
            for (int i = 0; i < resulTransaction.getAllData().size(); i++) {
                serverTypeModel = resulTransaction.getAllData().get(i);

                serverTypeDto = new ServerTypeDetailDto();
                serverTypeDto.setIdTipoServidor(serverTypeModel.getIdTipoServidor());
                serverTypeDto.setNombre(serverTypeModel.getNombre());
                serverTypeDto.setEstatus(serverTypeModel.getEstatus());

                listservertypeDto.addServerTypes(serverTypeDto);
            }
        }

        return listservertypeDto;
    }

    @Override
    public ApiSuccessResponse getById(int id) throws MasterException {

        DbMessageModel<ServerTypeModel> resulTransaction;
        ServerTypeDto serverTypeDto = null;
        ServerTypeDetailDto serverType = null;

        try {
            resulTransaction = this.serverTypeRepository.getById(id);
        } catch (Exception exception) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        serverType = new ServerTypeDetailDto();
        serverType.setIdTipoServidor(resulTransaction.getSingleData().getIdTipoServidor());
        serverType.setNombre(resulTransaction.getSingleData().getNombre());
        serverType.setEstatus(resulTransaction.getSingleData().getEstatus());

        serverTypeDto = new ServerTypeDto();
        serverTypeDto.setServerType(serverType);

        return serverTypeDto;
    }

    private ApiSuccessResponse save(ServerTypeDetailDto dto, boolean isUpdate) throws MasterException {

        DbMessageModel resulTransaction = null;
        ArrayList<String> errors = new ArrayList<>();
        ServerTypeModel serverTypeModel = null;
        ApiSuccessResponse response = null;

        if (!isValidDto(isUpdate, dto, errors)) {
            throw new MasterException(SystemMessage.INVALID_INFORMATION, errors, HttpStatus.BAD_REQUEST);
        }

        serverTypeModel = ServerTypeModel.dtoToModel(dto);

        try {
            if (isUpdate) {
                resulTransaction = this.serverTypeRepository.update(serverTypeModel);
            } else {
                resulTransaction = this.serverTypeRepository.create(serverTypeModel);
            }
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        response = new ApiSuccessResponse(SystemMessage.RECORD_SAVED_SUCCESSFULLY);

        return response;
    }

    private boolean isValidDto(boolean isUpdate, ServerTypeDetailDto serverTypeDto, List<String> errors) {

        boolean isOk = true;

        if (serverTypeDto == null) {
            errors.add(SystemMessage.INVALID_DATA_NULL);
            isOk = false;
        }

        if (isOk) {
            if (isUpdate && serverTypeDto.getIdTipoServidor() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idTipoServidor"));
            }

            if (Utils.isEmptyOrWhiteSpaceOrNUll(serverTypeDto.getNombre())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("nombre"));
            }

            if (!StatusRecordDb.isValidStatus(serverTypeDto.getEstatus())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("estatus"));
            }
        }

        return isOk;
    }
}
