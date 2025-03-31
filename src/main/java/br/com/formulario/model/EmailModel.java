package br.com.formulario.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.formulario.enumtipo.Status;
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

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EmailModel  implements Serializable{
     private static  Long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long curriculo_id;
    private String enviador;
    private String receptor;
    private String subjt_titutlo;
    @Column(columnDefinition = "TEXT")
    private String texto;
    private LocalDateTime data;
   @Enumerated(EnumType.STRING)
    private Status status;
   
    private String candidato;
    //########## caso queira salvar o curriculo em um banco de dados########################## !!
    //@Lob
   // @Column(nullable = true,length = 10048576) // Tamanho máximo de 1MB (em bytes)
   // private byte[] arquivo;
}
