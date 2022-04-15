package com.gs.service.user;

import com.gs.exception.MasterException;

public interface IAuthenticationService {

    void login(String userName, String password) throws MasterException, Exception;
}
