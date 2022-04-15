package com.gs.controller.servertype;

import com.gs.constants.ApiConfigConsts;
import com.gs.constants.ApiRoutes;
import com.gs.constants.SystemMessage;
import com.gs.constants.TransactionType;
import com.gs.controller.IController;
import com.gs.controller.user.AuthenticationController;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.servertype.ServerTypeDetailDto;
import com.gs.enums.ActionMethod;
import com.gs.exception.GenericException;
import com.gs.exception.MasterException;
import com.gs.helper.logger.HelperLog;
import com.gs.helper.logger.Servicio;
import com.gs.service.servertype.IServerTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = " Server Type controller")
@RestController
public class ServerTypeController implements IController<ServerTypeDetailDto> {

    private String className = this.getClass().getSimpleName();
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private IServerTypeService serverTypeService;

    @ApiOperation(value = "Create new server type")
    @ApiResponses(value = {@ApiResponse(code = org.apache.http.HttpStatus.SC_CREATED, message = ApiConfigConsts.SUCCESSFUL_OPERATION)})
    @PostMapping(value = ApiRoutes.V1_TIPOSERVIORES, produces = ApiConfigConsts.APPLICATION_JSON_CHARSET_UTF_8)
    @Override
    public ResponseEntity<ApiSuccessResponse> create(@RequestBody ServerTypeDetailDto body){

        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        ResponseEntity<ApiSuccessResponse> response = null;
        ApiSuccessResponse successResponse = null;

        successResponse = this.execute(body, methodName, ActionMethod.Create);
        response = new ResponseEntity<>(successResponse, HttpStatus.CREATED);

        return response;
    }

    @ApiOperation(value = "Update an existing server type")
    @ApiResponses(value = {@ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = ApiConfigConsts.SUCCESSFUL_OPERATION)})
    @PutMapping(value = ApiRoutes.V1_TIPOSERVIORES, produces = ApiConfigConsts.APPLICATION_JSON_CHARSET_UTF_8)
    @Override
    public ResponseEntity<ApiSuccessResponse> update(@RequestBody ServerTypeDetailDto body){

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

    @ApiOperation(value = "List all existing server types")
    @ApiResponses(value = {@ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = ApiConfigConsts.SUCCESSFUL_OPERATION)})
    @GetMapping(value = ApiRoutes.V1_TIPOSERVIORES, produces = ApiConfigConsts.APPLICATION_JSON_CHARSET_UTF_8)
    @Override
    public ResponseEntity<ApiSuccessResponse> listAll(){

        ResponseEntity<ApiSuccessResponse> response = null;
        ApiSuccessResponse successResponse = null;
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        successResponse = this.execute(null, methodName, ActionMethod.ListAll);
        response = new ResponseEntity<>(successResponse, HttpStatus.OK);

        return response;
    }

    @ApiOperation(value = "Get an existing server type")
    @ApiResponses(value = {@ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = ApiConfigConsts.SUCCESSFUL_OPERATION)})
    @GetMapping(value = ApiRoutes.V1_TIPOSERVIORES, params = "id", produces = ApiConfigConsts.APPLICATION_JSON_CHARSET_UTF_8)
    @Override
    public ResponseEntity<ApiSuccessResponse> getById(@RequestParam int id){

        ResponseEntity<ApiSuccessResponse> response = null;
        ApiSuccessResponse successResponse = null;
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        ServerTypeDetailDto serverTypeDto = new ServerTypeDetailDto();

        serverTypeDto.setIdTipoServidor(id);
        successResponse = this.execute(serverTypeDto, methodName, ActionMethod.GetById);
        response = new ResponseEntity<>(successResponse, HttpStatus.OK);

        return response;
    }


    private ApiSuccessResponse execute(ServerTypeDetailDto dto, String methodName, ActionMethod actionMethodController) {

        HelperLog helperLog = new HelperLog();
        ApiSuccessResponse successResponse = null;

        helperLog.addServicio(new Servicio(className + " - " + methodName, TransactionType.SP_OBJECT));

        try {
            switch (actionMethodController) {
                case Create:
                    successResponse = this.serverTypeService.create(dto);
                    break;
                case Update:
                    successResponse = this.serverTypeService.update(dto);
                    break;
                case ListAll:
                    successResponse = this.serverTypeService.listAll();
                    break;
                case GetById:
                    successResponse = this.serverTypeService.getById(dto.getIdTipoServidor());
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
