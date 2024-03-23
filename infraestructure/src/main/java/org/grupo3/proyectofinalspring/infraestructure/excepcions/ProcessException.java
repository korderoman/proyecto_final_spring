package org.grupo3.proyectofinalspring.infraestructure.excepcions;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProcessException extends RuntimeException{
    private List<String> errors;

    public  ProcessException (String message){
        super(message);
    }
    public ProcessException(String message, List<String> errors){
        super(message);
        this.errors= errors;
    }
}
