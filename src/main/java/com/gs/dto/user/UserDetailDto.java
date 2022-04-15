package com.gs.dto.user;

import com.gs.constants.SystemMessage;
import com.gs.dto.api.ApiSuccessResponse;
import com.gs.model.user.UserModel;

public class UserDetailDto  {
    private int idUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String numeroEmpleado;
    private int status;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static UserDetailDto userModelToDto(UserModel userModel) {

        UserDetailDto dto = new UserDetailDto();

        dto.setIdUsuario(userModel.getIdusuario());
        dto.setNombre(userModel.getNombre());
        dto.setApellidoPaterno(userModel.getApellidoPaterno());
        dto.setApellidoMaterno(userModel.getApellidoMaterno());
        dto.setNumeroEmpleado(userModel.getNumeroEmpleado());
        dto.setStatus(userModel.getEstatus());

        return dto;
    }
}
