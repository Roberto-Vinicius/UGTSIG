package br.com.formulario.erros;

public class FormExecption extends RuntimeException {

    public FormExecption() {
        super("Erro no formulario");
    }

    public FormExecption(String msg) {
        super(msg);
    }
}
