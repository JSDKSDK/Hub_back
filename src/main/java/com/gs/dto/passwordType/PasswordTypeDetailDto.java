package com.gs.dto.passwordType;

public class PasswordTypeDetailDto {

    private int idPasswordType;
    private String passwordType;
    private int estatus;

    public int getIdPasswordType() {
        return idPasswordType;
    }

    public void setIdPasswordType(int idPasswordType) {
        this.idPasswordType = idPasswordType;
    }

    public String getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(String passwordType) {
        this.passwordType = passwordType;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
}
