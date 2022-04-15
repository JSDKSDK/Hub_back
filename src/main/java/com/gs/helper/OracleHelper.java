package com.gs.helper;

import com.gs.constants.DataBase;
import com.gs.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.reflect.Type;
import java.util.Map;

@Component
public class OracleHelper {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OracleHelper(DataSource dataSource) {

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public <T> T executeProcedure(String packageName, String procedureName, Map<String, Object> paramters, Type type) {

        Map<String, Object> mapResult = null;
        T objResult;

        mapResult = this.executeWithReturnValue(packageName, procedureName, paramters);

        objResult = DataUtil.mapToObject(mapResult, type);

        return objResult;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public <T> T executeProcedure(String packageName, String procedureName, Type type) {

        Map<String, Object> mapResult = null;
        T objResult;

        mapResult = this.executeWithReturnValue(packageName, procedureName, null);

        objResult = DataUtil.mapToObject(mapResult, type);

        return objResult;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public <T> T executeProcedure(String packageName, String procedureName, Map<String, Object> paramters, Class<T> classOfT) {

        Map<String, Object> mapResult = null;
        T objResult;

        mapResult = this.executeWithReturnValue(packageName, procedureName, paramters);

        objResult = DataUtil.mapToObject(mapResult, classOfT);

        return objResult;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public <T> T executeProcedure(String packageName, String procedureName, Class<T> classOfT) {

        Map<String, Object> mapResult = null;
        T objResult;

        mapResult = this.executeWithReturnValue(packageName, procedureName, null);

        objResult = DataUtil.mapToObject(mapResult, classOfT);

        return objResult;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void executeProcedure(String packageName, String procedureName, Map<String, Object> paramters) {

        SimpleJdbcCall simpleJdbcCall = null;

        simpleJdbcCall = this.createSimpleJdbcCall(packageName, procedureName);

        simpleJdbcCall.execute(paramters);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void executeProcedure(String packageName, String procedureName) {

        SimpleJdbcCall simpleJdbcCall = null;

        simpleJdbcCall = this.createSimpleJdbcCall(packageName, procedureName);

        simpleJdbcCall.execute();
    }

    private SimpleJdbcCall createSimpleJdbcCall(String packageName, String procedureName) {

        SimpleJdbcCall simpleJdbcCall = null;

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName(DataBase.SCHEMA_ADM_QA_ACC)
                .withCatalogName(packageName)
                .withProcedureName(procedureName);

        return simpleJdbcCall;
    }

    private Map<String, Object> executeWithReturnValue(String packageName, String procedureName, Map<String, Object> paramters) {

        SimpleJdbcCall simpleJdbcCall = null;
        Map<String, Object> mapResult = null;

        simpleJdbcCall = this.createSimpleJdbcCall(packageName, procedureName);

        if (paramters == null) {
            mapResult = simpleJdbcCall.execute();
        } else {
            mapResult = simpleJdbcCall.execute(paramters);
        }

        return mapResult;
    }
}
