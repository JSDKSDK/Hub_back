package com.gs.repository.server.credential.impl;

import com.google.gson.reflect.TypeToken;
import com.gs.constants.OraclePackage;
import com.gs.helper.OracleHelper;
import com.gs.model.database.DbMessageModel;
import com.gs.model.server.credential.ServerCredentialDetailModel;
import com.gs.model.server.credential.ServerCredentialModel;
import com.gs.repository.server.credential.IServerCredentialRepository;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ServerCredentialRepository implements IServerCredentialRepository {

    @Autowired
    OracleHelper oracleHelper;

    @Autowired
    public ServerCredentialRepository(){

    }

    @Transactional
    @Override
    public DbMessageModel create(ServerCredentialModel model) {

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_CREARSCRED";
        DbMessageModel result = null;

        sqlParamValues = this.modelToParameterSave(model, false);
        result = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SCREDENCIALES, procedure, sqlParamValues, DbMessageModel.class);

        return result;
    }

    @Transactional
    @Override
    public DbMessageModel update(ServerCredentialModel model) {

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_MODIFICARSCRED";
        DbMessageModel result = null;

        sqlParamValues = this.modelToParameterSave(model, true);

        result = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SCREDENCIALES, procedure, sqlParamValues, DbMessageModel.class);

        return result;
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
    public DbMessageModel listAllByServerId(int idServidor) {

        String procedure = "SP_CONSULTARSCRED";
        Map<String, Object> sqlParamValues = new HashMap();
        DbMessageModel<ServerCredentialDetailModel> result = new DbMessageModel<>();
        Type type = new TypeToken<DbMessageModel<ServerCredentialDetailModel>>() {
        }.getType();

        if (idServidor > 0){
            sqlParamValues.put("PA_IDSERVIDOR", idServidor);
        }else{
            sqlParamValues.put("PA_IDSERVIDOR", null);
        }

        result = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SCREDENCIALES, procedure,sqlParamValues, type);

        return result;
    }

    @Transactional
    @Override
    public DbMessageModel<ServerCredentialDetailModel> getById(int id) {

        String procedure = "SP_CONSULTARSCREDDET";
        Map<String, Object> sqlParamValues = new HashMap();
        DbMessageModel<ServerCredentialDetailModel> result = null;
        Type type = new TypeToken<DbMessageModel<ServerCredentialDetailModel>>() {
        }.getType();

        sqlParamValues.put("PA_IDSCREDENCIAL", id);
        result = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SCREDENCIALES, procedure, sqlParamValues, type);

        return result;
    }

    private Map<String, Object> modelToParameterSave(ServerCredentialModel model, boolean isUpdate) {

        Map<String, Object> sqlParamValues = new HashMap();

        if (isUpdate) {
            sqlParamValues.put("PA_IDSCREDENCIAL", model.getIdSCredencial());
        } else {
            sqlParamValues.put("PA_USUARIO_CREACION", model.getIdUsuarioCreacion());
        }

        sqlParamValues.put("PA_IDSERVIDOR", model.getIdServidor());
        sqlParamValues.put("PA_IDTCONTRASENIA", model.getIdTContrasena());
        sqlParamValues.put("PA_USUARIO", model.getUsuario());
        sqlParamValues.put("PA_CONTRASENIA", model.getContrasena());
        sqlParamValues.put("PA_COMENTARIO", model.getComentario());
        sqlParamValues.put("PA_USUARIO_MODIFICO", model.getIdUsuarioUltimaModificacion());
        sqlParamValues.put("PA_ESTATUS", model.getEstatus());

        return sqlParamValues;
    }
}
