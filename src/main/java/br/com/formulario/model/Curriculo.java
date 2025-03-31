package br.com.formulario.model;

import java.time.LocalDateTime;

import br.com.formulario.enumtipo.Escolaridade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Curriculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    //@Column(unique = true)
    private String email;
    private String telefone;
    private String cargoDesejado;
    @Enumerated(EnumType.STRING)
    private Escolaridade escolaridade;
    private String observacoes;

    @Lob
    @Column(nullable = false, length = 1048576) // Tamanho máximo de 1MB (em bytes)
    private byte[] arquivo;

    private String ip;
    private LocalDateTime dataHoraEnvio;

    private String fileName;

}
