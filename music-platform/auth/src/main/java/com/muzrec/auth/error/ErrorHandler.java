package com.muzrec.auth.error;

import com.muzrec.auth.error.model.ErrorResponse;
import com.muzrec.auth.exception.BadRequestException;
import com.muzrec.auth.exception.JwtTokenException;
import com.muzrec.auth.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({JwtTokenException.class, UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleJwtTokenException(final RuntimeException e) {
        log.error("Error 401: {}", e.getMessage());
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(final BadRequestException e) {
        log.error("Error 400: {}", e.getMessage());
        return new ErrorResponse(e.getMessage());
    }
}
