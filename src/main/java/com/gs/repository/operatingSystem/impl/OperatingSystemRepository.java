package com.gs.repository.operatingSystem.impl;

import com.google.gson.reflect.TypeToken;
import com.gs.constants.OraclePackage;
import com.gs.helper.OracleHelper;
import com.gs.model.database.DbMessageModel;
import com.gs.model.operatingSystem.OperatingSystemModel;
import com.gs.repository.operatingSystem.IOperatingSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Repository
public class OperatingSystemRepository implements IOperatingSystemRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    OracleHelper oracleHelper;

    @Autowired
    public OperatingSystemRepository(DataSource dataSource) {

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    @Override
    public DbMessageModel<OperatingSystemModel> create(OperatingSystemModel model) {

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_CREARSISOP";
        DbMessageModel<OperatingSystemModel> result = null;

        sqlParamValues = this.modelToParameterSave(model, false);
        result = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SISTEMAS_OPERATIVOS, procedure, sqlParamValues, DbMessageModel.class);

        return result;
    }

    @Transactional
    @Override
    public DbMessageModel update(OperatingSystemModel model) {

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_MODIFICARSISOP";
        DbMessageModel<OperatingSystemModel> result = null;

        sqlParamValues = this.modelToParameterSave(model, true);

        result = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SISTEMAS_OPERATIVOS, procedure, sqlParamValues, DbMessageModel.class);

        return result;
    }

    @Override
    public DbMessageModel changeStatus(Object model) {
        throw new UnsupportedOperationException();
    }

    @Transactional
    @Override
    public DbMessageModel<OperatingSystemModel> listAll() {

        String procedure = "SP_CONSULTARSISOP";
        DbMessageModel<OperatingSystemModel> result = new DbMessageModel<>();
        Type type = new TypeToken<DbMessageModel<OperatingSystemModel>>() {
        }.getType();

        result = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SISTEMAS_OPERATIVOS, procedure, type);

        return result;
    }

    @Transactional
    @Override
    public DbMessageModel<OperatingSystemModel> getById(int id) {

        String procedure = "SP_CONSULTARSISOPDET";
        Map<String, Object> sqlParamValues = new HashMap();
        DbMessageModel<OperatingSystemModel> result = null;
        Type type = new TypeToken<DbMessageModel<OperatingSystemModel>>() {
        }.getType();

        sqlParamValues.put("PA_IDSISOP", id);
        result = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SISTEMAS_OPERATIVOS, procedure, sqlParamValues, type);

        return result;
    }

    private Map<String, Object> modelToParameterSave(OperatingSystemModel model, boolean isUpdate) {

        Map<String, Object> sqlParamValues = new HashMap();

        if (isUpdate) {
            sqlParamValues.put("PA_IDSISOP", model.getIdSOperativo());
        }

        sqlParamValues.put("PA_NOMBRE", model.getNombre());
        sqlParamValues.put("PA_USUARIO_CREACION", model.getIdUsuarioCreacion());
        sqlParamValues.put("PA_USUARIO_MODIFICO", model.getIdUsuarioUltimaModificacion());
        sqlParamValues.put("PA_ESTATUS", model.getEstatus());

        return sqlParamValues;
    }
}
