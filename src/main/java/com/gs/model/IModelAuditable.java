package com.gs.model;

import java.util.Date;

public interface IModelAuditable {

    void setFechaCreacion(Date fechaCreacion);

    Date getFechaUltimaModificacion();

    void setFechaUltimaModificacion(Date fechaUltimaModificacion);

    int getIdUsuarioCreacion();

    void setIdUsuarioCreacion(int idUsuarioCreacion);

    int getIdUsuarioUltimaModificacion();

    void setIdUsuarioUltimaModificacion(int idUsuarioUltimaModificacion);

}
