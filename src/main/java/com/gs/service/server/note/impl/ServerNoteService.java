package com.gs.service.server.note.impl;

import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.server.note.ServerNoteDetailDto;
import com.gs.dto.server.note.ServerNoteDto;
import com.gs.dto.server.note.ServerNoteEntryDto;
import com.gs.dto.server.note.ServerNotesDto;
import com.gs.enums.StatusRecordDb;
import com.gs.exception.MasterException;
import com.gs.model.database.DbMessageModel;
import com.gs.model.server.note.ServerNoteDetailModel;
import com.gs.model.server.note.ServerNoteModel;
import com.gs.repository.server.note.IServerNoteRepository;
import com.gs.service.server.note.IServerNoteService;
import com.gs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServerNoteService implements IServerNoteService {

    @Autowired
    private IServerNoteRepository serverNoteRepository;

    @Override
    public ApiSuccessResponse create(ServerNoteEntryDto dto) throws MasterException {

        ApiSuccessResponse response = null;

        response = this.save(dto, false);

        return response;
    }

    @Override
    public ApiSuccessResponse update(ServerNoteEntryDto dto) throws MasterException {

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

        int idServidor = -1;

        return this.list(idServidor);
    }

    @Override
    public ApiSuccessResponse getById(int id) throws MasterException {

        DbMessageModel<ServerNoteDetailModel> resulTransaction;
        ServerNoteDto serverNoteDto = null;
        ServerNoteDetailDto serverNoteDetailDto = null;
        try {
            resulTransaction = this.serverNoteRepository.getById(id);
        } catch (Exception exception) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        serverNoteDto = new ServerNoteDto();
        serverNoteDetailDto = serverNoteDetailDto.serverNoteDetailModelToDto(resulTransaction.getSingleData());
        serverNoteDto.setServerNoteDetail(serverNoteDetailDto);

        return serverNoteDto;
    }

    @Override
    public ApiSuccessResponse listByServerId(int serverId) throws MasterException {

        return this.list(serverId);
    }

    private ApiSuccessResponse list(int serverId) throws MasterException {

        ServerNoteDetailDto serverNoteDetailDto = null;
        DbMessageModel<ServerNoteDetailModel> resultTransaction = null;
        ServerNotesDto serverNotesDto = null;

        try {
            if(serverId > 0){
                resultTransaction = this.serverNoteRepository.listByServerId(serverId);
            }
            else {
                resultTransaction = this.serverNoteRepository.listAll();
            }
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!resultTransaction.IsTransactionOk()) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, resultTransaction.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        serverNotesDto = new ServerNotesDto();

        if (resultTransaction.getAllData() != null) {
            for (int i = 0; i < resultTransaction.getAllData().size(); i++) {
                serverNoteDetailDto = ServerNoteDetailDto.serverNoteDetailModelToDto(resultTransaction.getAllData().get(i));
                serverNotesDto.addServerNoteDetails(serverNoteDetailDto);
            }
        }

        return serverNotesDto;
    }

    private ApiSuccessResponse save(ServerNoteEntryDto serverNoteDto, boolean isUpdate) throws MasterException {

        DbMessageModel resulTransaction = null;
        ArrayList<String> errors = new ArrayList<>();
        ServerNoteModel serverNoteModel = null;
        ApiSuccessResponse response = null;

        if (!isValidDto(isUpdate, serverNoteDto, errors)) {
            throw new MasterException(SystemMessage.INVALID_INFORMATION, errors, HttpStatus.BAD_REQUEST);
        }

        serverNoteModel = ServerNoteModel.dtoToModel(serverNoteDto);

        try {
            if (isUpdate) {
                resulTransaction = this.serverNoteRepository.update(serverNoteModel);
            } else {
                resulTransaction = this.serverNoteRepository.create(serverNoteModel);
            }
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        response = new ApiSuccessResponse(SystemMessage.RECORD_SAVED_SUCCESSFULLY);

        return response;
    }

    private boolean isValidDto(boolean isUpdate, ServerNoteEntryDto serverNoteDto, List<String> errors) {

        boolean isOk = true;

        if (serverNoteDto == null) {
            errors.add(SystemMessage.INVALID_DATA_NULL);
            isOk = false;
        }

        if (isOk) {
            if (isUpdate && serverNoteDto.getIdSNota() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idSNota"));
            }

            if (serverNoteDto.getIdServidor() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idServidor"));
            }

            if (Utils.isEmptyOrWhiteSpaceOrNUll(serverNoteDto.getTitulo())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("titulo"));
            }

            if (Utils.isEmptyOrWhiteSpaceOrNUll(serverNoteDto.getNota())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("nota"));
            }

            if (Utils.isEmptyOrWhiteSpaceOrNUll(serverNoteDto.getRutaArchivo())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("rutaArchivo"));
            }

            if (!StatusRecordDb.isValidStatus(serverNoteDto.getEstatus())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("estatus"));
            }
        }

        return isOk;
    }
}
