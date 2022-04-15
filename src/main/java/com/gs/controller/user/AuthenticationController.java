package com.gs.controller.user;

import com.gs.constants.ApiConfigConsts;
import com.gs.constants.ApiRoutes;
import com.gs.constants.SystemMessage;
import com.gs.constants.TransactionType;
import com.gs.exception.MasterException;
import com.gs.helper.logger.HelperLog;
import com.gs.helper.logger.Servicio;
import com.gs.service.user.IAuthenticationService;
import com.gs.util.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(value = "Authentication controller")
@RestController
public class AuthenticationController {

    private String className = this.getClass().getSimpleName();
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private IAuthenticationService authenticationService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @ApiOperation(value = " Login user")
    @ApiResponses(value = {@ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = ApiConfigConsts.SUCCESSFUL_OPERATION)})
    @PostMapping(value = ApiRoutes.V1_LOGIN)
    public void login(@RequestParam("user") String user, @RequestParam("password") String password, HttpServletResponse response) {

        HelperLog helperLog = new HelperLog();
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

        helperLog.startTransaction();
        helperLog.addServicio(new Servicio(className + " - " + methodName, TransactionType.SP_OBJECT));
        helperLog.getLastService().startTransaction();

        try {
            this.authenticationService.login(user, password);
            response.setHeader(ApiConfigConsts.ACCESS_TOKEN, this.jwtTokenUtil.generateToken(user));

            helperLog.getLastService().setStatus(SystemMessage.SUCESS);
            helperLog.setMensaje(SystemMessage.SUCESS);
        } catch (MasterException e) {
            helperLog.setError(e);
            throw e.getInnerException();
        } catch (Exception ex) {
            helperLog.setError(ex);
            throw MasterException.createInternalException(ex);
        } finally {
            helperLog.getLastService().endTransaction();
            helperLog.endTransaction(logger);
        }
    }
}
