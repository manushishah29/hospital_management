package com.softvan.hospitalManagement.enums;


public enum SwaggerEnum {

    API_TITLE("Hospital Management "),
    API_DESCRIPTION("Hospital Management "),
    AUTHORIZATION("AUTHORIZATION"),
    CONTROLLER_BASE_PACKAGE("com.softvan.hospitalManagement.controller");


    private final String value;

    SwaggerEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
