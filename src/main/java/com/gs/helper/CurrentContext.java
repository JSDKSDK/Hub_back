package com.gs.helper;

import com.gs.dto.user.UserDetailDto;
import com.gs.model.IModelAuditable;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentContext {

    /**
     * Return current user from the token
     * @return
     */
    public static UserDetailDto getCurrentUser() {

        UserDetailDto userDetailDto = (UserDetailDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userDetailDto;
    }

    /**
     * Set idUsuarioCreacion
     * @param modelAuditable
     */
    public static void setCurrentUserToModelCreate(IModelAuditable modelAuditable) {

        UserDetailDto userDetailDto = getCurrentUser();

        modelAuditable.setIdUsuarioCreacion(userDetailDto.getIdUsuario());
    }

    /**
     * Set idUsuarioUltimaModificacion
     * @param modelAuditable
     */
    public static void setCurrentUserToModelUpdate(IModelAuditable modelAuditable) {

        UserDetailDto userDetailDto = getCurrentUser();

        modelAuditable.setIdUsuarioUltimaModificacion(userDetailDto.getIdUsuario());
    }

    /**
     * Set idUusarioCreacion & idUsuarioModificacion
     * @param modelAuditable
     */
    public static void setCurrentUserToModel(IModelAuditable modelAuditable) {

        UserDetailDto userDetailDto = getCurrentUser();

        modelAuditable.setIdUsuarioCreacion(userDetailDto.getIdUsuario());
        modelAuditable.setIdUsuarioUltimaModificacion(userDetailDto.getIdUsuario());
    }
}
