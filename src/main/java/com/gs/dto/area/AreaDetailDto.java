package com.gs.dto.area;

import com.gs.model.area.AreaDetailModel;
import com.gs.model.direction.DirectionDetailModel;

public class AreaDetailDto {

    private int idArea;
    private String area;
    private int idDireccion;
    private String direccion;
    private int estatus;
    private int idNegocio;
    private String negocio;

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

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

    public static AreaDetailDto areaDetailModelToDto(AreaDetailModel model) {

        AreaDetailDto dto = new AreaDetailDto();

        dto.setIdDireccion(model.getIdDireccion());
        dto.setIdNegocio(model.getIdNegocio());
        dto.setDireccion(model.getDireccion());
        dto.setEstatus(model.getEstatus());
        dto.setNegocio(model.getNegocio());
        dto.setArea(model.getArea());
        dto.setIdArea(model.getIdArea());

        return dto;
    }
}
