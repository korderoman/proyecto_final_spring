package org.grupo3.proyectofinalspring.infraestructure.excepcions;

import org.grupo3.proyectofinalspring.domain.aggregates.response.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e){
        return new ResponseEntity<>(new ExceptionResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ProcessException.class)
    public final ResponseEntity<ExceptionResponse> handleAppException(ProcessException e){
        ExceptionResponse exceptionResponse;
        if(e.getErrors()!=null && !e.getErrors().isEmpty()){
            exceptionResponse = new ExceptionResponse(e.getMessage(), e.getErrors());
        }else exceptionResponse = new ExceptionResponse(e.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT );
    }

    @Override //manipulamos el validate de jakarta
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors= new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError)error).getField();
            String mensaje = error.getDefaultMessage();
            errors.put(nombreCampo, mensaje);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
