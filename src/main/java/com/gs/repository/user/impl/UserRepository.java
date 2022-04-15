package com.gs.repository.user.impl;

import com.google.gson.reflect.TypeToken;
import com.gs.constants.OraclePackage;
import com.gs.helper.OracleHelper;
import com.gs.model.database.DbMessageModel;
import com.gs.model.user.UserModel;
import com.gs.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository implements IUserRepository {

    private OracleHelper oracleHelper;

    @Autowired
    public UserRepository(OracleHelper oracleHelper) {

        this.oracleHelper = oracleHelper;
    }

    @Override
    public DbMessageModel create(UserModel model) {
        return null;
    }

    @Override
    public DbMessageModel update(UserModel model) {
        return null;
    }

    @Override
    public DbMessageModel changeStatus(Object model) {
        return null;
    }

    @Override
    public DbMessageModel<UserModel> listAll() {
        return null;
    }

    @Transactional
    @Override
    public DbMessageModel<UserModel> getById(int id) {

        String employeeNumber = null;

        return this.getUser(id, employeeNumber);
    }

    @Transactional
    @Override
    public DbMessageModel<UserModel> getByEmployeeNumber(String employeeNumber) {


        return this.getUser(null, employeeNumber);
    }

    private DbMessageModel<UserModel> getUser(@Nullable Integer idUsuario, String numeroEmpleado) {

        String procedure = "SP_CONSULTAUSUARIOSDET";
        Map<String, Object> sqlParamValues = new HashMap();
        DbMessageModel<UserModel> resul = null;
        Type type = new TypeToken<DbMessageModel<UserModel>>() {
        }.getType();

        sqlParamValues.put("PA_IDEMPLEADO", idUsuario);
        sqlParamValues.put("PA_NUMEMPLEADO", numeroEmpleado);

        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_USUARIOS, procedure, sqlParamValues, type);

        return resul;
    }
}
