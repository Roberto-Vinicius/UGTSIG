package br.com.formulario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.formulario.model.EmailModel;

@Repository
public interface EmailRespository extends JpaRepository<EmailModel, Long> {

}
