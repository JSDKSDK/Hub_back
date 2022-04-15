package com.gs.repository.user;

import com.gs.model.database.DbMessageModel;

public interface IAuthenticationRepository {

    DbMessageModel login(String user, String password);
}
