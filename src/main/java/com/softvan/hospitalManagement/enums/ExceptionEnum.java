package com.softvan.hospitalManagement.enums;

/**
 * <h1>ExceptionEnum</h1>
 * <p>
 * This enum class represent the ExceptionEnum.
 * </p>
 *
 * @author Finpal
 * @version 1.0
 * @since 20-09-2022
 */
public enum ExceptionEnum {
    UNAUTHORIZED("Unauthorized", "UNAUTHORIZED"),
    SOMETHING_WENT_WRONG("Something went wrong", "SOMETHING_WENT_WRONG"),
    REST_CLIENT_EXCEPTION("REST_CLIENT_EXCEPTION", "REST_CLIENT_EXCEPTION"),

    USER_ALREADY_EXISTS("User already exists","USER_ALREADY_EXISTS"),
    USERNAME_NOT_FOUND("Username not found","USERNAME_NOT_FOUND"),
    ROLE_NOT_FOUND("Role not found","ROLE_NOT_FOUND"),
    INVALID_CREDENTIALS("Incorrect username or password ","INVALID_CREDENTIALS"),
    PRIVILEGE_NOT_FOUND("Privilege not found","PRIVILEGE_NOT_FOUND"),
    ROLE_ALREADY_EXISTS("Role already exists","ROLE_ALREADY_EXISTS"),

    PRIVILEGE_ALREADY_EXISTS("Privilege already exists","PRIVILEGE_ALREADY_EXISTS"),
    INCORRECT_PASSWORD("Incorrect password","INCORRECT_PASSWORD" );

    private String value;
    private String message;

    ExceptionEnum(String value, String message) {
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
