package br.com.formulario.DTO;

import br.com.formulario.enumtipo.Escolaridade;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurriculoFormDto {

    @NotBlank(message = "Este campo não pode ser vazio!!")
    @Size(min = 5, max = 35, message = "O nome deve ter entre 5 e 35 caracteres!!")
    @NotNull(message = "Campo nome não pode ser nulo!!")
    private String nome;

    @NotBlank(message = "O campo e-mail não pode ser vazio")
    @NotNull
    @Size(min = 12, message = "Por favor, use um endereço de e-mail válido e existente, como por exemplo exemplo@gmail.com")
    @Email(message = "Por favor, use um endereço de e-mail válido e existente, como por exemplo exemplo@gmail.com")
    private String email;

    @NotBlank(message = "Este campo não pode ser vazio!!")
    @NotNull
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{4,5}-\\d{4}$", message = "Por favor, forneça um número de telefone válido no formato (XX) XXXXX-XXXX")
    private String telefone;

    @Size(min = 5, message = "O nome deve ter entre 5 e 35 caracteres!!")
    @NotBlank(message = "Digite seu cargo desejado!!")
    @NotNull(message = "Campo nome não pode ser nulo!!")
    private String cargoDesejado;
    @Enumerated(EnumType.STRING)

    private Escolaridade escolaridade;
    private String observacoes;
    @Lob
    private byte[] arquivo;
}
