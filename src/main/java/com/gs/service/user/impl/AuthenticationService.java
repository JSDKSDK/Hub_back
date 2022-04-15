package com.gs.service.user.impl;

import com.gs.constants.SystemMessage;
import com.gs.exception.MasterException;
import com.gs.model.database.DbMessageModel;
import com.gs.repository.user.IAuthenticationRepository;
import com.gs.service.user.IAuthenticationService;
import com.gs.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private IAuthenticationRepository authenticationRepository;

    @Override
    public void login(String user, String password) throws MasterException {

        DbMessageModel resulTransaction = null;
        ArrayList<String> errorsService = new ArrayList<String>();

        if (!isValidRequest(user, password, errorsService)) {
            throw new MasterException(SystemMessage.INVALID_CREDENTIALS, errorsService, HttpStatus.BAD_REQUEST);
        }

        resulTransaction = authenticationRepository.login(user, password);

        if (!resulTransaction.IsTransactionOk()) {
            switch (resulTransaction.getStatusTransacction()) {
                case CustomError:
                    throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, resulTransaction.getMessage(), HttpStatus.BAD_REQUEST);
                case NoData:
                    throw new MasterException(SystemMessage.INVALID_CREDENTIALS, SystemMessage.NO_DATA_FOUND, HttpStatus.UNAUTHORIZED);
                default:
                    throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, resulTransaction.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    private boolean isValidRequest(String userName, String password, ArrayList<String> errorsService) {

        boolean result = true;

        if (Utils.isEmptyOrWhiteSpaceOrNUll(userName)) {
            errorsService.add(String.format(SystemMessage.getMessageParamValueInvalid("Usuario")));
            result = false;
        }
        if (Utils.isEmptyOrWhiteSpaceOrNUll(password)) {
            errorsService.add(String.format(SystemMessage.getMessageParamValueInvalid("Contraseña")));
            result = false;
        }

        if (Utils.isEmptyOrWhiteSpaceOrNUll(userName)) {
            errorsService.add(String.format(SystemMessage.getMessageParamValueInvalid("Usuario")));
            result = false;
        }

        return result;
    }
}
