package com.gs.exception;


import java.util.ArrayList;

import com.gs.exception.api.ResponseException;
import com.gs.util.Utils;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;

public class MasterException extends Throwable {

	private String folio;
	private String mensaje;
	private ArrayList<String> detalles;
	private HttpStatus httpStatus;

	public MasterException(String mensaje, Throwable throwable, HttpStatus httpStatus){

		super(mensaje, throwable);
		this.folio= Utils.getTimeStamp();
		this.mensaje = mensaje;
		this.detalles =new ArrayList<>();
		this.detalles.add(throwable.getMessage());
		this.AddStackTraceToDetale(throwable.getStackTrace());
		this.httpStatus =httpStatus;
	}
	public MasterException(String mensaje, ArrayList<String> detalles, HttpStatus httpStatus){

		super(mensaje);
		this.folio= Utils.getTimeStamp();
		this.mensaje = mensaje;
		this.detalles = detalles;
		this.httpStatus =httpStatus;
	}

	public MasterException(String mensaje, StackTraceElement[] stackTraceElements, HttpStatus httpStatus){

		super(mensaje);
		this.folio= Utils.getTimeStamp();
		this.mensaje = mensaje;
		this.httpStatus =httpStatus;
		this.AddStackTraceToDetale(stackTraceElements);
	}

	public MasterException(String mensaje, String detalle, HttpStatus httpStatus){

		super(mensaje);
		this.folio= Utils.getTimeStamp();
		this.mensaje = mensaje;
		this.httpStatus =httpStatus;
		this.detalles = new ArrayList<String>();
		this.detalles.add(detalle);
	}

	public String getFolio() {

		return folio;
	}
	public String getMensaje() {

		return mensaje;
	}
	public ArrayList<String> getDetalles() {

		return detalles;
	}

	public HttpStatus getHttpStatus()
	{
		return httpStatus;
	}

	public static RuntimeException createInternalException(Throwable throwable){

		MasterException masterException = new MasterException(throwable.getMessage(), throwable, HttpStatus.INTERNAL_SERVER_ERROR);

		return masterException.getInnerException();
	}

	public RuntimeException getInnerException(){

		return new ResponseException(this.getHttpStatus(), this.getMensaje(), this.getFolio(), null, this.detalles);
	}

	private void AddStackTraceToDetale(StackTraceElement[] stackTraceElements){

		if(this.detalles == null){
			this.detalles = new ArrayList<>();
		}

		if(stackTraceElements !=null && stackTraceElements.length>0){
			this.detalles.add(new JSONArray(stackTraceElements).toString());
		}
	}
}
