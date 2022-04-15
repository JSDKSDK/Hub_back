package com.gs.repository.business.impl;

import com.google.gson.reflect.TypeToken;
import com.gs.constants.OraclePackage;
import com.gs.helper.OracleHelper;
import com.gs.model.business.BusinessModel;
import com.gs.model.database.DbMessageModel;
import com.gs.repository.business.IBusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BusinessRepository implements IBusinessRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    OracleHelper oracleHelper;

    @Autowired
    public BusinessRepository(DataSource dataSource) {

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    @Override
    public DbMessageModel<BusinessModel> create(BusinessModel model) {

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_CREARNEGOCIOS";
        DbMessageModel<BusinessModel> resul = null;

        sqlParamValues = this.modelToParameterSave(model, false);
        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_NEGOCIOS, procedure, sqlParamValues, DbMessageModel.class);

        return resul;
    }

    @Transactional
    @Override
    public DbMessageModel update(BusinessModel model) {

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_MODIFICARNEGOCIOS";
        DbMessageModel<BusinessModel> resul = null;

        sqlParamValues = this.modelToParameterSave(model, true);

        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_NEGOCIOS, procedure, sqlParamValues, DbMessageModel.class);

        return resul;
    }

    @Override
    public DbMessageModel changeStatus(Object model) {

        throw new UnsupportedOperationException();
    }

    @Transactional
    @Override
    public DbMessageModel listAll() {

        String procedure = "SP_CONSULTANEGOCIOS";
        DbMessageModel<BusinessModel> resul = new DbMessageModel<>();
        Type type = new TypeToken<DbMessageModel<BusinessModel>>() {
        }.getType();

        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_NEGOCIOS, procedure, type);

        return resul;
    }

    @Transactional
    @Override
    public DbMessageModel getById(int id) {

        String procedure = "SP_CONSULTANEGOCIOSDET";
        Map<String, Object> sqlParamValues = new HashMap();
        DbMessageModel<BusinessModel> resul = null;
        Type type = new TypeToken<DbMessageModel<BusinessModel>>() {
        }.getType();

        sqlParamValues.put("PA_IDNEGOCIO", id);
        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_NEGOCIOS, procedure, sqlParamValues, type);

        return resul;
    }

    private Map<String, Object> modelToParameterSave(BusinessModel model, boolean isUpdate) {

        Map<String, Object> sqlParamValues = new HashMap();

        if (isUpdate) {
            sqlParamValues.put("PA_IDNEGOCIO", model.getIdNegocio());
        }

        sqlParamValues.put("PA_NOMBRE", model.getNombre());
        sqlParamValues.put("PA_USUARIO_CREACION", model.getIdUsuarioCreacion());
        sqlParamValues.put("PA_USUARIO_MODIFICO", model.getIdUsuarioUltimaModificacion());
        sqlParamValues.put("PA_ESTATUS", model.getEstatus());

        return sqlParamValues;
    }
}
