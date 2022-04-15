package com.gs.service.module.impl;

import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.servertype.module.ModuleDetailDto;
import com.gs.dto.servertype.module.ModuleDto;
import com.gs.dto.servertype.module.ModulesDto;
import com.gs.enums.StatusRecordDb;
import com.gs.exception.MasterException;
import com.gs.model.database.DbMessageModel;
import com.gs.model.module.ModuleModel;
import com.gs.repository.module.IModuleRepository;
import com.gs.service.module.IModuleService;
import com.gs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleService implements IModuleService {



    @Autowired
    private IModuleRepository muduleRepository;

    @Override
    public ApiSuccessResponse create(ModuleDetailDto dto) throws MasterException {

        ApiSuccessResponse response = null;

        response = this.save(dto, false);

        return response;
    }

    @Override
    public ApiSuccessResponse update(ModuleDetailDto dto) throws MasterException {

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

        ModulesDto listmoduleDto = null;
        DbMessageModel<ModuleModel> resulTransaction = null;
        ModuleDetailDto moduleDto = null;
        ModuleModel moduleModel = null;

        try {
            resulTransaction = this.muduleRepository.listAll();
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!resulTransaction.IsTransactionOk()) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, resulTransaction.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        listmoduleDto = new ModulesDto();
        listmoduleDto.setModules(new ArrayList<>());

        if (resulTransaction.getAllData() != null) {
            for (int i = 0; i < resulTransaction.getAllData().size(); i++) {
                moduleModel = resulTransaction.getAllData().get(i);

                moduleDto = new ModuleDetailDto();
                moduleDto.setIdModulo(moduleModel.getIdModulo());
                moduleDto.setNombre(moduleModel.getNombre());
                moduleDto.setUrl(moduleModel.getUrl());
                moduleDto.setPermitirAsignacionNivel(moduleModel.getPermitirAsignacionNivel());
                moduleDto.setEstatus(moduleModel.getEstatus());

                listmoduleDto.addModules(moduleDto);
            }
        }

        return listmoduleDto;
    }

    @Override
    public ApiSuccessResponse getById(int id) throws MasterException {

        DbMessageModel<ModuleModel> resulTransaction;
        ModuleDto moduleDto = null;
        ModuleDetailDto module = null;

        try {
            resulTransaction = this.muduleRepository.getById(id);
        } catch (Exception exception) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, exception, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        module = new ModuleDetailDto();
        module.setIdModulo(resulTransaction.getSingleData().getIdModulo());
        module.setNombre(resulTransaction.getSingleData().getNombre());
        module.setUrl(resulTransaction.getSingleData().getUrl());
        module.setPermitirAsignacionNivel(resulTransaction.getSingleData().getPermitirAsignacionNivel());
        module.setEstatus(resulTransaction.getSingleData().getEstatus());

        moduleDto = new ModuleDto();
        moduleDto.setModule(module);

        return moduleDto;
    }

    private ApiSuccessResponse save(ModuleDetailDto dto, boolean isUpdate) throws MasterException {

        DbMessageModel resulTransaction = null;
        ArrayList<String> errors = new ArrayList<>();
        ModuleModel moduleModel = null;
        ApiSuccessResponse response = null;

        if (!isValidDto(isUpdate, dto, errors)) {
            throw new MasterException(SystemMessage.INVALID_INFORMATION, errors, HttpStatus.BAD_REQUEST);
        }

        moduleModel = ModuleModel.dtoToModel(dto);

        try {
            if (isUpdate) {
                resulTransaction = this.muduleRepository.update(moduleModel);
            } else {
                resulTransaction = this.muduleRepository.create(moduleModel);
            }
        } catch (Exception ex) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        resulTransaction.validateTransaction();

        response = new ApiSuccessResponse(SystemMessage.RECORD_SAVED_SUCCESSFULLY);

        return response;
    }

    private boolean isValidDto(boolean isUpdate, ModuleDetailDto moduleDto, List<String> errors) {

        boolean isOk = true;

        if (moduleDto == null) {
            errors.add(SystemMessage.INVALID_DATA_NULL);
            isOk = false;
        }

        if (isOk) {
            if (isUpdate && moduleDto.getIdModulo() <= 0) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("idModulo"));
            }

            if (Utils.isEmptyOrWhiteSpaceOrNUll(moduleDto.getNombre())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("nombre"));
            }

            if (Utils.isEmptyOrWhiteSpaceOrNUll(moduleDto.getUrl())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("url"));
            }

            if (!StatusRecordDb.isValidPermitirAsignacion(moduleDto.getPermitirAsignacionNivel())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("permitirAsignacionNivel"));
            }

            if (!StatusRecordDb.isValidStatus(moduleDto.getEstatus())) {
                isOk = false;
                errors.add(SystemMessage.getMessageParamValueInvalid("estatus"));
            }
        }

        return isOk;
    }


}
