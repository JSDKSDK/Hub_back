package com.gs.repository.user;

import com.gs.model.database.DbMessageModel;
import com.gs.model.user.UserModel;
import com.gs.repository.IRepository;

public interface IUserRepository extends IRepository<UserModel, UserModel> {

    DbMessageModel<UserModel> getByEmployeeNumber(String employeeNumber);
}
