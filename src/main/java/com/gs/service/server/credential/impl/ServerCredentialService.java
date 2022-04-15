package com.gs.service.server.credential.impl;

import com.gs.constants.SystemMessage;
import com.gs.service.server.credential.IServerCredentialService;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.server.credential.ServerCredentialDetailDto;
import com.gs.dto.server.credential.ServerCredentialDto;
import com.gs.dto.server.credential.ServerCredentialEntryDto;
import com.gs.dto.server.credential.ServerCredentialsDto;
import com.gs.enums.StatusRecordDb;
import com.gs.exception.MasterException;
import com.gs.model.database.DbMessageModel;
import com.gs.model.server.credential.ServerCredentialDetailModel;
import com.gs.model.server.credential.ServerCredentialModel;
import com.gs.repository.server.credential.IServerCredentialRepository;
import com.gs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServerCredentialService implements IServerCredentialService {

    @Autowired
    private IServerCredentialRepository serverCredentialRepository;

    @Override
    public ApiSuccessResponse create(ServerCredentialEntryDto dto) throws MasterException {

        ApiSuccessResponse response = null;

        response = this.save(dto, false);

        return response;
    }

    @Override
    public ApiSuccessResponse update(ServerCredentialEntryDto dto) throws MasterException {

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
        int idServer = -1;

        return this.listAllCredentialsByServerId(idServer);
    }

    @Override
    public ApiSuccessResponse listAllCredentialsByServerId(int idServidor) throws MasterException {

        ServerCredentialsDto serverCredentialListDto = null;
        DbMessageModel<ServerCredentialDetailModel> resultTransaction = null;
        ServerCredentialDetailDto serverCredentialDto = null;
        ServerCredentialDetailModel serverCredentialModel = null;

        try {
            if(idServidor > 0){
                resultTransaction = this.serverCredentialRepository.listAllByServerId(idServidor);
            }
            else{
                resultTransaction = this.serverCredentialRepository.listAll();
            }
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!resultTransaction.IsTransactionOk()) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, resultTransaction.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        serverCredentialListDto = new ServerCredentialsDto();
        serverCredentialListDto.setServerCredentialDetails(new ArrayList<>());

        if (resultTransaction.getAllData() != null) {
            for (int i = 0; i < resultTransaction.getAllData().size(); i++) {

                serverCredentialModel = resultTransaction.getAllData().get(i);

                serverCredentialDto = new ServerCredentialDetailDto();
                serverCredentialDto.setIdSCredencial(serverCredentialModel.getIdSCredencial());
                serverCredentialDto.setUsuario(serverCredentialModel.getUsuario());
                serverCredentialDto.setContrasena(serverCredentialModel.getContrasena());
                serverCredentialDto.setComentario(serverCredentialModel.getComentario());
                serverCredentialDto.setEstatus(serverCredentialModel.getEstatus());
                serverCredentialDto.setIdServidor(serverCredentialModel.getIdServidor());
                serverCredentialDto.setDescripcion(serverCredentialModel.getDescripcion());
                serverCredentialDto.setUrl(serverCredentialModel.getUrl());
                serverCredentialDto.setIdTContrasena(serverCredentialModel.getIdTContrasena());
                serverCredentialDto.setTipoContrasena(serverCredentialModel.getTipoContrasena());

                serverCredentialListDto.addServerCredentialDetails(serverCredentialDto);
            }
        }

        return serverCredentialListDto;
    }

    @Override
    public ApiSuccessResponse getById(int id) throws MasterException {

        DbMessageModel<ServerCredentialDetailModel> resulTransaction;
        ServerCredentialDto serverCredentialDto = null;
        ServerCredentialDetailDto serverCredentialDetailDto = null;
        try {
            resulTransaction = this.serverCredentialRepository.getById(id);
        } catch (Exception exception) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        serverCredentialDto = new ServerCredentialDto();
        serverCredentialDetailDto = ServerCredentialDetailDto.serverCredentialDetailModelToDto(resulTransaction.getSingleData());
        serverCredentialDto.setServerCredentialDetail(serverCredentialDetailDto);

        return serverCredentialDto;
    }

    private ApiSuccessResponse save(ServerCredentialEntryDto serverCredentialDto, boolean isUpdate) throws MasterException {

        DbMessageModel resulTransaction = null;
        ArrayList<String> errors = new ArrayList<>();
        ServerCredentialModel serverCredentialModel = null;
        ApiSuccessResponse response = null;

        if (!isValidDto(isUpdate, serverCredentialDto, errors)) {
            throw new MasterException(SystemMessage.INVALID_INFORMATION, errors, HttpStatus.BAD_REQUEST);
        }

        serverCredentialModel = ServerCredentialModel.dtoToModel(serverCredentialDto);

        try {
            if (isUpdate) {
                resulTransaction = this.serverCredentialRepository.update(serverCredentialModel);
            } else {
                resulTransaction = this.serverCredentialRepository.create(serverCredentialModel);
            }
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        response = new ApiSuccessResponse(SystemMessage.RECORD_SAVED_SUCCESSFULLY);

        return response;
    }

    private boolean isValidDto(boolean isUpdate, ServerCredentialEntryDto serverCredentialDto, List<String> errors) {

        boolean isOk = true;

        if (serverCredentialDto == null) {
            errors.add(SystemMessage.INVALID_DATA_NULL);
            isOk = false;
        }

        if (isOk) {
            if (isUpdate && serverCredentialDto.getIdSCredencial() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idSCredencial"));
            }

            if (serverCredentialDto.getIdServidor() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idServidor"));
            }

            if (serverCredentialDto.getIdTContrasena() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idTContrasena"));
            }

            if (Utils.isEmptyOrWhiteSpaceOrNUll(serverCredentialDto.getUsuario())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("usuario"));
            }

            if (Utils.isEmptyOrWhiteSpaceOrNUll(serverCredentialDto.getContrasena())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("contrasena"));
            }

            if (!StatusRecordDb.isValidStatus(serverCredentialDto.getEstatus())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("estatus"));
            }
        }

        return isOk;
    }
}
