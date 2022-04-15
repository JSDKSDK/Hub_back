package com.gs.model.database;

import com.google.gson.annotations.SerializedName;
import com.gs.constants.DataBase;
import com.gs.constants.SystemMessage;
import com.gs.enums.DbTransaccionStatus;
import com.gs.exception.MasterException;
import org.springframework.http.HttpStatus;

import java.util.List;

public class DbMessageModel<T> {

    @SerializedName("VL_PIORESPONSECODE")
    private int responseCode = 0;

    @SerializedName("VL_PCORESPONSEMESSAG")
    private String message = null;

    @SerializedName("VL_POCURSOR")
    private List<T> dataCursor;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getDataCursor() {
        return dataCursor;
    }

    public void setDataCursor(List<T> dataCursor) {
        this.dataCursor = dataCursor;
    }

    public List<T> getAllData() {

        return this.dataCursor;
    }

    public T getSingleData() {

        int firstRecord = 0;

        if (this.dataCursor == null) {
            return null;
        } else {
            return this.dataCursor.get(firstRecord);
        }
    }

    public boolean IsTransactionOk() {

        boolean isOk = false;

        if (this.getResponseCode() == DataBase.DB_TRANSACTION_SUCCESS) {
            isOk = true;
        }

        return isOk;
    }

    public void validateTransaction() throws MasterException {

        if (!this.IsTransactionOk()) {
            switch (this.getStatusTransacction()) {
                case CustomError:
                    throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, this.getMessage(), HttpStatus.BAD_REQUEST);
                case NoData:
                    throw new MasterException(SystemMessage.NO_DATA_FOUND, this.getMessage(), HttpStatus.NOT_FOUND);
                default:
                    throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, this.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public DbTransaccionStatus getStatusTransacction() throws MasterException {

        DbTransaccionStatus result = null;

        if (this.getResponseCode() == DataBase.DB_TRANSACTION_SUCCESS) {
            result = DbTransaccionStatus.Succes;
        }

        if (this.getResponseCode() == DataBase.DB_TRANSACTION_NO_DATA) {
            result = DbTransaccionStatus.NoData;
        }

        if (this.getResponseCode() == DataBase.DB_TRANSACTION_CUSTOM_ERROR) {
            result = DbTransaccionStatus.CustomError;
        }

        if (this.getResponseCode() == DataBase.DB_TRANSACTION__SQL_ERROR) {
            result = DbTransaccionStatus.SqlError;
        }

        if (result == null) {
            throw new MasterException(SystemMessage.AN_ERROR_HAS_OCCURRED, SystemMessage.INVALID_STATUS_CODE_TRANSACTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }
}
