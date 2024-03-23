package org.grupo3.proyectofinalspring.domain.aggregates.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignInRequest {
    @NotBlank(message = "Este campo no puede estar vacio")
    @Size(min = 2, message = "El nombre de usuario tiene que tener como mínimo 2 caracteres.")
    private String username;
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$",
            message = "La contraseña debe contener al menos una mayúscula, un carácter especial y tener un mínimo de 8 dígitos."
    )
    @NotBlank(message = "Este campo no puede estar vacio")
    private String password;
}
