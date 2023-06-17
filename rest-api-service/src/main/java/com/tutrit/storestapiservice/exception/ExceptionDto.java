package com.tutrit.storestapiservice.exception;

public record ExceptionDto (
    String timestamp,
    String status,
    String error,
    String path,
    String entityName
){}
