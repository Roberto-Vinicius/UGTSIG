package br.com.formulario.DTO;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class EmailDto {

    private String referencia_ip;
    @Email
    private String enviador;
    @Email
    private String receptor;
    private String subjt_titutlo;
    private String texto;
}
