package com.onlineExam.exception;

public class QexzWebException extends Exception {

    public final ERRORCODE ERRORCODE;

    public QexzWebException(ERRORCODE ERRORCODE) {
        this.ERRORCODE = ERRORCODE;
    }
}
