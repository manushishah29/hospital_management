package com.softvan.hospitalManagement.enums;

/**
 * <h1>ApiResponsesEnum</h1>
 * <p>
 * This enum class represent the ApiResponsesEnum.
 * </p>
 *
 * @author Finpal
 * @version 1.0
 * @since 20-09-2022
 */

public enum ApiResponsesEnum {
    SIGN_IN_SUCCESSFULLY("User logged in successfully", "SIGN_IN_SUCCESSFULLY"),
    ROLE_CREATED_SUCCESSFULLY("Role created Successfully","ROLE_CREATED_SUCCESSFULLY"),
    ROLE_FETCHED_SUCCESSFULLY("Role fetched successfully","ROLE_FETCHED_SUCCESSFULLY"),
    PRIVILEGE_CREATED_SUCCESSFULLY("Privilege created successfully","PRIVILEGE_CREATED_SUCCESSFULLY"),
    PRIVILEGES_FETCHED_SUCCESSFULLY("Privileges fetched successfully","PRIVILEGES_FETCHED_SUCCESSFULLY"),
    USER_CREATED_SUCCESSFULLY("User created successfully", "USER_CREATED_SUCCESSFULLY"),
    USER_FETCHED_SUCCESSFULLY("User fetched successfully","USER_FETCHED_SUCCESSFULLY");


    private String value;
    private String message;

    ApiResponsesEnum(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }


}
