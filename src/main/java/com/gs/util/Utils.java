package com.gs.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class Utils {
	
	public static String valorBuscado;

	public static boolean isEmptyOrWhiteSpaceOrNUll(String arg){

		if(arg == null){
			return true;
		}

		if(arg.trim().length()==0){
			return  true;
		}

		return false;
	}

	public static String getTimeStamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return dateFormat.format(new Date());
	}

	public static String getTimeStampLogData() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date());
	}

	public static Date getRequestDateFormat(String dateRequest) throws ParseException {
		DateFormat parseFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
		return parseFormat.parse(dateRequest);
	}

	public static void getvalueFromStringXML(String imputStringXML, String findStringToGet) {
		valorBuscado = new String();
		JSONObject jsonObject = XML.toJSONObject(imputStringXML);
		jsonObject.keys().forEachRemaining(key -> {
			Object value = jsonObject.get(key);
			// logger.error("Key{0}: " + key + "\t" + "Value{1}: " + value);
			if (value instanceof JSONObject) {
				handleJSONObject((JSONObject) value, findStringToGet);
			} else if (value instanceof JSONArray) {
				handleJSONArray((JSONArray) value, findStringToGet);
			} else {
				// logger.error("Value{0}:" + value.toString());
			}
		});
	}

	public static void getvalueFromStringJSON(String imputStringJSON, String findStringToGet) {
		valorBuscado = new String();
		JSONObject jsonObject = new JSONObject(imputStringJSON);
		jsonObject.keys().forEachRemaining(key -> {
			Object value = jsonObject.get(key);
			// logger.error("Key{0}: " + key + "\t" + "Value{1}: " + value);
			if (value instanceof JSONObject) {
				handleJSONObject((JSONObject) value, findStringToGet);
			} else if (value instanceof JSONArray) {
				handleJSONArray((JSONArray) value, findStringToGet);
			} else {
				// logger.error("Value{0}:" + value.toString());
			}
		});
	}

	static void handleJSONObject(JSONObject jsonObject, String findStringToGet) {
		jsonObject.keys().forEachRemaining(key -> {
			Object value = jsonObject.get(key);
			// logger.error("Key: {0}", key, "Key: {0}", value);
			handleValue(value, findStringToGet);
			if (findStringToGet != null && key.equals(findStringToGet)) {
				// valorBuscado= value.toString();
				valorBuscado = value.toString();
				// System.out.println("FOUND.... " + "key:" + key + " value:" +
				// value);
			}
		});
	}

	static void handleValue(Object value, String findStringToGet) {
		if (value instanceof JSONObject) {
			// logger.error("Value{0}:" + value.toString());
			handleJSONObject((JSONObject) value, findStringToGet);
		} else if (value instanceof JSONArray) {
			handleJSONArray((JSONArray) value, findStringToGet);
		} else {
			// logger.error("Value{0}:" + value.toString());
		}
	}

	static void handleJSONArray(JSONArray jsonArray, String findStringToGet) {
		jsonArray.iterator().forEachRemaining(element -> {
			handleValue(element, findStringToGet);
		});
	}
		
	public static boolean isValidRegexString(String stringValue, String regex) {
		Pattern p = Pattern.compile(regex);
		if (stringValue == null) {
			return false;
		}
		Matcher m = p.matcher(stringValue);
		return m.matches();
	}

}