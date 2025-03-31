package br.com.formulario.erros;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.formulario.erros.lancamento.MessegerErro;

@ControllerAdvice
public class FormeErroMesseger extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FormExecption.class)
    private ResponseEntity<MessegerErro> NotFound(FormExecption execption) {
        MessegerErro menMesseger = new MessegerErro();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(menMesseger);
    }
}
