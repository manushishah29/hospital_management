package com.softvan.hospitalManagement.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softvan.hospitalManagement.enums.ExceptionEnum;
import com.softvan.hospitalManagement.responseDto.ApiResponse;
import com.softvan.hospitalManagement.responseDto.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <h1>ControllerAdvice</h1>
 * <p>
 * This class will be used for globally handle exceptions
 * </p>
 *
 * @author finpal
 * @version 1.0
 * @since 23-09-2022
 */

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    /**
     * <p>
     * This method handles Exception and gives custom response
     * </p>
     *
     * @param req httpServletRequest request
     * @param e   Exception
     * @return ResponseEntity &lt;ApiResponse&gt;
     * @see Exception
     * @see ApiResponse
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(HttpServletRequest req, Exception e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorDTO errorDTO = new ErrorDTO(httpStatus, new Date().getTime(),
                ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), req.getServletPath());
        ApiResponse apiResponse = new ApiResponse(httpStatus, e.getMessage(), errorDTO);
        log.error("handleException ::", e);
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }

    /**
     * <p>
     * This Method handles CustomException and returns ApiResponse
     * </p>
     *
     * @param req httpServletRequest request
     * @param e   CustomException
     * @return ResponseEntity &lt;ApiResponse&gt;
     * @see CustomException
     * @see ApiResponse
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> handleCustomException(HttpServletRequest req, CustomException e) {
        HttpStatus httpStatus = e.getHttpStatus();
        ErrorDTO errorDTO = new ErrorDTO(httpStatus, new Date().getTime(), e.getMessage(), req.getServletPath());
        errorDTO.setError(e.getMessage());
        errorDTO.setMessage(httpStatus.name());
        ApiResponse apiResponse = new ApiResponse(httpStatus, e.getMessage(), errorDTO);
        log.error("handleCustomException :: ", e);
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }

//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ApiResponse> handleAccessDeniedException(HttpServletRequest req, AccessDeniedException e) {
//        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
//        ErrorDTO errorDTO = new ErrorDTO(httpStatus, new Date().getTime(), e.getMessage(), req.getServletPath());
//        errorDTO.setError(e.getMessage());
//        errorDTO.setMessage(httpStatus.name());
//        ApiResponse apiResponse = new ApiResponse(httpStatus, e.getMessage(), errorDTO);
//        log.error("handleAccessDeniedException :: ", e);
//        return ResponseEntity.status(httpStatus).body(apiResponse);
//    }


    /**
     * <p>
     * This handles IllegalArgumentException and gives custom response
     * </p>
     *
     * @param req HttpServletRequest   request
     * @param e   IllegalArgumentException
     * @return ResponseEntity &lt;ApiResponse&gt;
     * @see IllegalArgumentException
     * @see ApiResponse
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(HttpServletRequest req,
                                                                      IllegalArgumentException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorDTO errorDTO = new ErrorDTO(httpStatus, new Date().getTime(),
                ExceptionEnum.SOMETHING_WENT_WRONG.getValue(), req.getServletPath());
        ApiResponse apiResponse = new ApiResponse(httpStatus, e.getMessage(), errorDTO);
        log.error("handleIllegalArgumentException :: ", e);
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }

    /**
     * <p>
     * This handles MethodArgumentNotValidException and gives custom response which
     * contains list of errors
     * </p>
     *
     * @param req httpServletRequest request
     * @param e   MethodArgumentNotValidException
     * @return ResponseEntity &lt;ApiResponse&gt;
     * @see MethodArgumentNotValidException
     * @see ApiResponse
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(HttpServletRequest req,
                                                                             MethodArgumentNotValidException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        List<String> errorMessages = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(error -> errorMessages.add(error.getDefaultMessage()));
        ErrorDTO errorDTO = new ErrorDTO(httpStatus, new Date().getTime(), httpStatus.name(), req.getServletPath(),
                errorMessages);
        ApiResponse apiResponse = new ApiResponse(httpStatus, httpStatus.name(), errorDTO);
        log.error("handleMethodArgumentNotValidException :: ", e);
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }


    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ApiResponse> handleMethodRestClientException(HttpServletRequest req,
                                                                       RestClientException e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorDTO errorDTO = new ErrorDTO(httpStatus, new Date().getTime(),
                ExceptionEnum.REST_CLIENT_EXCEPTION.getValue(), req.getServletPath());
        ApiResponse apiResponse = new ApiResponse(httpStatus, e.getMessage(), errorDTO);
        log.error("handleMethodRestClientException :: ", e);
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }

    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<ApiResponse> handleMethodRestClientResponseException(HttpServletRequest req,
                                                                               RestClientResponseException e) throws JsonProcessingException {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        log.info("handleMethodRestClientResponseException getResponseBodyAsString :: {}", e.getResponseBodyAsString());
        log.info("handleMethodRestClientResponseException getRawStatusCode :: {}", e.getRawStatusCode());
        String responseString = e.getResponseBodyAsString();
        Object a = null;
        if (!responseString.isEmpty()) {
            a = new ObjectMapper().readValue(e.getResponseBodyAsString(), new TypeReference<Object>() {
            });
        }
        ApiResponse apiResponse1 = new ApiResponse(e.getMessage(), a, e.getRawStatusCode());
        ApiResponse apiResponse = new ApiResponse(httpStatus, httpStatus.getReasonPhrase(), apiResponse1);
        log.info("handleMethodRestClientResponseException ::");
        return ResponseEntity.status(httpStatus).body(apiResponse);
    }

}
