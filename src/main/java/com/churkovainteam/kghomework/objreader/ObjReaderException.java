package com.churkovainteam.kghomework.objreader;

public class ObjReaderException extends RuntimeException {
    public ObjReaderException(String errorMessage, int fileLineId) {
        super("Error parsing OBJ file on line: " + fileLineId + ". " + errorMessage);
    }
}
