package com.gs.repository.server.note.impl;

import com.google.gson.reflect.TypeToken;
import com.gs.constants.OraclePackage;
import com.gs.repository.server.note.IServerNoteRepository;
import com.gs.helper.OracleHelper;
import com.gs.model.database.DbMessageModel;
import com.gs.model.server.note.ServerNoteDetailModel;
import com.gs.model.server.note.ServerNoteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ServerNoteRepository implements IServerNoteRepository {

    @Autowired
    OracleHelper oracleHelper;

    @Autowired
    public ServerNoteRepository(){

    }

    @Transactional
    @Override
    public DbMessageModel create(ServerNoteModel model) {

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_CREARSNOTAS";
        DbMessageModel result = null;

        sqlParamValues = this.modelToParameterSave(model, false);
        result = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SNOTAS, procedure, sqlParamValues, DbMessageModel.class);

        return result;
    }

    @Transactional
    @Override
    public DbMessageModel update(ServerNoteModel model) {

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_MODIFICARSNOTAS";
        DbMessageModel result = null;

        sqlParamValues = this.modelToParameterSave(model, true);

        result = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SNOTAS, procedure, sqlParamValues, DbMessageModel.class);

        return result;
    }

    @Override
    public DbMessageModel changeStatus(Object model) {
        throw new UnsupportedOperationException();
    }

    @Transactional
    @Override
    public DbMessageModel<ServerNoteDetailModel> listAll() {

        int idServidor = -1;

        return this.list(idServidor);
    }

    @Transactional
    @Override
    public DbMessageModel<ServerNoteDetailModel> listByServerId(int serverId) {

        return this.list(serverId);
    }

    @Transactional
    @Override
    public DbMessageModel<ServerNoteDetailModel> getById(int id) {

        String procedure = "SP_CONSULTARSNOTASDET";
        Map<String, Object> sqlParamValues = new HashMap();
        DbMessageModel<ServerNoteDetailModel> result = null;
        Type type = new TypeToken<DbMessageModel<ServerNoteDetailModel>>() {
        }.getType();

        sqlParamValues.put("PA_IDSNOTA", id);
        result = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SNOTAS, procedure, sqlParamValues, type);

        return result;
    }

    private DbMessageModel<ServerNoteDetailModel> list(int serverId) {

        String procedure = "SP_CONSULTARSNOTAS";
        DbMessageModel<ServerNoteDetailModel> result = new DbMessageModel<>();
        Map<String, Object> sqlParamValues = new HashMap();
        Type type = new TypeToken<DbMessageModel<ServerNoteDetailModel>>() {
        }.getType();

        if (serverId > 0) {
            sqlParamValues.put("PA_IDSERVIDOR", serverId);
        } else {
            sqlParamValues.put("PA_IDSERVIDOR", null);
        }

        result = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_SNOTAS, procedure, sqlParamValues, type);

        return result;
    }

    private Map<String, Object> modelToParameterSave(ServerNoteModel model, boolean isUpdate) {

        Map<String, Object> sqlParamValues = new HashMap();

        if (isUpdate) {
            sqlParamValues.put("PA_IDSNOTA", model.getIdSNota());
        } else {
            sqlParamValues.put("PA_USUARIO_CREACION", model.getIdUsuarioCreacion());
        }

        sqlParamValues.put("PA_IDSERVIDOR", model.getIdServidor());
        sqlParamValues.put("PA_TITULO", model.getTitulo());
        sqlParamValues.put("PA_NOTA", model.getNota());
        sqlParamValues.put("PA_RUTAARCHIVO", model.getRutaArchivo());
        sqlParamValues.put("PA_USUARIO_MODIFICO", model.getIdUsuarioUltimaModificacion());
        sqlParamValues.put("PA_ESTATUS", model.getEstatus());

        return sqlParamValues;
    }
}
