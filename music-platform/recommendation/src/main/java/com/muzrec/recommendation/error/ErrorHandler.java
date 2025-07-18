package com.muzrec.recommendation.error;

import com.muzrec.recommendation.error.model.ErrorResponse;
import com.muzrec.recommendation.exception.AiServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorResponse handleServiceUnavailable(final AiServiceException e) {
        log.error("Error 503: {}", e.getMessage());
        return new ErrorResponse(e.getMessage());
    }
}
