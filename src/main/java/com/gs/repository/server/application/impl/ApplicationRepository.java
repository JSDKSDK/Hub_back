package com.gs.repository.server.application.impl;

import com.google.gson.reflect.TypeToken;
import com.gs.constants.OraclePackage;
import com.gs.helper.OracleHelper;
import com.gs.model.server.application.ApplicationModel;
import com.gs.model.database.DbMessageModel;
import com.gs.repository.server.application.IApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.sql.DataSource;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ApplicationRepository  implements IApplicationRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    OracleHelper oracleHelper;

    @Autowired
    public ApplicationRepository(DataSource dataSource) {

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    @Override
    public DbMessageModel<ApplicationModel> create(ApplicationModel model) {

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_CREARSAPL";
        DbMessageModel<ApplicationModel> resul = null;

        sqlParamValues = this.modelToParameterSave(model, false);
        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SAPLICATIVOS, procedure, sqlParamValues, DbMessageModel.class);

        return resul;
    }

    @Transactional
    @Override
    public DbMessageModel update(ApplicationModel model) {

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_MODIFICARSAPL";
        DbMessageModel<ApplicationModel> resul = null;

        sqlParamValues = this.modelToParameterSave(model, true);

        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SAPLICATIVOS, procedure, sqlParamValues, DbMessageModel.class);

        return resul;
    }

    @Override
    public DbMessageModel changeStatus(Object model) {

        throw new UnsupportedOperationException();
    }

    @Transactional
    @Override
    public DbMessageModel listAll() {

        int idServer = -1;

        return this.listAllByServerId(idServer);

    }

    @Transactional
    @Override
    public DbMessageModel<ApplicationModel> listAllByServerId(int idServidor) {

        String procedure = "SP_CONSULTARSAPL";
        Map<String, Object> sqlParamValues = new HashMap();
        DbMessageModel<ApplicationModel> resul = new DbMessageModel<>();
        Type type = new TypeToken<DbMessageModel<ApplicationModel>>() {
        }.getType();
        if (idServidor > 0){
            sqlParamValues.put("PA_IDSERVIDOR", idServidor);
        }else{
            sqlParamValues.put("PA_IDSERVIDOR", null);
        }

        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SAPLICATIVOS, procedure, sqlParamValues, type);

        return resul;
    }

    @Transactional
    @Override
    public DbMessageModel getById(int id) {

        String procedure = "SP_CONSULTARSAPLDET";
        Map<String, Object> sqlParamValues = new HashMap();
        DbMessageModel<ApplicationModel> resul = null;
        Type type = new TypeToken<DbMessageModel<ApplicationModel>>() {
        }.getType();

        sqlParamValues.put("PA_IDSAPL", id);
        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SAPLICATIVOS, procedure, sqlParamValues, type);

        return resul;
    }

    private Map<String, Object> modelToParameterSave(ApplicationModel model, boolean isUpdate){

        Map<String, Object> sqlParamValues = new HashMap();

        if (isUpdate){
            sqlParamValues.put("PA_IDSAPL", model.getIdSAplicativo());
        }

        sqlParamValues.put("PA_IDSERVIDOR", model.getIdServidor());
        sqlParamValues.put("PA_URL", model.getUrl());
        sqlParamValues.put("PA_COMENTARIOS", model.getComentarios());
        sqlParamValues.put("PA_USUARIO_CREACION", model.getIdUsuarioCreacion());
        sqlParamValues.put("PA_USUARIO_MODIFICO", model.getIdUsuarioUltimaModificacion());
        sqlParamValues.put("PA_ESTATUS", model.getEstatus());

        return sqlParamValues;


    }
}
