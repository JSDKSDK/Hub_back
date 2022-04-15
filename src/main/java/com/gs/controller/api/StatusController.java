package com.gs.controller.api;


import com.gs.constants.ApiRoutes;
import com.gs.dto.api.ApiStatusResponseDto;
import com.gs.util.Utils;
import com.gs.constants.SystemMessage;
import com.gs.helper.logger.HelperLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Status Rest Controller")
@RestController
public class StatusController {

	private static final Logger logger = LoggerFactory.getLogger(StatusController.class);

	@ApiOperation(value = "status")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successful Operation") })
	@GetMapping(value = ApiRoutes.V1_STATUS)
	public ApiStatusResponseDto getStatus() {

		HelperLog helperLog = new HelperLog();

		helperLog.startTransaction();

		ApiStatusResponseDto response = new ApiStatusResponseDto("Servicio Funcionando Correctamente", Utils.getTimeStamp());

		helperLog.endTransaction(logger);
		helperLog.setMensaje(SystemMessage.SUCESS);
		logger.error(helperLog.toJson());

		return response;

	}
}