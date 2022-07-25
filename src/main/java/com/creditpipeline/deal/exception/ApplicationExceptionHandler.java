package com.creditpipeline.deal.exception;

import com.creditpipeline.deal.entity.Application;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(value = {ApplicationException.class})
    public ResponseEntity<Object> handleScoringServiceException(ApplicationException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        Application application = new Application();
        return new ResponseEntity<>(application,badRequest);
    }

}
