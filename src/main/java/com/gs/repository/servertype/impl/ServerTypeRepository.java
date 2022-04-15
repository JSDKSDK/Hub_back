package com.gs.repository.servertype.impl;

import com.google.gson.reflect.TypeToken;
import com.gs.constants.OraclePackage;
import com.gs.helper.OracleHelper;
import com.gs.model.servertype.ServerTypeModel;
import com.gs.model.database.DbMessageModel;
import com.gs.repository.servertype.IServerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ServerTypeRepository implements IServerTypeRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    OracleHelper oracleHelper;

    @Autowired
    public ServerTypeRepository(DataSource dataSource) {

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    @Override
    public DbMessageModel<ServerTypeModel> create(ServerTypeModel model) {

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_CREARTIPOSERV";
        DbMessageModel<ServerTypeModel> resul = null;

        sqlParamValues = this.modelToParameterSave(model, false);
        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_TSERVIDORES, procedure, sqlParamValues, DbMessageModel.class);

        return resul;
    }

    @Transactional
    @Override
    public DbMessageModel update(ServerTypeModel model){

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_MODIFICARTIPOSERV";
        DbMessageModel<ServerTypeModel> resul = null;

        sqlParamValues = this.modelToParameterSave(model, true);

        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_TSERVIDORES, procedure, sqlParamValues, DbMessageModel.class);

        return resul;
    }

    @Override
    public DbMessageModel changeStatus(Object model) {

        throw new UnsupportedOperationException();
    }

    @Transactional
    @Override
    public DbMessageModel listAll(){

        String procedure = "SP_CONSULTATIPOSERV";
        DbMessageModel<ServerTypeModel> resul = new DbMessageModel<>();

        Type type = new TypeToken<DbMessageModel<ServerTypeModel>>() {}.getType();

        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_TSERVIDORES, procedure, type);

        return resul;
    }

    @Transactional
    @Override
    public DbMessageModel getById(int id) {

        String procedure = "SP_CONSULTATIPOSERVDET";
        Map<String, Object> sqlParamValues = new HashMap();
        DbMessageModel<ServerTypeModel> resul = null;
        Type type = new TypeToken<DbMessageModel<ServerTypeModel>>() {
        }.getType();

        sqlParamValues.put("PA_IDTSERVIDOR", id);
        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_TSERVIDORES, procedure, sqlParamValues, type);

        return resul;
    }

    private Map<String, Object> modelToParameterSave(ServerTypeModel model, boolean isUpdate) {

        Map<String, Object> sqlParamValues = new HashMap();

        if (isUpdate) {
            sqlParamValues.put("PA_TIPOSERVIDOR", model.getIdTipoServidor());
        }

        sqlParamValues.put("PA_NOMBRE", model.getNombre());
        sqlParamValues.put("PA_USUARIO_CREACION", model.getIdUsuarioCreacion());
        sqlParamValues.put("PA_USUARIO_MODIFICO", model.getIdUsuarioUltimaModificacion());
        sqlParamValues.put("PA_ESTATUS", model.getEstatus());

        return sqlParamValues;
    }
}
