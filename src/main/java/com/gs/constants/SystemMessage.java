package com.gs.constants;

public class SystemMessage {

    private static final String PARAM_VALUE_INVALID = "El parámtero '%s' no tiene un valor válido";
    public static final String OK = "OK";
    public static final String SUCESS = "SUCCESS";
    public static final String ERROR = "ERROR";
    public static final String INVALID_CREDENTIALS = "Las credenciales indicadas no son válidas. ";
    public static final String INVALID_INFORMATION = "La información indicada no es válida.";
    public static final String AN_ERROR_HAS_OCCURRED = "Ha ocurrido un error al procesar la solicitud";
    public static final String NO_DATA_FOUND = "No existe información con los parametros indicados.";
    public static final String INVALID_STATUS_CODE_TRANSACTION = "El estatus code de la transacción no es válido.";
    public static final String INVALID_DATA_NULL = "La inforcación recibida no es válida. Se requiere un valor diferente a NULL.";
    public static final String RECORD_SAVED_SUCCESSFULLY = "Registro gurdado satisfactoriamente.";
    public static final String RECORD_UPDATED_SUCCESSFULLY = "Registro actualizado satisfactoriamente.";
    public static final String INVALID_SELECTED_OPTION = "Opción seleccionada invalidad.";

    public static final String getMessageParamValueInvalid(String param) {

        return String.format(PARAM_VALUE_INVALID, param);
    }
}
