package com.gs.repository.area.impl;

import com.google.gson.reflect.TypeToken;
import com.gs.constants.OraclePackage;
import com.gs.helper.OracleHelper;
import com.gs.model.area.AreaDetailModel;
import com.gs.model.area.AreaModel;
import com.gs.model.database.DbMessageModel;
import com.gs.repository.area.IAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AreaRepository implements IAreaRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    OracleHelper oracleHelper;

    @Transactional
    @Override
    public DbMessageModel create(AreaModel model) {

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_CREARAREAS";
        DbMessageModel resul = null;

        sqlParamValues = this.modelToParameterSave(model, false);
        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_AREAS, procedure, sqlParamValues, DbMessageModel.class);

        return resul;
    }

    @Transactional
    @Override
    public DbMessageModel update(AreaModel model) {

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_MODIFICARAREAS";
        DbMessageModel resul = null;

        sqlParamValues = this.modelToParameterSave(model, true);

        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_AREAS, procedure, sqlParamValues, DbMessageModel.class);

        return resul;
    }

    @Override
    public DbMessageModel changeStatus(Object model) {

        throw new UnsupportedOperationException();
    }

    @Transactional
    @Override
    public DbMessageModel<AreaDetailModel> listAll() {

        int idDirection = -1;

        return this.listAllByDirectionId(idDirection);
    }

    @Transactional
    @Override
    public DbMessageModel<AreaDetailModel> listAllByDirectionId(int directionId) {

        String procedure = "SP_CONSULTARAREAS";
        Map<String, Object> sqlParamValues = new HashMap();
        DbMessageModel<AreaDetailModel> resul = new DbMessageModel<>();
        Type type = new TypeToken<DbMessageModel<AreaDetailModel>>() {
        }.getType();

        if(directionId>0){
            sqlParamValues.put("PA_IDDIRECCION", directionId);
        }
        else {
            sqlParamValues.put("PA_IDDIRECCION", null);
        }
        
        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_AREAS, procedure, sqlParamValues, type);

        return resul;
    }

    @Transactional
    @Override
    public DbMessageModel<AreaDetailModel> getById(int id) {

        String procedure = "SP_CONSULTARAREASDET";
        Map<String, Object> sqlParamValues = new HashMap();
        DbMessageModel<AreaDetailModel> resul = null;
        Type type = new TypeToken<DbMessageModel<AreaDetailModel>>() {
        }.getType();

        sqlParamValues.put("PA_IDAREA", id);
        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_AREAS, procedure, sqlParamValues, type);

        return resul;
    }

    private Map<String, Object> modelToParameterSave(AreaModel model, boolean isUpdate) {

        Map<String, Object> sqlParamValues = new HashMap();

        if (isUpdate) {
            sqlParamValues.put("PA_IDAREA", model.getIdArea());
        } else {
            sqlParamValues.put("PA_USUARIO_CREACION", model.getIdUsuarioCreacion());
        }

        sqlParamValues.put("PA_IDDIRECCION", model.getIdDireccion());
        sqlParamValues.put("PA_NOMBRE", model.getNombre());
        sqlParamValues.put("PA_USUARIO_MODIFICO", model.getIdUsuarioUltimaModificacion());
        sqlParamValues.put("PA_ESTATUS", model.getEstatus());

        return sqlParamValues;
    }
}
