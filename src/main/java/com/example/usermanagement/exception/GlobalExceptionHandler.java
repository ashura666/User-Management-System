package com.example.usermanagement.exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public Map<String, String> handleValidation(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getFieldErrors()
	          .forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));
	        return errors;
	    }

	    @ExceptionHandler(BadCredentialsException.class)
	    @ResponseStatus(HttpStatus.UNAUTHORIZED)
	    public Map<String, String> handleBadCreds(BadCredentialsException ex) {
	        return Map.of("error", ex.getMessage());
	    }

	    @ExceptionHandler(RuntimeException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public Map<String, String> handleRuntime(RuntimeException ex) {
	        return Map.of("error", ex.getMessage());
	    }
}
