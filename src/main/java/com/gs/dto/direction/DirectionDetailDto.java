package com.gs.dto.direction;

import com.gs.model.direction.DirectionDetailModel;

public class DirectionDetailDto {

    private int idDireccion;
    private String direccion;
    private int estatus;
    private int idNegocio;
    private String negocio;

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public int getIdNegocio() {
        return idNegocio;
    }

    public void setIdNegocio(int idNegocio) {
        this.idNegocio = idNegocio;
    }

    public String getNegocio() {
        return negocio;
    }

    public void setNegocio(String negocio) {
        this.negocio = negocio;
    }

    public static DirectionDetailDto directioDetailModelToDto(DirectionDetailModel directioDetailModel){

        DirectionDetailDto directionDetailDto = new DirectionDetailDto();

        directionDetailDto.setIdDireccion(directioDetailModel.getIdDireccion());
        directionDetailDto.setIdNegocio(directioDetailModel.getIdNegocio());
        directionDetailDto.setDireccion(directioDetailModel.getDireccion());
        directionDetailDto.setEstatus(directioDetailModel.getEstatus());
        directionDetailDto.setNegocio(directioDetailModel.getNegocio());

        return directionDetailDto;
    }
}
