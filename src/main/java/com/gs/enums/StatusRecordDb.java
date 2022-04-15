package com.gs.enums;

public enum StatusRecordDb {

    Activo(1),
    Inactivo(0);

    private final int status;

    StatusRecordDb(int status) {
        this.status = status;
    }

    public int getValue() {

        return this.status;
    }

    public static StatusRecordDb getEnum(int estatus) {

        StatusRecordDb result = null;

        for (StatusRecordDb item : values()) {
            if (item.getValue() == estatus) {
                result = item;
                break;
            }
        }

        return result;
    }

    public static boolean isValidStatus(int status) {

        boolean result = false;
        StatusRecordDb statusRecord = getEnum(status);

        if (statusRecord != null) {
            result = true;
        }

        return result;
    }

    public static boolean isValidPermitirAsignacion(int permitirAsignacion) {

        boolean result = false;
        StatusRecordDb statusRecord = getEnum(permitirAsignacion);

        if (statusRecord != null) {
            result = true;
        }

        return result;
    }
}
