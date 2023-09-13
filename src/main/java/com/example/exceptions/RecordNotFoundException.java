package com.example.exceptions;

public class RecordNotFoundException extends RuntimeException {


    public RecordNotFoundException() {
    }

    public RecordNotFoundException(String msg) {
        super(msg);
    }


}
