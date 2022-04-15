package com.gs.controller.user;


import com.gs.constants.ApiConfigConsts;
import com.gs.constants.ApiRoutes;
import com.gs.constants.SystemMessage;
import com.gs.constants.TransactionType;
import com.gs.controller.IController;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.dto.user.UserDto;
import com.gs.dto.user.UserEntryDto;
import com.gs.enums.ActionMethod;
import com.gs.exception.GenericException;
import com.gs.exception.MasterException;
import com.gs.helper.CurrentContext;
import com.gs.helper.logger.HelperLog;
import com.gs.helper.logger.Servicio;
import com.gs.service.user.IUserService;
import io.swagger.annotations.*;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "User Controller")
@RestController
public class UserController implements IController<UserEntryDto> {

    @Autowired
    private IUserService userService;

    private String className = this.getClass().getSimpleName();
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Override
    public ResponseEntity<ApiSuccessResponse> create(UserEntryDto body) {
        return null;
    }

    @Override
    public ResponseEntity<ApiSuccessResponse> update(UserEntryDto body) {
        return null;
    }

    @Override
    public ResponseEntity<ApiSuccessResponse> changeStatus(Object body) {
        return null;
    }

    @Override
    public ResponseEntity<ApiSuccessResponse> listAll() {
        return null;
    }

    @Override
    public ResponseEntity<ApiSuccessResponse> getById(int id) {
        return null;
    }

    @ApiOperation(value = "Get current user", authorizations = {@Authorization(value = ApiConfigConsts.JWT_TOKEN)})
    @ApiResponses(value = {@ApiResponse(code = HttpStatus.SC_OK, message = ApiConfigConsts.SUCCESSFUL_OPERATION)})
    @GetMapping(value = ApiRoutes.V1_USERS_CURRENT, produces = ApiConfigConsts.APPLICATION_JSON_CHARSET_UTF_8)
    public ResponseEntity<ApiSuccessResponse> getCurrent() {

        ResponseEntity<ApiSuccessResponse> response = null;
        ApiSuccessResponse successResponse = null;
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        successResponse = this.execute(null, methodName, ActionMethod.Current);
        response = new ResponseEntity<>(successResponse, org.springframework.http.HttpStatus.OK);

        return response;
    }

    private ApiSuccessResponse execute(UserEntryDto dto, String methodName, ActionMethod actionMethodController) {

        HelperLog helperLog = new HelperLog();
        ApiSuccessResponse successResponse = null;
        UserDto userDto;

        helperLog.addServicio(new Servicio(className + " - " + methodName, TransactionType.SP_OBJECT));

        try {
            switch (actionMethodController) {
                case Create:

                    break;
                case Update:

                    break;
                case ListAll:

                    break;
                case GetById:

                    break;
                case Current:
                    userDto = new UserDto();
                    userDto.setUserDetailDto(CurrentContext.getCurrentUser());
                    successResponse = userDto;
                    break;
                default:
                    throw new GenericException(SystemMessage.INVALID_SELECTED_OPTION);
            }

            helperLog.getLastService().setStatus(SystemMessage.SUCESS);
            helperLog.setMensaje(SystemMessage.SUCESS);
        } /*catch (MasterException e) {
            helperLog.setError(e);
            throw e.getInnerException();
        } */ catch (Exception | GenericException ex) {
            helperLog.setError(ex);
            throw MasterException.createInternalException(ex);
        } finally {
            helperLog.getLastService().endTransaction();
            helperLog.endTransaction(logger);
        }

        return successResponse;
    }
}


