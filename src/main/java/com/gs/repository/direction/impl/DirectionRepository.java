package com.gs.repository.direction.impl;

import com.google.gson.reflect.TypeToken;
import com.gs.constants.OraclePackage;
import com.gs.helper.OracleHelper;
import com.gs.model.database.DbMessageModel;
import com.gs.model.direction.DirectionDetailModel;
import com.gs.model.direction.DirectionModel;
import com.gs.repository.direction.IDirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DirectionRepository implements IDirectionRepository {


    @Autowired
    OracleHelper oracleHelper;

    @Autowired
    public DirectionRepository() {
    }

    @Transactional
    @Override
    public DbMessageModel create(DirectionModel model) {

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_CREARDIRECCIONES";
        DbMessageModel resul = null;

        sqlParamValues = this.modelToParameterSave(model, false);
        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_DIRECCIONES, procedure, sqlParamValues, DbMessageModel.class);

        return resul;
    }

    @Transactional
    @Override
    public DbMessageModel update(DirectionModel model) {

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_MODIFICARDIRECCIONES";
        DbMessageModel resul = null;

        sqlParamValues = this.modelToParameterSave(model, true);

        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_DIRECCIONES, procedure, sqlParamValues, DbMessageModel.class);

        return resul;
    }

    @Override
    public DbMessageModel changeStatus(Object model) {

        throw new UnsupportedOperationException();
    }

    @Transactional
    @Override
    public DbMessageModel<DirectionDetailModel> listAll() {

        int idNegocio = -1;

        return this.list(idNegocio);
    }

    @Transactional
    @Override
    public DbMessageModel<DirectionDetailModel> listByBusinessId(int businessId) {

        return this.list(businessId);
    }

    @Transactional
    @Override
    public DbMessageModel<DirectionDetailModel> getById(int id) {

        String procedure = "SP_CONSULTADIRECCIONESDET";
        Map<String, Object> sqlParamValues = new HashMap();
        DbMessageModel<DirectionDetailModel> resul = null;
        Type type = new TypeToken<DbMessageModel<DirectionDetailModel>>() {
        }.getType();

        sqlParamValues.put("PA_IDDIRECCION", id);
        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_DIRECCIONES, procedure, sqlParamValues, type);

        return resul;
    }

    private DbMessageModel<DirectionDetailModel> list(int idNegocio) {

        String procedure = "SP_CONSULTADIRECCIONES";
        DbMessageModel<DirectionDetailModel> resul = new DbMessageModel<>();
        Map<String, Object> sqlParamValues = new HashMap();
        Type type = new TypeToken<DbMessageModel<DirectionDetailModel>>() {
        }.getType();

        if (idNegocio > 0) {
            sqlParamValues.put("PA_IDNEGOCIO", idNegocio);
        } else {
            sqlParamValues.put("PA_IDNEGOCIO", null);
        }

        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_DIRECCIONES, procedure, sqlParamValues, type);

        return resul;
    }

    private Map<String, Object> modelToParameterSave(DirectionModel model, boolean isUpdate) {

        Map<String, Object> sqlParamValues = new HashMap();

        if (isUpdate) {
            sqlParamValues.put("PA_IDDIRECCION", model.getIdDireccion());
        } else {
            sqlParamValues.put("PA_USUARIO_CREACION", model.getIdUsuarioCreacion());
        }

        sqlParamValues.put("PA_IDNEGOCIO", model.getIdNegocio());
        sqlParamValues.put("PA_NOMBRE", model.getNombre());
        sqlParamValues.put("PA_USUARIO_MODIFICO", model.getIdUsuarioUltimaModificacion());
        sqlParamValues.put("PA_ESTATUS", model.getEstatus());

        return sqlParamValues;
    }
}
