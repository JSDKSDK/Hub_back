package com.gs.repository.server.impl;

import com.google.gson.reflect.TypeToken;
import com.gs.constants.OraclePackage;
import com.gs.helper.OracleHelper;
import com.gs.model.database.DbMessageModel;
import com.gs.model.server.ServerModel;
import com.gs.repository.server.IServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ServerRepository implements IServerRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    OracleHelper oracleHelper;

    @Autowired
    public ServerRepository(DataSource dataSource) {

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    @Override
    public DbMessageModel<ServerModel> create(ServerModel model){

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_CREARSERVIDORES";
        DbMessageModel<ServerModel> resul = null;

        sqlParamValues = this.modelToParameterSave(model, false);
        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SERVIDORES, procedure, sqlParamValues, DbMessageModel.class);

        return resul;

    }

    @Transactional
    @Override
    public DbMessageModel<ServerModel> update(ServerModel model){

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_MODIFICARSERVIDORES";
        DbMessageModel<ServerModel> resul = null;

        sqlParamValues = this.modelToParameterSave(model, true);
        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SERVIDORES, procedure, sqlParamValues, DbMessageModel.class);

        return resul;

    }

    @Override
    public DbMessageModel changeStatus(Object model) {

        throw new UnsupportedOperationException();
    }

    @Transactional
    @Override
    public DbMessageModel listAll() {

        String procedure = "SP_CONSULTARSERVIDORES";
        DbMessageModel<ServerModel> resul = new DbMessageModel<>();
        Type type = new TypeToken<DbMessageModel<ServerModel>>() {
        }.getType();

        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SERVIDORES, procedure, type);

        return resul;
    }

    @Transactional
    @Override
    public DbMessageModel getById(int id) {

        String procedure = "SP_CONSULTARSERVIDORESDET";
        Map<String, Object> sqlParamValues = new HashMap();
        DbMessageModel<ServerModel> resul = null;
        Type type = new TypeToken<DbMessageModel<ServerModel>>() {
        }.getType();

        sqlParamValues.put("PA_IDSERVIDOR", id);
        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SERVIDORES, procedure, sqlParamValues, type);

        return resul;
    }

    private Map<String, Object> modelToParameterSave(ServerModel model, boolean isUpdate) {

        Map<String, Object> sqlParamValues = new HashMap();

        if (isUpdate) {
            sqlParamValues.put("PA_IDSERVIDOR", model.getIdServidor());
        }

        sqlParamValues.put("PA_IDAREA", model.getIdArea());
        sqlParamValues.put("PA_IDTSERVIDOR", model.getIdTipoServidor());
        sqlParamValues.put("PA_IDSISOPE", model.getIdSistemaOperativo());
        sqlParamValues.put("PA_URL", model.getUrl());
        sqlParamValues.put("PA_DESCRIPCION", model.getDescripcion());
        sqlParamValues.put("PA_USUARIO_CREACION", model.getIdUsuarioCreacion());
        sqlParamValues.put("PA_USUARIO_MODIFICO", model.getIdUsuarioUltimaModificacion());
        sqlParamValues.put("PA_ESTATUS", model.getEstatus());

        return sqlParamValues;
    }

}
