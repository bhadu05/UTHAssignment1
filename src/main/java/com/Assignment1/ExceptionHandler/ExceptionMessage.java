package com.Assignment1.ExceptionHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionMessage {
    private String timeStamp;
    private int status;
   private String error;
   private String message;


    public ExceptionMessage( String error, String message, int status) {
        Date date = new Date();
        String strDateFormat = "dd-MM-yyyy HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        this.timeStamp= dateFormat.format(date);
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public ExceptionMessage()
    {

    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
