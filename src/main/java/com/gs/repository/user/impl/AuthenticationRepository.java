package com.gs.repository.user.impl;

import com.gs.constants.OraclePackage;
import com.gs.helper.OracleHelper;
import com.gs.model.database.DbMessageModel;
import com.gs.repository.user.IAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthenticationRepository implements IAuthenticationRepository {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    OracleHelper oracleHelper;

    @Autowired
    public AuthenticationRepository(DataSource dataSource) {

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    @Override
    public DbMessageModel login(String user, String password) {

        Map<String, Object> sqlParamValues = new HashMap();
        String procedure = "SP_ACCESOUSUARIOS";
        DbMessageModel resul;

        sqlParamValues.put("PA_NUMEMPLEADO", user);
        sqlParamValues.put("PA_CONTRASENIA", password);

        resul = this.oracleHelper.executeProcedure(OraclePackage.PACKAGE_PA_USUARIOS, procedure, sqlParamValues, DbMessageModel.class);

        return resul;
    }
}
