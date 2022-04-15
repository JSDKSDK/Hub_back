package com.gs.service.server.impl;

import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.server.ServerDetailDto;
import com.gs.dto.server.ServerDto;
import com.gs.dto.server.ServersDto;
import com.gs.enums.StatusRecordDb;
import com.gs.exception.MasterException;
import com.gs.model.database.DbMessageModel;
import com.gs.model.server.ServerModel;
import com.gs.repository.server.IServerRepository;
import com.gs.service.server.IServerService;
import com.gs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServerService implements IServerService {

    @Autowired
    private IServerRepository serverRepository;

    @Override
    public ApiSuccessResponse create(ServerDetailDto dto) throws MasterException {

        ApiSuccessResponse response = null;

        response = this.save(dto, false);

        return response;
    }

    @Override
    public ApiSuccessResponse update(ServerDetailDto dto) throws MasterException{

        ApiSuccessResponse response = null;

        response = this.save(dto, true);

        return response;
    }

    @Override
    public ApiSuccessResponse changeStatus(Object body) {
        return null;
    }

    @Override
    public ApiSuccessResponse listAll() throws MasterException{
        ServersDto listserverDto = null;
        DbMessageModel<ServerModel> resulTransaction = null;
        ServerDetailDto serverDto = null;
        ServerModel serverModel = null;

        try {
            resulTransaction = this.serverRepository.listAll();
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!resulTransaction.IsTransactionOk()) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, resulTransaction.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        listserverDto = new ServersDto();
        listserverDto.setServers(new ArrayList<>());

        if (resulTransaction.getAllData() != null) {
            for (int i = 0; i < resulTransaction.getAllData().size(); i++) {
                serverModel = resulTransaction.getAllData().get(i);

                serverDto = new ServerDetailDto();
                serverDto.setIdServidor(serverModel.getIdServidor());
                serverDto.setUrl(serverModel.getUrl());
                serverDto.setDescripcion(serverModel.getDescripcion());
                serverDto.setEstatus(serverModel.getEstatus());
                serverDto.setIdArea(serverModel.getIdArea());
                serverDto.setArea(serverModel.getArea());
                serverDto.setIdDireccion(serverModel.getIdDireccion());
                serverDto.setDireccion(serverModel.getDireccion());
                serverDto.setIdNegocio(serverModel.getIdNegocio());
                serverDto.setNegocio(serverModel.getNegocio());
                serverDto.setIdTipoServidor(serverModel.getIdTipoServidor());
                serverDto.setTipoServidor(serverModel.getTipoServidor());
                serverDto.setIdSistemaOperativo(serverModel.getIdSistemaOperativo());
                serverDto.setSistemaOperativo(serverModel.getSistemaOperativo());

                listserverDto.addServer(serverDto);
            }
        }

        return listserverDto;
    }

    @Override
    public ApiSuccessResponse getById(int id) throws MasterException {

        DbMessageModel<ServerModel> resulTransaction;
        ServerDto serverDto = null;
        ServerDetailDto server = null;

        try {
            resulTransaction = this.serverRepository.getById(id);
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        server = new ServerDetailDto();
        server.setIdServidor(resulTransaction.getSingleData().getIdServidor());
        server.setUrl(resulTransaction.getSingleData().getUrl());
        server.setDescripcion(resulTransaction.getSingleData().getDescripcion());
        server.setEstatus(resulTransaction.getSingleData().getEstatus());
        server.setIdArea(resulTransaction.getSingleData().getIdArea());
        server.setArea(resulTransaction.getSingleData().getArea());
        server.setIdDireccion(resulTransaction.getSingleData().getIdDireccion());
        server.setDireccion(resulTransaction.getSingleData().getDireccion());
        server.setIdNegocio(resulTransaction.getSingleData().getIdNegocio());
        server.setNegocio(resulTransaction.getSingleData().getNegocio());
        server.setIdTipoServidor(resulTransaction.getSingleData().getIdTipoServidor());
        server.setTipoServidor(resulTransaction.getSingleData().getTipoServidor());
        server.setIdSistemaOperativo(resulTransaction.getSingleData().getIdSistemaOperativo());
        server.setSistemaOperativo(resulTransaction.getSingleData().getSistemaOperativo());

        serverDto = new ServerDto();
        serverDto.setServer(server);

        return serverDto;
    }


    private ApiSuccessResponse save(ServerDetailDto dto, boolean isUpdate) throws MasterException {

        DbMessageModel resulTransaction = null;
        ArrayList<String> errors = new ArrayList<>();
        ServerModel serverModel = null;
        ApiSuccessResponse response = null;

        if (!isValidDto(isUpdate, dto, errors)) {
            throw new MasterException(SystemMessage.INVALID_INFORMATION, errors, HttpStatus.BAD_REQUEST);
        }

        serverModel = ServerModel.dtoToModel(dto);

        try {
            if (isUpdate) {
                resulTransaction = this.serverRepository.update(serverModel);
            } else {
                resulTransaction = this.serverRepository.create(serverModel);
            }
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        response = new ApiSuccessResponse(SystemMessage.RECORD_SAVED_SUCCESSFULLY);

        return response;
    }

    private boolean isValidDto(boolean isUpdate, ServerDetailDto serverDto, List<String> errors) {

        boolean isOk = true;

        if (serverDto == null) {
            errors.add(SystemMessage.INVALID_DATA_NULL);
            isOk = false;
        }

        if (isOk) {
            if (isUpdate && serverDto.getIdServidor() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idServidor"));
            }

            if (serverDto.getIdArea() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idArea"));
            }

            if (serverDto.getIdTipoServidor() <= 0){
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idTipoServidor"));
            }

            if (serverDto.getIdSistemaOperativo() <= 0){
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idSistemaOperativo"));
            }

            if (Utils.isEmptyOrWhiteSpaceOrNUll(serverDto.getUrl())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("url"));
            }

            if (Utils.isEmptyOrWhiteSpaceOrNUll(serverDto.getDescripcion())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("descripcion"));
            }

            if (!StatusRecordDb.isValidStatus(serverDto.getEstatus())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("estatus"));
            }
        }

        return isOk;
    }
}
