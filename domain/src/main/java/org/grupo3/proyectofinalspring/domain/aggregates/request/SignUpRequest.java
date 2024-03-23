package org.grupo3.proyectofinalspring.domain.aggregates.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SignUpRequest {
    @NotBlank(message = "Este campo no puede estar vacio")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 caracteres")
    private String dni;
    @NotBlank(message = "Este campo no puede estar vacio")
    private String nombres;
    @NotBlank(message = "Este campo no puede estar vacio")
    private String apePaterno;
    @NotBlank(message = "Este campo no puede estar vacio")
    private String apeMaterno;
    @NotBlank(message = "Este campo no puede estar vacio")
    @Size(min = 3,message = "La dirección tiene que tener más de 3 caracteres.")
    private String direccion;
    @NotBlank(message = "Este campo no puede estar vacio")
    @Size(min = 3,message = "La ciudad tiene que tener más de 3 caracteres.")
    private String ciudad;
    @NotBlank(message = "Este campo no puede estar vacio")
    @Size(min = 3,message = "La provincia tiene que tener más de 3 caracteres.")
    private String provincia;
    private String calle;
    private String numero;
    private String piso;
    private String codigPostal;
    @NotBlank(message = "Este campo no puede estar vacio")
    @Pattern(regexp = "\\d{8,15}", message = "El número de teléfono debe tener entre 8 y 15 dígitos.")
    private String telefonoCont;
    @Email(message = "El formato del correo electrónico no es válido.")
    @NotBlank(message = "Este campo no puede estar vacio")
    private String email;
    @NotBlank(message = "Este campo no puede estar vacio")
    @Size(min = 2, message = "El nombre de usuario tiene que tener como mínimo 2 caracteres.")
    private String nomUsuario;
    @NotBlank(message = "Este campo no puede estar vacio")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "La contraseña debe contener al menos una mayúscula, un carácter especial y tener un mínimo de 8 dígitos."
    )
    private String password;
}
