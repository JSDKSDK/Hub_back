package com.gs.repository.passwordType.impl;

import com.google.gson.reflect.TypeToken;
import com.gs.constants.OraclePackage;
import com.gs.helper.OracleHelper;
import com.gs.model.database.DbMessageModel;
import com.gs.model.passwordType.PasswordTypeModel;
import com.gs.repository.passwordType.IPasswordTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PasswordTypeRepository implements IPasswordTypeRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    OracleHelper oracleHelper;

    @Autowired
    public PasswordTypeRepository(DataSource dataSource) {

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    @Override
    public DbMessageModel create(PasswordTypeModel model) {

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_CREARTCONTRA";
        DbMessageModel<PasswordTypeModel> result = null;

        sqlParamValues = this.modelToParameterSave(model, false);
        result = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_TIPO_CONTRASENA, procedure, sqlParamValues, DbMessageModel.class);

        return result;
    }

    @Transactional
    @Override
    public DbMessageModel update(PasswordTypeModel model) {

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_MODIFICARTCONTRA";
        DbMessageModel<PasswordTypeModel> result = null;

        sqlParamValues = this.modelToParameterSave(model, true);

        result = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_TIPO_CONTRASENA, procedure, sqlParamValues, DbMessageModel.class);

        return result;
    }

    @Override
    public DbMessageModel changeStatus(Object model) {
        throw new UnsupportedOperationException();
    }

    @Transactional
    @Override
    public DbMessageModel<PasswordTypeModel> listAll() {

        String procedure = "SP_CONSULTARTCONTRA";
        DbMessageModel<PasswordTypeModel> result = new DbMessageModel<>();
        Type type = new TypeToken<DbMessageModel<PasswordTypeModel>>() {
        }.getType();

        result = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_TIPO_CONTRASENA, procedure, type);

        return result;
    }

    @Transactional
    @Override
    public DbMessageModel<PasswordTypeModel> getById(int id) {

        String procedure = "SP_CONSULTARTCONTRADET";
        Map<String, Object> sqlParamValues = new HashMap();
        DbMessageModel<PasswordTypeModel> result = null;
        Type type = new TypeToken<DbMessageModel<PasswordTypeModel>>() {
        }.getType();

        sqlParamValues.put("PA_IDTCONTRASENIA", id);
        result = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_TIPO_CONTRASENA, procedure, sqlParamValues, type);

        return result;
    }

    private Map<String, Object> modelToParameterSave(PasswordTypeModel model, boolean isUpdate) {

        Map<String, Object> sqlParamValues = new HashMap();

        if (isUpdate) {
            sqlParamValues.put("PA_IDTCONTRASENIA", model.getIdPasswordType());
        }

        sqlParamValues.put("PA_TIPOCONTRASENIA", model.getPasswordType());
        sqlParamValues.put("PA_USUARIO_CREACION", model.getIdUsuarioCreacion());
        sqlParamValues.put("PA_USUARIO_MODIFICO", model.getIdUsuarioUltimaModificacion());
        sqlParamValues.put("PA_ESTATUS", model.getEstatus());

        return sqlParamValues;
    }
}
