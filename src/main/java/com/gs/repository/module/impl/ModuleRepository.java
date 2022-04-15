package com.gs.repository.module.impl;

import com.google.gson.reflect.TypeToken;
import com.gs.constants.OraclePackage;
import com.gs.helper.OracleHelper;
import com.gs.model.database.DbMessageModel;
import com.gs.model.module.ModuleModel;
import com.gs.repository.module.IModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ModuleRepository implements IModuleRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    OracleHelper oracleHelper;

    @Autowired
    public ModuleRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    @Override
    public DbMessageModel<ModuleModel> create(ModuleModel model){

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_CREARMODULOS";
        DbMessageModel<ModuleModel> resul = null;

        sqlParamValues = this.modelToParameterSave(model, false);
        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_MODULOS, procedure, sqlParamValues, DbMessageModel.class);

        return resul;

    }

    @Transactional
    @Override
    public DbMessageModel<ModuleModel> update(ModuleModel model){

        Map<String, Object> sqlParamValues = null;
        String procedure = "SP_MODIFICARMODULOS";
        DbMessageModel<ModuleModel> resul = null;

        sqlParamValues = this.modelToParameterSave(model, true);
        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_MODULOS, procedure, sqlParamValues, DbMessageModel.class);

        return resul;

    }

    @Override
    public DbMessageModel changeStatus(Object model){
        throw new UnsupportedOperationException();
    }

    @Transactional
    @Override
    public DbMessageModel listAll() {

        String procedure = "SP_CONSULTAMODULOS";
        DbMessageModel<ModuleModel> resul = new DbMessageModel<>();
        Type type = new TypeToken<DbMessageModel<ModuleModel>>() {
        }.getType();

        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_MODULOS, procedure, type);

        return resul;
    }

    @Transactional
    @Override
    public DbMessageModel getById(int id) {

        String procedure = "SP_CONSULTAMODULOSDET";
        Map<String, Object> sqlParamValues = new HashMap();
        DbMessageModel<ModuleModel> resul = null;
        Type type = new TypeToken<DbMessageModel<ModuleModel>>() {
        }.getType();

        sqlParamValues.put("PA_IDMODULO", id);
        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_MODULOS, procedure, sqlParamValues, type);

        return resul;
    }


    private Map<String, Object> modelToParameterSave(ModuleModel model, boolean isUpdate) {

        Map<String, Object> sqlParamValues = new HashMap();

        if (isUpdate) {
            sqlParamValues.put("PA_MODULO", model.getIdModulo());
        }

        sqlParamValues.put("PA_NOMBRE", model.getNombre());
        sqlParamValues.put("PA_URL" , model.getUrl());
        sqlParamValues.put("PA_USUARIO_CREACION", model.getIdUsuarioCreacion());
        sqlParamValues.put("PA_USUARIO_MODIFICO", model.getIdUsuarioUltimaModificacion());
        sqlParamValues.put("PA_PERMISOMOD", model.getPermitirAsignacionNivel());
        sqlParamValues.put("PA_ESTATUS", model.getEstatus());

        return sqlParamValues;
    }
}
