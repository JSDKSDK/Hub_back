package com.gs.controller.server.application;

import com.gs.constants.ApiConfigConsts;
import com.gs.constants.ApiRoutes;
import com.gs.constants.SystemMessage;
import com.gs.constants.TransactionType;
import com.gs.controller.IController;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.server.application.ApplicationDetailDto;
import com.gs.enums.ActionMethod;
import com.gs.exception.GenericException;
import com.gs.exception.MasterException;
import com.gs.helper.logger.HelperLog;
import com.gs.helper.logger.Servicio;
import com.gs.service.server.application.IApplicationService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "ApplicationController")
@RestController
public class ApplicationController implements IController<ApplicationDetailDto> {

    private String className = this.getClass().getSimpleName();
    private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private IApplicationService applicationService;

    @ApiOperation(value = "Create new application", authorizations = {@Authorization(value = ApiConfigConsts.JWT_TOKEN)})
    @ApiResponses(value = {@ApiResponse(code = org.apache.http.HttpStatus.SC_CREATED, message = ApiConfigConsts.SUCCESSFUL_OPERATION)})
    @PostMapping(value = ApiRoutes.V1_APLICATIVOS, produces = ApiConfigConsts.APPLICATION_JSON_CHARSET_UTF_8)
    @Override
    public ResponseEntity<ApiSuccessResponse> create(@RequestBody ApplicationDetailDto body) {

        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        ResponseEntity<ApiSuccessResponse> response = null;
        ApiSuccessResponse successResponse = null;

        successResponse = this.execute(body, methodName, ActionMethod.Create);
        response = new ResponseEntity<>(successResponse, HttpStatus.CREATED);

        return response;
    }

    @ApiOperation(value = "Update an existing application", authorizations = {@Authorization(value = ApiConfigConsts.JWT_TOKEN)})
    @ApiResponses(value = {@ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = ApiConfigConsts.SUCCESSFUL_OPERATION)})
    @PutMapping(value = ApiRoutes.V1_APLICATIVOS, produces = ApiConfigConsts.APPLICATION_JSON_CHARSET_UTF_8)
    @Override
    public ResponseEntity<ApiSuccessResponse> update(@RequestBody ApplicationDetailDto body) {

        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        ResponseEntity<ApiSuccessResponse> response = null;
        ApiSuccessResponse successResponse = null;

        successResponse = this.execute(body, methodName, ActionMethod.Update);
        response = new ResponseEntity<>(successResponse, HttpStatus.OK);

        return response;
    }

    @Override
    public ResponseEntity<ApiSuccessResponse> changeStatus(Object body) {
        return null;
    }

    @ApiOperation(value = "List all existing application", authorizations = {@Authorization(value = ApiConfigConsts.JWT_TOKEN)})
    @ApiResponses(value = {@ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = ApiConfigConsts.SUCCESSFUL_OPERATION)})
    @GetMapping(value = ApiRoutes.V1_APLICATIVOS, produces = ApiConfigConsts.APPLICATION_JSON_CHARSET_UTF_8)
    @Override
    public ResponseEntity<ApiSuccessResponse> listAll() {

        ResponseEntity<ApiSuccessResponse> response = null;
        ApiSuccessResponse successResponse = null;
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        successResponse = this.execute(null, methodName, ActionMethod.ListAll);
        response = new ResponseEntity<>(successResponse, HttpStatus.OK);

        return response;
    }

    @ApiOperation(value = "Get an existing application for server", authorizations = {@Authorization(value = ApiConfigConsts.JWT_TOKEN)})
    @ApiResponses(value = {@ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = ApiConfigConsts.SUCCESSFUL_OPERATION)})
    @GetMapping(value = ApiRoutes.V1_APLICATIVOS, params = "serverId", produces = ApiConfigConsts.APPLICATION_JSON_CHARSET_UTF_8)
    public ResponseEntity<ApiSuccessResponse> listAllByServerId(@RequestParam int serverId) {

        ResponseEntity<ApiSuccessResponse> response = null;
        ApiSuccessResponse successResponse = null;
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        ApplicationDetailDto applicationDto = new ApplicationDetailDto();

        applicationDto.setIdServidor(serverId);
        successResponse = this.execute(applicationDto, methodName, ActionMethod.GetByIdParent);
        response = new ResponseEntity<>(successResponse, HttpStatus.OK);

        return response;
    }

    @ApiOperation(value = "Get an existing application", authorizations = {@Authorization(value = ApiConfigConsts.JWT_TOKEN)})
    @ApiResponses(value = {@ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = ApiConfigConsts.SUCCESSFUL_OPERATION)})
    @GetMapping(value = ApiRoutes.V1_APLICATIVOS, params = "id", produces = ApiConfigConsts.APPLICATION_JSON_CHARSET_UTF_8)
    @Override
    public ResponseEntity<ApiSuccessResponse> getById(@RequestParam int id) {

        ResponseEntity<ApiSuccessResponse> response = null;
        ApiSuccessResponse successResponse = null;
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        ApplicationDetailDto applicationDto = new ApplicationDetailDto();

        applicationDto.setIdSAplicativo(id);
        successResponse = this.execute(applicationDto, methodName, ActionMethod.GetById);
        response = new ResponseEntity<>(successResponse, HttpStatus.OK);

        return response;
    }

    private ApiSuccessResponse execute(ApplicationDetailDto dto, String methodName, ActionMethod actionMethodController) {

        HelperLog helperLog = new HelperLog();
        ApiSuccessResponse successResponse = null;

        helperLog.addServicio(new Servicio(className + " - " + methodName, TransactionType.SP_OBJECT));

        try {
            switch (actionMethodController) {
                case Create:
                    successResponse = this.applicationService.create(dto);
                    break;
                case Update:
                    successResponse = this.applicationService.update(dto);
                    break;
                case ListAll:
                    successResponse = this.applicationService.listAll();
                    break;
                case GetByIdParent:
                    successResponse = this.applicationService.listAllByServerId(dto.getIdServidor());
                    break;
                case GetById:
                    successResponse = this.applicationService.getById(dto.getIdSAplicativo());
                    break;
                default:
                    throw new GenericException("El tipo de actión no es válido");
            }

            helperLog.getLastService().setStatus(SystemMessage.SUCESS);
            helperLog.setMensaje(SystemMessage.SUCESS);
        } catch (MasterException e) {
            helperLog.setError(e);
            throw e.getInnerException();
        } catch (Exception | GenericException ex) {
            helperLog.setError(ex);
            throw MasterException.createInternalException(ex);
        } finally {
            helperLog.getLastService().endTransaction();
            helperLog.endTransaction(logger);
        }

        return successResponse;
    }

}
